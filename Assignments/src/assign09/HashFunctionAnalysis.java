package assign09;

import java.util.Random;

import lec19.HashTable;

/**
 * Analyzes number of collisions incurred while doing operations on a hash table of strings
 * for several different hash functions.  Linear probing used to resolve collisions.
 * 
 * 
 * @author camillevanginkel
 *
 */

public class HashFunctionAnalysis {
	
	public static void main(String[] args) {
		// generate random strings of 1 to 5 lower-case letters
		Random rng = new Random();
		String[] randStrings = new String[100];
		
		for(int k = 0; k < randStrings.length; k++) {
			//create a character array of random length between 1-5
			char[] arr = new char[rng.nextInt(5) + 1];  // length range: 1 to 5
			
			//at each index add a random character 
			for(int i = 0; i < arr.length; i++)
				arr[i] = (char)((int)'a' + rng.nextInt(26));  // letter range: 'a' to 'z'
			
			randStrings[k] = new String(arr);
		}
		
		// for every hash function choice: BAD, BETTER, BEST, JAVA ...
		for(HashTable.HashOption choice : HashTable.HashOption.values()) {
			
		}
				
		
	}
	

}
