package rentals;

/**
 * @author Todd Lim
 * @version 1.0.0.0
 *
 * Last Updated on 9 November 2017
 */

import java.time.LocalDate;
import java.util.Comparator;

import java.util.*;
import org.junit.*;

import static org.junit.Assert.*;


public class VideoJUnitTest {

    /**
     * >>JUNIT4 TEST<<
     * >>CLASS: VIDEO<<
     *
     *
     * ATTRIBUTES:
     * - (static) EARLIEST_YEAR : int = 1900
     * - title : String
     * - year : Integer
     *
     * METHODS:
     * + Video(title : String, year : Integer)
     * + equals(other : Object) : boolean
     * + hashCode() : int
     * + compareTo(other : Object) : int
     * + getTitle() : String
     * + getYear() : Integer
     * + toString() : String
     * + isAvailable() : boolean
     * + isNotAvailable() : boolean
     * + checkOut() : void
     * + checkIn() : void
     * + removeFromStock() : void
     * + replaceToStock() : void
     */

    /**
     * METHOD: TESTVIDEO
     * RESULT: PASS
     * DESCRIPTION: assertNotNull was used to assert that video has passed values of String title and Integer year
     */
	@Test
	public void testVideo() throws VideoException {
	    Video video = new Video("JIGSAW", 2017);
	    assertNotNull(video);
	}

    /**
     * METHOD: TESTEQUALS
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that other object using equals method would return true value
     */
	@Test
    public void testEquals() throws VideoException {
            Video other = new Video("JIGSAW", 2017);
            assertTrue(other.equals(other));
    }

    /**
     * METHOD: TESTHASHCODE
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that hashCode method applied to video1 object would not equal the
     * hashCode method applied to video2 object because there are different titles, though same year (2017)
     */
    @Test
    public void testHashCode() throws VideoException{
        Video video1 = new Video("JIGSAW", 2017);
        Video video2 = new Video("IT", 2017);
        assertTrue(video1.hashCode() != video2.hashCode());
    }

    /**
     * METHOD: TESTCOMPARETO
     * RESULT: PASS
     * DESCRIPTION: assertNotNull was used to assert that the compareTo method returned a value back, though it was not
     * easy to figure out how to test the RuntimeException because of the Other object's class
     */
    @Test
    public void testCompareTo() throws VideoException{
        Video other = new Video("JIGSAW", 2017);
        assertNotNull(other.compareTo(other));
        //how to test RuntimeException if Other object is in the same class?
    }

    /**
     * METHOD: TESTGETTITLE
     * RESULT: PASS
     * DESCRIPTION: assertEquals was used to assert that the getTitle method returned the correct title: "JIGSAW"
     */
    @Test
    public void testGetTitle() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        assertEquals("JIGSAW", video.getTitle());
    }

    /**
     * METHOD: TESTGETYEAR
     * RESULT: PASS
     * DESCRIPTION: assertEquals was used to assert that the getYear method returned the correct year: 2017, though
     * it was perplexing as to why Optional.of() was needed
     */
    @Test
    public void testGetYear() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        assertEquals(Optional.of(2017), Optional.of(video.getYear()));
    }

    /**
     * METHOD: TESTTOSTRING
     * RESULT: PASS
     * DESCRIPTION: assertEquals was used to assert that proper spaces and casing was yielded for the toString method
     */
    @Test
    public void testToString() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        assertEquals("JIGSAW: 2017 is AVAILABLE", video.toString());
    }

    /**
     * METHOD: TESTISAVAILABLE
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that that boolean value for isAvailable method returned true when
     * the new video object was created
     */
    @Test
    public void testIsAvailable() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        assertTrue(video.isAvailable());
    }

    /**
     * METHOD: TESTISNOTAVAILABLE
     * RESULT: PASS
     * DESCRIPTION: assertFalse was used to assert that isNotAvailable method returned a false boolean value when the
     * new video object was created, which in the constructor set the availability to available
     */
    @Test
    public void testIsNotAvailable() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        assertFalse(video.isNotAvailable());
    }

    /**
     * METHOD: TESTCHECKOUT
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that checkOut method called by video object did in fact return a
     * false boolean value for isNotAvailable method called on same video object
     */
    @Test
    public void testCheckOut() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        video.checkOut();
        assertTrue(video.isNotAvailable());
    }

    /**
     * METHOD: TESTCHECKIN
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that the video was available after it called the checkOut method
     * followed by the checkIn method
     */
    @Test
    public void testCheckIn() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        video.checkOut();
        video.checkIn();
        assertTrue(video.isAvailable());
    }

    /**
     * METHOD: TESTREMOVEFROMSTOCK
     * RESULT: FAIL
     * DESCRIPTION: assertTrue was used to assert that when video object called removeFromStock method, there would be
     * a method that returned the OUT_OF_STOCK enum value; however, there was not and neither is it a public field
     */
    /**
    @Test
    public void testRemoveFromStock() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        //public boolean isOutOfStock = {return availability == Video.AVAILABILITY.OUT_OF_STOCK}
        //why no return method for returning OUT_OF_STOCK? I can't test because it's a private field
        video.removeFromStock();
        assertTrue(video.isAvailable());
    }
    */

    /**
     * METHOD: TESTREPLACETOSTOCK
     * RESULT: PASS
     * DESCRIPTION: assertTrue was used to assert that when a video called removeFromStock method and then immediately
     * replaceToStock method, the vide would be available
     */
    @Test
    public void testReplaceToStock() throws VideoException {
        Video video = new Video("JIGSAW", 2017);
        video.removeFromStock();
        video.replaceToStock();
        assertTrue(video.isAvailable());
    }
}
