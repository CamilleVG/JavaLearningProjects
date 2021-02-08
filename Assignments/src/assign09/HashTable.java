package assign09;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a generic HashTable which will use separate chaining to resolve collisions.  
 */
public class HashTable<K, V> implements Map<K, V> {

	//backing array
	private ArrayList table;
	
	// number of items in the hash table
	private int numOfItems;

	// used in collecting stats, not needed otherwise
	private int numOfCollisions;
	
	//default arraylist size
	private static final int DEFAULT_CAPACITY = 100; 
	
	// used for analyzing performance of several hash functions, not needed otherwise
	public enum HashOption {
			BAD, BETTER, BEST, JAVA
	}

	
	public HashTable(int cap) {
		table = new ArrayList<LinkedList<V>>(cap);
		numOfCollisions = 0;
		numOfItems = 0;
	}
	
	public HashTable() {
		this(DEFAULT_CAPACITY);
	}
	
	
	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length)
	 */
	@Override
	public void clear() {
		numOfItems = 0;
		numOfCollisions = 0;
		table.clear();
	}

	
	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		if (table.get((int) key) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(V value) {
		int key = value.hashCode();
		if (table.get(key) != null) {
			return true;
		}
		return false;
	}

	@Override
	public List<MapEntry<K, V>> entries() {
		
		return null;
	}

	/**
	 * potentially add if else statement depending on whether the table contains key
	 */
	@Override
	public V get(K key) {
		return (V) table.get((int)key);
	}

	@Override
	public boolean isEmpty() {
		if (numOfItems == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * (I.e., if the key already exists in this map, resets the value; 
	 * otherwise adds the specified key-value pair.)
	 * 
	 * O(1)
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V put(K key, V value) {
		//if key is empty
		if(table.get((int) key)== null) {
			LinkedList L = new LinkedList<V>();
			L.add(value); 
			return null;
		}
		//if the key already has a value, add to already existing linked list
		else {
			LinkedList ref = (LinkedList) table.get((int)key);
			V previousVal =  (V) ref.getLast();
			ref.add(value);
			return previousVal;
		}
		
	}

	
	/**
	 * 
	 * 
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1)
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V remove(K key) {
		(V) table.get((int)key);
		return previousVal;
	}

	
	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1)
	 * 
	 * @return the number of mappings in this map
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
