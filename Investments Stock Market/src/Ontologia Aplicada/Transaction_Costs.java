package stockTrading.ontology;

import jade.content.Predicate;
import jade.core.AID;

public class Transaction_Costs implements Predicate {
    private Empresa item;
    private String  transactionItem;
    private float   transactionStockPrice;
    private int     transactionQuantity;


    public Transaction_Costs(Empresa a){
      this.Empresa=a;
    }

    // GETTERS
    public Empresa getEmpresa() {
      return this.empresa;
    }
    public String getTransactionItem(){
      return this.transactionItem;
    }
    public float getTransactionStockPrice(){
      return this.transactionStockPrice;
    }
    public int getTransactionQuantity(){
      return this.transactionQuantity;
    }

    //SETTERS
    public void setEmpresa(Empresa e) {
      this.empresa=e;
    }
    public void setTransactionItem(String ti){
      this.transactionItem=ti;
    }
    public void setTransactionStockPrice(float tsp){
      this.transactionStockPrice=tsp;
    }
    public void setTransactionQuantity(int tq){
      this.transactionQuantity=tq;
    }
}
