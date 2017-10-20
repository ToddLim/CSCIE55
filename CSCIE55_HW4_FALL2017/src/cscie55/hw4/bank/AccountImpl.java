package cscie55.hw4.bank;

/**
 * IMPLEMENTATION
 * CLASS: ACCOUNTIMPL
 *
 * Copy Account into your project and then define a class implementing this interface, named AccountImpl. Balances are
 * of type long, (you can think of the balance as tracking pennies instead of dollars). Your implementation should
 * throw InsufficientFundsException if withdraw attempts to withdraw an amount exceeding the Account's balance.
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw4Fall2017/hw4Fall2017.html
 * Last Accessed: 19 October 2017 @ 12:15 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

//@see Account
public class AccountImpl implements Account {

    //private long field that stores balance of an account; it is set to 0
    private long balance = 0;
    //private final integer field that stores the id of an account
    private final int id;

    /**
     * CONSTRUCTOR: ACCOUNTIMPL
     *
     * -initializes object of type: 'int' through instantiation
     *
     * @param id - integer value for uniquely identifying accounts
     */

    public AccountImpl(int id) {
        this.id = id;
    }

    /**
     * METHOD: BALANCE
     *
     * @return balance - amount left on account after transaction or lack thereof
     */
    @Override
    public long balance() {
        return balance;
    }

    /**
     * METHOD: ID
     *
     * @return id - the unique identifier for an account
     */
    @Override
    public int id() {
        return id;
    }

    /**
     * METHOD: DEPOSIT
     *
     * -handle exceptions for deposit amounts less than or equal to 0
     * -add amount to balance for valid parameter
     * -handle exceptions for overflows when balance is less than 0
     *
     * @param amount - long value that is added to account (needs to be more than 0)
     */
    @Override
    public void deposit(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    String.format("The amount, %s, is an incorrect value for deposit. Needs to be more than 0", amount)
            );
        }
        balance += amount;
        if (balance < 0) {
            throw new RuntimeException(
                    "There is an overflow detected on account: " + this.id()
            );
        }
    }

    /**
     * METHOD: WITHDRAW
     *
     * -handles exceptions for withdraw amounts less than or equal to 0
     * -handles exceptions for insufficient funds when the withdraw amount is greater than the balance
     * -subtract amount from balance for valid parameter
     *
     * @param amount
     * @throws InsufficientFundsException
     */
    @Override
    public void withdraw(long amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                    String.format("The amount, %s, is an incorrect value for deposit. Needs to be more than 0", amount)
            );
        }
        if (balance - amount < 0) {
            throw new InsufficientFundsException(this, amount);
        }
        balance -= amount;
    }
}
