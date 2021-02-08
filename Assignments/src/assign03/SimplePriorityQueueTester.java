package assign03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests SimplePriorityQueue.
 * 
 * @author Camille Van Ginkel and Julia Ma
 * @version January 30, 2020
 */

public class SimplePriorityQueueTester {

	private SimplePriorityQueue<Integer> numbers;
	private SimplePriorityQueue<Integer> empty;
	private SimplePriorityQueue<String> roleCall;
	private SimplePriorityQueue<String> roleCallNatural;

	private numOfElements compString = new numOfElements();

	protected class numOfElements implements Comparator<String> {
		public int compare(String obj1, String obj2) {
			return sumOfElements(obj1) - sumOfElements(obj2);
		}

		private int sumOfElements(String item) {
			return item.length();
		}
	}

	@BeforeEach
	void setUp() throws Exception {

		// Order will be [Dave Campbell, Megan Barlow, Alice White, John Smith, Lucy Bowe]
		roleCall = new SimplePriorityQueue<String>(compString);
		roleCall.insert("John Smith");
		roleCall.insert("Megan Barlow");
		roleCall.insert("Alice White");
		roleCall.insert("Dave Campbell");
		roleCall.insert("Lucy Bowe");

		roleCallNatural = new SimplePriorityQueue<String>();
		roleCallNatural.insert("John Smith");
		roleCallNatural.insert("Megan Barlow");
		roleCallNatural.insert("Alice White");
		roleCallNatural.insert("Dave Campbell");
		roleCallNatural.insert("Lucy Bowe");

		numbers = new SimplePriorityQueue<Integer>();
		numbers.insert(1);
		numbers.insert(400);
		numbers.insert(46);
		numbers.insert(129);
		numbers.insert(3);

		empty = new SimplePriorityQueue<Integer>();

	}

	@Test
	public void testInsertRoleCall() {
		roleCall.insert("Voldemort Supercalifragiolisticexpialidouscious");
		assertEquals(roleCall.size(), 6);
	}

	@Test
	public void testClearRoleCallNatural() {
		roleCallNatural.clear();
		assertEquals(roleCallNatural.size(), 0);
	}

	@Test
	public void testFindMinRoleCall() {
		assertEquals(roleCall.findMin(), "Lucy Bowe");
	}

	@Test
	public void testFindMinRoleCallNatural() {
		assertEquals(roleCallNatural.findMin(), "Alice White");
	}

	@Test
	public void testFindMinNumbersNatural() {
		assertTrue(numbers.findMin() == 1);
	}

	@Test
	public void testDeleteMinRoleCall() {
		assertEquals(roleCall.deleteMin(), "Lucy Bowe");
	}

	@Test
	public void testDeleteMinRoleCallNatural() {
		assertEquals(roleCallNatural.deleteMin(), "Alice White");
	}

	@Test
	public void testDeleteMinNumbersNatural() {
		assertTrue(numbers.deleteMin() == 1);
	}

	@Test
	public void testInsertAll() {
		ArrayList<String> names = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			names.add("Sara Johnson");
			names.add("Olivia Choi");
			names.add("James Cook");
			names.add("Dylan Pott");
			names.add("Aaron Garcia");
		}
		roleCallNatural.insertAll(names);
		assertEquals(roleCallNatural.findMin(), "Aaron Garcia");

	}

	@Test
	public void testSizeRoleCall() {
		assertEquals(roleCall.size(), 5);
	}

	@Test
	public void testSize() {
		assertEquals(roleCallNatural.size(), 5);
	}

	@Test
	public void testSizeNumbersNatural() {
		assertEquals(numbers.size(), 5);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(empty.isEmpty());
	}

	@Test
	public void testIsEmptyNumbers() {
		assertFalse(numbers.isEmpty());
	}

	@Test
	public void testClear() {
		roleCall.clear();
		assertTrue(roleCall.isEmpty());
	}
	@Test
	public void testFindMinEmpty() {
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			empty.findMin(); });	
	}
	
	@Test
	public void testDeleteMinEmpty() {
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			empty.deleteMin(); });	
	}

}
