import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 10/11/16.
 */
public class S1 extends Agent {

    protected String message;

    @Override
    protected void setup() {
        super.setup();
        this.addBehaviour(new ReceiveBehaviour());
        this.addBehaviour(new SendMessage(this,2000,"Let's talk?"));
    }

    public class SendMessage extends TickerBehaviour {
        public SendMessage(Agent a, long timeout, String aux){
            super(a,timeout);
            message=aux;
        }

        @Override
        protected void onTick() {
            AID receiver=new AID();
            receiver.setLocalName("proposal");
            long time=System.currentTimeMillis();
            ACLMessage msg1=new ACLMessage(ACLMessage.PROPOSE);
            msg1.setContent(message);
            msg1.setConversationId(""+time);
            msg1.addReceiver(receiver);
            myAgent.send(msg1);
            block(1000);
        }
    };

    public class ReceiveBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg=receive();
            if(msg!=null){
                if(msg.getPerformative()==0){
                    System.out.println("Received message from "+msg.getSender()+" .Request Accepted.");
                }else{
                    System.out.println("Received message from "+msg.getSender()+" .Request Denied.");
                }
            }
            block();
        }
    };

}
