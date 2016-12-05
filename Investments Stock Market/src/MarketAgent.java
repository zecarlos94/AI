import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by andrepinto on 05/12/16.
 */
public class MarketAgent extends Agent{

    private HashMap<String,ArrayList<Double>> compras;
    private HashMap<String,ArrayList<Double>> vendas;
    private HashMap<String,ArrayList<String>> efetuadasV;
    private HashMap<String,ArrayList<String>> efetuadasC;
    @Override
    protected void setup(){
        super.setup();

        System.out.println(this.getLocalName()+ " starting!");

        //registo do serviço
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("MarketAgent");
        sd.setName("MarketAgent");
        dfd.addServices(sd);
        try{
            DFService.register(this,dfd);
        }catch (FIPAException e){
            e.printStackTrace();
        }
        this.addBehaviour(new ReceiveBehaviourInformative());
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println(this.getLocalName()+ " finishing!");
    }





    private class InvestibleBehaviour extends TickerBehaviour {

        public InvestibleBehaviour(Agent a, long timeout){
            super(a,timeout);
        }
        @Override
        public void onTick(){


            for (Map.Entry<String,ArrayList<Double>> e : compras.entrySet()){
                ArrayList<Double> list = e.getValue();

                Random r1 = new Random();
                switch (r1.nextInt(10)){
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        Double price = list.get(0)+ r1.nextDouble() * (list.get(2)-list.get(0));
                        Double stock = list.get(1)/price;
                        if(list.get(3)*10/100 < stock) stock = list.get(3)*10/100;
                        int bought = r1.nextInt(Integer.parseInt(stock.toString()));

                        if(efetuadasC.get(e.getKey())==null) {
                            ArrayList<String> a = new ArrayList<String>();
                            a.add(bought+"#"+price);
                            efetuadasC.put(e.getKey(),a);
                        }
                        else{
                            ArrayList<String> a = efetuadasC.get(e.getKey());
                            a.add(bought+"#"+price);
                        }
                }
            }


            for (Map.Entry<String,ArrayList<Double>> e : compras.entrySet()){
                ArrayList<Double> list = e.getValue();

                Random r1 = new Random();
                switch (r1.nextInt(10)){
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        Double price = 0.7*list.get(0)+ r1.nextDouble() * (1.2*list.get(0)-0.7*list.get(0));
                        Double stock = list.get(1);
                        if(list.get(1)*30/100 < stock) stock = list.get(1)*30/100;
                        int bought = r1.nextInt(Integer.parseInt(stock.toString()));

                        if(efetuadasC.get(e.getKey())==null) {
                            ArrayList<String> a = new ArrayList<String>();
                            a.add(bought+"#"+price);
                            efetuadasV.put(e.getKey(),a);
                        }
                        else{
                            ArrayList<String> a = efetuadasV.get(e.getKey());
                            a.add(bought+"#"+price);
                        }
                }
            }



            for (Map.Entry<String,ArrayList<Double>> e : vendas.entrySet()){
                ArrayList<Double> list = e.getValue();

                Random r1 = new Random();
                switch (r1.nextInt(10)){
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        Double price = list.get(0)+ r1.nextDouble() * (list.get(2)-list.get(0));
                        Double stock = list.get(1)/price;
                        if(list.get(3)/10 < stock) stock = list.get(3)/10;
                        int bought = r1.nextInt(Integer.parseInt(stock.toString()));

                        if(efetuadasC.get(e.getKey())==null) {
                            ArrayList<String> a = new ArrayList<String>();
                            a.add(bought+"#"+price);
                            efetuadasC.put(e.getKey(),a);
                        }
                        else{
                            ArrayList<String> a = efetuadasC.get(e.getKey());
                            a.add(bought+"#"+price);
                        }
                }
            }








            AID receiver = new AID();
            long time=System.currentTimeMillis();
            ACLMessage msg=new ACLMessage(ACLMessage.PROPOSE);
            msg.setContent("Está disponivel?");
            msg.setConversationId(""+time);
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            if (!efetuadasV.isEmpty()){
                sd.setType("BidderAgent");
                template.addServices(sd);

                try {
                    DFAgentDescription[] result = DFService.search(myAgent, template);

                    receiver.setLocalName(result[0].getName().getLocalName().toString());
                }catch (Exception e ){e.printStackTrace();}

                msg.addReceiver(receiver);
                send(msg);
            }
            if (!efetuadasC.isEmpty()){
                sd.setType("SellerAgent");
                template.addServices(sd);

                try {
                    DFAgentDescription[] result = DFService.search(myAgent, template);

                    receiver.setLocalName(result[0].getName().getLocalName().toString());
                }catch (Exception e ){e.printStackTrace();}

                msg.addReceiver(receiver);
                send(msg);
            }
        }
    };





    private class ReceiveBehaviourInformative extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = receive();
            if(msg != null){
                ACLMessage response=msg.createReply();
                if(msg.getPerformative()==ACLMessage.REQUEST){
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    response.setContent("Yes");
                    response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                }
                else if(msg.getPerformative()==ACLMessage.INFORM && msg.getSender().getLocalName().equals("AnalyzerAgent")) {
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());


                }else if(msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL && msg.getSender().getLocalName().equals("BidderAgent")) {
                    ACLMessage msg1 = msg.createReply();
                    for (Map.Entry<String,ArrayList<String>> e : efetuadasV.entrySet()) {
                        StringBuilder st = new StringBuilder();
                        st.append(e.getKey() + "_");
                        for (String s : e.getValue())
                            st.append("|"+s);
                        msg1.setContent(st.toString());
                        send(msg1);
                        efetuadasV.remove(e.getKey());
                    }

                }else if(msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL && msg.getSender().getLocalName().equals("SellerAgent")) {
                    ACLMessage msg1 = msg.createReply();
                    for (Map.Entry<String,ArrayList<String>> e : efetuadasC.entrySet()) {
                        StringBuilder st = new StringBuilder();
                        st.append(e.getKey() + "_");
                        for (String s : e.getValue())
                            st.append("|"+s);
                        msg1.setContent(st.toString());
                        send(msg1);
                        efetuadasC.remove(e.getKey());
                    }

                }
                else{
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    response.setContent("No");
                    response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                }

                send(response);

            }
            block();
        }
    };

}
