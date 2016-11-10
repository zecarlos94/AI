import com.sun.org.apache.bcel.internal.generic.NEW;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ReceiverBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 17-10-2016.
 */
public class CyclicSender extends Agent {

    int counter=0;

    @Override
    protected void  setup(){
        super.setup();
        System.out.println(this.getLocalName()+" a começar!");
        //this.addBehaviour(new SendMessage());
        //this.addBehaviour(new SendMessage(this,1000));
        this.addBehaviour(new ReceiveBehaviour());
        //this.addBehaviour(new NewSendMessage2());
        this.addBehaviour(new NewSendMessage("ping",this,5000));
        this.addBehaviour(new NewSendMessage("pong",this,1000));
        this.addBehaviour(new NewSendMessage("ola!",this,2500));
    }

    @Override
    protected void takeDown(){
        System.out.println(this.getLocalName()+" a morrer!");
    }

    private class ReceiveBehaviour extends CyclicBehaviour{
        @Override
        public void action(){
            ACLMessage msg = receive();
            if(msg != null){
                System.out.println("Recebi uma mensagem de: "+ msg.getSender()+". Conteúdo: "+ msg.getContent());
            }
            block();
        }
    };
    private class NewSendMessage2 extends SimpleBehaviour {

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

    private class NewSendMessage extends TickerBehaviour{
        String content;
        public NewSendMessage(String content,Agent a, long timeout){
            super(a,timeout);
            this.content = content;
        }
        @Override
        protected void onTick(){
            AID receiver = new AID();
            receiver.setLocalName("pingaponga");

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setConversationId(""+System.currentTimeMillis());
            msg.addReceiver(receiver);
            msg.setContent(content);

            myAgent.send(msg);
        }
    }
    private class SendMessage extends TickerBehaviour{

        public SendMessage (Agent a, long timeout){
            super(a,timeout);
        }
        @Override
       protected void onTick(){

            AID receiver = new AID();
            receiver.setLocalName("pingaponga");

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
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
        }
    };

    /*private class SendMessage extends CyclicBehaviour{

        @Override
        public void action(){

            AID receiver = new AID();
            receiver.setLocalName("pingaponga");

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
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
        }
    };*/
}
