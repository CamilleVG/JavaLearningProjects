package assign08;


import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/*
 * Times how efficient Java's TreeSet is at accessing elements using contains()
 * compared to our BinarySearchTree
 */
public class TreeSetContainsTimer {
	
	public static void main(String[] args) {		
		int timesToLoop = 1000;
		TreeSet<Integer> tree;
		ArrayList<Integer> list;
		
		for (int n = 1000; n <= 20_000; n += 1000) {			
			tree = new TreeSet<>();
			list = new ArrayList<>();
			
			/* Adds the numbers 0 to N to an ArrayList, then shuffles the ArrayList
			 * to create a random ordering.
			 */
			for (int i = 0; i < n; i++) {
				list.add(i);
			}
			Collections.shuffle(list);
			
			// Adds the numbers from the ArrayList to the TreeSet
			for (int i = 0; i < list.size(); i++) {
				tree.add(list.get(i));
			}
			
			// empty block
			long startTime, stopTime;			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { 
			}
			
			//Times how long it takes to invoke contains() method for each item in the TreeSet
			startTime = System.nanoTime();			
			for (int i = 0; i < timesToLoop; i++) {
				for (int j = 0; j < n; j++) {
					tree.contains(j);
				}
			}
			stopTime = System.nanoTime();
			
			double averageTime = (stopTime - startTime) / (double) timesToLoop;
			System.out.println(averageTime);			
		}
		System.out.println("Done");
	}

}
