package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class BalancedSymbolCheckerTest {

	@Test
	void testAllSymbolsMatch() {
		try {
			assertEquals("No errors found. All symbols match.",BalancedSymbolChecker.checkFile("src/assign06/Class16.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void test() {
		try {
			System.out.print(BalancedSymbolChecker.checkFile("src/assign06/Class1.java"));
		}
		catch (FileNotFoundException e) {
			System.out.println("oops");
		}
	}
	
	@Test
	void testWrongUnmatchedSymbol() {
		try {
			assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",BalancedSymbolChecker.checkFile("src/assign06/Class1.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void testNoUnmatchedSymbol() {
		try {
			assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",BalancedSymbolChecker.checkFile("src/assign06/Class2.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void testUnmatchedSymbolAtEOF() {
		try {
			assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",BalancedSymbolChecker.checkFile("src/assign06/Class11.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void testSingleLineComment() {
		try {
			assertEquals("No errors found. All symbols match.",BalancedSymbolChecker.checkFile("src/assign06/Class15.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void testMultiLineComment() {
		try {
			assertEquals("No errors found. All symbols match.",BalancedSymbolChecker.checkFile("src/assign06/Class3.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void testUnfinishedComment() {
		try {
			assertEquals("ERROR: File ended before closing comment.",BalancedSymbolChecker.checkFile("src/assign06/Class4.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void testNestedComments() {
		try {
			assertEquals("No errors found. All symbols match.",BalancedSymbolChecker.checkFile("src/assign06/Class6.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void testInsideString() {
		try {
			assertEquals("No errors found. All symbols match.",BalancedSymbolChecker.checkFile("src/assign06/Class12.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}		
	
	@Test 
	void testInsideChar() {
		try {
			assertEquals("No errors found. All symbols match.",BalancedSymbolChecker.checkFile("src/assign06/Class13.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}
	
	@Test
	void testInsideString2() {
		try {
			assertEquals("No errors found. All symbols match.",BalancedSymbolChecker.checkFile("src/assign06/Class14.java"));
		}
		catch (FileNotFoundException e) {
			assert(false);
		}
	}

}
