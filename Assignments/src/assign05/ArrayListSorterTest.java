package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayListSorterTest {
	ArrayList<Integer> smallArr, smallArrSorted, largeArr, largeArrSorted;
	ArrayList<String> stringArr, stringArrSorted;
	
	@BeforeEach
	void setUp() throws Exception {
		smallArr = new ArrayList<Integer>();
		smallArr.add(5);
		smallArr.add(3);
		smallArr.add(7);
		
		smallArrSorted = new ArrayList<Integer>();		
		smallArrSorted.add(3);
		smallArrSorted.add(5);
		smallArrSorted.add(7);
		
		largeArr = ArrayListSorter.generatePermuted(70);
		
		largeArrSorted = ArrayListSorter.generateAscending(70);
		
		stringArr = new ArrayList<String>();
		stringArr.add("bark");
		stringArr.add("spot");
		stringArr.add("dog");
		stringArr.add("bark");
		
		stringArrSorted = new ArrayList<String>();
		stringArrSorted.add("bark");
		stringArrSorted.add("bark");
		stringArrSorted.add("dog");
		stringArrSorted.add("spot");	
		
	}

	@Test
	void testSmallMerge() {
		ArrayListSorter.mergesort(smallArr);
		String actual = "";
		String expected = "";
		for(int i = 0; i < smallArr.size(); i++) {
			actual += smallArr.get(i);
		}
		for(int i = 0; i < smallArrSorted.size(); i++) {
			expected += smallArrSorted.get(i);
		}
		assertEquals(expected, actual);		
	}
	
	@Test
	void testLargeMerge() {
		ArrayListSorter.mergesort(largeArr);
		String actual = "";
		String expected = "";
		for(int i = 0; i < largeArr.size(); i++) {
			actual += largeArr.get(i) + ", ";
		}
		for(int i = 0; i < largeArrSorted.size(); i++) {
			expected += largeArrSorted.get(i) + ", ";
		}
		assertEquals(expected, actual);	
	}
	
	@Test
	void testStringMerge() {
		ArrayListSorter.mergesort(stringArr);
		String actual = "";
		String expected = "";
		for(int i = 0; i < stringArr.size(); i++) {
			actual += stringArr.get(i);
		}
		for(int i = 0; i < stringArrSorted.size(); i++) {
			expected += stringArrSorted.get(i);
		}
		assertEquals(expected, actual);		
	}
	
	@Test
	void testSmallQuick() {
		ArrayListSorter.quicksort(smallArr);
		String actual = "";
		String expected = "";
		for(int i = 0; i < smallArr.size(); i++) {
			actual += smallArr.get(i);
		}
		for(int i = 0; i < smallArrSorted.size(); i++) {
			expected += smallArrSorted.get(i);
		}
		assertEquals(expected, actual);		
	}
	
	@Test
	void testLargeQuick() {
		ArrayListSorter.quicksort(largeArr);
		String actual = "";
		String expected = "";
		for(int i = 0; i < largeArr.size(); i++) {
			actual += largeArr.get(i) + ", ";
		}
		for(int i = 0; i < largeArrSorted.size(); i++) {
			expected += largeArrSorted.get(i) + ", ";
		}
		assertEquals(expected, actual);	
	}
	
	@Test
	void testStringQuick() {
		ArrayListSorter.quicksort(stringArr);
		String actual = "";
		String expected = "";
		for(int i = 0; i < stringArr.size(); i++) {
			actual += stringArr.get(i);
		}
		for(int i = 0; i < stringArrSorted.size(); i++) {
			expected += stringArrSorted.get(i);
		}
		assertEquals(expected, actual);		
	}
	
	@Test
	void testAscending() {
		ArrayList<Integer> actualArr = ArrayListSorter.generateAscending(10);
		ArrayList<Integer> expectedArr = new ArrayList<>();
		for (int i = 1; i < 11; i++) {
			expectedArr.add(i);
		}
		String actual = "";
		String expected = "";
		for(int i = 0; i < actualArr.size(); i++) {
			actual += actualArr.get(i);
		}
		for(int i = 0; i < expectedArr.size(); i++) {
			expected += expectedArr.get(i);
		}
		assertEquals(expected, actual);
	}
	
	@Test
	void testDescending() {
		ArrayList<Integer> actualArr = ArrayListSorter.generateDescending(10);
		ArrayList<Integer> expectedArr = new ArrayList<>();
		for (int i = 10; i > 0; i--) {
			expectedArr.add(i);
		}
		String actual = "";
		String expected = "";
		for(int i = 0; i < actualArr.size(); i++) {
			actual += actualArr.get(i);
		}
		for(int i = 0; i < expectedArr.size(); i++) {
			expected += expectedArr.get(i);
		}
		assertEquals(expected, actual);
	}
	
	@Test
	void testPermuted() {
		ArrayList<Integer> perm = ArrayListSorter.generatePermuted(10);
		String ascending = "12345678910";
		String descending = "10987654321";
		String actual = "";
		for (int i = 0; i < perm.size(); i++) {
			actual += perm.get(i);
		}
		assertFalse(actual.equals(ascending));
		assertFalse(actual.equals(descending));
		for (int i = 1; i < perm.size() + 1; i++) {
			if (!perm.contains(i)) {
				assert(false);
			}
		}
		assertEquals(10, perm.size());
	}

}
