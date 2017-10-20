package cscie55.hw4.bank;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IMPLEMENTATION
 * CLASS: BANKIMPL
 *
 * Copy Bank into your project and then define a class implementing this interface, named BankImpl. Note that there are
 * three methods doing transfers between accounts:
 *
 * -transferWithoutLocking just calls withdraw on one account, and deposit on the other, without doing any
 *  synchronization. In other words, your implementation of BankImpl.transferWithoutLocking should not use the
 *  synchronized keyword at all. This is completely wrong, but will give you some idea of what happens when
 *  synchronization is missing.
 * -transferLockingBank does the transfer while synchronizing on the Bank object. This means that only one thread can
 *  do a transfer at any given moment. While this approach does not provide any concurrency, it should be correct.
 * -transferLockingAccounts does the transfer, locking just the two affected Accounts. Your implementation should use
 *  the synchronized keyword twice, once on each Account. This should provide for greater concurrency, because threads
 *  not touching the two Accounts will not be blocked. Think very carefully about how to lock the two Accounts.
 *
 * The supplied test code (cscie55.hw4.Tester) will call these methods to try different locking strategies.
 *
 * addAccount adds an Account to the Bank. No two Accounts should have the same id. If an Account is added with a
 * duplicate id, then addAccount must throw DuplicateAccountException.
 *
 * You can assume that the methods addAccount, totalBalances and numberOfAccounts do not require synchronization.
 *
 * Source: https://courses.dce.harvard.edu/~cscie55/hw4Fall2017/hw4Fall2017.html
 * Last Accessed: 19 October 2017 @ 12:15 CST
 *
 * @author Todd Lim
 * @version 1.0.0.0
 */

//@see Bank
public class BankImpl implements Bank {

    //ConcurrentHashMap is set up for existingAccounts, which contains key-value pair of integer-account
    Map<Integer,Account> existingAccounts = new ConcurrentHashMap<Integer,Account>();

    /**
     * METHOD: ADDACCOUNT
     *
     * -handles exceptions for duplicate accounts
     * -adds account to hash map via id, if account does not already exist
     *
     * @param account - instance of an Account object stored inside the hash map
     * @throws DuplicateAccountException - throws when account being added already exists
     */

    @Override
    public void addAccount(Account account) throws DuplicateAccountException {
        if(existingAccounts.containsKey(account.id())) {
            throw new DuplicateAccountException(account.id());
    }
    existingAccounts.put(new Integer(account.id()), account);
}

    /**
     * METHOD: TRANSFERWITHOUTLOCKING
     *
     * -does not synchronize for withdraw and deposit method calls
     * -uses separate threads instead
     *
     * @param fromId - account id for source of transfer
     * @param toId - account id for destination of transfer
     * @param amount - value that is being passed via the transfer method
     * @throws InsufficientFundsException - throws when attempt to withdraw x exceeds y from account
     */

    @Override
    public void transferWithoutLocking(int fromId, int toId, long amount)

        throws InsufficientFundsException {

        existingAccounts.get(fromId).withdraw(amount);
        existingAccounts.get(toId).deposit(amount);

    }

    /**
     * METHOD: TRANSFERLOCKINGBANK
     *
     * -synchronizes entire bank object for withdraw and deposit method calls
     * -does not provide concurrency
     *
     * @param fromId - account id for source of transfer
     * @param toId - account id for destination of transfer
     * @param amount - value that is being passed via the transfer method
     * @throws InsufficientFundsException - throws when attempt to withdraw x exceeds y from account
     */

    @Override
    public void transferLockingBank(int fromId, int toId, long amount) throws InsufficientFundsException {
        synchronized(this) {
            existingAccounts.get(fromId).withdraw(amount);
            existingAccounts.get(toId).deposit(amount);
        }
    }

    /**
     * METHOD: TRANSFERLOCKINGACCOUNTS
     *
     * -synchronizes the fromAccount and toAccount to execute transaction for withdraw and deposit method calls
     * -provides concurrency
     *
     * @param fromId - account id for source of transfer
     * @param toId - account id for destination of transfer
     * @param amount - value that is being passed via the transfer method
     * @throws InsufficientFundsException - throws when attempt to withdraw x exceeds y from account
     */

    @Override
    public void transferLockingAccounts(int fromId, int toId, long amount) throws InsufficientFundsException {
        Account fromAccount = existingAccounts.get(fromId);
        Account toAccount = existingAccounts.get(toId);
        synchronized(fromAccount) {
            fromAccount.withdraw(amount);
        }
        synchronized(toAccount) {
            toAccount.deposit(amount);
        }
    }

    /**
     * METHOD: TOTALBALANCES
     *
     * @return total - returns total, or sum, of balances from several accounts
     */

    @Override
    public long totalBalances() {
        long total = 0;
        for(Account account : existingAccounts.values()) {
            total += account.balance();
        }
        return total;
    }

    /**
     * METHOD: NUMBEROFACCOUNTS
     *
     * @return existingAccounts.keySet().size() - returns the size, or number, of existingAccounts for a unique id
     */

    @Override
    public int numberOfAccounts() {
        return existingAccounts.keySet().size();
    }
}
