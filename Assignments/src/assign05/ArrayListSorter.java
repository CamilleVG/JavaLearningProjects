package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ArrayListSorter {
	
	private static int threshold = 16; // Threshold of ArrayList size where mergesort switches to quicksort
	private static int quickType = 2; // The type of pivot choosing strategy quicksort uses
	private static Random rand = new Random();
	
	/**
	 * Sorts the given ArrayList using merge sort
	 * @param <T>
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		ArrayList<T> temp = new ArrayList<T>();
		for (int i = 0; i < arr.size(); i++) {
			temp.add(null);
		}
		mergesort(arr, temp, 0, arr.size() - 1);
	}
	
	/**
	 * Helper method for recursively calling mergesort
	 * @param <T>
	 * @param arr
	 * @param temp
	 * @param start
	 * @param end
	 */
	private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> temp, int start, int end) {
		
		if (start < end) {			
			if(end - start < threshold) {
				insertionSort(arr, start, end);
			}
			else {
				int mid = (start + end) /2;
				mergesort(arr, temp, start, mid);
				mergesort(arr, temp, mid + 1, end);
				merge(arr, temp, start, mid + 1, end);
			}		
		}
	}
	
	/**
	 * Helper method that combines left and right sides of ArrayList in sorted order
	 * @param <T>
	 * @param arr
	 * @param temp
	 * @param start
	 * @param mid
	 * @param end
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int start, int mid, int end) {
		int left = start;  // Initial index of the left ArrayList
		int right = mid;   // Initial index of the right ArrayList
		int index = left;  // Counter index for the temp ArrayList
		while(index < end + 1) {
			if (right > end) {
				temp.set(index, arr.get(left));
				left++;
			}
			else if (left > mid - 1) {
				temp.set(index, arr.get(right));
				right++;
			}				
			else if (arr.get(left).compareTo(arr.get(right)) < 0) {
				temp.set(index, arr.get(left));
				left++;
			} 
			else if (arr.get(left).compareTo(arr.get(right)) >= 0) {
				temp.set(index, arr.get(right));
				right++;
			}
			index++;
		}
		for(int i = start; i < end + 1; i++) {
			arr.set(i, temp.get(i));
		}
	}
	
	/**
	 * Sorts the given ArrayList using quicksort
	 * @param <T>
	 * @param arr
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		quicksort(arr, 0, arr.size() - 1, quickType);
	}
	
	/**
	 * Helper method for recursively calling quicksort
	 * @param <T>
	 * @param arr
	 * @param start
	 * @param end
	 * @param pivotType
	 */
	private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr, int start, int end, int pivotType) {
		if (start < end) {
			int pivot = pickPivot(arr, start, end, pivotType); // Location of pivot after being shifted out of the way
			int left = start; // Where the left counter starts
			int right = pivot - 1; // Where the right counter starts
			T temp;
			while (left <= right) {
				if (arr.get(left).compareTo(arr.get(pivot)) > 0) {
					if (arr.get(right).compareTo(arr.get(pivot)) < 0) {
						temp = arr.get(left);
						arr.set(left, arr.get(right));
						arr.set(right, temp);
					}
					else {
						right--;
					}
				}
				else {
					left++;
				}
			}
			temp = arr.get(left);
			arr.set(left, arr.get(pivot));
			arr.set(pivot, temp);
			pivot = left;
			quicksort(arr, start, pivot - 1, pivotType);
			quicksort(arr, pivot + 1, end, pivotType);
		}
	}
	
	/**
	 * Determines which pivot strategy quicksort should use, shifts the pivot out of 
	 * the way, and returns its location.
	 * @param <T>
	 * @param arr
	 * @param start
	 * @param end
	 * @param type
	 * @return
	 */
	private static <T extends Comparable<? super T>> int pickPivot(ArrayList<T> arr, int start, int end, int type) {
		int pivot;
		T temp;
		if(type == 1) { //1: pick right most element as pivot
			pivot = end;
		}
		else if (type == 2) { //2: random element as pivot
			pivot = rand.nextInt(end + 1 - start) + start;
			temp = arr.get(pivot);
			arr.set(pivot, arr.get(end));
			arr.set(end, temp);
			pivot = end;
		}
		else { //3: best of three as pivot
			int mid = (end + start) / 2; 
			
			if(arr.get(start).compareTo(arr.get(mid)) > 0) {
				temp = arr.get(start);
				arr.set(start, arr.get(mid));
				arr.set(mid, temp);
			}
			if (arr.get(start).compareTo(arr.get(end)) > 0) {
				temp = arr.get(start);
				arr.set(start, arr.get(end));
				arr.set(end, temp);
			}
			if (arr.get(mid).compareTo(arr.get(end)) > 0) {
				temp = arr.get(mid);
				arr.set(mid, arr.get(end));
				arr.set(end, temp);
			}
			
			temp = arr.get(mid);
			arr.set(mid, arr.get(end - 1));
			arr.set(end - 1, temp);
			pivot = end - 1;
		}
		return pivot;
	}
	
	/**
	 * Sorts the given portion of an ArrayList using insertion sort
	 * @param <T>
	 * @param arr
	 * @param start
	 * @param end
	 */
	private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int start, int end) {
		for (int i = start + 1; i < end + 1; i++) {
			for (int j = i; j > start && arr.get(j).compareTo(arr.get(j - 1)) < 0; j--) {
				T temp = arr.get(j);
				arr.set(j, arr.get(j - 1));
				arr.set(j - 1, temp);
			}
		}		
	}
	
	/**
	 * Creates an ArrayList of integers in ascending order from 1 to size
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		ArrayList<Integer> ascending = new ArrayList<>(); 
		for (int i = 1; i < size + 1; i++) {
			ascending.add(i);
		}
		return ascending;
	}
	
	/**
	 * Creates an ArrayList of all integers from 1 to size in permuted order
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		ArrayList<Integer> permuted = generateAscending(size);
		Collections.shuffle(permuted);
		return permuted;
	}
	
	/**
	 * Creates an ArrayList of integers in descending order from size - 1 to 0
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		ArrayList<Integer> descending = new ArrayList<>(); 
		for (int i = size; i > 0; i--) {
			descending.add(i);
		}
		return descending;
	}

}
