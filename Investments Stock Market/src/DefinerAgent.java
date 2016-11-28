import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
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
public class DefinerAgent extends Agent{

    protected DefinerGui myGui;
    protected String message;

    @Override
    protected void setup() {
        super.setup();
        System.out.println(this.getLocalName()+ " starting!");


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
    /*@Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        int cmd=guiEvent.getType();
        if(cmd==1){
            String content=(String)guiEvent.getSource();



        }else{


        }
    }*/
    public class DefinerGui {
        public Agent myAgent;
        /**
         * Creates new form DefinerAgent
         */
        public DefinerGui() {
            initComponents();
        }
        public DefinerGui(Agent m) {
            initComponents();
            myAgent = m;
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">
        private void initComponents() {

            jComboBox1 = new javax.swing.JComboBox();
            jButton1 = new javax.swing.JButton();
//            jButton2 = new javax.swing.JButton();
            frame = new javax.swing.JFrame();
            frame.setResizable(false);
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tecnologia", "Finanças" }));

            jButton1.setText("Send Message");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

//            jButton2.setText("Send Cyclic Message");
//            jButton2.addActionListener(new java.awt.event.ActionListener() {
//                public void actionPerformed(java.awt.event.ActionEvent evt) {
//                    jButton2ActionPerformed(evt);
//                }
//            });

           /* javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
            frame.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(jButton1)
                                    .addGap(30, 30, 30)
//                                    .addComponent(jButton2)
                                    .addContainerGap(50, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButton1))
//                                            .addComponent(jButton2))
                                    .addGap(10, 10, 10))
            );*/

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
            frame.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(15, 15, 15)
                                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(90, 90, 90)
                                                    .addComponent(jButton1)))
                                    .addGap(15, 15, 15))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                    .addComponent(jButton1)
                                    .addContainerGap())
            );

            frame.pack();
        }// </editor-fold>

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            String content = jComboBox1.getSelectedItem().toString();

            System.out.println(content + " one shot message sent!!");

            AID receiver=new AID();
            // o receiver será o agente sector
            // TODO dar o nome sector ao SectorAgent
            receiver.setLocalName("SectorAgent");
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
        }

        private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
            myAgent.addBehaviour(new CommunicationBehaviour(jComboBox1.getSelectedItem().toString(),System.currentTimeMillis()));
        }

        /**
         * @param args the command line arguments
         */
        public void main(String args[]) {
        /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(DefinerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(DefinerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(DefinerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(DefinerGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

        /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new DefinerGui().frame.setVisible(true);
                }
            });
        }

        // Variables declaration - do not modify
        private javax.swing.JButton jButton1;
//        private javax.swing.JButton jButton2;
        private javax.swing.JComboBox jComboBox1;
        public JFrame frame;
        // End of variables declaration
    };

    /*public class DefinerGui {
        public JFrame frame;
        private JTextField textField;

        //Agente que implementa a interface gráfica na qual se vai dar oportunidade
        //ao utilizador para escolher a sua área de investimentos.

        private DefinerAgent myAgent;
        *//**//**//**//**
         *Launch da aplicação
         *//**//**//**//*

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
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

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

    };*/



    public class CommunicationBehaviour extends SimpleBehaviour {

        protected String content;
        protected long time;
        public CommunicationBehaviour(String f,long time){
            content = f;
            this.time = time;
        }

        @Override
        public boolean done() {
            return (System.currentTimeMillis()>(time+10000));
        }

        @Override
        public void action() {

            AID receiver=new AID();
            // o receiver será o agente sector
            // TODO dar o nome sector ao SectorAgent
            receiver.setLocalName("SectorAgent");
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
                    //JOptionPane.showMessageDialog(myGui.frame,"SectorAgent not available, try again later!");
                }
            }
            block();
        }
    };

}
