import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 24/10/16.
 */
public class SenderArgument extends Agent{
    protected String message;

    @Override
    protected void setup() {
        super.setup();
        System.out.println(this.getLocalName()+" a começar!");

        Object[] args=getArguments();
        if(args!=null && args.length>0){
            message=(String) args[0];
            System.out.println("Sending "+ message+"!");
            this.addBehaviour(new SendMessage());
        }
    }

    public class SendMessage extends OneShotBehaviour{
        @Override
        public void action() {
            AID receiver=new AID();
            receiver.setLocalName("pingaponga");
            ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
            long time=System.currentTimeMillis();
            msg.setConversationId(""+time);
            msg.addReceiver(receiver);
            msg.setContent(message);
            myAgent.send(msg);
        }
    };

}
