import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 * Created by zecarlos on 10/11/16.
 */
public class AnalyzerAgent extends Agent{

    @Override
    protected void setup() {
        super.setup();

        System.out.println(this.getLocalName()+ " starting!");

        //registo do serviço
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("AnalyserAgent");
        sd.setName("AnalyserAgent");
        dfd.addServices(sd);
        try{
            DFService.register(this,dfd);
        }catch (FIPAException e){
            e.printStackTrace();
        }
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



}
