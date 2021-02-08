package assign04;
import java.util.*;

/**
 * This class tests the running times of methods in AnagramChecker
 * @author Erin Parker, Camille van Ginkel and Shiv Patel
 * @version February 6, 2020
 */
import assign03.SimplePriorityQueue; 


public class areAnagramTiming extends AnagramChecker {
	
	
	public static void main(String[] args) {
		
		int timesToLoop = 500;

		int incr = 100;
		for(int probSize = 1; probSize <= 10001; probSize += incr) {


			for(int i = 0; i < probSize; i++) 
				
				areAnagrams(createWord(probSize - i),createWord(probSize - i));

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			for(int i = 0; i < timesToLoop; i++) {
				
				areAnagrams(createWord(probSize),createWord(probSize));
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for(int i = 0; i < timesToLoop; i++) {
				createWord(probSize);
				createWord(probSize);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and searching.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - 
						(stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println(probSize + "  " + averageTime);
			
		}
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Helper method for implementation of the "Check Analysis" technique that creates parameters for areAnagrams()
	 * Creates a word of specified length
	 * @param wordSize
	 * @return  string of randomly generated words
	 */
	
	private static String createWord(int wordSize) {
		
        int lowerLimit = 97; // Letter a
        int upperLimit = 122; // letter z
  
        Random random = new Random(); 
        StringBuffer r = new StringBuffer(wordSize); 
        for (int i = 0; i < wordSize; i++) { 
        	  
            // take a random value between 97 and 122 
            int nextRandomChar = lowerLimit 
                                 + (int)(random.nextFloat() 
                                         * (upperLimit - lowerLimit + 1)); 
  
            r.append((char)nextRandomChar); 
        } 
  
        // return the resultant string 
        return r.toString(); 
    } 
		
	

}


