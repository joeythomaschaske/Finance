/**
 * Created by josephthomaschaske on 10/17/15.
 */
class AccountTest extends GroovyTestCase {
    void testGetBalance() {
        Account test = new Account();
        test.addFunds(5000);
        assertEquals(5000, test.getBalance());
    }

    void testAddFunds() {
        Account test = new Account();
        test.addFunds(5000);
        assertEquals(5000, test.getBalance());
        test.addFunds(5.33342341341234);
        assertEquals(5000, test.getBalance());
    }

    void testRemoveFunds() {
        Account test = new Account();
        test.addFunds(5000);
        test.removeFunds(2000);
        assertEquals(3000, test.getBalance());
        test.removeFunds(4.56463463456);
        assertEquals(3000, test.getBalance());
        test.removeFunds(18000);
        assertEquals(3000, test.getBalance());
    }

    void testPlaceBuyOrder() {
        Account test = new Account();
        test.addFunds(5000);
        Account.Transaction t = test.placeBuyOrder("MSFT");
        assert(test.getBalance() < 5000);
    }

    void testPlaceBuyOrder1() {

    }

    void testPlaceBuyOrder2() {

    }
}
