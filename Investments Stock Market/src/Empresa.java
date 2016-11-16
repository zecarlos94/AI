
import java.util.ArrayList;
import java.util.List;
import jade.content.Concept;
// import jade.util.leap.List;


public class Empresa implements Concept{
    private List<String> stockExchangeName;
    //private String stockExchangeName;
    private String companyName; // TODO Ajustar aqui André! Substitui nos agentes.java por este nome
    private String companyIndustry;
    private List<String>   companyOwners;
    private int    stockAvailable;
    private float  lastStockPrice;
    private int    year;
    private int    stock; // TODO Ver o que é esta Variável
    private int    companyCapital;

    public Empresa (String a){
        this.companyName = a;
    }

    // GETTERS
    public List<String> getStockExchangeName(){
        return this.stockExchangeName;
    }
    public String getCompanyName(){
        return this.companyName;
    }
    public String getCompanyIndustry(){
        return this.companyIndustry;
    }
    public List<String> getCompanyOwners(){
        List<String> aux = new ArrayList<String>();
        for(String a : this.companyOwners){
          aux.add(a);
        }
        return aux;
    }
    public int getStockAvailable(){
        return this.stockAvailable;
    }
    public float getLastStockPrice(){
        return this.lastStockPrice;
    }
    public int getYear(){
        return this.year;
    }
    public int getStock(){
        return this.stock;
    }
    public int getCompanyCapital(){
        return this.companyCapital;
    }

    // SETTERS
    public void setStockExchangeName(List<String> sen){
        this.stockExchangeName.addAll(sen);
    }
    public void setCompanyName(String cn){
        this.companyName=cn;
    }
    public void setCompanyIndustry(String ci){
        this.companyIndustry=ci;
    }
    public void setCompanyOwners(List<String> co){
        for(String a : co){
          this.companyOwners.add(a);
        }
    }
    public void setStockAvailable(int sa){
        this.stockAvailable=sa;
    }
    public void setLastStockPrice(float lsp){
        this.lastStockPrice=lsp;
    }
    public void setYear(int y){
        this.year=y;
    }
    public void setStock(int s){
        this.stock=s;
    }
    public void setCompanyCapital(int cc){
        this.companyCapital=cc;
    }
}
