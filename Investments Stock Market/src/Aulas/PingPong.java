import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 24-10-2016.
 */
// java -cp lib/jade.jar jade.Boot -gui
public class PingPong extends Agent {

    @Override
    protected void setup(){
        super.setup();
        System.out.println(this.getLocalName()+ " a começar!");
        //this.addBehaviour(new ReceiveBehaviour());
        this.addBehaviour(new AnswerBehaviour());
    }

    @Override
    protected void takeDown(){
        super.takeDown();
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


