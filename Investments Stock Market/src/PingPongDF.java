import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 25-10-2016.
 */
// java -cp lib/jade.jar jade.Boot -gui
public class PingPongDF extends Agent {

    @Override
    protected void setup(){
        super.setup();

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd=new ServiceDescription();
        sd.setType("ping pong");
        sd.setName("JADE-replies-pong");
        dfd.addServices(sd);
        try{
            DFService.register(this,dfd);

        }catch (FIPAException e){
            e.printStackTrace();
        }
        System.out.println(this.getLocalName()+ " a começar!");
        //this.addBehaviour(new ReceiveBehaviour());
        this.addBehaviour(new AnswerBehaviour());
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println(this.getLocalName()+ " a terminar!");
    }

    private class AnswerBehaviour extends CyclicBehaviour{

        @Override
        public void action() {
            ACLMessage msg = receive();
            if(msg!=null){
                System.out.println("Recebi uma mensagem de: "+msg.getSender()+". Conteúdo: "+ msg.getContent());
                ACLMessage resp =msg.createReply();
                if(msg.getContent().equals("ping")){
                    resp.setContent("pong");
                    resp.setPerformative(ACLMessage.INFORM);
                }
                else {
                    resp.setContent("Não percebi");
                    resp.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                }
                send(resp);
            }
            block();
        }
    };

    private class ReceiveBehaviour extends CyclicBehaviour{

        @Override
        public void action() {

            ACLMessage msg = receive();
            if(msg != null){
                System.out.println("Recebi uma mensagem de "+msg.getSender()+". Conteúdo: "+ msg.getContent());
            }
            block();
        }
    };
}


