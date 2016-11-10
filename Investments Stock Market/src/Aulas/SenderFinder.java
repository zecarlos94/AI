import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 25/10/16.
 */
public class SenderFinder extends Agent {

    private AID[] pingpongAgents;

    @Override
    protected void setup(){
        super.setup();
        System.out.println(this.getLocalName()+" a começar!");
        this.addBehaviour(new SendMessage());
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName()+ " a terminar!");
    }

    private class SendMessage extends OneShotBehaviour{
        @Override
        public void action() {
            DFAgentDescription template=new DFAgentDescription();
            ServiceDescription sd=new ServiceDescription();
            sd.setType("ping pong");
            template.addServices(sd);

            DFAgentDescription[] result;
            try {
                result= DFService.search(myAgent,template);
                pingpongAgents = new AID[result.length];
                for(int i=0;i<result.length;i++){
                    pingpongAgents[i]=result[i].getName();
                    ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
                    long time=System.currentTimeMillis();
                    msg.setConversationId(""+time);
                    msg.addReceiver(pingpongAgents[i]);
                    msg.setContent("ping");
                    myAgent.send(msg);
                }
            }catch (FIPAException e){
                e.printStackTrace();
            }


        }
    };
}
