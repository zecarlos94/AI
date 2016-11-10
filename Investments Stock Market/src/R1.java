import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 10/11/16.
 */
public class R1 extends Agent {

    @Override
    protected void setup(){
        super.setup();
        System.out.println(this.getLocalName()+ " starting...");
        this.addBehaviour(new ReceiveBehaviourProposal());
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName()+ " finished");
    }

    private class ReceiveBehaviourProposal extends CyclicBehaviour{

        @Override
        public void action() {
            ACLMessage msg = receive();
            if(msg != null){
                ACLMessage response=msg.createReply();
                if(msg.getPerformative()==ACLMessage.PROPOSE){
                    if(msg.getContent().equals("")){
                        System.out.println("Received message from "+msg.getSender()+". Content: "+ msg.getContent());
                        response.setContent("Denied");
                        response.setPerformative(ACLMessage.REJECT_PROPOSAL);
                    }
                    else{
                        System.out.println("Received message from "+msg.getSender()+". Content: "+ msg.getContent());
                        response.setContent("Accepted");
                        response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                    }
                }
                else{
                    System.out.println("Received message from "+msg.getSender()+". Content: "+ msg.getContent());
                    response.setContent("Not Understood");
                    response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                }

                send(response);
            }
            block();
        }
    };
}


