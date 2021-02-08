package assign03;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class tests SimplePriorityQueue.
 * 
 * @author Erin Parker, Camille Van Ginkel and Julia Ma
 * @version January 30, 2020
 */

public class SimplePriorityQueueTimer {
	public static void main(String args[]) {
		timeFindMin();
		timeInsert();
	}

	/**
	 * Returns randomly generated list of Strings
	 *
	 * @param size -- size of list generated
	 * @return SimplePriorityQueue<Integer>
	 */
	private static ArrayList<Integer> generateList(int size) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			result.add(size - i);
		}
		return result;
	}

	public static void timeFindMin() {

		// do findMin 100 000 times and use average running time
		int timesToLoop = 10_000;

		System.out.println("\nRUNNING TIMES for findMin\nN\tTime(ns)");

		// For each problem size n . . .
		for (int n = 100_000; n <= 2_000_000; n += 100_000) {

			// Generate new random list of size n
			SimplePriorityQueue<Integer> randList = new SimplePriorityQueue<Integer>();
			randList.insertAll(generateList(n));

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1_000_000_000) { // empty block
			}

			// Start the test
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				randList.findMin();
			}

			midpointTime = System.nanoTime();

			// Run an empty loop to capture the cost of running the loop.
			for (int i = 0; i < timesToLoop; i++) { // empty block
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and calling findMax.
			// Average it over the number of runs.

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (double) timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}

	public static void timeInsert() {
		Random ran = new Random();
		// do findMin 10 000 times and use average running time
		int timesToLoop = 10000;

		System.out.println("\nRUNNING TIMES for insert\nN\tTime(ns)");

		// For each problem size n . . .
		for (int n = 100_000; n <= 2_000_000; n += 100_000) {

			// Generate new random list of size n
			SimplePriorityQueue<Integer> randList = new SimplePriorityQueue<Integer>();
			randList.insertAll(generateList(n));

			long startTime, midpointTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Start the test
			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {

				randList.insert(ran.nextInt(n));
				randList.deleteMin();
			}

			midpointTime = System.nanoTime();

			// Capture the cost of running the loop and any other operations done
			// above that are not the essential method call being timed.
			for (int i = 0; i < timesToLoop; i++) {
				randList.deleteMin();
				ran.nextInt(n);
			}

			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and calling findMax.
			// Average it over the number of runs.

			long averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			System.out.println(n + "\t" + averageTime);
		}
	}
}
