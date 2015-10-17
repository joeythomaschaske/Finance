import java.math.BigDecimal;

/**
 * Created by josephthomaschaske on 10/17/15.
 */
public class Account {
    double balance;

    public Account(){
        this.balance = 0;
    }

    public void addFunds(double amount){
        BigDecimal a = new BigDecimal(amount);
        if(a.scale() > 2) {
            throw new IllegalArgumentException("Not a dollar decimal");
        }
        balance += amount;
    }

    public void removeFunds(double amount){
        BigDecimal a = new BigDecimal(amount);
        if(a.scale() > 2){
            throw new IllegalArgumentException("Not a dollar decimal");
        }
        if(amount > balance){
            throw new IllegalArgumentException("Amount to remove is greater than balance");
        }
    }
}
