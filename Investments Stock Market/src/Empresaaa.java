
import java.util.ArrayList;
import java.util.List;
import jade.content.Concept;
// import jade.util.leap.List;


public class Empresaaa implements Concept{
    private List<String> stockExchangeName;
//    private String stockExchangeName;
    private String companyExchangeNname;
    private String companyName; // TODO Ajustar aqui André! Substitui nos agentes.java por este nome
    private String companyIndustry;
    private List<String>   companyOwners;
    private int    stockAvailable;
    private float  lastStockPrice;
    private String currency;
    private int    year;
    private double    stock; // TODO Ver o que é esta Variável
    private int    companyCapital;

    public Empresaaa(String a, int i){
        this.companyName = a;
    }
    public Empresaaa(String nome, String industria, String owners, double st, String c, int y, String SEN, String cen){
        this.companyName=nome;
        this.companyIndustry=industria;
        String [] own = owners.split(";");
        this.companyOwners= new ArrayList<String>();
        for (String s : own){
            companyOwners.add(s);
        }
        this.stock = st;
        this.currency = c;
        this.year = y;

        String [] sen = SEN.split(";");
        this.stockExchangeName= new ArrayList<String>();
        for (String s1 : sen){
            stockExchangeName.add(s1);
        }
        this.companyExchangeNname = cen;
    }

    public Empresaaa(String a){
        String[] empresa = a.split(";");
        for (int i=0;i<empresa.length;i++){
            switch (i){
                case 0:String [] sen = empresa[i].split("#");
                    this.stockExchangeName= new ArrayList<String>();
                    for (String s : sen){
                        stockExchangeName.add(s);
                    }
                    break;
                case 1:String [] co = empresa[i].split("#");
                    this.companyOwners= new ArrayList<String>();
                    for (String s : co){
                        companyOwners.add(s);
                    }
                    break;
                case 2: this.companyName = empresa[i];
                    break;
                case 3: this.companyIndustry = empresa[i];
                    break;
                case 4: this.stockAvailable = Integer.parseInt(empresa[i]);
                    break;
                case 5: this.lastStockPrice = Float.parseFloat(empresa[i]);
                    break;
                case 6: this.currency= empresa[i];
                    break;
                case 7: this.year = Integer.parseInt(empresa[i]);
                    break;
                case 8: this.stock = Integer.parseInt(empresa[i]);
                    break;
                case 9: this.companyCapital = Integer.parseInt(empresa[i]);
                    break;
            }
        }
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
    public double getStock(){
        return this.stock;
    }
    public String getCurrency (){ return this.currency;}
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
    public void setCurrency(String s) {this.currency=s;}
    public void setCompanyCapital(int cc){
        this.companyCapital=cc;
    }

    @Override
    public String toString(){
        StringBuilder a = new StringBuilder();
        int i =0;
        for (String s : stockExchangeName)
            if (i==0) {
                a.append(s);
                i=1;
            } else a.append("#"+s);
        i=0;a.append(";");
        for (String s : stockExchangeName)
            if (i==0){
                a.append(s);
                i=1;
            } else a.append("#"+s);
        a.append(";");
        a.append(companyName + ";");
        a.append(companyIndustry+";");
        a.append(stockAvailable+";");
        a.append(lastStockPrice+";");
        a.append(currency+";");
        a.append(year+";");
        a.append(stock+";");
        a.append(companyCapital+";");
        return a.toString();
    }
}
