import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * Created by josephthomaschaske on 10/11/15.
 */
public class Finance {
    public static void main(String [] args){
        try {
            Stock stock = YahooFinance.get("MSFT");
            BigDecimal price = stock.getQuote().getPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
