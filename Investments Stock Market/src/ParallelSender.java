import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by zecarlos on 24-10-2016.
 */
public class ParallelSender extends Agent {


    int counter1 = 0;
    int counter2 = 0;
    int counter3 = 0;

    @Override
    protected void setup(){

        super.setup();

        System.out.println(this.getLocalName() + " a começar!");

        ParallelBehaviour pl = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ALL){

            @Override
            public int onEnd(){

                System.out.println("Parallel Behaviour finallizing!");
                myAgent.doDelete();
                return 0;
            }
        };

        this.addBehaviour(pl);
        pl.addSubBehaviour(new sendMessage1());
        pl.addSubBehaviour(new sendMessage2());
        pl.addSubBehaviour(new sendMessage3());
    }

    @Override
    protected void takeDown(){

        System.out.println(this.getLocalName() + " a morrer!");
    }


    private class sendMessage1 extends SimpleBehaviour{

        @Override
        public void action(){

            AID receiver = new AID();
            receiver.setLocalName("pingaponga");

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            long time = System.currentTimeMillis();
            msg.setConversationId(""+time);
            msg.addReceiver(receiver);

            if(time%2 == 0)
                msg.setContent("ping1");
            else
                msg.setContent("not ping1");

            myAgent.send(msg);

            counter1++;

            System.out.println( "Executing SendMessage1 for the "+counter1+" time");
        }

        @Override
        public boolean done(){
            if(counter1 >= 3){
                System.out.println("Behaviour SendMessage1 finnalizing!");
                return true;
            }
            else
                return false;
        }
    }


    private class sendMessage2 extends SimpleBehaviour{

        @Override
        public void action(){

            AID receiver = new AID();
            receiver.setLocalName("pingaponga");

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            long time = System.currentTimeMillis();
            msg.setConversationId(""+time);
            msg.addReceiver(receiver);

            if(time%2 == 0)
                msg.setContent("ping2");
            else
                msg.setContent("not ping2");

            myAgent.send(msg);

            counter2++;

            System.out.println( "Executing SendMessage2 for the "+counter2+" time");
        }

        @Override
        public boolean done(){
            if(counter2 >= 5){
                System.out.println("Behaviour SendMessage2 finnalizing!");
                return true;
            }
            else
                return false;
        }
    }


    private class sendMessage3 extends SimpleBehaviour{

        @Override
        public void action(){

            AID receiver = new AID();
            receiver.setLocalName("pingaponga");

            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            long time = System.currentTimeMillis();
            msg.setConversationId(""+time);
            msg.addReceiver(receiver);

            if(time%2 == 0)
                msg.setContent("ping3");
            else
                msg.setContent("not ping3");

            myAgent.send(msg);

            counter3++;

            System.out.println( "Executing SendMessage3 for the "+counter3+" time");
        }

        @Override
        public boolean done(){
            if(counter3 >= 13){
                System.out.println("Behaviour SendMessage3 finnalizing!");
                return true;
            }
            else
                return false;
        }
    }
}
