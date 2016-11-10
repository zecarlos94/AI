import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

/**
 * Created by zecarlos on 31/10/16.
 */
public class PingPongFilter extends Agent {

    @Override
    protected void setup(){
        super.setup();
        System.out.println(this.getLocalName()+ " a começar!");
        this.addBehaviour(new ReceiveBehaviourFilter());
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName()+ " a terminar!");
    }

    private class ReceiveBehaviourFilter extends CyclicBehaviour{

        @Override
        public void action() {
            MessageTemplate mt1=MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
            MessageTemplate mt2=MessageTemplate.MatchOntology("event");
            MessageTemplate mt3=MessageTemplate.and(mt1,mt2);

            ACLMessage msg = receive(mt3);
            if(msg != null){
                ACLMessage response=msg.createReply();
                System.out.println("Received message from "+msg.getSender()+". Conteúdo: "+ msg.getContent());
                response.setContent("Yes");
                response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                send(response);
            }
            block();
        }
    };
}


