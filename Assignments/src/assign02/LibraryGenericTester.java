package assign02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains tests for LibraryGeneric.
 * 
 * @author Erin Parker and Camille Van Ginkel and Julia Ma
 * @version January 23, 2020
 */
public class LibraryGenericTester {
	
	private LibraryGeneric<String> nameLib;  // library that uses names to identify patrons (holders)
	private LibraryGeneric<PhoneNumber> phoneLib;  // library that uses phone numbers to identify patrons
	private LibraryGeneric<String> medNameLib;  // medium sized library that uses names to identify patrons
	private LibraryGeneric<PhoneNumber> medPhoneLib; // medium sized library that uses phone numbers to identify patrons
	private ArrayList<LibraryBookGeneric<String>> sortedISBNArrayList;  //ArrayList library copy sorted by isbn
	private ArrayList<LibraryBookGeneric<String>> sortedDueDateArrayList; //ArrayList library copy sorted by due date
	private ArrayList<LibraryBookGeneric<String>> sortedTitleArrayList; //ArrayList library copy sorted by title
	private ArrayList<LibraryBookGeneric<String>> sortedMedISBNArrayList;  //ArrayList library copy sorted by isbn

	
	@BeforeEach
	void setUp() throws Exception {
		nameLib = new LibraryGeneric<String>();
		nameLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		nameLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		nameLib.add(9780446580342L, "David Baldacci", "Simple Genius");

		phoneLib = new LibraryGeneric<PhoneNumber>();
		phoneLib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		phoneLib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		phoneLib.add(9780446580342L, "David Baldacci", "Simple Genius");		
	
		medNameLib = new LibraryGeneric<String>();
		medNameLib.addAll("src/assign02/Mushroom_Publishing.txt");
		
		medPhoneLib = new LibraryGeneric<PhoneNumber>();
		medPhoneLib.addAll("src/assign02/Mushroom_Publishing.txt");
		
		sortedISBNArrayList = new ArrayList<LibraryBookGeneric<String>>();
		sortedISBNArrayList.add(new LibraryBookGeneric<String>(9780330351690L, "Jon Krakauer", "Into the Wild"));
		sortedISBNArrayList.add(new LibraryBookGeneric<String>(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		sortedISBNArrayList.add(new LibraryBookGeneric<String>(9780446580342L, "David Baldacci", "Simple Genius"));
		
		sortedDueDateArrayList = new ArrayList<LibraryBookGeneric<String>>();
		sortedDueDateArrayList.add(new LibraryBookGeneric<String>(9780330351690L, "Jon Krakauer", "Into the Wild"));
		sortedDueDateArrayList.add(new LibraryBookGeneric<String>(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		sortedDueDateArrayList.add(new LibraryBookGeneric<String>(9780446580342L, "David Baldacci", "Simple Genius"));
		
		sortedTitleArrayList = new ArrayList<LibraryBookGeneric<String>>();
		sortedTitleArrayList.add((new LibraryBookGeneric<String>(9780330351690L, "Jon Krakauer", "Into the Wild")));
		sortedTitleArrayList.add((new LibraryBookGeneric<String>(9780446580342L, "David Baldacci", "Simple Genius")));
		sortedTitleArrayList.add((new LibraryBookGeneric<String>(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		
		
	}
	
	@Test
	public void testNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(nameLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}
	
	//checkout same book in a row with different holder
	@Test
	public void testSameBookDifferentHolder() {
		String patron = "Jane Doe";
		String patron2 = "John Smith";
		assertTrue(nameLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertFalse(nameLib.checkout(9780374292799L, patron2, 1, 1, 2008));
	}

	@Test
	public void testNameLibLookup() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = nameLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}
	
	@Test
	public void testNameLibCheckin() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 1, 2008);
		assertTrue(nameLib.checkin(patron));
	}

	@Test
	public void testPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(phoneLib.checkout(9780330351690L, patron, 1, 1, 2008));
		assertTrue(phoneLib.checkout(9780374292799L, patron, 1, 1, 2008));
	}

	@Test
	public void testPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		phoneLib.checkout(9780374292799L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = phoneLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}

	@Test
	public void testPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		phoneLib.checkout(9780330351690L, patron, 1, 1, 2008);
		assertTrue(phoneLib.checkin(patron));
	}
	
	@Test
	public void testMedNameLibCheckout() {
		String patron = "Jane Doe";
		assertTrue(medNameLib.checkout(9781843190875L, patron, 1, 1, 2008));
		assertTrue(medNameLib.checkout(9781843190516L, patron, 1, 1, 2008));
	}

	@Test
	public void testMedNameLibLookup() {
		String patron = "Jane Doe";
		medNameLib.checkout(9781843190394L, patron, 1, 1, 2008);
		medNameLib.checkout(9781843192954L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = medNameLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9781843190394L, "Kate Clarke", "The Royal United Hospital")));
		assertTrue(booksCheckedOut.contains(new Book(9781843192954L, "Dennis Radha-Rose", "The Anxiety Relief Program")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}
	
	@Test
	public void testMedNameLibCheckin() {
		String patron = "Jane Doe";
		medNameLib.checkout(9781843190400L, patron, 1, 1, 2008);
		assertTrue(medNameLib.checkin(patron));
		assertTrue(medNameLib.checkin(patron));
	}
	@Test
	public void testMedPhoneLibCheckout() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(medPhoneLib.checkout(9781843190875L, patron, 1, 1, 2008));
		assertTrue(medPhoneLib.checkout(9781843190516L, patron, 1, 1, 2008));
	}

	@Test
	public void testMedPhoneLibLookup() {
		PhoneNumber patron = new PhoneNumber("801.444.4321");
		medPhoneLib.checkout(9781843190394L, patron, 1, 1, 2008);
		medPhoneLib.checkout(9781843192954L, patron, 1, 1, 2008);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = medPhoneLib.lookup(patron);
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9781843190394L, "Kate Clarke", "The Royal United Hospital")));
		assertTrue(booksCheckedOut.contains(new Book(9781843192954L, "Dennis Radha-Rose", "The Anxiety Relief Program")));
		assertEquals(patron, booksCheckedOut.get(0).getHolder());
		assertEquals(patron, booksCheckedOut.get(1).getHolder());
	}
	
	@Test
	public void testMedPhoneLibCheckin() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		medPhoneLib.checkout(9781843190400L, patron, 1, 1, 2008);
		assertTrue(medPhoneLib.checkin(patron));
	}
	
	// tests small library, return sorted list by ISBN
	@Test 
	public void testSmallISBNList() {
		for (int i= 0 ; i < sortedISBNArrayList.size(); i++ ) {
			assertTrue(nameLib.getInventoryList().get(i).equals(sortedISBNArrayList.get(i)));	
		}
	}
	
	//tests small library, return sorted list by overdue date
	@Test
	public void testSmallDueDateList() {
		String patron = "Jane Doe";
		nameLib.checkout(9780330351690L, patron, 1, 2, 2008);
		nameLib.checkout(9780374292799L, patron, 1, 5, 2008);
		nameLib.checkout(9780446580342L, patron, 2, 8, 2008);
		for (int i= 0 ; i < sortedDueDateArrayList.size(); i++ ) {
			assertTrue(nameLib.getOverdueList(1, 1, 2008).get(i).equals(sortedDueDateArrayList.get(i)));	
		}
	}
	
	// tests small library, return sorted list by title
	@Test
	public void testSmallTitleList() {
		for (int i= 0 ; i < sortedTitleArrayList.size(); i++ ) {
			assertTrue(nameLib.getOrderedByTitle().get(i).equals(sortedTitleArrayList.get(i)));	
		}
	}
}
