package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedListStackTest {
	
	LinkedListStack<Integer> emptyStack, fullStack;
	LinkedListStack<String> stringStack;

	@BeforeEach
	void setUp() throws Exception {
		emptyStack = new LinkedListStack<>();
		fullStack = new LinkedListStack<>();
		fullStack.push(1);
		fullStack.push(2);
		fullStack.push(3);
		fullStack.push(4);
		fullStack.push(5);
		stringStack = new LinkedListStack<>();
	}
	
	@Test
	void testClear() {
		fullStack.clear();
		assertTrue(fullStack.isEmpty());
	}
	
	@Test
	void testIsEmpty() {
		assertTrue(emptyStack.isEmpty());
	}
	
	@Test
	void testPeek() {
		assertEquals(5, fullStack.peek());
		assertEquals(5, fullStack.peek());
		assertEquals(5, fullStack.peek());
	}
	
	@Test
	void testPeekException() {
		assertThrows(NoSuchElementException.class, () -> {
			emptyStack.peek();;
		});
	}
	
	@Test
	void testPop() {
		assertEquals(5, fullStack.peek());
		fullStack.pop();
		assertEquals(4, fullStack.peek());
	}
	
	@Test
	void testPopException() {
		assertThrows(NoSuchElementException.class, () -> {
			emptyStack.pop();;
		});
	}

	@Test
	void testPush() {
		emptyStack.push(7);
		emptyStack.push(2);
		assertEquals(2, emptyStack.pop());
		assertEquals(7, emptyStack.pop());
	}
	
	@Test
	void testSize() {
		assertEquals(5, fullStack.size());
		fullStack.push(2);
		assertEquals(6, fullStack.size());
		fullStack.pop();
		fullStack.pop();
		assertEquals(4, fullStack.size());
	}
	
	@Test
	void testGeneric() {
		stringStack.push("hello");
		stringStack.push("world");
		stringStack.push("foo");		
		assertEquals("foo", stringStack.pop());
		assertEquals("world", stringStack.peek());
		assertEquals(2, stringStack.size());
		stringStack.clear();
		assertTrue(stringStack.isEmpty());
	}
}
