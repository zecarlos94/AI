package OntologiaAplicada;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;


/**
 * @author Christian Fries
 */
public class YahooQuoteFetcher {

	private static HashMap<String, MarketDataBean> stocks = new HashMap<String, MarketDataBean>();
	private long updateIntervall;

	public static void main(String[] args) {
		try {
			YahooQuoteFetcher fetcher = new YahooQuoteFetcher(5.0);
			MarketDataBean bean = fetcher.getData("TSLA");
		  System.out.println("TSLA -> Price: "+bean.getPrice()+" Change: "+bean.getChange()+" Volume: "+bean.getVolume()+" Last update: "+bean.getLastUpdated()+"\n Open: "+bean.getOpen()+" High: "+bean.getHigh()+" Low: "+bean.getLow());

      MarketDataBean bean1 = fetcher.getData("MSFT");
      System.out.println("MSFT -> Price: "+bean1.getPrice()+","+bean1.getSymbol());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

  public YahooQuoteFetcher(double updateIntervallInSeconds) {
	  super();
	  this.updateIntervall = (long)(updateIntervallInSeconds * 1000.0);
  }


	public MarketDataBean getData(String symbol) throws IOException {
		updateData(symbol);
		return stocks.get(symbol);
	}

	public synchronized void updateData(String symbol) throws IOException {

		// Check if we need to update
    MarketDataBean dataBean = stocks.get(symbol);
		if(dataBean != null && System.currentTimeMillis() - dataBean.getLastUpdated() < updateIntervall) return;

		/*
		 * Fetch CSV data from Yahoo. The format codes (f=) are:
		 * s=symbol, l1 = last, c1 = change, d1 = trade day, t1 = trade time, o = open, h = high, g = low, v = volume
		 */
    URL ulr = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=sl1c1vd1t1ohg&e=.csv");
    URLConnection urlConnection = ulr.openConnection();
    BufferedReader reader = null;
    try {
	    reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	    String inputLine;
	    while ((inputLine = reader.readLine()) != null) {
	      String[] yahooStockInfo = inputLine.split(",");
	      MarketDataBean stockInfo = new MarketDataBean();
	      stockInfo.setSymbol(yahooStockInfo[0].replaceAll("\"", ""));
	      stockInfo.setPrice(Double.valueOf(yahooStockInfo[1]));
	      stockInfo.setChange(Double.valueOf(yahooStockInfo[2]));
	      stockInfo.setVolume(Double.valueOf(yahooStockInfo[3]));
	      stockInfo.setLastUpdated(System.currentTimeMillis());
        stockInfo.setTradeDay(yahooStockInfo[4]);
        stockInfo.setTradeHour(yahooStockInfo[5]);
        stockInfo.setOpen(Double.valueOf(yahooStockInfo[6]));
        stockInfo.setHigh(Double.valueOf(yahooStockInfo[7]));
        stockInfo.setLow(Double.valueOf(yahooStockInfo[8]));
	      stocks.put(symbol, stockInfo);
	      break;
	    }
    }
    finally {
      if(reader != null) reader.close();
    }
  }
}
