import yahoofinance.Stock;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by josephthomaschaske on 10/17/15.
 */
public class Account {
    private double balance;
    private HashMap<String, StockItem> portfolio;
    /**
     * Default no argument constructor
     */
    public Account(){
        this.balance = 0;
        this.portfolio = new HashMap<String, StockItem>();
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
            return new Transaction(symbol, 0, 0, balance, portfolio);
        }
        Stock stock = new Stock(symbol);
        int shares = (int)Math.floor( maxAmount / stock.getQuote().getPrice().doubleValue());
        double total = shares * stock.getQuote().getPrice().doubleValue();
        removeFunds(total);
        StockItem stockItem = portfolio.get(symbol);
        if(stockItem != null){
            stockItem.shares += shares;
        }else{
            stockItem = new StockItem();
            stockItem.shares = shares;
            stockItem.symbol = symbol;
            portfolio.put(symbol, stockItem);
        }
        return new Transaction(symbol, shares, total, balance, portfolio);
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
        StockItem stockItem = portfolio.get(symbol);
        if(stockItem != null){
            stockItem.shares += shares;
        }else{
            stockItem = new StockItem();
            stockItem.shares = shares;
            stockItem.symbol = symbol;
            portfolio.put(symbol, stockItem);
        }
        return new Transaction(symbol, shares, total, balance, portfolio);
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
            return new Transaction(symbol, 0, 0, balance, portfolio);
        }
        removeFunds(total);
        StockItem stockItem = portfolio.get(symbol);
        if(stockItem != null){
            stockItem.shares += shares;
        }else{
            stockItem = new StockItem();
            stockItem.shares = shares;
            stockItem.symbol = symbol;
            portfolio.put(symbol, stockItem);
        }
        return new Transaction(symbol, shares, total, balance, portfolio);
    }



    public class Transaction{
        String symbol;
        int sharesBought;
        double total;
        double balance;
        HashMap<String, StockItem> portfolio;

        public Transaction(String symbol, int sharesBought, double total, double balance, HashMap<String, StockItem> portfolio){
            this.symbol = symbol;
            this.sharesBought = sharesBought;
            this.total = total;
            this.balance = balance;
            this.portfolio = portfolio;
        }
    }

    public class StockItem {
        private String symbol;
        private int shares;

        public StockItem(){

        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public int getShares() {
            return shares;
        }

        public void setShares(int shares) {
            this.shares = shares;
        }
    }

}
