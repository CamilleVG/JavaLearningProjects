package assign06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Erin Parker && ??
 * @version ??
 */
public class BalancedSymbolChecker {

	/**
	 * Generates a message indicating whether the input file has unmatched
	 * symbols. (Use the helper methods below for constructing messages.)
	 * 
	 * @param filename - name of the input file to check
	 * @return a message indicating whether the input file has unmatched symbols
	 * @throws FileNotFoundException if the file does not exist
	 */
	public static String checkFile(String filename) throws FileNotFoundException {
		LinkedListStack<String> stack = new LinkedListStack<>();
		int lineNum = 0;
		boolean prevSlash = false; // Whether or not the previous character was a '/' 
		boolean isComment = false; // Whether or not the current character is inside of a comment
		boolean isString = false; // Whether or not the current character is inside of a String
		boolean isChar = false; // Whether or not the current character is inside of a char
		boolean escaped = false; // Whether or not the previous character was a '\'
		try(Scanner scn = new Scanner(new File(filename))) {
			while (scn.hasNextLine()) {
				String line;
				line = scn.nextLine();
				lineNum++;
				int index = 1; // The index/column of the current char
				for (int i = 0; i < line.length(); i++) {					 
						char current = line.charAt(i);
						if(isComment) {
							if (line.indexOf("*/") < 0) {
								break; // Skip to the next line if the comment isn't closed on the current line
							}
							else if (index > line.indexOf("*/")) {
								isComment = false; // Leave the comment when the end is passed
							}
						}
						else if (current == '\\') {
							escaped = true; // A '\' has been located, exercise caution next char
						}
						else if (!escaped && current == '\"') {
							if (isString) {
								isString = false; // Leave String if inside of a String
							}
							else {
								isString = true; // Enter String if outside of a String
							}
						}
						else if (!escaped && current == '\'') {
							if (isChar) {
								isChar = false; // Leave char if inside of a char
							}
							else {
								isChar = true; // Enter char if outside of a char
							}
						}
						else if (escaped) {
							escaped = false; // Reset escaped if current char wasn't a quote
						}
						else if (isString || isChar) {
							continue; // Don't check for braces inside of Strings or chars
						}						
						else if (current =='/') {
							if (prevSlash) {
								prevSlash = false;
								break; // Skip to next line if '//' is found
							}
							else {
								prevSlash = true; // A '//' has been located, exercise caution next char
								continue; 
							}
						}
						else if (current == '*' && prevSlash) {
							isComment = true; // Enter a comment
							prevSlash = false;
						}						
						else {
							if (current == '{' || current == '[' || current ==  '(') {
								stack.push(((Character)current).toString());
							}
							if (current == '}' || current == ']' || current == ')') {
								if (stack.size() == 0) {
									return unmatchedSymbol(lineNum, index, current, ' ');
								}
								else if (stack.peek().equals(((Character)complement(current)).toString())) {
									stack.pop();
								}								
								else {
									return unmatchedSymbol(lineNum, index, current, complement(stack.peek().charAt(0)));
								}
							}						
						}
						index++;
						prevSlash = false;	
				}				
			}
		}	
		if (isComment) {
			return unfinishedComment();
		}
		else if (stack.isEmpty()) {
			return allSymbolsMatch();
		}
		else {
			return unmatchedSymbolAtEOF(complement(stack.pop().charAt(0)));
		}
	}

	/**
	 * Returns the complement of the given brace
	 * @param c
	 * @return
	 */
	private static char complement(char c) {
		if (c == '{')
			return '}';
		else if (c == '}') 
			return '{';
		else if (c == '(') 
			return ')';
		else if (c == ')') 
			return '(';
		else if (c == '[') 
			return ']';
		else
			return '[';	
	}

	/**
	 * Use this error message in the case of an unmatched symbol.
	 * 
	 * @param lineNumber - the line number of the input file where the matching symbol was expected
	 * @param colNumber - the column number of the input file where the matching symbol was expected
	 * @param symbolRead - the symbol read that did not match
	 * @param symbolExpected - the matching symbol expected
	 * @return the error message
	 */
	private static String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected) {
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected " + symbolExpected
				+ ", but read " + symbolRead + " instead.";
	}

	/**
	 * Use this error message in the case of an unmatched symbol at the end of the file.
	 * 
	 * @param symbolExpected - the matching symbol expected
	 * @return the error message
	 */
	private static String unmatchedSymbolAtEOF(char symbolExpected) {
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Use this error message in the case of an unfinished comment
	 * (i.e., a file that ends with an open /* comment).
	 * 
	 * @return the error message
	 */
	private static String unfinishedComment() {
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Use this message when no unmatched symbol errors are found in the entire file.
	 * 
	 * @return the success message
	 */
	private static String allSymbolsMatch() {
		return "No errors found. All symbols match.";
	}
}