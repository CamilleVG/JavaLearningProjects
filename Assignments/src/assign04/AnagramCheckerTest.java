package assign04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class tests the running times of methods in getLargestAnagramGroup
 * @author Erin Parker, Camille van Ginkel and Shiv Patel
 * @version February 6, 2020
 */

class AnagramCheckerTest extends AnagramChecker {
	
	
	private String[] anagram1 =  {"drab", "bared", "asters", "assert", "beard", "stares", "bard", "bread", "debar"};
	private String[] anagram1Sorted = { "abder", "abder", "abder", "abder","abdr", "abdr", "aersst", "aersst", "aersst"};
	private String[] anagram1Largest = {"bared" , "beard", "bread" ,"debar"};
	
	private String[] anagram2 =  {"drab", "bared", "asters", "assert", "beard", "stares", "bard", "bread", "debar"};
	
	
	private String[] test = {"drab", "bared","to", "asters","to"};
	private String[] testSorted = {"asters","bared", "drab","to","to"};
	private String[] anagramlong = {"drab", "bared", "asters", "assert", "beard", "stares", "bard", "bread", "debar","drab", "bared", "asters", "assert", "beard", "stares", "bard", "bread", "debar"};
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	
	@Test
	void sortTest() {
		
		String output = sort("ear");
		assertEquals("aer", output);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	void insetionSortTest() {
		for (int i = 0; i < anagram1.length; i++) {
			anagram1[i] = sort(anagram1[i]);
		}
		
		Comparator<String> comparator = (String st1, String st2) -> st1.compareTo(st2);
		insertionSort(anagram1, comparator);
	
		
		for(int i = 0; i < anagram1.length; i++) {
			assertEquals(anagram1Sorted[i], anagram1[i]);
		}
	}

	@Test
	void areAnagramTrueTest() {
		assertTrue(areAnagrams("ear","aer"));
	}
	
	@Test
	void areAnagramTrueTest2() {
		assertTrue(areAnagrams("detains","instead"));
	}
	
	@Test
	void areAnagramFalseTest() {
		assertFalse(areAnagrams("earr","aer"));
	}
	
	@Test
	void areAnagramFalseTest2() {
		assertFalse(areAnagrams("ae r","aer"));
	}
	
	@Test
	void getLargestAnagramGroup() {
		String[] list = getLargestAnagramGroup(anagram1);
		for(int i = 0; i < list.length; i++) {
			assertEquals(anagram1Largest[i], list[i]);
		}
	}
	@Test 
	void areAnagramTrueCapital(){
		assertTrue(areAnagrams("alert","Later"));
	}
	
	@Test 
	void getLargestAnagramGroupFile() {
		
		String[] list = getLargestAnagramGroup("/Users/shivpatel/Documents/Java/CS 2420/src/sample_word_list.txt");
		String[] listCompare = {"asp","pas","sap","spa"};
		for(int i = 0; i < list.length; i++) {
			assertEquals(listCompare[i], list[i]);
		}
	}
	@Test
	void noAnagramTest() {
		String[] list = {"hello" , "test", "tes"};
		assertEquals(0,getLargestAnagramGroup(list).length);	
	}
	
	@Test
	void noAnagramTestFile() {
		//assertEquals(0,getLargestAnagramGroup("no-anagram.txt").length);	
	}
	
	
	
	
	

}
