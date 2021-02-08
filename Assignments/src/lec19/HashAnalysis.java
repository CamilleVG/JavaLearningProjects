package lec19;

import java.util.Random;

/**
 * Analyzes number of collisions incurred while doing operations on a hash table of strings
 * for several different hash functions.  Linear probing used to resolve collisions.
 * 
 * @author Erin Parker
 * @version March 17, 2020
 */
public class HashAnalysis {

	public static void main(String[] args) {

		// generate random strings of 1 to 5 lower-case letters
		Random rng = new Random();
		String[] randStrings = new String[100];
		
		for(int k = 0; k < randStrings.length; k++) {
			char[] arr = new char[rng.nextInt(5) + 1];  // length range: 1 to 5
			
			for(int i = 0; i < arr.length; i++)
				arr[i] = (char)((int)'a' + rng.nextInt(26));  // letter range: 'a' to 'z'
			
			randStrings[k] = new String(arr);
		}
		
		// for every hash function choice: BAD, BETTER, BEST, JAVA ...
		for(HashTable.HashOption choice : HashTable.HashOption.values()) {

			// create and populate the hash table
			HashTable strTable = new HashTable(200);
			strTable.setHashFunction(choice);
			for(String s : randStrings)
				strTable.add(s);
			
			System.out.println("Results for " + choice.name() + " hash function (hash table capacity = " + strTable.capacity() + ")");

			// successful hash table searches
			strTable.resetCollisions();
			for(int i = 0; i < randStrings.length; i++)
				strTable.contains(randStrings[i]);

			System.out.println("\tCollisions incurred during " + randStrings.length + " successful searches: " + strTable.collisions() +
					" (average of " + strTable.collisions() / (double) randStrings.length + " per search)");

			// random and likely unsuccessful hash table searches
			strTable.resetCollisions();
			for(int i = 0; i < randStrings.length; i++) {
				char[] arr = new char[rng.nextInt(5) + 1];  // length range: 1 to 5
				
				for(int j = 0; j < arr.length; j++)
					arr[j] = (char)((int)'a' + rng.nextInt(26));  // letter range: 'a' to 'z'

				strTable.contains(new String(arr));
			}

			System.out.println("\tCollisions incurred during " + randStrings.length + " random searches: " + strTable.collisions() + 
					" (average of " + strTable.collisions() / (double) randStrings.length + " per search)\n");
		}
	}
}