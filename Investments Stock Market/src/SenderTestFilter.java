import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 31/10/16.
 */
public class SenderTestFilter extends Agent {
    @Override
    protected void setup() {
        super.setup();
        this.addBehaviour(new SendMessage(this,2000));
    }

    public class SendMessage extends TickerBehaviour{
        public SendMessage(Agent a,long timeout){
            super(a,timeout);
        }

        @Override
        protected void onTick() {
            AID receiver = new AID();
            receiver.setLocalName("pingaponga");
            long time = System.currentTimeMillis();

            if(time%2==0){
                ACLMessage msg1=new ACLMessage(ACLMessage.PROPOSE);
                msg1.setOntology("event");
                msg1.setContent("Let's play ping pong?");
                msg1.setConversationId(""+time);
                msg1.addReceiver(receiver);
                myAgent.send(msg1);
            }
            else{
                ACLMessage msg2=new ACLMessage(ACLMessage.INFORM);
                msg2.setContent("Let's play ping pong?");
                msg2.setConversationId(""+time);
                msg2.addReceiver(receiver);
                myAgent.send(msg2);
            }
            block(1000);
        }
    };
}
