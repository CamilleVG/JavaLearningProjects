package assign06;

import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {
	SinglyLinkedList<E> list;
	
	public LinkedListStack() {
		list = new SinglyLinkedList<E>();
	}

	@Override
	public void clear() {
		list.clear();		
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public E peek() throws NoSuchElementException {
		return list.getFirst();
	}

	@Override
	public E pop() throws NoSuchElementException {
		return list.removeFirst();
	}

	@Override
	public void push(E element) {
		list.addFirst(element);		
	}

	@Override
	public int size() {
		return list.size();
	}

}
