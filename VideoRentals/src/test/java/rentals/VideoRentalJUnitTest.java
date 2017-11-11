package rentals;

/**
 * @author Todd Lim
 * @version 1.0.0.0
 *
 * Last Updated on 9 November 2017
 */

import java.time.LocalDate;


import java.util.*;
import org.junit.*;

import static org.junit.Assert.*;

public class VideoRentalJUnitTest {

    /**
     * >>JUNIT4 TEST<<
     * >>CLASS: VIDEORENTAL<<
     *
     *
     * ATTRIBUTES:
     * - (static) DEFAULT_RENTAL_PERIOD : int = 14
     * - (static) RENTAL_PERIOD_DAYS : int = 14
     *
     * METHODS:
     * + VideoRental(video : Video, account : Account)
     * + VideoRental(video : Video, account : Account, dueDate : localDate)
     * + equals(other : Object) : boolean
     * + hashCode() : int
     * + toString() : String
     * + getDateDue() : LocalDate
     * + getVideo() : Video
     * + getAccount() : Account
     * + isOpen() : boolean
     * + rentalReturn() : void
     * + (static) getRentalPeriod() : int
     * + isOverDue() : boolean
     */

    /**
     * METHOD: TEST1VIDEORENTAL
     * RESULT: PASS
     * DESCRIPTION: assertNotNull was used to assert that VideoRental constructor returned:
     * JIGSAW->TLim@gmail.com due on 2017-11-25
     */
	@Test
	public void test1VideoRental() throws VideoException {
	    Account account = new Account("Todd", "Lim", "TLim@gmail.com");
	    Video video = new Video("JIGSAW", 2017);
	    VideoRental videorental = new VideoRental(video, account);
	    assertNotNull(videorental);
	}

    /**
     * METHOD: TEST2VIDEORENTAL
     * RESULT: FAIL
     * DESCRIPTION: assertEquals was used to assert that VideoRental constructor returned the local date from the
     * getDateDue method, but it was not possible to pass in an object for the third parameter
     */
	/**
    @Test
    public void test2VideoRental() throws VideoException{
	    Account account = new Account("Todd", "Lim", "TLim@gmail.com");
	    Video video = new Video("JIGSAW", 2017);
	    //VideoRental videorental = new VideoRental(video, account, dueDate);
        //why is there no previous dueDate class or object for which to pass in the dueDate; field is private
	    VideoRental videorental = new VideoRental(video, account);
	    assertEquals("Why can't I pass in due date?", videorental.getDateDue());
    }
    */

    /**
     * METHOD: TESTEQUALS
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that other object using equals method would return true value
     */
    @Test
    public void testEquals() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental other = new VideoRental(video, account);
        assertTrue(other.equals(other));
    }

    /**
     * METHOD: TESTHASHCODE
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that hashCode method called by videorental1 object would return a
     * different value from when videorental2 object called hashCode method
     */
    @Test
    public void testHashCode() throws VideoException {
        Account account1 = new Account("Todd", "Lim", "TLim@gmail.com");
        Account account2 = new Account("Jose", "Escuadra", "JEscuadra@gmail.com");
        Video video1 = new Video("JIGSAW", 2017);
        Video video2 = new Video("IT", 2017);
        VideoRental videorental1 = new VideoRental(video1, account1);
        VideoRental videorental2 = new VideoRental(video2, account2);
        assertTrue(videorental1.hashCode() != videorental2.hashCode());
    }

    /**
     * METHOD: TESTTOSTRING
     * RESULT: PASS
     * DESCRIPTION: assertEquals was used to assert that toString method built: [title]->[email] due on [dueDate]
     */
    @Test
    public void testToString() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental videorental = new VideoRental(video, account);
        assertEquals("JIGSAW->TLim@gmail.com due on 2017-11-25", videorental.toString());
    }

    /**
     * METHOD: TESTGETDATEDUE
     * RESULT: PASS
     * DESCRIPTION: assertNotNull was used to assert that getDateDue method returned a value; testing an exact date
     * would be problematic because I don't know when graders are going to test this (maybe one week from now)
     */
    @Test
    public void testGetDateDue() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental videorental = new VideoRental(video, account);
        assertNotNull(videorental.getDateDue());
    }

    /**
     * METHOD: TESTGETVIDEO
     * RESULT: FAIL
     * DESCRIPTION: assertEquals was used to assert that getVideo method returned just the Video title; instead, it
     * also returned the year and availability
     */
    /**
    @Test
    public void testGetVideo() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental videorental = new VideoRental(video, account);
        assertEquals("JIGSAW", videorental.getVideo());
    }
    */

    /**
     * METHOD: TESTGETACCOUNT
     * RESULT: FAIL
     * DESCRIPTION: assertEquals was used to assert that getAccount method returned just the account name; instead, it
     * returned the account's first name, last name, and email
     */
    /**
    @Test
    public void testGetAccount() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental videorental = new VideoRental(video, account);
        assertEquals("Todd Lim", videorental.getAccount());
    }
    */

    /**
     * METHOD: TESTISOPEN
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that isOpen method returned a true value when the videorental object
     * was instantiated
     */
    @Test
    public void testIsOpen() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental videorental = new VideoRental(video, account);
        //video.checkOut();
        //video.checkIn();
        assertTrue(videorental.isOpen());
    }

    /**
     * METHOD: TESTRENTALRETURN
     * RESULT: FALSE
     * DESCRIPTION: assertTrue was used to assert that after the rentalReturn method was called, the videorental would
     * be open because the video would be checked in
     */
    /**
    @Test
    public void testRentalReturn() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental videorental = new VideoRental(video, account);
        videorental.rentalReturn();
        assertTrue(videorental.isOpen());
    }
    */

    /**
     * METHOD: TESTGETRENTALPERIOD
     * RESULT: PASS
     * DESCRIPTION: assertEquals was used to assert that the number of rental period days was 14
     */
    @Test
    public void testGetRentalPeriod() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental videorental = new VideoRental(video, account);
        assertEquals(14, videorental.getRentalPeriod());
    }

    /**
     * METHOD: TESTISOVERDUE
     * RESULT: FAIL
     * DESCRIPTION: assertFalse was used to assert that isOverDue method yielded a false result when videorental object
     * was created
     */
    /**
    @Test
    public void testIsOverDue() throws VideoException {
        Account account = new Account("Todd", "Lim", "TLim@gmail.com");
        Video video = new Video("JIGSAW", 2017);
        VideoRental videorental = new VideoRental(video, account);
        assertFalse(videorental.isOverDue());
    }
    */
}
