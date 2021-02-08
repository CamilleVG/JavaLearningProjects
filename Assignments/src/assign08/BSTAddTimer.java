package assign08;


import java.util.ArrayList;
import java.util.Collections;

/*
 * Times how efficient our BinarySearchTree is at inserting elements in random order
 * compared to Java's TreeSet
 */
public class BSTAddTimer {
	
	public static void main(String[] args) {		
		int timesToLoop = 1000;
		BinarySearchTree<Integer> bst;
		ArrayList<Integer> list;
		
		for (int n = 1000; n <= 20000; n += 1000) {						
			list = new ArrayList<>();
			
			/* Adds the numbers 0 to N to an ArrayList, then shuffles the ArrayList
			 * to create a random ordering.
			 */
			for (int i = 0; i < n; i++) {
				list.add(i);
			}
			Collections.shuffle(list);
			
			// empty block
			long startTime, midpointTime, stopTime;			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { 
			}
			
			// Times how long it takes to add N elements in random order to the BST
			startTime = System.nanoTime();
			for (int i = 0; i < timesToLoop; i++) {
				bst = new BinarySearchTree<>();
				for (int j = 0; j < n; j++) {
					bst.add(list.get(j));
				}
			}
						
			midpointTime = System.nanoTime();
			
			// Subtract out the time required to create the TreeSet and call get() on the ArrayList
			for (int i = 0; i < timesToLoop; i++) {
				bst = new BinarySearchTree<>();
				for (int j = 0; j < n; j++) {
					list.get(j);
				}
			}
			stopTime = System.nanoTime();
			
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;
			System.out.println(averageTime);			
		}
		System.out.println("Done");
	}

}
