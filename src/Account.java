import yahoofinance.Stock;

import java.math.BigDecimal;

/**
 * Created by josephthomaschaske on 10/17/15.
 */
public class Account {
    private double balance;


    /**
     * Default no argument constructor
     */
    public Account(){
        this.balance = 0;
    }

    /**
     * gets the current account balance
     * @return account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * adds funds to the balance
     * @param amount the amount to add to the balance
     * @return the new balance
     */
    public double addFunds(double amount){
        BigDecimal a = new BigDecimal(amount);
        if(a.scale() > 2) {
            return getBalance();
        }
        balance += amount;
        return getBalance();
    }

    /**
     * removes funds from the balance
     * @param amount the amount to remove from the balance
     * @return returns the new balance
     */
    public double removeFunds(double amount){
        BigDecimal a = new BigDecimal(amount);
        if(a.scale() > 2){
            return getBalance();
        }
        if(amount > getBalance()){
            return getBalance();
        }
        balance -= amount;
        return getBalance();
    }

    /**
     * buys the maximum amount of shares with the amount passed in
     * @param symbol the stock symbol of the stock to order
     * @param maxAmount the maximum dollar amount to spend on orders
     * @return returns a Transaction of the order
     */
    public Transaction placeBuyOrder(String symbol, double maxAmount){
        BigDecimal a = new BigDecimal(maxAmount);
        if(a.scale() > 2){
            return new Transaction(symbol, 0, 0, balance);
        }
        Stock stock = new Stock(symbol);
        int shares = (int)Math.floor( maxAmount / stock.getQuote().getPrice().doubleValue());
        double total = shares * stock.getQuote().getPrice().doubleValue();
        removeFunds(total);
        return new Transaction(symbol, shares, total, balance);
    }

    /**
     * orders the maximum amount of shares with the current balance
     * @param symbol
     * @return
     */
    public Transaction placeBuyOrder(String symbol){
        Stock stock = new Stock(symbol);
        int shares = (int)Math.floor( balance / stock.getQuote().getPrice().doubleValue());
        double total = shares * stock.getQuote().getPrice().doubleValue();
        removeFunds(total);
        return new Transaction(symbol, shares, total, balance);
    }

    /**
     * orders the specified amount of shares
     * @param symbol the stock symbol of the stock to order
     * @param shares the number of shares to purchase
     * @return a transaction of the order
     */
    public Transaction placeBuyOrder(String symbol, int shares){
        Stock stock = new Stock(symbol);
        BigDecimal stockPrice = stock.getQuote().getPrice();
        double total = (shares * stockPrice.doubleValue());
        if(total > balance){
            return new Transaction(symbol, 0, 0, balance);
        }
        removeFunds(total);
        return new Transaction(symbol, shares, total, balance);
    }


    public class Transaction{
        String symbol;
        int sharesBought;
        double total;
        double balance;

        public Transaction(String symbol, int sharesBought, double total, double balance){
            this.symbol = symbol;
            this.sharesBought = sharesBought;
            this.total = total;
            this.balance = balance;
        }
    }

}
