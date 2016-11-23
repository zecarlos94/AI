import jade.content.Concept;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marioferreira on 23/11/16.
 */

public class Company implements Concept {

    private List<String>   companyOwners;
    private double stock;
    private int companyCapital;
    private int year;
    private List<String> stockExchangeName;
    private String companyExchangeNname;
    private String companyIndustry;
    private int stockAvailable; // variável para simulação
    // Pricing
    private double ask; // a
    private double bid; // b
    private double askRT; // b2
    private double bidRT; // b3
    private double previousOpen; // p
    private double open; // o
    // Dividends
    private double dividendPerShare; // d
    // Date
    private double change; // c1
    private double changeRT; // c6
    private double changePercentRT; // k2
    private double changeInPercent; // p2
    private String lastTradeDate; // d1
    private String tradeDate; // d2
    private String lastTradeTime; // t1
    // Averages
    private double afterHoursChangeRT; // c8
    private double comission; // c3
    private double dayLow; // g
    private double dayHigh; // h
    private String lastTradeRTwTime; // k1
    private String lastTradewTime; // l
    private double percentChange200; // m6
    private double percentChange50; // m8
    // Misc
    private double dayValueChange; // w1
    private double dayValueChangeRT; // w4
    private double pricePaid; // p1
    //private double dayRange; // m DELETED (dá dayLow e dayHigh)
    //private double dayRangeRT; // m2 DELETED (dá dayLowRT e dayHighRT)
    private double holdingGainPercent; // g1
    private double annualizedGain; // g3
    private double holdingGainPercentRT; // g5
    private double tickerTrend; // t7
    private double orderBookRT; // i5
    private double highLimit; // l2
    private double lowLimit; // l3
    private double holdingsValue; // v1
    private double holdingValueRT; // v7
    private String revenue; // s6
    // Week Pricing
    private double weekHigh; // k
    private double weekLow; // j
    private double percentChange52low; // j6
    private double percentChange52high; // k5
    // private double weekRange; // w DELETED
    // Symbol info
    private String currency; // c4
    private String marketCap; // j1
    private String marketCapRT; // j3
    private String name; // n
    private String notes; // n4
    private String symbol; // s
    private double sharesOwned; // s1
    private String stockExchange; // x
    private double sharesOutsta; // j2
    // Volume
    private double volume; // v
    private double askSize; // a5
    private double bidSize; // b6
    private double lastTradeSize; // k3
    private double averageDailyVolume; // a2
    // Ratios
    private double earningsPerShare; // e
    private double epsEstimCurYear; // e7
    private double epsEstimNextYear; // e8
    private double epsEstimNextQuarter; // e9
    private double bookBalue; //b4
    private String ebitda; // j4
    private double priceSales; // p5
    private double priceBook; // p6
    private double peRatio; // r
    private double peRatioRT; // r2
    private double pegRatio; // r5
    private double priceEpsEstCurYear; // r6
    private double priceEpsEstNextYear; // r7
    private double shortRatio; // s7

    // Construtores

    public Company (String symbol, int a) {
        this.symbol = symbol;
    }

    public Company (String a) {
        String[] empresa = a.split("#");
        for (int i=0;i<empresa.length;i++){
            switch (i){
                case 0:String [] sen = empresa[i].split(";");
                    this.stockExchangeName= new ArrayList<String>();
                    for (String s : sen){
                        stockExchangeName.add(s);
                    }
                    break;
                case 1:String [] co = empresa[i].split(";");
                    this.companyOwners= new ArrayList<String>();
                    for (String s : co){
                        companyOwners.add(s);
                    }
                    break;
                case 2: this.companyExchangeNname = empresa[i];
                    break;
                case 3: this.companyIndustry = empresa[i];
                    break;
                case 4: this.stockAvailable = Integer.parseInt(empresa[i]);
                    break;
                case 5: this.currency= empresa[i];
                    break;
                case 6: this.year = Integer.parseInt(empresa[i]);
                    break;
                case 7: this.stock = Integer.parseInt(empresa[i]);
                    break;
                case 8: this.companyCapital = Integer.parseInt(empresa[i]);
                    break;
                case 9: this.ask = Double.parseDouble(empresa[i]);
                    break;
                case 10: this.bid = Double.parseDouble(empresa[i]);
                    break;
                case 11: this.askRT = Double.parseDouble(empresa[i]);
                    break;
                case 12: this.bidRT = Double.parseDouble(empresa[i]);
                    break;
                case 13: this.previousOpen = Double.parseDouble(empresa[i]);
                    break;
                case 14: this.open = Double.parseDouble(empresa[i]);
                    break;
                case 15: this.dividendPerShare = Double.parseDouble(empresa[i]);
                    break;
                case 16: this.change = Double.parseDouble(empresa[i]);
                    break;
                case 17: this.changeRT = Double.parseDouble(empresa[i]);
                    break;
                case 18: this.changePercentRT = Double.parseDouble(empresa[i]);
                    break;
                case 19: this.changeInPercent = Double.parseDouble(empresa[i]);
                    break;
                case 20: this.lastTradeDate = empresa[i];
                    break;
                case 21: this.tradeDate = empresa[i];
                    break;
                case 22: this.lastTradeTime = empresa[i];
                    break;
                case 23: this.afterHoursChangeRT = Double.parseDouble(empresa[i]);
                    break;
                case 24: this.comission = Double.parseDouble(empresa[i]);
                    break;
                case 25: this.dayLow = Double.parseDouble(empresa[i]);
                    break;
                case 26: this.dayHigh = Double.parseDouble(empresa[i]);
                    break;
                case 27: this.lastTradeRTwTime = empresa[i];
                    break;
                case 28: this.lastTradewTime = empresa[i];
                    break;
                case 29: this.percentChange200 = Double.parseDouble(empresa[i]);
                    break;
                case 30: this.percentChange50 = Double.parseDouble(empresa[i]);
                    break;
                case 31: this.dayValueChange = Double.parseDouble(empresa[i]);
                    break;
                case 32: this.dayValueChangeRT = Double.parseDouble(empresa[i]);
                    break;
                case 33: this.pricePaid = Double.parseDouble(empresa[i]);
                    break;
                case 34: this.holdingGainPercent = Double.parseDouble(empresa[i]);
                    break;
                case 35: this.annualizedGain = Double.parseDouble(empresa[i]);
                    break;
                case 36: this.holdingGainPercentRT = Double.parseDouble(empresa[i]);
                    break;
                case 37: this.tickerTrend = Double.parseDouble(empresa[i]);
                    break;
                case 38: this.orderBookRT = Double.parseDouble(empresa[i]);
                    break;
                case 39: this.highLimit = Double.parseDouble(empresa[i]);
                    break;
                case 40: this.lowLimit = Double.parseDouble(empresa[i]);
                    break;
                case 41: this.holdingsValue = Double.parseDouble(empresa[i]);
                    break;
                case 42: this.holdingValueRT = Double.parseDouble(empresa[i]);
                    break;
                case 43: this.revenue = empresa[i];
                    break;
                case 44: this.weekHigh = Double.parseDouble(empresa[i]);
                    break;
                case 45: this.weekLow = Double.parseDouble(empresa[i]);
                    break;
                case 46: this.percentChange52low = Double.parseDouble(empresa[i]);
                    break;
                case 47: this.percentChange52high = Double.parseDouble(empresa[i]);
                    break;
                case 48: this.currency = empresa[i];
                    break;
                case 49: this.marketCap = empresa[i];
                    break;
                case 50: this.marketCapRT = empresa[i];
                    break;
                case 51: this.name = empresa[i];
                    break;
                case 52: this.notes = empresa[i];
                    break;
                case 53: this.symbol = empresa[i];
                    break;
                case 54: this.sharesOwned = Double.parseDouble(empresa[i]);
                    break;
                case 55: this.stockExchange = empresa[i];
                    break;
                case 56: this.sharesOutsta = Double.parseDouble(empresa[i]);
                    break;
                case 57: this.volume = Double.parseDouble(empresa[i]);
                    break;
                case 58: this.askSize = Double.parseDouble(empresa[i]);
                    break;
                case 59: this.bidSize = Double.parseDouble(empresa[i]);
                    break;
                case 60: this.lastTradeSize = Double.parseDouble(empresa[i]);
                    break;
                case 61: this.averageDailyVolume = Double.parseDouble(empresa[i]);
                    break;
                case 62: this.earningsPerShare = Double.parseDouble(empresa[i]);
                    break;
                case 63: this.epsEstimCurYear = Double.parseDouble(empresa[i]);
                    break;
                case 64: this.epsEstimNextYear = Double.parseDouble(empresa[i]);
                    break;
                case 65: this.epsEstimNextQuarter = Double.parseDouble(empresa[i]);
                    break;
                case 66: this.bookBalue = Double.parseDouble(empresa[i]);
                    break;
                case 67: this.ebitda = empresa[i];
                    break;
                case 68: this.priceSales = Double.parseDouble(empresa[i]);
                    break;
                case 69: this.priceBook = Double.parseDouble(empresa[i]);
                    break;
                case 70: this.peRatio = Double.parseDouble(empresa[i]);
                    break;
                case 71: this.peRatioRT = Double.parseDouble(empresa[i]);
                    break;
                case 72: this.pegRatio = Double.parseDouble(empresa[i]);
                    break;
                case 73: this.priceEpsEstCurYear = Double.parseDouble(empresa[i]);
                    break;
                case 74: this.priceEpsEstNextYear = Double.parseDouble(empresa[i]);
                    break;
                case 75: this.shortRatio = Double.parseDouble(empresa[i]);
                    break;
            }
        }
    }

    // Getters e Setters

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAskRT() {
        return askRT;
    }

    public void setAskRT(double askRT) {
        this.askRT = askRT;
    }

    public double getBidRT() {
        return bidRT;
    }

    public void setBidRT(double bidRT) {
        this.bidRT = bidRT;
    }

    public double getPreviousOpen() {
        return previousOpen;
    }

    public void setPreviousOpen(double previousOpen) {
        this.previousOpen = previousOpen;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getDividendPerShare() {
        return dividendPerShare;
    }

    public void setDividendPerShare(double dividendPerShare) {
        this.dividendPerShare = dividendPerShare;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getChangeRT() {
        return changeRT;
    }

    public void setChangeRT(double changeRT) {
        this.changeRT = changeRT;
    }

    public double getChangePercentRT() {
        return changePercentRT;
    }

    public void setChangePercentRT(double changePercentRT) {
        this.changePercentRT = changePercentRT;
    }

    public double getChangeInPercent() {
        return changeInPercent;
    }

    public void setChangeInPercent(double changeInPercent) {
        this.changeInPercent = changeInPercent;
    }

    public String getLastTradeDate() {
        return lastTradeDate;
    }

    public void setLastTradeDate(String lastTradeDate) {
        this.lastTradeDate = lastTradeDate;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(String lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public double getAfterHoursChangeRT() {
        return afterHoursChangeRT;
    }

    public void setAfterHoursChangeRT(double afterHoursChangeRT) {
        this.afterHoursChangeRT = afterHoursChangeRT;
    }

    public double getComission() {
        return comission;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

    public double getDayLow() {
        return dayLow;
    }

    public void setDayLow(double dayLow) {
        this.dayLow = dayLow;
    }

    public double getDayHigh() {
        return dayHigh;
    }

    public void setDayHigh(double dayHigh) {
        this.dayHigh = dayHigh;
    }

    public String getLastTradeRTwTime() {
        return lastTradeRTwTime;
    }

    public void setLastTradeRTwTime(String lastTradeRTwTime) {
        this.lastTradeRTwTime = lastTradeRTwTime;
    }

    public String getLastTradewTime() {
        return lastTradewTime;
    }

    public void setLastTradewTime(String lastTradewTime) {
        this.lastTradewTime = lastTradewTime;
    }

    public double getPercentChange200() {
        return percentChange200;
    }

    public void setPercentChange200(double percentChange200) {
        this.percentChange200 = percentChange200;
    }

    public double getPercentChange50() {
        return percentChange50;
    }

    public void setPercentChange50(double percentChange50) {
        this.percentChange50 = percentChange50;
    }

    public double getDayValueChange() {
        return dayValueChange;
    }

    public void setDayValueChange(double dayValueChange) {
        this.dayValueChange = dayValueChange;
    }

    public double getDayValueChangeRT() {
        return dayValueChangeRT;
    }

    public void setDayValueChangeRT(double dayValueChangeRT) {
        this.dayValueChangeRT = dayValueChangeRT;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public double getHoldingGainPercent() {
        return holdingGainPercent;
    }

    public void setHoldingGainPercent(double holdingGainPercent) {
        this.holdingGainPercent = holdingGainPercent;
    }

    public double getAnnualizedGain() {
        return annualizedGain;
    }

    public void setAnnualizedGain(double annualizedGain) {
        this.annualizedGain = annualizedGain;
    }

    public double getHoldingGainPercentRT() {
        return holdingGainPercentRT;
    }

    public void setHoldingGainPercentRT(double holdingGainPercentRT) {
        this.holdingGainPercentRT = holdingGainPercentRT;
    }

    public double getTickerTrend() {
        return tickerTrend;
    }

    public void setTickerTrend(double tickerTrend) {
        this.tickerTrend = tickerTrend;
    }

    public double getOrderBookRT() {
        return orderBookRT;
    }

    public void setOrderBookRT(double orderBookRT) {
        this.orderBookRT = orderBookRT;
    }

    public double getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(double highLimit) {
        this.highLimit = highLimit;
    }

    public double getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(double lowLimit) {
        this.lowLimit = lowLimit;
    }

    public double getHoldingsValue() {
        return holdingsValue;
    }

    public void setHoldingsValue(double holdingsValue) {
        this.holdingsValue = holdingsValue;
    }

    public double getHoldingValueRT() {
        return holdingValueRT;
    }

    public void setHoldingValueRT(double holdingValueRT) {
        this.holdingValueRT = holdingValueRT;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public double getWeekHigh() {
        return weekHigh;
    }

    public void setWeekHigh(double weekHigh) {
        this.weekHigh = weekHigh;
    }

    public double getWeekLow() {
        return weekLow;
    }

    public void setWeekLow(double weekLow) {
        this.weekLow = weekLow;
    }

    public double getPercentChange52low() {
        return percentChange52low;
    }

    public void setPercentChange52low(double percentChange52low) {
        this.percentChange52low = percentChange52low;
    }

    public double getPercentChange52high() {
        return percentChange52high;
    }

    public void setPercentChange52high(double percentChange52high) {
        this.percentChange52high = percentChange52high;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }

    public String getMarketCapRT() {
        return marketCapRT;
    }

    public void setMarketCapRT(String marketCapRT) {
        this.marketCapRT = marketCapRT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getSharesOwned() {
        return sharesOwned;
    }

    public void setSharesOwned(double sharesOwned) {
        this.sharesOwned = sharesOwned;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public double getSharesOutsta() {
        return sharesOutsta;
    }

    public void setSharesOutsta(double sharesOutsta) {
        this.sharesOutsta = sharesOutsta;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getAskSize() {
        return askSize;
    }

    public void setAskSize(double askSize) {
        this.askSize = askSize;
    }

    public double getBidSize() {
        return bidSize;
    }

    public void setBidSize(double bidSize) {
        this.bidSize = bidSize;
    }

    public double getLastTradeSize() {
        return lastTradeSize;
    }

    public void setLastTradeSize(double lastTradeSize) {
        this.lastTradeSize = lastTradeSize;
    }

    public double getAverageDailyVolume() {
        return averageDailyVolume;
    }

    public void setAverageDailyVolume(double averageDailyVolume) {
        this.averageDailyVolume = averageDailyVolume;
    }

    public double getEarningsPerShare() {
        return earningsPerShare;
    }

    public void setEarningsPerShare(double earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }

    public double getEpsEstimCurYear() {
        return epsEstimCurYear;
    }

    public void setEpsEstimCurYear(double epsEstimCurYear) {
        this.epsEstimCurYear = epsEstimCurYear;
    }

    public double getEpsEstimNextYear() {
        return epsEstimNextYear;
    }

    public void setEpsEstimNextYear(double epsEstimNextYear) {
        this.epsEstimNextYear = epsEstimNextYear;
    }

    public double getEpsEstimNextQuarter() {
        return epsEstimNextQuarter;
    }

    public void setEpsEstimNextQuarter(double epsEstimNextQuarter) {
        this.epsEstimNextQuarter = epsEstimNextQuarter;
    }

    public double getBookBalue() {
        return bookBalue;
    }

    public void setBookBalue(double bookBalue) {
        this.bookBalue = bookBalue;
    }

    public String getEbitda() {
        return ebitda;
    }

    public void setEbitda(String ebitda) {
        this.ebitda = ebitda;
    }

    public double getPriceSales() {
        return priceSales;
    }

    public void setPriceSales(double priceSales) {
        this.priceSales = priceSales;
    }

    public double getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(double priceBook) {
        this.priceBook = priceBook;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }

    public double getPeRatioRT() {
        return peRatioRT;
    }

    public void setPeRatioRT(double peRatioRT) {
        this.peRatioRT = peRatioRT;
    }

    public double getPegRatio() {
        return pegRatio;
    }

    public void setPegRatio(double pegRatio) {
        this.pegRatio = pegRatio;
    }

    public double getPriceEpsEstCurYear() {
        return priceEpsEstCurYear;
    }

    public void setPriceEpsEstCurYear(double priceEpsEstCurYear) {
        this.priceEpsEstCurYear = priceEpsEstCurYear;
    }

    public double getPriceEpsEstNextYear() {
        return priceEpsEstNextYear;
    }

    public void setPriceEpsEstNextYear(double priceEpsEstNextYear) {
        this.priceEpsEstNextYear = priceEpsEstNextYear;
    }

    public double getShortRatio() {
        return shortRatio;
    }

    public void setShortRatio(double shortRatio) {
        this.shortRatio = shortRatio;
    }

    // GETTERS
    public String getCompanyExchangeNname() { return this.companyExchangeNname; }
    public List<String> getStockExchangeName(){
        return this.stockExchangeName;
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
    public int getYear(){
        return this.year;
    }
    public double getStock(){
        return this.stock;
    }
    public int getCompanyCapital(){
        return this.companyCapital;
    }

    // SETTERS
    public void setCompanyExchangeNname(String c) { this.companyExchangeNname=c; }
    public void setStockExchangeName(List<String> sen){
        this.stockExchangeName.addAll(sen);
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
    public void setYear(int y){
        this.year=y;
    }
    public void setStock(int s){
        this.stock=s;
    }
    public void setCompanyCapital(int cc){
        this.companyCapital=cc;
    }

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder();
        int i =0;
        for (String s : stockExchangeName)
            if (i==0) {
                a.append(s);
                i=1;
            } else a.append(";"+s);
        i=0;a.append("#");
        for (String s : companyOwners)
            if (i==0){
                a.append(s);
                i=1;
            } else a.append(";"+s);
        a.append("#");
        a.append(companyExchangeNname+"#");
        a.append(companyIndustry+"#");
        a.append(stockAvailable+"#");
        a.append(year+"#");
        a.append(stock+"#");
        a.append(companyCapital+"#");
        String str = ask + "#" + bid + "#" + askRT + "#" + bidRT + "#" + previousOpen + "#" + open + "#" + dividendPerShare + "#" + change + "#" + changeRT + "#" + changePercentRT + "#" + changeInPercent + "#" + lastTradeDate + "#" + tradeDate + "#" + lastTradeTime + "#" + afterHoursChangeRT + "#" + comission + "#" + dayLow + "#" + dayHigh + "#" + lastTradeRTwTime + "#" + lastTradewTime + "#" + percentChange200 + "#" + percentChange50 + "#" + dayValueChange + "#" + dayValueChangeRT + "#" + pricePaid + "#" + holdingGainPercent + "#" + annualizedGain + "#" + holdingGainPercentRT + "#" + tickerTrend + "#" + orderBookRT + "#" + highLimit + "#" + lowLimit + "#" + holdingsValue + "#" + holdingValueRT + "#" + revenue + "#" + weekHigh + "#" + weekLow + "#" + percentChange52low + "#" + percentChange52high + "#" + currency + "#" + marketCap + "#" + marketCapRT + "#" + name + "#" + notes + "#" + symbol + "#" + sharesOwned + "#" + stockExchange + "#" + sharesOutsta + "#" + volume + "#" + askSize + "#" + bidSize + "#" + lastTradeSize + "#" + averageDailyVolume + "#" + earningsPerShare + "#" + epsEstimCurYear + "#" + epsEstimNextYear + "#" + epsEstimNextQuarter + "#" + bookBalue + "#" + ebitda + "#" + priceSales + "#" + priceBook + "#" + peRatio + "#" + peRatioRT + "#" + pegRatio + "#" + priceEpsEstCurYear + "#" + priceEpsEstNextYear + "#" + shortRatio ;
        a.append(str);
        return a.toString();
    }

}
