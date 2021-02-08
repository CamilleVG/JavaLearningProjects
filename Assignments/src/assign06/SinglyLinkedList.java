package assign06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
		
	private Node head;
	private int size;
	
	public SinglyLinkedList() {
		head = new Node (null, null);
		size = 0;
		
	}
	
	private class Node{
		public E data;
		public Node next;
		
		public Node(E element, Node next) {
			data = element;
			this.next = next;
		}
		
	}

	@Override
	public void addFirst(E element) {
		Node temp = new Node(element, head.next);
		head.next = temp;
		size++;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node prev = head;
		for (int i = 0; i <= size; i++) {
			if (i == index) {
				prev.next = new Node(element, prev.next);
			}
			else {
				prev = prev.next;
			}						
		}
		size++;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return head.next.data;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		Node prev = head;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				break;
			}
			else {
				prev = prev.next;
			}						
		}	
		return prev.next.data;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node temp = head.next;
		if (size == 1) {
			head.next = null;
		}
		else {
			head.next = head.next.next;
		}	
		size--;
		return temp.data;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		Node prev = head;
		Node temp = prev;
		for (int i = 0; i < size; i++) {
			if (i == index) {
				temp = prev.next;
				prev.next = prev.next.next;				
			}
			else {
				prev = prev.next;
			}						
		}
		size--;
		return temp.data;
	}

	@Override
	public int indexOf(E element) {
		int index = -1;
		Node prev = head;
		for (int i = 0; i < size; i++) {
			if (prev.next.data.equals(element)) {
				index = i;
				break;
			}
			else {
				prev = prev.next;
			}						
		}
		return index;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return head.next == null;
	}

	@Override
	public void clear() {
		head.next = null;
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		Node prev = head;
		for (int i = 0; i < size; i++) {
			arr[i] = prev.next.data;
			prev = prev.next;
		}
		return arr;
	}

	@Override
	public Iterator<E> iterator() {
		return new listIterator(this, head);
	}
	
	private class listIterator implements Iterator<E> {
		private SinglyLinkedList<E> list;
		private Node prev;
		private Node current;
		private boolean nextCalled;
		
		public listIterator(SinglyLinkedList<E> l, Node start) {
			list = l;
			current = start;
			prev = new Node(null, current);
		}

		@Override
		public boolean hasNext() {
			return current != null && current.next != null;
		}

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Node temp = current.next;
			prev = prev.next;
			current = current.next;
			nextCalled = true;
			return temp.data;
		}
		
		public void remove() throws IllegalStateException {
			if(!nextCalled) {
				throw new IllegalStateException();
			}
			if (list.size == 1) {
				prev.next = null;
				current = null;
			}
			else {
				prev.next = current.next;
				current = current.next;
				list.size--;
			}			
			nextCalled = false;
		}
		
	}

	
}
