import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

/**
 * Created by zecarlos on 10/11/16.
 *
 * Este agente servirá como interface com o utilizador, na qual
 * permite escolher a área de investimentos.
 * Apenas irá transmitir ao agente sector a informação recolhida.
 *
 */
public class S1 extends GuiAgent{

    protected DefinerGui myGui;
    protected String message;
    protected int counter=0;
    protected Random r = new Random();

    @Override
    protected void setup() {
        myGui=new DefinerGui(this);
        System.out.println(this.getLocalName()+ " starting...");
        myGui.frame.setVisible(true);
    }

    @Override
    protected void takeDown(){
        super.takeDown();
        System.out.println(this.getLocalName()+ " finished");
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        int cmd=guiEvent.getType();
        if(cmd==1){
            String content=(String)guiEvent.getSource();
            this.addBehaviour(new ReceiveBehaviour());
            this.addBehaviour(new SendMessage(this,content));
        }else{
            message=(String)guiEvent.getSource();
            this.addBehaviour(new ReceiveBehaviour());
            this.addBehaviour(new SendMessage(this,"else"));
        }
    }

    public class DefinerGui {
        public JFrame frame;
        private JTextField textField;

        //Agente que implementa a interface gráfica na qual se vai dar oportunidade
        //ao utilizador para escolher a sua área de investimentos.

        private S1 myAgent;
        /**
         *Launch da aplicação
         */

        public DefinerGui(){
            initialize();
        }

        public DefinerGui(S1 a){
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

    public class SendMessage extends OneShotBehaviour {
        public SendMessage(Agent a , String aux){
            super(a);
            message=aux;
        }

        @Override
        public void action() {
            AID receiver=new AID();
            receiver.setLocalName("proposal");
            long time=System.currentTimeMillis();
            // 2/3 das vezes será accepted; 1/6 será not understood; 1/6 será rejected
            switch(r.nextInt(6)){
                case 1:
                    //Para simular reject_proposal
                    ACLMessage msg2=new ACLMessage(ACLMessage.PROPOSE);
                    msg2.setContent("");
                    msg2.setConversationId(""+time);
                    msg2.addReceiver(receiver);
                    myAgent.send(msg2);
                    break;

                case 2:
                    //Para simular not_understood
                    ACLMessage msg3=new ACLMessage(ACLMessage.INFORM);
                    msg3.setContent("Inform");
                    msg3.setConversationId(""+time);
                    msg3.addReceiver(receiver);
                    myAgent.send(msg3);

                default:
                    ACLMessage msg1=new ACLMessage(ACLMessage.PROPOSE);
                    msg1.setContent(message);
                    msg1.setConversationId(""+time);
                    msg1.addReceiver(receiver);
                    myAgent.send(msg1);
                    break;
            }
            block(1000);
        }
    };

    public class ReceiveBehaviour extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage msg=receive();
            if(msg!=null){
                if(msg.getPerformative()==ACLMessage.ACCEPT_PROPOSAL){
                    System.out.println("Received message from "+msg.getSender()+" .Request Accepted.");
                }else if(msg.getPerformative()==ACLMessage.REJECT_PROPOSAL){
                    System.out.println("Received message from "+msg.getSender()+" .Request Denied.");
                }else{
                    System.out.println("Received message from "+msg.getSender()+" .Request Not Understood.");
                }
            }
            block();
        }
    };

}
