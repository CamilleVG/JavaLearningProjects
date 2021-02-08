package lec26;

import java.util.ArrayList;
import java.util.List;

/** 
 * This class contains a method that performs a bucket sort.
 * Bucket sort works by partitioning the array into a number of buckets. 
 * Each bucket is then sorted individually.
 * 
 * This bucket sort partitions an array of strings into 26 buckets (one per letter).
 * It then uses insertion sort to sort each bucket.
 * 
 * @author Erin Parker
 * @version April 16, 2020
 */
public class SortUtil {

	public static void bucketSort(String arr[]) {
		if (arr == null || arr.length == 0 || arr.length == 1)
			return;

		ArrayList<ArrayList<String>> buckets = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < 26; i++)
			buckets.add(new ArrayList<String>()); // Should we use ArrayList or LinkedList?

		for (String s : arr) {
			int idx = s.toLowerCase().charAt(0) - 'a';
			buckets.get(idx).add(s);
		}

		int i = 0;
		for (ArrayList<String> b : buckets) {  
			insertionSort(b);  // Can the sorts be done in parallel?  Why insertion sort?
			for (String s : b)
				arr[i++] = s;
		}
	}

	private static <T extends Comparable<? super T>> void insertionSort(List<T> list) {
		for (int i = 1; i < list.size(); i++) {
			T val = list.get(i);
			int j;
			for (j = i - 1; j >= 0 && list.get(j).compareTo(val) >= 0; j--)
				list.set(j + 1, list.get(j));
			list.set(j + 1, val);
		}
	}
}