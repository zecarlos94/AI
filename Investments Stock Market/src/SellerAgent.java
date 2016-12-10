import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.StringACLCodec;

import java.lang.reflect.Array;
import java.util.*;

public class SellerAgent extends Agent {


    public HashMap<String,Empresa> investCompanies = new HashMap<String,Empresa>();
    public HashMap<String,ArrayList<Double>> investargs = new HashMap<>();
    public HashMap<String,ArrayList<String>> historic = new HashMap<>();

    @Override
    protected void setup() {
        super.setup();

        System.out.println(this.getLocalName()+ " starting!");

        //registo do serviço
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("SellerAgent");
        sd.setName("SellerAgent");
        dfd.addServices(sd);
        try{
            DFService.register(this,dfd);
        }catch (FIPAException e){
            e.printStackTrace();
        }
        this.addBehaviour(new ReceiveBehaviourInformative());
        this.addBehaviour(new InvestibleBehaviour(this,2000));
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println(this.getLocalName()+ " finishing!");

    }
    private class InvestibleBehaviour extends TickerBehaviour {

        public InvestibleBehaviour(Agent a, long timeout){
            super(a,timeout);
        }
        @Override
        public void onTick(){
            AID receiver = new AID();
            long time=System.currentTimeMillis();
            ACLMessage msg=new ACLMessage(ACLMessage.PROPOSE);
            msg.setContent("Está disponivel?");
            msg.setConversationId(""+time);
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
                sd.setType("MarketAgent");
                template.addServices(sd);

                try {
                    DFAgentDescription[] result = DFService.search(myAgent, template);

                    receiver.setLocalName(result[0].getName().getLocalName().toString());
                }catch (Exception e ){e.printStackTrace();}

                msg.addReceiver(receiver);
                send(msg);

        }
    };
    private class ReceiveBehaviourInformative extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = receive();
            if(msg != null){
                ACLMessage response=msg.createReply();
                if(msg.getPerformative()==ACLMessage.PROPOSE){
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    response.setContent("Yes");
                    response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                }
                else if(msg.getPerformative()==ACLMessage.INFORM && msg.getSender().getLocalName().equals("AnalyzerAgent")) {
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    String[] a = msg.getContent().split("_");
                    Empresa temp = new Empresa(a[0]);
                    investCompanies.remove(temp.getCompanyExchangeName());
                    investCompanies.put(temp.getCompanyExchangeName(),temp);
                    String[] a2 = a[1].split("#");
                    ArrayList<Double> args = new ArrayList<>();
                    for(String s : a2){
                        args.add(Double.parseDouble(s));
                    }
                    investargs.remove(temp.getCompanyExchangeName());
                    investargs.put(temp.getCompanyExchangeName(),args);
                }else if(msg.getPerformative()==ACLMessage.INFORM && msg.getSender().getLocalName().equals("MarketAgent")) {
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    String[] a = msg.getContent().split("_");
                    if(historic.get(a[0])==null){
                        ArrayList<String> a1 = new ArrayList<>();
                        a1.add(a[1]);
                        historic.put(a[0],a1);
                    }else{
                        ArrayList<String> a1 = historic.get(a[0]);
                        a1.add(a[1]);
                    }
                    AID receiver = new AID();
                    long time=System.currentTimeMillis();
                    ACLMessage msg1=new ACLMessage(ACLMessage.PROPOSE);
                    msg1.setContent("Está disponivel?");
                    msg1.setConversationId(""+time);
                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType("AnalyzerAgent");
                    template.addServices(sd);

                    try {
                        DFAgentDescription[] result = DFService.search(myAgent, template);

                        receiver.setLocalName(result[0].getName().getLocalName().toString());
                    }catch (Exception e ){e.printStackTrace();}

                    msg1.addReceiver(receiver);
                    send(msg1);



                }else if(msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL && msg.getSender().getLocalName().equals("AnalyzerAgent")) {
                    ACLMessage msg1 = msg.createReply();
                    StringBuilder st;
                    for (Map.Entry<String,ArrayList<String>> e : historic.entrySet()) {
                        st = new StringBuilder();
                        st.append(e.getValue().toString() + "_");
                        for (String d : e.getValue())
                            st.append("#" + d);

                        msg1.setContent(st.toString());
                        send(msg1);
                        historic.remove(e.getKey());
                    }
                }else if(msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL && msg.getSender().getLocalName().equals("MarketAgent")) {
                    ACLMessage msg1 = msg.createReply();
                    StringBuilder st;
                    for (Map.Entry e : investCompanies.entrySet()) {
                        st = new StringBuilder();
                        st.append(e.getValue().toString() + "_");
                        for (Double d : investargs.get(e.getKey()))
                            st.append("#" + d);

                        msg1.setContent(st.toString());
                        send(msg1);

                    }
                }
                else{
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    response.setContent("No");
                    response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                }

                send(response);

            }
            block();
        }
    };

}
