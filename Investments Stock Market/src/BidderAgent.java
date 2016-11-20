/**
 * Created by zecarlos on 10/11/16.
 */

 import jade.core.AID;
 import jade.core.Agent;
 import jade.core.behaviours.CyclicBehaviour;
 import jade.domain.DFService;
 import jade.domain.FIPAAgentManagement.DFAgentDescription;
 import jade.domain.FIPAAgentManagement.ServiceDescription;
 import jade.domain.FIPAException;
 import jade.lang.acl.ACLMessage;

 import java.util.HashSet;
 import java.util.List;
 import java.util.TreeMap;

public class BidderAgent extends Agent {

  @Override
  protected void setup() {
      super.setup();

      System.out.println(this.getLocalName()+ " starting!");

      //registo do serviço
      DFAgentDescription dfd = new DFAgentDescription();
      dfd.setName(getAID());
      ServiceDescription sd = new ServiceDescription();
      sd.setType("BidderAgent");
      sd.setName("BidderAgent");
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
              if(msg.getPerformative()==ACLMessage.INFORM) {
                  System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                  //parse do content da mensagem
                  System.out.println("Bid");
                  response.setContent("Yes");
                  response.setPerformative(ACLMessage.INFORM);
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
