import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zecarlos on 31/10/16.
 */
public class SenderWithGui extends GuiAgent{

    protected SenderGui myGui;

    @Override
    protected void setup() {
        myGui=new SenderGui(this);
        myGui.frame.setVisible(true);
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        int cmd=guiEvent.getType();
        if(cmd==1){
            String content=(String)guiEvent.getSource();
            AID receiver=new AID();
            receiver.setLocalName("pingaponga");
            long time=System.currentTimeMillis();
            ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
            msg.setContent(content);
            msg.setConversationId(""+time);
            msg.addReceiver(receiver);
            send(msg);
        }else{
            this.addBehaviour(new SendMessage((String)guiEvent.getSource()));
        }
    }

    public class SenderGui {
        public JFrame frame;
        private JTextField textField;
        //Agente que implementa a interface gráfica
        private SenderWithGui myAgent;
        /**
         *Launch da aplicação
         */

        public SenderGui(){
            initialize();
        }

        public SenderGui(SenderWithGui a){
            initialize();
            myAgent=a;
        }

        private void initialize(){
            frame=new JFrame();
            frame.setBounds(100,100,450,300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel=new JPanel();
            frame.getContentPane().add(panel,BorderLayout.NORTH);

            JLabel lblTypeAMessage = new JLabel("Type a message to send to pingaponga:");
            panel.add(lblTypeAMessage);

            textField = new JTextField();
            frame.getContentPane().add(textField,BorderLayout.CENTER);
            textField.setColumns(10);

            JButton btnNewButton = new JButton("Send");
            frame.getContentPane().add(btnNewButton,BorderLayout.SOUTH);
            btnNewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    textField.getText();
                    GuiEvent ge = new GuiEvent(textField.getText(),1);
                    myAgent.postGuiEvent(ge);
                }
            });

            JButton btnNewButton_1 = new JButton("Send Cyclical Messages");
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
                        SenderGui window=new SenderGui();
                        window.frame.setVisible(true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }

    };

    public class SendMessage extends CyclicBehaviour{
        String message;

        public SendMessage(String content){
            message=content;
        }

        @Override
        public void action() {
            AID receiver=new AID();
            receiver.setLocalName("pingaponga");

            ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
            long time=System.currentTimeMillis();
            msg.setConversationId(""+time);
            msg.addReceiver(receiver);
            msg.setContent(message);
            myAgent.send(msg);
            block(1000);
        }
    }

}
