package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the Vector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a 
 *                 correctly implemented equals method.  Be careful of false
 *                 positives (i.e., tests that pass because your equals method
 *                 incorrectly returns true). 
 * 
 * @author Erin Parker & Camille van Ginkel
 * @version January 15, 2020
 */
class MathVectorJUnitTester {
	
	private MathVector rowVec, newRowVec, rowVecTranspose, unitVec, sumVec, colVec, newColVec, colVecTranspose, colUnitVec;

	@BeforeEach
	void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][]{{3, 1, 2}});
		
		// Creates a column vector with three elements: 3.0, 1.0, 2.0
		rowVecTranspose = new MathVector(new double[][]{{3}, {1}, {2}});
		
		// Creates a new row vector with three elements: -4.0, -3.0, -2.0, -1.0
		newRowVec = new MathVector(new double[][]{{-4}, {-3}, {-2}, {-1}});
		
		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][]{{1, 1, 1}});
		
		// Creates a row vector with three elements: 4.0, 2.0, 3.0
		sumVec = new MathVector(new double[][]{{4, 2, 3}});
		
		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVec = new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}});
		
		// Creates a new column vector with five elements: 4.0, 3.0, 2.0, 1.0
		newColVec = new MathVector(new double[][]{{4}, {3}, {2}, {1}});
		
		// Creates a column unit vector with five elements: 1.0, 1.0, 1.0, 1.0, 1.0
		colUnitVec = new MathVector(new double[][]{{1}, {1}, {1}, {1}, {1}});
		
		// Creates a row vector with five elements: -11.0, 2.5, 36.0, -3.14, 7.1
		colVecTranspose = new MathVector(new double[][]{{-11.0, 2.5, 36.0, -3.14, 7.1}});
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void smallRowVectorEquality() {  //provided test #1
		assertTrue(rowVec.equals(new MathVector(new double[][]{{3, 1, 2}})));
	}
	
	@Test
	void smallRowVectorInequality() { //provided test #2
		assertFalse(rowVec.equals(unitVec));
	}
	
	@Test
	void equalsRowToColVectorInequality() {
		assertFalse(rowVec.equals(rowVecTranspose));
	}
	
	@Test
	void equalsColToRowVectorInequality() {
		assertFalse(colVec.equals(colVecTranspose));
	}
	
	@Test
	void equalsFailsColVectorInequality() {
		assertFalse(colVec.equals(new MathVector(new double[][]{{-11}, {0.0}, {0.0}, {-3.14}, {7.1}})));
	}
	
	@Test
	void equalsFailShortRowVectorInequalityLength() {
		MathVector shortVec= new MathVector(new double[][]{{3, 1}});
		assertFalse(rowVec.equals(shortVec));
	}
	
	@Test
	void equalsFailLongRowVectorUnequalLength() {
		MathVector longVec= new MathVector(new double[][]{{3, 1, 3, 0}});
		assertFalse(rowVec.equals(longVec));
	}
	
	@Test
	void equalsFailShortColVectorUnequalLength() {
		MathVector shortVec= new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}});
		assertFalse(colVec.equals(shortVec));
	}
	
	@Test
	void equalsFailLongColVectorUnequalLength() {
		MathVector longVec= new MathVector(new double[][]{{-11}, {2.5}, {36}, {-3.14}, {7.1}, {4.7}});
		assertFalse(colVec.equals(longVec));
	}

	@Test
	public void createVectorFromBadArray() { //provided test #3
	  double arr[][] = {{1, 2}, {3, 4}};
	  assertThrows(IllegalArgumentException.class, () -> { new MathVector(arr); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void transposeSmallRowVector() { //provided test #4
		MathVector transposeResult = rowVec.transpose();
		assertTrue(transposeResult.equals(rowVecTranspose));
	}
	
	
	@Test
	void transposeSmallColVector() {
		MathVector transposeResult = colVec.transpose();
		assertTrue(transposeResult.equals(colVecTranspose));
	}
	
	
	@Test 
	public void addRowAndColVectors() {  //provided test #5
	  assertThrows(IllegalArgumentException.class, () -> { rowVec.add(colVec); });
	  // NOTE: The code above is an example of a lambda expression. See Lab 1 for more info.
	}
	
	@Test
	void addSmallRowVectors() {  //provided test #6
		MathVector addResult = rowVec.add(unitVec);
		assertTrue(addResult.equals(sumVec));		
	}

	@Test
	void dotProductSmallColVectors() {
		double dotProdResult = colVec.dotProduct(colUnitVec);
		assertEquals(dotProdResult, -11.0 * 1.0 + 2.5 * 1.0 + 36.0 * 1.0 + -3.14 * 1.0 + 7.1*1.0);		
	}
	
	@Test
	void dotProductSmallRowVectors() { //provided test #7
		double dotProdResult = rowVec.dotProduct(unitVec);
		assertEquals(dotProdResult, 3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0);	
	}
	
	@Test
	void dotProductRowUnequalLengthException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			double dotProdResult = rowVec.dotProduct(newRowVec);
		  });		
	}
	
	@Test
	void dotProductColUnequalLengthException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			double dotProdResult = colVec.dotProduct(newColVec);
		  });	
	}
	
	@Test
	void dotProductRowxColException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			double dotProdResult = rowVec.dotProduct(colVec);
		  });	
	}
	
	@Test
	void dotProductColxRowException() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			double dotProdResult = colVec.dotProduct(rowVec);
		  });	
	}
	
	
	@Test
	void smallRowVectorLength() { //provided test #8
		double vecLength = rowVec.magnitude();
		assertEquals(vecLength, Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0));		
	}
	@Test
	void magnitudeColVectorLength() {
		double vecLength = colVec.magnitude(); //-11.0, 2.5, 36.0, -3.14, 7.1
		assertEquals(vecLength, Math.sqrt(-11.0 * -11.0 + 2.5 * 2.5 + 36.0 * 36.0 + -3.14*-3.14 + 7.1*7.1));		
	}
	
	@Test
	void smallRowVectorNormalize() {  //provided test #9
		MathVector normalVec = rowVec.normalize();
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		assertTrue(normalVec.equals(new MathVector(new double[][]{{3.0 / length, 1.0 / length, 2.0 / length}})));		
	}
	
	@Test
	void normalizeColVector() {
		MathVector normalVec = colVec.normalize(); //colVec: -11.0, 2.5, 36.0, -3.14, 7.1
		double length = Math.sqrt((-11.0 * -11.0 + 2.5 * 2.5 + 36.0 * 36.0 + -3.14*-3.14 + 7.1*7.1));
		assertTrue(normalVec.equals(new MathVector(new double[][]{{-11.0 / length}, {2.5 / length}, {36.0 / length}, {-3.14 / length}, {7.1 / length}})));		
	}
	
	@Test
	void smallColVectorToString() {  //provided test #10
		String resultStr = "-11.0\n2.5\n36.0\n-3.14\n7.1";
		assertEquals(resultStr, colVec.toString());		
	}
	
	@Test
	void toStringRowVector() {
		//3.0, 1.0, 2.0
		String resultStr = "3.0 1.0 2.0";
		assertEquals(resultStr, rowVec.toString());		
	}
	
	@Test
	void toStringFailUnitVector() {
		String resultStr = "1.0 1.0 1.0\n";
		assertFalse(resultStr.equals(unitVec.toString()));		
	}

}