package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class represents a SimplePriorityQueue, which is a sorted array of any type of item that is comparable.
 * It is sorted from largest to smallest, using either a given comparator method or the natural ordering compare method of the type.
 * It is derived from PriorityQueue.
 * 
 * @author Camille Van Ginkel and Julia Ma
 * @version January 30, 2020
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {
	
	private int size = 0; //number of elements in Queue
	private E[] array = (E[]) new Object[16]; //container array that holds all elements in Queue
	private Comparator comparator; //if comparator constructor is used, holds the comparator passed from the arguement

	/**
	 * If this constructor is used to create the priority queue, it is assumed that
	 * the elements are ordered using their natural ordering (i.e., E implements
	 * Comparable<? super E>).
	 */
	public SimplePriorityQueue() {
	}

	/**
	 * If this constructor is used to create the priority queue, it is assumed that
	 * the elements are ordered using the provided Comparator object.
	 */
	public SimplePriorityQueue(Comparator<? super E> comp) {
		comparator = comp;
	}

	/**
	 * Retrieves, but does not remove, the minimum element in this priority queue.
	 * 
	 * @return the minimum element
	 * @throws NoSuchElementException if the priority queue is empty
	 */
	
	@Override
	public E findMin() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return array[size -1];
	}

	/**
	 * To achieve fast findMin and deleteMin methods, we have decided to maintain
	 * the priority queue as a sorted array of items, largest to smallest.
	 * Therefore, the minimum item will always be the item with the largest index,
	 * which is quick to locate and remove.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E deleteMin() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		E[] minElement = (E[]) new Object[1];
		minElement[0] = findMin();
		array[size - 1] = null;
		size = size -1;
		return minElement[0];
	}

	/**
	 * The insert method must take advantage of the list being sorted and implement a 
	 * binary search.  For running time efficiency, use a loop in your implementation
	 *  and avoid recursion.  You may not invoke Java's Arrays.binarySearch routine.
	 *  
	 *  insert: O(logN) + O(N)  ... linear
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void insert(E item) {
		E[] arrayCopy;
		if (size == array.length) {  //if the array is full it is doubled in size
			arrayCopy = (E[]) new Object[array.length * 2];
			for (int i = 0; i < size; i++) {
				arrayCopy[i] = this.array[i];
			}
			this.array = arrayCopy;
		}
		int count = 0;  //holds the index where the item should go
          
		
		//binary search
           int low = 0; 
           int high = size -1;
           int mid = 0;
           
           while (low <= high) {
        	   
               mid = (high - low)/2 + low; 
     
               	if (array[0] == null) {
               		count = 0;
               		break;
               	}
               	else if (comparator == null) {
                	  if (array[mid] == null) {
                		  count = mid;
                		  break;
                	  }
                	  //if item is present at mid, count is the index after mid.
                	  if  (((Comparable<? super E>) array[mid]).compareTo(item) == 0) {
                			  count = mid; //mid+ 1
                       	  }
                	  //if item is less than mid it returns +, and search ignores the left half 
                   	  //if obj1 > obj2 returns +
                	  
                   	  if(((Comparable<? super E>) array[mid]).compareTo(item) > 0) {
                          low = mid + 1;
                          if(low > high) {
                   			  count = low;
                   			  break;
                   		  }
                      }
                   	  //if item is greater than mid it returns -, and search ignores the right half
                	  //if obj1 < obj2 returns -
                   	  else {
              		  high = mid-1;

            		  if (high <= low) {
            			  if (((Comparable<? super E>) array[low]).compareTo(item) > 0) {
            				  count = mid;
            			  }
            			  else {
            				  count = low;
            			  }
            			  break;
            		  }
                   	  }
                  }
                  else {
                	  if (array[mid] == null) {
                		  count = mid;
                		  break;
                	  }
                	  if (comparator.compare(array[mid], item) == 0) {
                		  count = mid; // mid + 1
                		  break;
                   	  } 
                	//if item is less than mid it returns +, and search ignores the left half 
                   	  //if obj1 > obj2 returns +
                	  if (comparator.compare(array[mid], item) > 0) {
                   		  low = mid +1;
                   		  if(low > high) {
                   			  count = low;
                   			  break;
                   		  }
                   		  
                   	  }
                	//if item is greater than mid it returns -, and search ignores the right half
                	  //if obj1 < obj2 returns -
                	  else {
                		  high = mid-1;
                		  
                		  if (low >= high) {
                			  if (comparator.compare(array[low], item) > 0) {
                				  count = mid;
                			  }
                			  else {
                				  count = low;
                			  }
                			  break;
                		  }
                		  
                	}
                  }
           }

           E[] placeHolder = (E[]) new Object[2]; 
           placeHolder[0] = this.array[count];
           array[count] = item;   //inserts new element into array in order at count 
           size = size +1;
           for (int i = count + 1; i < size; i++) { 
        	   //iterates through array from index after count and shifts elements over by one.
        	   				placeHolder[1] = array[i];
        	   				this.array[i] = placeHolder[0];
                           placeHolder[0] = placeHolder[1];
           }
           
}
          
	/**
	 * Takes in a collection as an argument, and inserts all elements into SimplePriorityQueue
	 * @param Collection
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void insertAll(Collection<? extends E> coll) {
		E[] collArray = (E[]) new Object[coll.size()];
		coll.toArray(collArray);
		for(int i = 0; i < coll.size(); i++) {
			this.insert(collArray[i]);
		}
	}
	
	/**
	 * @return number of elements in queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return boolean true if no elements in queue and false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if(array[0] == null) {
			return true;
		}
		return false;
	}
	/**
	 * Clears every element in queue
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void clear() {
		//sets queue to a new empty array
		E[] empty = (E[]) new Object[16];
		size = 0;
		this.array = empty;
}
	
	
	/**
	 * Comparison helper method for insert()
	 */
	@SuppressWarnings("unchecked")
	private int compare(E obj1, E obj2) {
		 if (comparator == null) {
			 return ((Comparable<? super E>) obj1).compareTo(obj2);
		 }
		 else {
			 return (comparator.compare(obj1, obj2));
		}
}
	
}
