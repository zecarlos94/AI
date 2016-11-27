import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by zecarlos on 10/11/16.
 */
public class AnalyzerAgent extends Agent{


    private HashMap<String,Empresa> companies = new HashMap<String,Empresa>();


    @Override
    protected void setup() {
        super.setup();

        System.out.println(this.getLocalName()+ " starting!");

        //registo do serviço
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("AnalyzerAgent");
        sd.setName("AnalyzerAgent");
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

    private class ReceiveBehaviourInformative extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = receive();
            if(msg != null){
                ACLMessage response=msg.createReply();
                if(msg.getPerformative()==ACLMessage.PROPOSE){
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    response.setContent("Yes");
                    response.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                }else if(msg.getPerformative()==ACLMessage.INFORM) {
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    Empresa e = new Empresa(msg.getContent());
                    if(companies.get(e.getCompanyExchangeName())!=null) companies.remove(e.getCompanyExchangeName());
                    companies.put(e.getCompanyExchangeName(),e);
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
