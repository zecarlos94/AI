import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * Created by zecarlos on 10/11/16.
 */
public class SearchAgent extends Agent {

    TreeMap<String,List<Empresa>> compainies = new TreeMap<String, List<Empresa>>();

    @Override
    protected void setup() {
        super.setup();
        System.out.println(this.getLocalName()+ " starting!");

        //registo do serviço
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("SearchAgent");
        sd.setName("SearchAgent");
        dfd.addServices(sd);
        try{
            DFService.register(this,dfd);
        }catch (FIPAException e){
            e.printStackTrace();
        }
        try {

            File fXmlFile = new File("src/input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("area");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);


                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String area = eElement.getAttribute("nome");

                    System.out.println("Area : " + eElement.getAttribute("nome"));
                    ArrayList<Empresa> temp2 = new ArrayList<Empresa>();

                    NodeList nList1 = eElement.getElementsByTagName("empresa");

                    for (int temp1 = 0; temp1 < nList1.getLength(); temp1++) {

                        Node nNode1 = nList1.item(temp1);


                        if (nNode1.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement1 = (Element) nNode1;
                            System.out.println("Owners : " + eElement1.getAttribute("owners"));
                            System.out.println("Stock : " + eElement1.getAttribute("stock"));
                            System.out.println("Year : " + eElement1.getAttribute("year"));
                            System.out.println("Stock Exchange Name : " + eElement1.getAttribute("sen"));
                            System.out.println("Company Exchange Name : " + eElement1.getAttribute("cen"));

                            temp2.add(new Empresa(area,eElement1.getAttribute("owners"),Double.parseDouble(eElement1.getAttribute("stock")),Integer.parseInt(eElement1.getAttribute("year")),eElement1.getAttribute("sen"),eElement1.getAttribute("cen")));
                        }
                        System.out.println("");
                    }
                    this.compainies.put(eElement.getAttribute("nome"),temp2);
                }
                System.out.print("\n\n");

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        this.addBehaviour(new SearchBehaviour());
    }
    @Override
    protected void takeDown(){

        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println(this.getLocalName()+ " finishing!");
        super.takeDown();
    }


    private class SearchBehaviour extends CyclicBehaviour{
        @Override
        public void action(){
            // TODO colocar código de procura no stock market
            ACLMessage msg = receive();
            AID receiver = new AID();
            receiver.setLocalName("sector");
            if(msg!=null)
                if (msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL){
                    for (Map.Entry<String, List<Empresa>> empli : compainies.entrySet()) {
                        List<Empresa> empl = empli.getValue();
                        for (Empresa emp : empl) {
                            //get data from yahoo
                            if (emp.getCompanyExchangeNname() != null) {
                                System.out.println("YOLO");
                                Empresa search = Search.getCompanyData(emp.getCompanyExchangeNname());
                                emp.update(search);
                                long id = System.currentTimeMillis();
                                msg = new ACLMessage(ACLMessage.INFORM);
                                msg.setContent(emp.toString());
                                msg.setConversationId("" + id);
                                msg.addReceiver(receiver);
                                send(msg);
                            }
                        }
                    }
                } else {
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    msg.setContent("No");
                    msg.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                    send(msg);
                }
            else {
                msg = new ACLMessage(ACLMessage.PROPOSE);
                msg.setContent("Will you?");
                msg.addReceiver(receiver);
                send(msg);
            }
        }
    };


}
