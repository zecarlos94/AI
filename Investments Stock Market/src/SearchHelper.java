import java.util.regex.Pattern;

public class SearchHelper {

  public SearchHelper() {}

	public double handleDouble(String x) {
		Double y;
		if (Pattern.matches("N/A", x))
			y = 0.00;
		else if (Pattern.matches("nan", x))
			y = 0.00;
		else if (x.equals("+inf"))
			y = 999999999999999999999999999999999.00;
		else if (x.equals("-inf"))
			y = -999999999999999999999999999999999.00;
		else
			y = Double.parseDouble(x);
		return y;
	}

	public int handleInt(String x) {
		int y;
		if (Pattern.matches("N/A", x))
			y = 0;
    else
			y = Integer.parseInt(x);
		return y;
	}

}
