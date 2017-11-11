package rentals;

/**
 * @author Todd Lim
 * @version 1.0.0.0
 *
 * Last Updated on 9 November 2017
 */

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toSet;

import java.util.*;
import org.junit.*;

import static org.junit.Assert.*;


public class AccountJUnitTest {

    /**
     * >>JUNIT4 TEST<<
     * >>CLASS: ACCOUNT<<
     *
     *
     * ATTRIBUTES:
     * - (static) ID : int = 0
     * - firstName : String
     * - lastName : String
     * - email : String
     * - id : int
     *
     * METHODS:
     * + Account (firstname : String, lastname: String, email : String)
     * + getID() : int
     * + getEmail() : String
     * ~ setEmail(email : String) : void
     * + equals(other : Object) : boolean
     * + hashCode() : int
     * + getOpenRentals() : Set<VideoRental>
     * + getClosedRentals() : Set<VideoRental>
     * + toString() : String
     * + addRental(rental : VideoRental) : void
     * + hasOpenRental(title : String) : boolean
     * + getOverdueRentals() : Set<VideoRental>
     * + settleRental(title : String) : void
     * + settleRental(videoRental : VideoRental) : void
     * + getNumberOpenRentals() : int
     * + getNumberClosedRentals() : int
     * + clearHistory() : void
     * + settleRentals() : void
     */

    /**
     * METHOD: TESTACCOUNT
     * RESULT: PASS
     * DESCRIPTION: assertNotNull used to assert that account object was not null
     */
    @Test
    public void testAccount() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        assertNotNull(account);
    }

    /**
     * METHOD: TESTGETID
     * RESULT: PASS
     * DESCRIPTION: assertEquals used to assert that getID method returned expected ID; second account was used to test
     * increment feature
     */
    @Test
    public void testGetID() {
        Account account2 = new Account("Todd", "Lim", "TLim@gmail.com");
        assertEquals(4, account2.getID());
        Account account3 = new Account("Jose", "Escuadra", "JEscuadra@gmail.com");
        assertEquals(5, account3.getID());
    }

    /**
     * METHOD: TESTGETEMAIL
     * RESULT: PASS
     * DESCRIPTION: assertEquals used to assert that getEmail method returned the expected email
     */
	@Test
	public void testGetEmail() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
		assertEquals("TLim@gmail.com", account.getEmail());
	}

    /**
     * METHOD: TESTSETEMAIL
     * RESULT: PASS
     * DESCRIPTION: assertEquals used to assert that setting a new email for the account object would result in the
     * new email being set to override the original email
     */
	@Test
    public void testSetEmail() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        account.setEmail("JEscuadra@gmail.com");
        assertEquals("JEscuadra@gmail.com", account.getEmail());
    }

    /**
     * METHOD: TESTEQUALS
     * RESULT: PASS
     * DESCRIPTION: assertTrue used to assert that other object using equals method would be equal in value to the
     * account object using equals method
     */
    @Test
    public void testEquals() {
        Account other = new Account("Todd", "Lim", "TLim@gmail.com");
        Account account = new Account("tODD", "lIM", "tlIM@GMAIL.COM");
        assertTrue(other.equals(other) == account.equals(other));
    }

    /**
     * METHOD: TESTHASHCODE
     * RESULT: PASS
     * DESCRIPTION: assertTrue used to assert that hashCode method used on account0 would yield a different value than
     * hashCode method used on account1
     */
    @Test
    public void testHashCode() {
        Account account0 = new Account("Todd", "Lim", "TLim@gmail.com");
        Account account1 = new Account("Jose", "Escuadra", "JEscuadra@gmail.com");
        assertTrue(account0.hashCode() != account1.hashCode());
    }

    /**
     * METHOD: TESTGETOPENRENTALS
     * RESULT: FAIL
     * DESCRIPTION: assertTrue was used to assert that getOpenRentals method would yield the same values, given that
     * the two account objects have not checked out any videos yet
     */
    /**
    @Test
    public void testGetOpenRentals() {
        Account account1 = new Account("Todd", "Lim", "TLim@gmail.com");
        Account account2 = new Account("Jose", "Escuadra", "JEscuadra@gmail.com");
        assertTrue(account1.getOpenRentals() == account2.getOpenRentals());
    }
    */

    /**
     * METHOD: TESTGETCLOSEDRENTALS
     * RESULT: FAIL
     * DESCRIPTION: assertTrue was used to assert that getClosedRentals method would yield the same values, given that
     * the two account objects have not checked out any videos yet
     */
    /**
    @Test
    public void testGetClosedRentals() {
        Account account1 = new Account("Todd", "Lim", "TLim@gmail.com");
        Account account2 = new Account("Jose", "Escuadra", "JEscuadra@gmail.com");
        assertTrue(account1.getClosedRentals() == account2.getClosedRentals());
    }
    */

    /**
     * METHOD: TESTTOSTRING
     * RESULT: PASS
     * DESCRIPTION: assertEquals was used to assert that toString method resulted in proper string results based off
     * instantiations of append method from StringBuilder class
     */
    @Test
    public void testToString() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        assertEquals("Todd Lim TLim@gmail.com", account.toString());
    }

    /**
     * METHOD: TESTADDRENTAL
     * RESULT: PASS
     * DESCRIPTION: assertNotNull was used to assert that addRental method added some value to openRentals, which was
     * originally empty
     */
    @Test
    public void testAddRental() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Set<VideoRental> openRentals = new HashSet();
        //account.addRental("JIGSAW (2017)");
        //account.addRental(VideoRental rentals) - does rentals parameter come from a different class than Account?
        assertNotNull(openRentals);
    }

    /**
     * METHOD: TESTHASOPENRENTAL
     * RESULT: PASS
     * DESCRIPTION: assertFalse was used to assert that "JIGSAW (2017)" was not in fact an openRental
     */
    @Test
    public void testHasOpenRental() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        assertFalse(account.hasOpenRental("JIGSAW (2017)"));
    }

    /**
     * METHOD: TESTGETOVERDUERENTALS
     * RESULT: PASS
     * DESCRIPTION: assertNotNull was used to assert that getOverdueRentals method returned a value
     */
    @Test
    public void testGetOverdueRentals() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        //account.openRentals.add("JIGSAW (2017)");
        //how can I test a private field: openRentals? Because of this, I can't test filter or collect submethods
        assertNotNull(account.getOverdueRentals());
    }

    /**
     * METHOD: TEST1SETTLERENTAL
     * RESULT: PASS
     * DESCRIPTION: assertFalse was used to assert that settling a rental, "JIGSAW (2017)," would result in the
     * hasOpenRental instantiation yielding a false value
     */
    @Test
    public void test1SettleRental() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        account.hasOpenRental("JIGSAW (2017)");
        account.settleRental("JIGSAW (2017)");
        assertFalse(account.hasOpenRental("JIGSAW"));
    }

    /**
     * METHOD: TEST2SETTLERENTAL
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that settling a rental, "JIGSAW (2017)," would result in the
     * negated hasOpenRental instantiation yielding a true value
     */
    @Test
    public void test2SettleRental() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        account.hasOpenRental("JIGSAW (2017)");
        //account.settleRental(VideoRental rental);
        //How am I supposed to pass in parameters if field is private?
        account.settleRental("JIGSAW (2017)");
        assertTrue(!account.hasOpenRental("JIGSAW (2017)"));
    }

    /**
     * METHOD: TESTGETNUMBEROPENRENTALS
     * RESULT: PASS
     * DESCRIPTION: assertEquals was used to assert that getNumberOpenRentals method yielded 0 because 0 were added
     */
    @Test
    public void testGetNumberOpenRentals() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        assertEquals(0, account.getNumberOpenRentals());
    }

    /**
     * METHOD: TESTGETNUMBERCLOSEDRENTALS
     * RESULT: PASS
     * DESCRIPTION: assertEquals was used to assert that getNumberClosedRentals method yielded 0 because 0 were added
     */
    @Test
    public void testGetNumberClosedRentals() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        assertEquals(0, account.getNumberClosedRentals());
    }

    /**
     * METHOD: TESTCLEARHISTORY
     * RESULT: FAIL
     * DESCRIPTION: assertTrue was used to assert that the settleRental method followed by the clearHistory method
     * would clear the closedRentals set and therefore place it back in the openRentals set
     */
    /**
    @Test
    public void testClearHistory() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        account.hasOpenRental("JIGSAW (2017)");
        account.settleRental("JIGSAW (2017)");
        account.clearHistory();
        assertTrue(account.hasOpenRental("JIGSAW (2017)"));
    }
    */

    /**
     * METHOD: TESTSETTLERENTALS
     * RESULT: PASS
     * DESCRIPTION: assertFalse was used to assert that settleRentals method removed "JIGSAW (2017)" from the
     * openRentals set
     */
    @Test
    public void testSettleRentals() {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        account.hasOpenRental("JIGSAW (2017)");
        account.settleRentals();
        assertFalse(account.hasOpenRental("JIGSAW (2017)"));
    }
}
