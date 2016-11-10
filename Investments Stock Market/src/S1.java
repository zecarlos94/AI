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
        System.out.println(this.getLocalName()+ " starting...");
        this.addBehaviour(new ReceiveBehaviour());
        this.addBehaviour(new SendMessage(this,2000,"Let's talk?"));
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName()+ " finished");
    }

    public class SendMessage extends TickerBehaviour {
        public SendMessage(Agent a, long timeout, String aux){
            super(a,timeout);
            message=aux;
        }

        @Override
        protected void onTick() {
            int counter=0;
            AID receiver=new AID();
            receiver.setLocalName("proposal");
            long time=System.currentTimeMillis();
            if(time%2==0){
                ACLMessage msg1=new ACLMessage(ACLMessage.PROPOSE);
                msg1.setContent(message);
                msg1.setConversationId(""+time);
                msg1.addReceiver(receiver);
                myAgent.send(msg1);
            }
            else{
                if(counter==0){
                    //Para simular reject_proposal
                    ACLMessage msg2=new ACLMessage(ACLMessage.PROPOSE);
                    msg2.setContent("");
                    msg2.setConversationId(""+time);
                    msg2.addReceiver(receiver);
                    myAgent.send(msg2);
                    counter++;
                }
                else if(counter==1){
                    //Para simular not_understood
                    ACLMessage msg3=new ACLMessage(ACLMessage.INFORM);
                    msg3.setContent("Inform");
                    msg3.setConversationId(""+time);
                    msg3.addReceiver(receiver);
                    myAgent.send(msg3);
                    counter--;
                }
            }
            block(1000);
        }
    };

    public class ReceiveBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg=receive();
            if(msg!=null){
                if(msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL){
                    System.out.println("Received message from "+msg.getSender()+" .Request Accepted.");
                }else if(msg.getPerformative()==ACLMessage.REJECT_PROPOSAL){
                    System.out.println("Received message from "+msg.getSender()+" .Request Denied.");
                }else{
                    System.out.println("Received message from "+msg.getSender()+" .Request Not Understood.");
                }
            }
            block();
        }
    };

}
