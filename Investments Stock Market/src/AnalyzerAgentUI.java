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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zecarlos on 10/11/16.
 *
 * Este agente servirá como interface com o utilizador, na qual
 * permite escolher a área de investimentos.
 * Apenas irá transmitir ao agente sector a informação recolhida.
 *
 */
public class AnalyzerAgentUI extends Agent{
    //As empresas já foram inicializadas no CyclicBehaviour
    private HashMap<String,Empresa> companies = new HashMap<String,Empresa>();

    //separar o map supramencionado nestes dois maps de acordo com o método investible();
    private HashMap<String,Empresa> companiesInvesting = new HashMap<String,Empresa>();
    private HashMap<String,Empresa> companiesNotInvesting = new HashMap<String,Empresa>();

    //Para cada empresa guardar o capital para investir, o preço máximo por ação, o número de ações que pode comprar no máximo(stockAvailable)
    private HashMap<String,ArrayList<Double>> investing = new HashMap<String,ArrayList<Double>>();


    protected DefinerGui myGui;
    protected String message;


    public void investible(){
        ArrayList<Double> args = new ArrayList<>();
        double capitalMax=0;
        double priceMaxPerShare=0;

        boolean investir=false;

        boolean crescimentoEntreDoisDias=false;
        boolean bomCrescimentoEntreDia=false;
        boolean precosAsubir=false;
        boolean crescimentoMudanca=false;
        boolean bomCrescimentoGradual=false;
        boolean epsAumentar=false;
        boolean bonsDividendos=false;
        boolean bonsEPS=false;
        boolean bomP_B=false;
        boolean bomP_S=false;
        boolean bomP_E=false;
        boolean bomPEG=false;

        double stockAvailable=0;

        double previousOpen=0;
        double open=0;
        double dividendPerShare=0;
        double changeInPercent=0;
        double dayLow=0;
        double dayHigh=0;
        double percentChange200=0;
        double percentChange50=0;
        double tickerTrend=0;
        double earningsPerShare=0;
        double epsEstimCurYear=0;
        double epsEstimNextYear=0;
        double priceSales=0;
        double priceBook=0;
        double peRatio=0;
        double pegRatio=0;

        //Percorrer cada empresa e fazer a tomada de decisão (separar nos dois casos)
        // https://www.share.com/new-to-investing/six-figures-that-can-help-you-decide-whether-to-buy-a-share/
        for( Map.Entry<String, Empresa> e : companies.entrySet() ){
            stockAvailable=e.getValue().getStockAvailable();

            previousOpen=e.getValue().getPreviousOpen();
            open=e.getValue().getOpen();
            dayLow=e.getValue().getDayLow();
            dayHigh=e.getValue().getDayHigh();
            if(open>previousOpen) {
                crescimentoEntreDoisDias = true;
                //Melhor caso, dayLow não desce do valor de abertura
                if (dayLow == open) {
                    if (dayHigh > open) {
                        bomCrescimentoEntreDia = true;
                    }
                } else if (dayLow < open) {
                    if (dayHigh < open) {
                        bomCrescimentoEntreDia = false;
                    } else if (dayHigh > open) {
                        bomCrescimentoEntreDia = true;
                    }
                }
            }else if(open<previousOpen) {
                crescimentoEntreDoisDias = false;
                //Melhor caso, dayLow não desce do valor de abertura
                if (dayLow == open) {
                    if (dayHigh > previousOpen) {
                        bomCrescimentoEntreDia = true;
                    }
                }
            }

            changeInPercent=e.getValue().getChangeInPercent();
            percentChange200=e.getValue().getPercentChange200();
            percentChange50=e.getValue().getPercentChange50();
            if(percentChange50>percentChange200 && percentChange50>0){
                bomCrescimentoGradual=true;
                if(changeInPercent>0.50){
                    crescimentoMudanca=true;
                }
                else{
                    crescimentoMudanca=false;
                }
            }else{
                bomCrescimentoGradual=false;
                if(changeInPercent>0.50){
                    crescimentoMudanca=true;
                }
                else{
                    crescimentoMudanca=false;
                }
            }

            tickerTrend=e.getValue().getTickerTrend();
            if(tickerTrend>=0){ precosAsubir=true; }
            else{ precosAsubir=false; }

            dividendPerShare=e.getValue().getDividendPerShare();
            if(dividendPerShare>1.5 && dividendPerShare<3.5){
                bonsDividendos=true;
            }

            earningsPerShare=e.getValue().getEarningsPerShare();
            if(earningsPerShare<15){
                bonsEPS=true;
            }

            epsEstimCurYear=e.getValue().getEpsEstimCurYear();
            epsEstimNextYear=e.getValue().getEpsEstimNextYear();

            if(epsEstimNextYear>epsEstimCurYear){
                epsAumentar=true;
            }

            priceSales=e.getValue().getPriceSales();
            if(priceSales<3){
                bomP_S=true;
            }
            else{
                bomP_S=false;
            }

            priceBook=e.getValue().getPriceBook();
            if(priceBook<5){
                //Ideal é <1 mas pode perder empresas subvalorizadas
                bomP_B=true;
            }
            else{
                //Sobrevalorizada
                bomP_B=false;
            }

            peRatio=e.getValue().getPeRatio();
            if(peRatio<15){
                //Bom preço
                bomP_E=true;
            }
            else{
                //Muito caro
                bomP_E=false;
            }

            pegRatio=e.getValue().getPegRatio();
            if(pegRatio<=2){
                bomPEG=true;
            }
            else{
                bomPEG=false;
            }

            investir=(bomPEG && bomP_E && bomP_B && bomP_S && epsAumentar && bonsEPS && bonsDividendos && precosAsubir) ||
                      bomCrescimentoGradual || crescimentoMudanca || crescimentoEntreDoisDias || bomCrescimentoEntreDia;


           System.out.println("Investible? "+investir);
           if(investir){
               companiesInvesting.put( e.getKey(), e.getValue() );
               //50$ a mais do preço atual
               priceMaxPerShare=e.getValue().getAsk()+50;
               //3000$ para investir(até 300 ações mais ou menos) se estiver a crescer
               if(bomCrescimentoGradual) capitalMax=3000;
               //Se não estiver a crescer, ser mais cauteloso (minimizar os riscos)
               else capitalMax=1000;
               //Adicionar argumentos para o BidderAgent comprar acções
               args.add(capitalMax);
               args.add(priceMaxPerShare);
               args.add(stockAvailable);
               investing.put( e.getKey(), args );
               //Limpar argumentos para a próxima empresa
               args.clear();
           }
           else{
               companiesNotInvesting.put( e.getKey(), e.getValue() );
           }
        }
        //System.out.println("#Investible: "+companiesInvesting.size());
        //System.out.println("#Not Investible: "+companiesNotInvesting.size());
    }

    //TODO Implementar método para parar de investir nas empresas da lista companiesInvesting!!
    // só depois de receber a lista das acções que conseguiu comprar !! ZÉ+ANDRÉ

    //TODO Implementar método para criar histórico de vendas e compras <- Com base no que receber do MarketAgent, BidderAgent and SellerAgent!!!! ANDRÉ


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
         * Creates new form AnalyzerAgentUI
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

            jScrollPane1 = new javax.swing.JScrollPane();
            jTextArea1 = new javax.swing.JTextArea();
            jLabel1 = new javax.swing.JLabel();

            frame = new javax.swing.JFrame();
            frame.setResizable(false);
            frame.setTitle("Histórico das Ações");
            frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

            jTextArea1.setColumns(20);
            jTextArea1.setRows(5);
            jScrollPane1.setViewportView(jTextArea1);

            jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compras", "Vendas" }));

            jButton1.setText("Registos DF");
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });

            jLabel1.setText("Agentes Licitador e Vendedor");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frame.getContentPane());
            frame.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(16, 16, 16)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel1)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jButton1)))
                                    .addContainerGap(16, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton1)
                                            .addComponent(jLabel1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                                    .addGap(16, 16, 16))
            );

            frame.pack();
        }// </editor-fold>

        private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
            // TODO add your handling code here:
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
        public javax.swing.JButton jButton1;
        public javax.swing.JComboBox<String> jComboBox1;
        public javax.swing.JLabel jLabel1;
        public javax.swing.JScrollPane jScrollPane1;
        public javax.swing.JTextArea jTextArea1;
        public JFrame frame;
        // End of variables declaration
    };


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
                    send(response);

                }else if(msg.getPerformative()==ACLMessage.INFORM) {
                    System.out.println("Received message from " + msg.getSender().getLocalName() + ". Conteúdo: " + msg.getContent());
                    Empresa e = new Empresa(msg.getContent());
                    if(companies.get(e.getCompanyExchangeName())!=null) companies.remove(e.getCompanyExchangeName());
                    companies.put(e.getCompanyExchangeName(),e);
                }
                else{
                    System.out.println("Received message from "+msg.getSender().getLocalName()+". Conteúdo: "+ msg.getContent());
                    response.setContent("No");
                    response.setPerformative(ACLMessage.REJECT_PROPOSAL);
                    send(response);

                }
            }
        investible(); // TODO ver onde se pode chamar a função
        }
    };

}
