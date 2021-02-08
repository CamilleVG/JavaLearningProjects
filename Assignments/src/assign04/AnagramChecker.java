package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Scanner;
/**
 * This class provides methods for anagrams and collections of anagrams
 * @author Camille van Ginkel and Shiv Patel
 * @version February 6, 2020
 */
public class AnagramChecker {

	/**
	 * This method sorts a string lexicographically
	 * @param - takes in a single word as a string
	 * 
	 */
	public static String sort(String word) throws NullPointerException {

		char[] charString = word.toCharArray(); // Cast to a char array

		for (int i = 1; i < charString.length; i++) {
			char char1 = charString[i];
			int j = i - 1;
			while (j >= 0 && charString[j] > char1) {
				charString[j + 1] = charString[j];
				j--;
			}

			charString[j + 1] = char1;

		}
		return String.valueOf(charString);
	}

	/**
	 * Groups same words together in an array using the given comparator
	 * 
	 * @param <T> arr
	 * @param comparator
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> comparator) throws NullPointerException {

		for (int i = 1; i < arr.length; i++) {
			T word = arr[i];
			int j = i - 1;
			while (j >= 0 && (comparator.compare(arr[j], word) > 0)) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = word;
		}
	}

	/**
	 * Compares two words and determines whether they are anagrams. Returns true if the are and false if they are not.
	 * @param word1
	 * @param word2
	 * @return boolean -- true if the words are anagrams and false if they are not.
	 */
	public static boolean areAnagrams(String word1, String word2) throws NullPointerException {

		String word1l = word1.toLowerCase();
		String word2l = word2.toLowerCase();
		word1 = sort(word1l);
		word2 = sort(word2l);

		char[] charString1 = word1.toCharArray(); // Cast to a char array
		char[] charString2 = word2.toCharArray(); // Cast to a char array

		if (charString1.length != charString2.length)
			return false;

		if (word1.compareTo(word2) == 0)
			return true;
		else
			return false;
	}

	/**
	 * Given an array of words, finds groups of words that are anagrams, and returns the largest group of words.
	 * @param stringArr -- String array holding words without spaces or punctuation.  No duplicate words 
	 * @return returns array of largest group of anagrams
	 */
	public static String[] getLargestAnagramGroup(String[] stringArr) throws NullPointerException {

		String[] stringArrOrig = new String[stringArr.length];

		System.arraycopy(stringArr, 0, stringArrOrig, 0, stringArr.length);

		for (int i = 0; i < stringArr.length; i++) {
			stringArr[i] = sort(stringArr[i]);
		}

		Comparator<String> comparator = (String st1, String st2) -> st1.compareTo(st2);
		insertionSort(stringArr, comparator);
		//java.util.Arrays.sort(stringArr);
		int countMax = 0;
		int countTemp = 0;
		String currentAnagram = stringArr[0];
		String maxAnagram = null;
		for (int i = 0; i < stringArr.length; i++) {
			if (areAnagrams(currentAnagram, stringArr[i]) == true) {
				countTemp++;
			}
			if (countTemp > countMax) {
				countMax = countTemp;
				maxAnagram = stringArr[i];
			}
			if (areAnagrams(currentAnagram, stringArr[i]) == false) {
				countTemp = 0;
				currentAnagram = stringArr[i];
			}
		}
		int maxCap = countMax + 1;
		String[] maxArr = new String[countMax];

		if (countMax == 1) {
			String[] nothing = new String[0];
			return nothing;

		}

		int k = 0;
		for (int i = 0; i < stringArr.length; i++) {

			if (k >= maxArr.length) {
				String[] tempArr = new String[maxCap];
				for (int j = 0; j < maxArr.length; j++) {
					tempArr[j] = maxArr[j];
				}

				maxArr = new String[maxCap + 1];

				for (int j = 0; j < tempArr.length; j++) {
					maxArr[j] = tempArr[j];
				}
				maxCap = maxCap + 1;

			}

			if (areAnagrams(stringArrOrig[i], maxAnagram)) {
				maxArr[k] = stringArrOrig[i];
				k++;
			}

		}
		
		int indexNull=0;
		for(int i=0;i<maxArr.length;i++)
		{
			if(maxArr[i] != null)
			{
				//do nothing
			}
			else
				indexNull = i;
		}
		
		if(indexNull>0)
		{
			String[] tempArrayNull = new String[maxArr.length-1];
			for(int i = 0; i<tempArrayNull.length;i++)
			{
				tempArrayNull[i] = maxArr[i];
			}
			return tempArrayNull;
		}
		
		
		return maxArr;
	}
	/**
	 * Given a file containing a list of words, finds groups of words that are anagrams, and returns the largest group of words.
	 * @param filename
	 * @return String array of the largest group of words that are anagrams
	 */
	
	public static String[] getLargestAnagramGroup(String filename) throws NullPointerException {
		try {
			Scanner inputIn;
			inputIn = new Scanner(new File(filename));
			String[] stringArr = new String[1];
			int currentSize = 0;
			int maxCap = 1;
			while (inputIn.hasNext()) {

				if (currentSize >= maxCap) {
					String[] tempArr = new String[maxCap];
					for (int i = 0; i < stringArr.length; i++) {
						tempArr[i] = stringArr[i];
					}

					stringArr = new String[maxCap + 1];

					for (int i = 0; i < tempArr.length; i++) {
						stringArr[i] = tempArr[i];
					}
					maxCap = maxCap + 1;
				}

				stringArr[currentSize] = inputIn.next();

				currentSize++;
			}

			inputIn.close();
			// System.out.println(stringArr.length);
			return getLargestAnagramGroup(stringArr);

		} catch (FileNotFoundException e) {
			System.err.println("No File Found");
		}
		String[] nothing = new String[0];
		return nothing;

	}

}
