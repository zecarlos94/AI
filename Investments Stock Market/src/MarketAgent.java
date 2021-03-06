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
import java.util.*;

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

        compras = new HashMap<>();
        vendas = new HashMap<>();
        efetuadasC = new HashMap<>();
        efetuadasV = new HashMap<>();
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
        this.addBehaviour(new InvestibleBehaviour(this,3000));
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



            if (!vendas.isEmpty())
            for (Map.Entry<String,ArrayList<Double>> e : vendas.entrySet()){
                ArrayList<Double> list = e.getValue();

                Random r1 = new Random();
                switch (r1.nextInt(10)){
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        Double price = 0.7*list.get(0)+ r1.nextDouble() * (1.2*list.get(0)-0.7*list.get(0));
                        Double stock = list.get(1)*30/100;
                        int bought = r1.nextInt(stock.intValue());

                        if(bought > 0) {
                            if (efetuadasV.get(e.getKey()) == null) {
                                ArrayList<String> a = new ArrayList<String>();
                                a.add(bought + "#" + price);
                                efetuadasV.put(e.getKey(), a);
                            } else {
                                ArrayList<String> a = efetuadasV.get(e.getKey());
                                a.add(bought + "#" + price);
                            }
                        }
                }
            }


            if (!compras.isEmpty())
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
                        if(list.get(3)/10 < stock) stock = list.get(3)/10;
                        int bought = r1.nextInt(stock.intValue());

                        if (bought > 0) {
                            if (efetuadasC.get(e.getKey()) == null) {
                                ArrayList<String> a = new ArrayList<String>();
                                a.add(bought + "#" + price);
                                efetuadasC.put(e.getKey(), a);
                            } else {
                                ArrayList<String> a = efetuadasC.get(e.getKey());
                                a.add(bought + "#" + price);
                            }
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
            if (!efetuadasC.isEmpty()){
//                System.out.println("PROPOSE SENT!!");

                sd.setType("BidderAgent");
                template.addServices(sd);

                try {
                    DFAgentDescription[] result = DFService.search(myAgent, template);

                    receiver.setLocalName(result[0].getName().getLocalName().toString());
                }catch (Exception e ){e.printStackTrace();}

                msg.addReceiver(receiver);
                send(msg);
            }

            if (!efetuadasV.isEmpty()){
//                System.out.println("PROPOSE SENT111111111!!");

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
                if(msg.getPerformative()==ACLMessage.PROPOSE){
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    response.setContent("Yes");
                    response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                    send(response);

                }
                else if(msg.getPerformative()==ACLMessage.INFORM && msg.getSender().getLocalName().equals("BidderAgent")) {
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    String[] a1 = msg.getContent().split("_");
                    ArrayList<Double> lista = new ArrayList<>();
                    String[] a2 = a1[1].split("#");
                    for (String d : a2){
                        if(!d.isEmpty())
                            lista.add(Double.parseDouble(d));
                    }

                    if(compras.get(a1[0])!=null) compras.remove(a1[0]);

                    compras.put(a1[0],lista);

                }else if(msg.getPerformative()==ACLMessage.INFORM && msg.getSender().getLocalName().equals("SellerAgent")) {
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    String[] a1 = msg.getContent().split("_");
                    ArrayList<Double> lista = new ArrayList<>();
                    String[] a2 = a1[1].split("#");
                    for (String d : a2){
                        if(!d.isEmpty())
                        lista.add(Double.parseDouble(d));
                    }

                    if(vendas.get(a1[0])!=null) vendas.remove(a1[0]);

                    vendas.put(a1[0],lista);
                    System.out.println(a1[0] +"       " + lista.toString());


                }else if(msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL && msg.getSender().getLocalName().equals("BidderAgent")) {

                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());

                    ACLMessage msg1 = msg.createReply();
                    ArrayList<String> remove = new ArrayList<>();
                    if(!efetuadasC.isEmpty())
                    for (Map.Entry<String,ArrayList<String>> e : efetuadasC.entrySet()) {
                        StringBuilder st = new StringBuilder();
                        st.append(e.getKey() + "_");
                        for (String s : e.getValue())
                            st.append("-"+s);
                        msg1.setPerformative(ACLMessage.INFORM);
                        msg1.setContent(st.toString());
                        send(msg1);
                        remove.add(e.getKey());
                    }
                    for (String e : remove){
                        efetuadasC.remove(e);
                    }
                }else if(msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL && msg.getSender().getLocalName().equals("SellerAgent")) {
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());

                    ACLMessage msg1 = msg.createReply();
                    ArrayList<String> remove = new ArrayList<>();
                    if(!efetuadasV.isEmpty())
                    for (Map.Entry<String,ArrayList<String>> e : efetuadasV.entrySet()) {
                        StringBuilder st = new StringBuilder();
                        st.append(e.getKey() + "_");
                        for (String s : e.getValue())
                            st.append("-"+s);
                        msg1.setPerformative(ACLMessage.INFORM);
                        msg1.setContent(st.toString());
                        send(msg1);
                        remove.add(e.getKey());
                    }

                    for (String e : remove){
                        efetuadasV.remove(e);
                    }

                }
                else{
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    response.setContent("No");
                    response.setPerformative(ACLMessage.NOT_UNDERSTOOD);
                    send(response);

                }

            }
            block();
        }
    };

}
