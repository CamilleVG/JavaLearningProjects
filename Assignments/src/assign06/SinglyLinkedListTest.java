package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SinglyLinkedListTest {
	public SinglyLinkedList<Integer> list, fullList;
	public SinglyLinkedList<String> stringList;
	public Iterator<Integer> fullIt, emptyIt;
	
	@BeforeEach
	void setUp() throws Exception {
		list = new SinglyLinkedList<>();
		emptyIt = list.iterator();
		fullList = new SinglyLinkedList<>();
			fullList.addFirst(1);
			fullList.addFirst(2);
			fullList.addFirst(3);
			fullList.addFirst(4);
			fullList.addFirst(5);
		fullIt = fullList.iterator();
		stringList = new SinglyLinkedList<>();
	}

	@Test
	void testAddAndGetFirst() {
		list.addFirst(26);
		assertEquals(26, list.getFirst());
	}
	
	@Test
	void testToArray() {
		list.addFirst(26);
		list.addFirst(5);
		list.addFirst(7);
		Object[] actual = list.toArray();
		Object[] expected = {7, 5, 26};
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
	
	@Test
	void testAdd() {
		fullList.add(3, 7);
		Object[] actual = fullList.toArray();
		Object[] expected = {5, 4, 3, 7, 2, 1};
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
	@Test
	void testAddatFirst() {
		fullList.add(0, 7);
		Object[] actual = fullList.toArray();
		Object[] expected = {7, 5, 4, 3, 2, 1};
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
	@Test
	void testAddatLast() {
		fullList.add(5, 7);
		Object[] actual = fullList.toArray();
		Object[] expected = { 5, 4, 3, 2, 1, 7};
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
	}
	
	@Test
	void testGet() {
		assertEquals(2, fullList.get(3));
	}
	
	@Test
	void testGetatFirst() {
		assertEquals(5, fullList.get(0));
	}
	
	@Test
	void testGetatLast() {
		assertEquals(1, fullList.get(4));
	}
	
	@Test
	void testRemoveFirst() {
		fullList.removeFirst();
		Object[] actual = fullList.toArray();
		Object[] expected = { 4, 3, 2, 1};
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
		
	}
	
	@Test
	void testRemove() {
		int removed = fullList.remove(3);
		Object[] actual = fullList.toArray();
		Object[] expected = {5, 4, 3, 1};
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
		assertEquals(2, removed);
	}
	
	@Test
	void testRemoveAtLast() {
		int removed = fullList.remove(4);
		Object[] actual = fullList.toArray();
		Object[] expected = {5, 4, 3, 2};
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
		assertEquals(1, removed);
	}
	
	@Test
	void testRemoveAtFirst() {
		int removed = fullList.remove(0);
		Object[] actual = fullList.toArray();
		Object[] expected = {4, 3, 2, 1};
		for (int i = 0; i < actual.length; i++) {
			assertEquals(expected[i], actual[i]);
		}
		assertEquals(5, removed);
	}
	
	@Test
	void testIndexOf() {
		int index = fullList.indexOf(3);
		assertEquals (2, index);
	}
	
	@Test
	void testIndexOfatFirst() {
		int index = fullList.indexOf(5);
		assertEquals (0, index);
	}
	
	@Test
	void testSize() {
		assertEquals(5, fullList.size());
		fullList.addFirst(6);
		assertEquals(6, fullList.size());
		fullList.removeFirst();
		fullList.removeFirst();
		assertEquals(4, fullList.size());
		fullList.add(3, 7);
		assertEquals(5, fullList.size());
		fullList.remove(2);
		assertEquals(4, fullList.size());
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(list.isEmpty());
		assertFalse(fullList.isEmpty());
	}
	
	@Test
	void testClear() {
		fullList.clear();
		assertTrue(fullList.isEmpty());
	}
	
	@Test
	void testIteratorHasNext() {
		assertTrue(fullIt.hasNext());
	}
	@Test
	void testEmptyIteratorHasNext() {
		assertFalse(emptyIt.hasNext());
	}
	
	@Test
	void testIteratorNext() {
		assertEquals(5, fullIt.next());
		assertEquals(4, fullIt.next());
		assertEquals(3, fullIt.next());
		assertEquals(2, fullIt.next());
		assertEquals(1, fullIt.next());
	}
	
	@Test
	void testIteratorRemove() {
		fullIt.next();
		fullIt.next();
		fullIt.remove();
		assertEquals(4, fullList.size());
	}
	
	@Test
	void testStringList() {
		stringList.addFirst("hello");
		assertFalse(stringList.isEmpty());
		stringList.add(1, "world");
		assertEquals(stringList.size(), 2);
		stringList.removeFirst();
		stringList.remove(0);
		assertTrue(stringList.isEmpty());
	}
	
	@Test
	void testAddException() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(7, 2);;
		});
	}
	
	@Test
	void testGetFirstException() {
		assertThrows(NoSuchElementException.class, () -> {
			list.getFirst();;
		});
	}
	
	@Test
	void testGetException() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.get(7);;
		});
	}
	
	@Test
	void testRemoveFirstException() {
		assertThrows(NoSuchElementException.class, () -> {
			list.removeFirst();;
		});
	}
	
	@Test
	void testRemoveException() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.remove(7);;
		});
	}
	
	@Test
	void testIteratorNextException() {
		assertThrows(NoSuchElementException.class, () -> {
			emptyIt.next();
		});
	}

}
