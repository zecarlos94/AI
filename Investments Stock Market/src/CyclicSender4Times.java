import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;


/**
 * Created by zecarlos on 24/10/16.
 */
public class CyclicSender4Times extends Agent {

    int counter=0;

    @Override
    protected void  setup(){
        super.setup();
        System.out.println(this.getLocalName()+" a começar!");
        this.addBehaviour(new NewSendMessage());
    }

    @Override
    protected void takeDown(){
        System.out.println(this.getLocalName()+" a morrer!");
    }


    private class NewSendMessage extends SimpleBehaviour {

        @Override
        public void action() {
            AID receiver= new AID();
            receiver.setLocalName("pingaponga");

            ACLMessage msg= new ACLMessage(ACLMessage.INFORM);
            long time = System.currentTimeMillis();
            msg.setConversationId(""+time);
            msg.addReceiver(receiver);

            if(time%2==0){
                msg.setContent("ping");
            }
            else{
                msg.setContent("not ping");
            }

            myAgent.send(msg);
            counter++;
            block(1000);

        }

        @Override
        public int onEnd(){
            myAgent.doDelete();
            return 0;
        }

        @Override
        public boolean done() {
            if(counter>=4){
                return true;
            }else return false;
        }
    };
}
