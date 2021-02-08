package assign08;


import java.util.ArrayList;
import java.util.Collections;

/*
 * Times how efficient our BST is with all elements inserted in random ordering
 * rather than sorted ordering
 */
public class RandomOrderTimer {
	
	public static void main(String[] args) {		
		int timesToLoop = 1000;
		BinarySearchTree<Integer> bst;
		ArrayList<Integer> list;
		
		for (int n = 100; n <= 2000; n += 100) {			
			bst = new BinarySearchTree<>();
			list = new ArrayList<>();
			
			/* Adds the numbers 0 to N to an ArrayList, then shuffles the ArrayList
			 * to create a random ordering.
			 */
			for (int i = 0; i < n; i++) {
				list.add(i);
			}
			Collections.shuffle(list);
			
			// Adds the numbers from the ArrayList to the BST to create a balanced BST
			for (int i = 0; i < list.size(); i++) {
				bst.add(list.get(i));
			}
			
			// empty block
			long startTime, stopTime;			
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { 
			}
			
			//Times how long it takes to invoke contains() method for each item in the BST
			startTime = System.nanoTime();			
			for (int i = 0; i < timesToLoop; i++) {
				for (int j = 0; j < n; j++) {
					bst.contains(j);
				}
			}
			stopTime = System.nanoTime();
			
			double averageTime = (stopTime - startTime) / (double) timesToLoop;
			System.out.println(averageTime);			
		}
		System.out.println("Done");
	}

}
