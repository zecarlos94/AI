// RT -> RealTime

public class Company {
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

  public Company(String symbol) {
    this.symbol = symbol;
  }

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

  @Override
    public String toString() {
        return "Company{" + "ask=" + ask + "\n bid=" + bid + "\n askRT=" + askRT + "\n bidRT=" + bidRT + "\n previousOpen=" + previousOpen + "\n open=" + open + "\n dividendPerShare=" + dividendPerShare + "\n change=" + change + "\n changeRT=" + changeRT + "\n changePercentRT=" + changePercentRT + "\n changeInPercent=" + changeInPercent + "\n lastTradeDate=" + lastTradeDate + "\n tradeDate=" + tradeDate + "\n lastTradeTime=" + lastTradeTime + "\n afterHoursChangeRT=" + afterHoursChangeRT + "\n comission=" + comission + "\n dayLow=" + dayLow + "\n dayHigh=" + dayHigh + "\n lastTradeRTwTime=" + lastTradeRTwTime + "\n lastTradewTime=" + lastTradewTime + "\n percentChange200=" + percentChange200 + "\n percentChange50=" + percentChange50 + "\n dayValueChange=" + dayValueChange + "\n dayValueChangeRT=" + dayValueChangeRT + "\n pricePaid=" + pricePaid + "\n holdingGainPercent=" + holdingGainPercent + "\n annualizedGain=" + annualizedGain + "\n holdingGainPercentRT=" + holdingGainPercentRT + "\n tickerTrend=" + tickerTrend + "\n orderBookRT=" + orderBookRT + "\n highLimit=" + highLimit + "\n lowLimit=" + lowLimit + "\n holdingsValue=" + holdingsValue + "\n holdingValueRT=" + holdingValueRT + "\n revenue=" + revenue + "\n weekHigh=" + weekHigh + "\n weekLow=" + weekLow + "\n percentChange52low=" + percentChange52low + "\n percentChange52high=" + percentChange52high + "\n currency=" + currency + "\n marketCap=" + marketCap + "\n marketCapRT=" + marketCapRT + "\n name=" + name + "\n notes=" + notes + "\n symbol=" + symbol + "\n sharesOwned=" + sharesOwned + "\n stockExchange=" + stockExchange + "\n sharesOutsta=" + sharesOutsta + "\n volume=" + volume + "\n askSize=" + askSize + "\n bidSize=" + bidSize + "\n lastTradeSize=" + lastTradeSize + "\n averageDailyVolume=" + averageDailyVolume + "\n earningsPerShare=" + earningsPerShare + "\n epsEstimCurYear=" + epsEstimCurYear + "\n epsEstimNextYear=" + epsEstimNextYear + "\n epsEstimNextQuarter=" + epsEstimNextQuarter + "\n bookBalue=" + bookBalue + "\n ebitda=" + ebitda + "\n priceSales=" + priceSales + "\n priceBook=" + priceBook + "\n peRatio=" + peRatio + "\n peRatioRT=" + peRatioRT + "\n pegRatio=" + pegRatio + "\n priceEpsEstCurYear=" + priceEpsEstCurYear + "\n priceEpsEstNextYear=" + priceEpsEstNextYear + "\n shortRatio=" + shortRatio + '}';
    }

}
