import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zecarlos on 10/11/16.
 *
 * Este agente servirá como interface com o utilizador, na qual
 * permite escolher a área de investimentos.
 * Apenas irá transmitir ao agente sector a informação recolhida.
 *
 */
public class DefinerAgent extends GuiAgent{

    protected DefinerGui myGui;
    protected String message;

    @Override
    protected void setup() {
        myGui=new DefinerGui(this);
        myGui.frame.setVisible(true);

        //registo do serviço
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("DefinerAgent");
        sd.setName("DefinerAgent");
        dfd.addServices(sd);
        try{
            DFService.register(this,dfd);
        }catch (FIPAException e){
            e.printStackTrace();
        }

    }
    @Override
    protected void takeDown(){

        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println(this.getLocalName()+ " finishing!");
        super.takeDown();
    }
    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        int cmd=guiEvent.getType();
        if(cmd==1){
            String content=(String)guiEvent.getSource();
            AID receiver=new AID();
            // o receiver será o agente sector
            // TODO dar o nome sector ao SectorAgent
            receiver.setLocalName("sector");
            long time=System.currentTimeMillis();
            ACLMessage msg=new ACLMessage(ACLMessage.PROPOSE);
            msg.setContent("Está disponivel?");
            msg.setConversationId(""+time);
            msg.addReceiver(receiver);
            send(msg);

            ACLMessage msg1 = receive();
            while(msg1==null) msg1 = receive();
            if(msg1 != null){
                if(msg1.getPerformative()==ACLMessage.ACCEPT_PROPOSAL){


                    ACLMessage msg2 = new ACLMessage(ACLMessage.INFORM);
                    System.out.println("Received message from "+msg1.getSender().getLocalName()+". Conteúdo: "+ msg1.getContent());
                    time = System.currentTimeMillis();
                    msg2.setContent(content);
                    msg2.setConversationId(""+time);
                    msg2.addReceiver(receiver);
                    send(msg2);
                }else{
                    JOptionPane.showMessageDialog(myGui.frame,"SectorAgent not available, try again later!");
                }
            }


        }else{
            this.addBehaviour(new ReceiveBehaviour());
            message=(String)guiEvent.getSource();
        }
    }

    public class DefinerGui {
        public JFrame frame;
        private JTextField textField;

        //Agente que implementa a interface gráfica na qual se vai dar oportunidade
        //ao utilizador para escolher a sua área de investimentos.

        private DefinerAgent myAgent;
        /**
         *Launch da aplicação
         */

        public DefinerGui(){
            initialize();
        }

        public DefinerGui(DefinerAgent a){
            initialize();
            myAgent=a;
        }

        private void initialize(){
            frame=new JFrame();
            frame.setBounds(100,100,450,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel=new JPanel();
            frame.getContentPane().add(panel,BorderLayout.NORTH);

            JLabel lblTypeAMessage = new JLabel("Type an area of investments to send to SectorAgent:");
            panel.add(lblTypeAMessage);

            textField = new JTextField();
            frame.getContentPane().add(textField,BorderLayout.CENTER);
            textField.setColumns(10);

            JButton btnNewButton = new JButton("Send Area of Investments");
            frame.getContentPane().add(btnNewButton,BorderLayout.SOUTH);
            btnNewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    textField.getText();
                    GuiEvent ge = new GuiEvent(textField.getText(),1);
                    myAgent.postGuiEvent(ge);
                }
            });

            JButton btnNewButton_1 = new JButton("Send Cyclical Messages With the Same Area of Investments");
            frame.getContentPane().add(btnNewButton_1,BorderLayout.EAST);
            btnNewButton_1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField.getText();
                    GuiEvent ge=new GuiEvent(textField.getText(),2);
                    myAgent.postGuiEvent(ge);
                }
            });
        }

        public JTextField getTextField(){
            return textField;
        }

        public void main(String[] args){
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        DefinerGui window=new DefinerGui();
                        window.frame.setVisible(true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

    };



    public class ReceiveBehaviour extends CyclicBehaviour{
        @Override
        public void action() {
            ACLMessage msg=receive();
            if(msg!=null){
                if(msg.getPerformative()==0){
                    System.out.println("Received message from "+msg.getSender()+" .Conteúdo: "+msg.getContent());
                }else{
                    System.out.println("Received message from "+msg.getSender()+" .Conteúdo: The offer to play ping pong was not accepted.");
                }
            }
            block();
        }
    };

}
