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
 * Created by zecarlos on 10/11/16.
 */
public class SectorAgent extends Agent {

    public HashMap<String,HashMap<String,Empresa>> areaFilter;
    public String area;


    @Override
    protected void setup(){
        super.setup();

        areaFilter = new HashMap<String,HashMap<String,Empresa>>();
        area = null;
        ArrayList<Empresa> lista = new ArrayList<Empresa>();



        System.out.println(this.getLocalName()+ " starting!");

       //registo do serviço
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("SectorAgent");
        sd.setName("SectorAgent");
        dfd.addServices(sd);
        try{
            DFService.register(this,dfd);
        }catch (FIPAException e){
            e.printStackTrace();
        }

        this.addBehaviour(new ReceiveBehaviourInformative());
        this.addBehaviour(new SendMessage(this,20000));
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








    public class SendMessage extends TickerBehaviour{

        public SendMessage(Agent a, long timeout){
            super(a,timeout);
        }

        @Override
        public void onTick(){
            if(area!=null) {
                System.out.println("A enviar a Analizador...");
                AID receiver = new AID();
                DFAgentDescription template = new DFAgentDescription();
                ServiceDescription sd = new ServiceDescription();
                sd.setType("AnalyzerAgent");
                template.addServices(sd);

                try {
                    DFAgentDescription[] result = DFService.search(myAgent, template);

                    receiver.setLocalName(result[0].getName().getLocalName().toString());
                }catch (Exception e ){e.printStackTrace();}
                long time = System.currentTimeMillis();
                ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                msg.setContent("Está disponivel?");
                msg.setConversationId("" + time);
                msg.addReceiver(receiver);
                send(msg);
            }
        }
    }









    private class ReceiveBehaviourInformative extends CyclicBehaviour{

        @Override
        public void action() {
            ACLMessage msg = receive();
            if(msg != null){
                ACLMessage response=msg.createReply();
                if(msg.getPerformative()==ACLMessage.PROPOSE){
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    response.setContent("Yes");
                    response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                    send(response);
                }else if(msg.getPerformative()==ACLMessage.INFORM){
                    if(msg.getSender().getLocalName().equals("SearchAgent")){
                        String[] a = msg.getContent().split("_");System.out.println(msg.getContent());
                        HashMap<String,Empresa> lista = areaFilter.get(a[0]);
                        Empresa temp = new Empresa(a[1]);

                        if(lista==null){
                            lista = new HashMap<String,Empresa>();
                            lista.put(temp.getCompanyExchangeName(),temp);
                            areaFilter.put(a[0],lista);
                        }
                        else{

                            lista.remove(temp.getCompanyExchangeName());
                            lista.put(temp.getCompanyExchangeName(),temp);
                            areaFilter.remove(a[0]);
                            areaFilter.put(a[0],lista);
                            for (Map.Entry<String, HashMap<String,Empresa>> e : areaFilter.entrySet())
                                for (Map.Entry<String, Empresa> b : e.getValue().entrySet())
                                    System.out.println(e.getKey()+" ---> " + b.getKey());


                        }
                        System.out.println("Company received: " + temp.getCompanyExchangeName());
                    }
                    else{
                        System.out.println("Área definida para "+msg.getContent());
                        area = msg.getContent();

                    }
                }
                else if (msg.getPerformative() == ACLMessage.ACCEPT_PROPOSAL){
                    ACLMessage msg1 = receive();
                    AID receiver = new AID();
                    DFAgentDescription template = new DFAgentDescription();
                    ServiceDescription sd = new ServiceDescription();
                    sd.setType("AnalyzerAgent");
                    template.addServices(sd);

                    try {
                        DFAgentDescription[] result = DFService.search(myAgent, template);

                        receiver.setLocalName(result[0].getName().getLocalName().toString());
                    }catch (Exception e ){e.printStackTrace();}

                    if(!areaFilter.isEmpty()) {
                            HashMap<String, Empresa> lista = areaFilter.get(area);

                            ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                            long time = System.currentTimeMillis();
                            msg2.addReceiver(receiver);
                            msg2.setConversationId("" + time);
                            if (lista != null) {
                                if (!lista.isEmpty()){
                                    for (Map.Entry<String, Empresa> e : lista.entrySet()) {
                                        msg2.setContent(e.getValue().toString());
                                        send(msg2);
                                    }
                                }else System.out.print("lista vazia");
                            }else System.out.println("lista nula!!!");
                        }else System.out.println("areaFilter vazio!!!");

                }
                else if (msg.getPerformative() != ACLMessage.REJECT_PROPOSAL){
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


