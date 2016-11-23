public class MarketDataBean {

	String	symbol;
	double	price;
	double	change;
	double	volume;
	long	lastUpdate;
  String tradeDay;
  String tradeHour;
  double open;
  double high;
  double low;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

  public double getVolume() {
    return volume;
  }

  public void setVolume(double volume) {
    this.volume = volume;
  }

  public long getLastUpdated() {
	  return lastUpdate;
	}

	public void setLastUpdated(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

  public String getTradeDay() {
    return tradeDay;
  }

  public void setTradeDay(String tradeDay) {
    this.tradeDay = tradeDay;
  }

  public String getTradeHour() {
    return tradeHour;
  }

  public void setTradeHour(String tradeHour) {
    this.tradeHour = tradeHour;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }
}
