package assign04;
import java.util.*;

import assign03.SimplePriorityQueue; 


public class getLargestAnagramTiming extends AnagramChecker {
	
	
	public static void main(String[] args) {
		
		int timesToLoop = 500;

		int incr = 1000;
		for(int probSize = 1; probSize <= 200001; probSize += incr) {


			for(int i = 0; i < probSize; i++) 
				
				getLargestAnagramGroup(createStringArray(probSize - i));

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			long stopTime, midpointTime, startTime = System.nanoTime();

			while(System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Collect running times.
			startTime = System.nanoTime();
			for(int i = 0; i < timesToLoop; i++) {
				
				getLargestAnagramGroup(createStringArray(probSize));;
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for(int i = 0; i < timesToLoop; i++) {
				createStringArray(probSize);
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
	
	
	
	
	
	
	
	
	private static String[] createStringArray(int size) {
		String[] strArr = new String[size];
		
		for(int i =0; i < strArr.length;i++)
		{
			int r = (int) (Math.random() * (10 - 2)) + 2;
			strArr[i] = createWord(r);
		}
		return strArr;
	}
	
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


