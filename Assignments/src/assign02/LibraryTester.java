package assign02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains tests for Library.
 * 
 * @author Erin Parker and Camille Van Ginkel and Julia Ma
 * @version January 23, 2020
 */
public class LibraryTester {

	private Library emptyLib, smallLib, mediumLib;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyLib = new Library();
		
		smallLib = new Library();
		smallLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		smallLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		smallLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		mediumLib = new Library();
		mediumLib.addAll("src/assign02/Mushroom_Publishing.txt");
		
	}

	@Test
	public void testEmptyLookupISBN() {
		assertNull(emptyLib.lookup(978037429279L));
	}
	
	@Test
	public void testEmptyLookupHolder() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLib.lookup("Jane Doe");
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testEmptyCheckout() {
		assertFalse(emptyLib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testEmptyCheckinISBN() {
		assertFalse(emptyLib.checkin(978037429279L));
	}
	
	@Test
	public void testEmptyCheckinHolder() {
		assertFalse(emptyLib.checkin("Jane Doe"));
	}

	@Test
	public void testSmallLibraryLookupISBN() {
		assertNull(smallLib.lookup(9780330351690L));
	}
	
	@Test
	public void testSmallLibraryLookupHolder() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		ArrayList<LibraryBook> booksCheckedOut = smallLib.lookup("Jane Doe");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testSmallLibraryCheckout() {
		assertTrue(smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
	}

	@Test
	public void testSmallLibraryCheckinISBN() {
		smallLib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008);
		assertTrue(smallLib.checkin(9780330351690L));
	}

	@Test
	public void testSmallLibraryCheckinHolder() {
		assertFalse(smallLib.checkin("Jane Doe"));
	}
	
	@Test
	public void testMedLibraryLookupISBN() {
		assertNull(mediumLib.lookup(9781843190028L));
	}
	
	@Test
	public void testMedLibraryLookupHolder() {
		mediumLib.checkout(9781843190011L, "John Don", 6, 2, 2010);
		ArrayList<LibraryBook> booksCheckedOut = mediumLib.lookup("John Don");
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9781843190011L, "Moyra Caldecott", "The Eye of Callanish"), booksCheckedOut.get(0));
		assertEquals("John Don", booksCheckedOut.get(0).getHolder());
	}

	@Test
	public void testMediumLibraryCheckout() {
		assertTrue(mediumLib.checkout(9781843190011L, "Eddie Murphy", 8, 11, 1999));
	}

	@Test
	public void testMediumLibraryCheckinISBN() {
		mediumLib.checkout(9781843190349L, "Chris Evans", 7, 4, 1776 );
		assertTrue(mediumLib.checkin(9781843190349L));
	}

	@Test
	public void testMediumLibraryCheckinHolder() {
		assertFalse(mediumLib.checkin("Jane Doe"));
	}
	
	
}