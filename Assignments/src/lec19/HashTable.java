package lec19;

/**
 * Represents a hash table of Strings (not generic). All operations are O(1).
 * Collisions are handled using linear probing.
 * 
 * @author Erin Parker
 * @version March 17, 2020
 */
public class HashTable {

	// backing array
	private String[] table;

	// default length of backing array
	private static final int DEFAULT_CAPACITY = 100;

	// number of items in the hash table
	private int numOfItems;

	// used in collecting stats, not needed otherwise
	private int numOfCollisions;

	// used for analyzing performance of several hash functions, not needed otherwise
	public enum HashOption {
		BAD, BETTER, BEST, JAVA
	}

	private HashOption hashFunction;

	/**
	 * Creates a new hash table with a default capacity.
	 */
	public HashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates a new hash table with a user-provided capacity.
	 * 
	 * @param capacity
	 */
	public HashTable(int capacity) {
		table = new String[capacity];
		numOfItems = 0;
		numOfCollisions = 0;
		hashFunction = HashOption.JAVA;
	}

	/**
	 * @return the (current) capacity of this hash table
	 */
	public int capacity() {
		return table.length;
	}

	/**
	 * @return the number of items stored in this hash table
	 */
	public int size() {
		return numOfItems;
	}

	/**
	 * @return the number of collisions incurred (so far)
	 */
	public int collisions() {
		return numOfCollisions;
	}

	/**
	 * Resets the number-of-collisions statistic to 0.
	 */
	public void resetCollisions() {
		numOfCollisions = 0;
	}
	
	/**
	 * Sets the kind (quality) of hash function used.
	 * 
	 * @param option - choice of BAD, BETTER, BEST, JAVA hash functions
	 */
	public void setHashFunction(HashOption option) {
		hashFunction = option;
	}

	/**
	 * Searches this hash table for the given item.
	 * 
	 * @param item
	 * @return true if the item is in the hash table, false otherwise
	 */
	public boolean contains(String item) {
		// if the element residing at the index returned by find is not null, then
		// the item already exists in the table
		return table[find(item)] != null;
	}

	/**
	 * Adds a given item to the hash table.
	 * 
	 * @param item
	 * @return the index where the item was inserted, -1 if the item was not added
	 */
	public int add(String item) {
		// find helper method uses linear probing to search for item
		int currentPos = find(item);

		// if the element residing at the index returned by find is not null, then
		// the item already exists in the table
		if(table[currentPos] != null)
			return -1;

		// otherwise, add the item to the table
		table[currentPos] = item;
		numOfItems++;

		if(numOfItems == table.length - 1)
			// implementation of resizing (doubling) backing array left as an exercise
			System.err.println("YIKES! We need to resize the hash table.");

		return currentPos;
	}

	/**
	 * Helper method that searches hash table for item starting from desired index.
	 * In the event of a collision, linear probing is used to resolve.
	 * 
	 * @param item
	 * @return the index where the search terminates
	 */
	private int find(String item) {
		// clamp the hash function result to a valid array index
		int currentPos = Math.abs(hash(item)) % table.length;

		while(table[currentPos] != null) {

			// if item already exists in hash table, return an index where the non-null
			// item resides
			if(item.equals(table[currentPos]))
				break;

			currentPos = (currentPos + 1) % table.length; // wrap-around
			numOfCollisions++;
		}
		// if the item does not already exist in the hash table, return an index
		// where null resides

		return currentPos;
	}

	/**
	 * Helper method that returns the hash function result for the given string,
	 * selecting among four choices according to setting of hashFunction variable.
	 */
	private int hash(String str) {
		switch (hashFunction) {
		case BAD:
			return badHash(str);
		case BETTER:
			return betterHash(str);
		case BEST:
			return bestHash(str);
		default:
			return str.hashCode(); // invokes Java's hash function for strings
		}
	}

	/**
	 * This is a bad, but correct, hash function for strings -- expect a lot of
	 * collisions.
	 */
	private int badHash(String str) {
		return str.length();
	}

	/**
	 * This is a better hash function for strings -- expect fewer collisions.
	 */
	private int betterHash(String str) {
		int hashVal = 0;

		for(int i = 0; i < str.length(); i++)
			hashVal = hashVal + str.charAt(i);

		return hashVal;
	}

	/**
	 * This is a very good hash function for strings -- expect few collisions.
	 */
	private int bestHash(String str) {
		int hashVal = 0;

		for(int i = 0; i < str.length(); i++)
			hashVal = (hashVal * 128 + str.charAt(i));

		return hashVal;
	}
}