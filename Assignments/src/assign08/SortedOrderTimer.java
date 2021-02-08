package assign08;


/*
 * Times how efficient our BST is with all elements inserted in sorted ordering
 * rather than random ordering
 */
public class SortedOrderTimer {
	
	public static void main(String[] args) {		
		int timesToLoop = 1000;
		BinarySearchTree<Integer> bst;
		
		for (int n = 100; n <= 2000; n += 100) {			
			bst = new BinarySearchTree<>();
			
			//Adds the numbers 0 to N in order, creating an unbalanced BST
			for (int i = 0; i < n; i++) {
				bst.add(i);
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
