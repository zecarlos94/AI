import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.io.IOException;

public class Search {

  public static Empresa getCompanyData(String symbol) {
    Empresa company = new Empresa(symbol);
    try {
      // Receber e ler ficheiro csv
      URL csv = new URL("http://finance.yahoo.com/d/quotes.csv?s="+ symbol +"&f=abb2b3podc1c6k2p2d1d2t1c8c3ghk1lm6m8w1w4p1mm2g1g3g5t7i5l2l3v1v7s6kjj6k5wc4j1j3nn4ss1xj2va5b6k3a2ee7e8e9b4j4p5p6rr2r5r6r7s7");
      URLConnection con = csv.openConnection();
      InputStreamReader is = new InputStreamReader(con.getInputStream());
      BufferedReader br = new BufferedReader(is);

      // Fazer parser do CSV para um array
      String line = br.readLine();
      // Fazer split de vírgulas que nao estao dentro de ""
      String[] stockInfo = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
      // Classe para transformar "N/A" em 0.0
      SearchHelper sh = new SearchHelper();
      // Guardar a informação na classe
      company.setAsk(sh.handleDouble(stockInfo[0]));
      company.setBid(sh.handleDouble(stockInfo[1]));
      company.setAskRT(sh.handleDouble(stockInfo[2]));
      company.setBidRT(sh.handleDouble(stockInfo[3]));
      company.setPreviousOpen(sh.handleDouble(stockInfo[4]));
      company.setOpen(sh.handleDouble(stockInfo[5]));
      company.setDividendPerShare(sh.handleDouble(stockInfo[6]));
      company.setChange(sh.handleDouble((stockInfo[7].replace("%", "")).replace("\"", "")));
      company.setChangeRT(sh.handleDouble((stockInfo[8].replace("%", "")).replace("\"", "")));
      company.setChangePercentRT(sh.handleDouble((stockInfo[9].replace("%", "")).replace("\"", "")));
      company.setChangeInPercent(sh.handleDouble((stockInfo[10].replace("%", "")).replace("\"", "")));
      company.setLastTradeDate(stockInfo[11].replace("\"",""));
      company.setTradeDate(stockInfo[12].replace("\"",""));
      company.setLastTradeTime(stockInfo[13].replace("\"",""));
      company.setAfterHoursChangeRT(sh.handleDouble(stockInfo[14]));
      company.setComission(sh.handleDouble(stockInfo[15]));
      company.setDayLow(sh.handleDouble(stockInfo[16]));
      company.setDayHigh(sh.handleDouble(stockInfo[17]));
      company.setLastTradeRTwTime(stockInfo[18].replace("\"",""));
      company.setLastTradewTime(stockInfo[19].replace("\"", ""));
      company.setPercentChange200(sh.handleDouble((stockInfo[20].replace("%", ""))));
      company.setPercentChange50(sh.handleDouble((stockInfo[21].replace("%", ""))));
      company.setDayValueChange(sh.handleDouble(stockInfo[22]));
      company.setDayValueChangeRT(sh.handleDouble(stockInfo[23]));
      company.setPricePaid(sh.handleDouble(stockInfo[24]));
      company.setHoldingGainPercent(sh.handleDouble(stockInfo[27]));
      company.setAnnualizedGain(sh.handleDouble(stockInfo[28]));
      company.setHoldingGainPercentRT(sh.handleDouble(stockInfo[29]));
      company.setTickerTrend(sh.handleDouble(stockInfo[30]));
      company.setOrderBookRT(sh.handleDouble(stockInfo[31]));
      company.setHighLimit(sh.handleDouble(stockInfo[32]));
      company.setLowLimit(sh.handleDouble(stockInfo[33]));
      company.setHoldingsValue(sh.handleDouble(stockInfo[34]));
      company.setHoldingValueRT(sh.handleDouble(stockInfo[35]));
      company.setRevenue(stockInfo[36].replace("\"",""));
      company.setWeekHigh(sh.handleDouble(stockInfo[37]));
      company.setWeekLow(sh.handleDouble(stockInfo[38]));
      company.setPercentChange52low(sh.handleDouble(stockInfo[39].replace("%", "")));
      company.setPercentChange52high(sh.handleDouble(stockInfo[40].replace("%", "")));
      company.setCurrency(stockInfo[42].replace("\"", ""));
      company.setMarketCap(stockInfo[43].replace("\"",""));
      company.setMarketCapRT(stockInfo[44].replace("\"",""));
      company.setName(stockInfo[45].replace("\"", ""));
      company.setNotes(stockInfo[46].replace("\"", ""));
      //company.setSymbol(stockInfo[47].replace("\"", ""));
      company.setSharesOwned(sh.handleDouble(stockInfo[48]));
      company.setStockExchange(stockInfo[49].replace("\"", ""));
      company.setSharesOutsta(sh.handleDouble(stockInfo[50]));
      company.setVolume(sh.handleDouble(stockInfo[51]));
      company.setAskSize(sh.handleDouble(stockInfo[52]));
      company.setBidSize(sh.handleDouble(stockInfo[53]));
      company.setLastTradeSize(sh.handleDouble(stockInfo[54]));
      company.setAverageDailyVolume(sh.handleDouble(stockInfo[55]));
      company.setEarningsPerShare(sh.handleDouble(stockInfo[56]));
      company.setEpsEstimCurYear(sh.handleDouble(stockInfo[57]));
      company.setEpsEstimNextYear(sh.handleDouble(stockInfo[58]));
      company.setEpsEstimNextQuarter(sh.handleDouble(stockInfo[59]));
      company.setBookBalue(sh.handleDouble(stockInfo[60]));
      company.setEbitda(stockInfo[61].replace("\"",""));
      company.setPriceSales(sh.handleDouble(stockInfo[62]));
      company.setPriceBook(sh.handleDouble(stockInfo[63]));
      company.setPeRatio(sh.handleDouble(stockInfo[64]));
      company.setPeRatioRT(sh.handleDouble(stockInfo[65]));
      company.setPegRatio(sh.handleDouble(stockInfo[66]));
      company.setPriceEpsEstCurYear(sh.handleDouble(stockInfo[67]));
      company.setPriceEpsEstNextYear(sh.handleDouble(stockInfo[68]));
      company.setShortRatio(sh.handleDouble(stockInfo[69]));
    } catch (IOException e) {}
    return company;
  }

  public static void main(String[] args) {
    // Já dá para outras SE!!!
    //Adicionar extensao .? à frente do nome
    //Neste caso .L é para a LDSE
    // Ver Exchanges and Yahoo em
    //http://www.jarloo.com/yahoo_finance/
    //Empresa company = getCompanyData("RMG.L");

    //Neste caso .LS é para a bolsa de Lisboa
    //Empresa company = getCompanyData("SON.LS");
    //System.out.println(company.getStockExchange()+","+company.getRevenue());

    Empresa company = getCompanyData("AAPL");
   System.out.println(company.getPegRatio()+";"+company.getPeRatio()+";"+company.getPriceBook()+";"+company.getPriceSales()+";"+company.getEpsEstimCurYear()+"#"+company.getEpsEstimNextYear()+";"+company.getEarningsPerShare()+";"+
   company.getDividendPerShare()+";"+company.getOpen()+"#"+company.getPreviousOpen()+","+company.getPercentChange200()+"#"+company.getPercentChange50()+
   "#"+company.getChangeInPercent()+";"+company.getHoldingGainPercent()+";"+company.getTickerTrend());


//    String x = company.toString();
//    System.out.println(x);
  }

}
