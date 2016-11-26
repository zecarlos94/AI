import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by zecarlos on 10/11/16.
 */
public class SectorAgent extends Agent {

    public HashMap<String,HashMap<String,Empresa>> areaFilter;



    @Override
    protected void setup(){
        super.setup();

        areaFilter = new HashMap<String,HashMap<String,Empresa>>();

        ArrayList<Empresa> lista = new ArrayList<Empresa>();



        System.out.println(this.getLocalName()+ " starting!");

       //registo do serviço
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("SectorAgent");
        sd.setName("SectorAgent");
        dfd.addServices(sd);
        try{
            DFService.register(this,dfd);
        }catch (FIPAException e){
            e.printStackTrace();
        }

        this.addBehaviour(new ReceiveBehaviourInformative());
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



    private class ReceiveBehaviourInformative extends CyclicBehaviour{

        @Override
        public void action() {
            ACLMessage msg = receive();
            if(msg != null){
                ACLMessage response=msg.createReply();
                if(msg.getPerformative()==ACLMessage.PROPOSE){
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    response.setContent("Yes");
                    response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                }else if(msg.getPerformative()==ACLMessage.INFORM){
                    if(msg.getSender().getName()=="SearchAgent"){
                        String[] a = msg.getContent().split("_");
                        System.out.println("Message received from"+msg.getSender()+" the company is " + new Empresa(a[1]));
                        HashMap<String,Empresa> lista = areaFilter.get(a[0]);
                        if(lista==null){
                            Empresa temp = new Empresa(a[1].toString());
                            lista = new HashMap<String,Empresa>();
                            lista.put(temp.getCompanyExchangeName(),temp);
                            areaFilter.put(a[0],lista);
                        }
                        else{

                            Empresa temp = new Empresa(a[1].toString());
                            lista.remove(temp.getCompanyExchangeName());
                            lista.put(temp.getCompanyExchangeName(),temp);
                            areaFilter.remove(a[0]);
                            areaFilter.put(a[0],lista);
                        }

                    }
                    if(msg.getSender().equals("DefinerAgent")) {
                        System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                        HashMap<String,Empresa> lista = areaFilter.get(msg.getContent());

                        ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
                        AID receiver = new AID();
                        receiver.setLocalName("analizador");
                        long time = System.currentTimeMillis();
                        msg1.addReceiver(receiver);
                        msg1.setConversationId("" + time);
                        if (lista != null)
                            if (lista.isEmpty())
                                for (Map.Entry<String,Empresa> e : lista.entrySet()) {
                                    msg1.setContent(e.getValue().toString());
                                    send(msg1);
                                }
                    }
                }
                else{
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    response.setContent("No");
                    response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                }
                System.out.println("" + response.getAllIntendedReceiver());
                send(response);
            }
            block();
        }
    };
}


