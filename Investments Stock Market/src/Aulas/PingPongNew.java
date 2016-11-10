import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 31/10/16.
 */
public class PingPongNew extends Agent {

    @Override
    protected void setup(){
        super.setup();
        System.out.println(this.getLocalName()+ " a começar!");
        this.addBehaviour(new ReceiveBehaviourProposal());
        //this.addBehaviour(new AnswerBehaviour());
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName()+ " a terminar!");
    }

    private class AnswerBehaviour extends CyclicBehaviour {

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

    private class ReceiveBehaviourProposal extends CyclicBehaviour{

        @Override
        public void action() {
            ACLMessage msg = receive();
            if(msg != null){
                ACLMessage response=msg.createReply();
                if(msg.getPerformative()==ACLMessage.PROPOSE){
                    System.out.println("Received message from "+msg.getSender()+". Conteúdo: "+ msg.getContent());
                    response.setContent("Yes");
                    response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                }else{
                    System.out.println("Received message from "+msg.getSender()+". Conteúdo: "+ msg.getContent());
                    response.setContent("No");
                    response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                }

                send(response);
            }
            block();
        }
    };
}


