/**
 * Created by andrepinto on 10/11/16.
 */
public class Empresa {

    public String nome;
    public int year;
    public int stock;
    public int capital;
    public int stockAvailable;
    public float stockPrice;


    public Empresa (String a){
        this.nome = a;
    }

    int getYear(){
        return year;
    }
    int getStock(){
        return stock;
    }
    int getCapital(){
        return capital;
    }
    int getStockAvailable(){
        return stockAvailable;
    }
    String getNome(){
        return nome;
    }
    float getStockPrice(){
        return stockPrice;
    }


}
