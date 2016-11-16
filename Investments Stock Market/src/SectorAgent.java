import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by zecarlos on 10/11/16.
 */
public class SectorAgent extends Agent {

    public TreeMap<String,List<Empresa>> areaFilter;



    @Override
    protected void setup(){
        super.setup();

        areaFilter = new TreeMap<String,List<Empresa>>();

        ArrayList<Empresa> lista = new ArrayList<Empresa >();
        lista.add(new Empresa("A",0));
        lista.add(new Empresa("B",0));
        lista.add(new Empresa("C",0));
        areaFilter.put("Finanças",lista);



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
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    List<Empresa> lista = areaFilter.get(msg.getContent());

                    ACLMessage msg1 = new ACLMessage(ACLMessage.INFORM);
                    AID receiver=new AID();
                    receiver.setLocalName("analizador");
                    long time=System.currentTimeMillis();
                    msg1.addReceiver(receiver);
                    msg1.setConversationId(""+time);

                    for (Empresa e: lista){
                        msg1.setContent(e.toString());
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


