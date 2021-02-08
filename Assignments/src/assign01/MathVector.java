package assign01;

/**
 * This class represents a simple row or column vector of numbers.
 * In a row vector, the numbers are written horizontally (i.e., along the columns).
 * In a column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author Erin Parker & Camille van Ginkel
 * @version January 15, 2020
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;      
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;
	
	/**
	 * Creates a new row or column vector.
	 * For a row vector, the input array is expected to have 1 row and a positive number of columns,
	 * and this number of columns represents the vector's length.
	 * For a column vector, the input array is expected to have 1 column and a positive number of rows,
	 * and this number of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the input 2D array is not 
	 *         compatible with a row or column vector
	 */
	public MathVector(double[][] data) {
		if(data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if(data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");
		
		if(data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		}
		else if(data[0].length == 1) {
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		}
		else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");
		
		// Create the array and copy data over.
		if(this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for(int i=0; i < this.data.length; i++) { 
			for(int j=0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}
	
	private boolean getIsRowVector() {
		return this.isRowVector;
		}
	private int getVectorSize() {
		return this.vectorSize;
		}
	
	/**
	 * Determines whether this vector is "equal to" another vector, where equality is
	 * defined as both vectors being row (or both being column), having the same 
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 */
	public boolean equals(Object other) {
		if(!(other instanceof MathVector)) {
			return false;
		}
		
		MathVector otherVec = (MathVector)other;
		
		//Check that both vectors are the same type: row or column
		if (this.isRowVector && !(otherVec.getIsRowVector())) {
			return false;
		}
		else if (!this.isRowVector && otherVec.getIsRowVector()){
			return false;
		}
		
		//Check that both vectors are the same length
		if (this.vectorSize != otherVec.getVectorSize()) {
			return false;
		}
		
		//Check that both vectors have the same numbers in the same positions
		if (this.isRowVector) {  //Row type vector case
			for (int i = 0; i < this.vectorSize; ++i) {
				if (this.data[0][i] != otherVec.data[0][i]) {
					return false;
				}
			}
		}
		else {  //Column type vector case
			for (int i = 0; i < this.vectorSize; ++i) {
				if (this.data[i][0] != otherVec.data[i][0]) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Generates a returns a new vector that is the transposed version of this vector.
	 */
	public MathVector transpose() {
		MathVector transVector; //Holds new tranpsposed version of vector
		if (this.isRowVector) {  //row vector case
			transVector = new MathVector(new double[this.data[0].length][1]);  //creates new col vector of same length
			
			for (int i = 0; i < this.vectorSize; ++i) {
				//iterates through provided vector and puts values to their according position in transVector
				transVector.data [i][0] = this.data[0][i];
			}
			
		}
		else { //col vector case
			transVector = new MathVector(new double[1][this.data.length]);  //creates new row vector of same length
			for (int i = 0; i < this.vectorSize; ++i) { 
				//iterates through provided vector and puts values to their according position in transVector
				transVector.data [0][i] = this.data[i][0];
			}
		}
		return transVector;
	}
	
	/**
	 * Generates and returns a new vector representing the sum of this vector and another vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */
	public MathVector add(MathVector other) {
		if (this.vectorSize != other.vectorSize) {
			//if vectors are not the same size they cannot be added
			throw new IllegalArgumentException();
		}
		MathVector sumVector;
		
		if (this.isRowVector) {
			if (!other.isRowVector) { //Checks that vectors are same type: row
				throw new IllegalArgumentException();
			}
			sumVector = new MathVector(this.data);
			for (int i = 0; i < this.data[0].length; ++i) {
				sumVector.data[0][i] = this.data[0][i] + other.data[0][i];
			}
			
		}
		else {
			if (other.isRowVector) { //Checks that vectors are same type: col
				throw new IllegalArgumentException();
			}
			sumVector = new MathVector(this.data);
			for (int i = 0; i < this.data.length; ++i) {
				sumVector.data[i][0] = this.data[i][0] + other.data[i][0];
			}
		}
		return sumVector;
	}
	
	/**
	 * Computes and returns the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not both row vectors of
	 *         the same length or column vectors of the same length
	 */	
	public double dotProduct(MathVector other) {
		double dotProduct = 0;
		if (this.isRowVector) {
			if (!other.isRowVector) { //if vectors are not both type row
				throw new IllegalArgumentException();
			}
			if (this.data[0].length != other.data[0].length) { // if both vectors are not the same length
				throw new IllegalArgumentException();
			}
			for (int i = 0; i < this.data[0].length; ++i) {
				dotProduct += (this.data[0][i] * other.data[0][i]);
			}
		}
		else {
			if (other.isRowVector) { //if vectors are not both type col
				throw new IllegalArgumentException();
			}
			if (this.data.length != other.data.length) { // if both vectors are not the same length
				throw new IllegalArgumentException();
			}
			for (int i = 0; i < this.data.length; ++i) {
				//multiplies corresponding elements in vectors and adds it to the resulting scalar
				dotProduct += (this.data[i][0] * other.data[i][0]);
			}
		}
		return dotProduct;
	}
	
	/**
	 * Computes and returns this vector's magnitude (also known as a vector's length) .
	 */
	public double magnitude() {
		double sum = 0;
		if (this.isRowVector) {  //if vector is type row
			for (int i = 0; i < this.vectorSize; ++i) {
				sum = sum + (this.data[0][i] * this.data[0][i]);
			}
		}
		else {  //if vector is type col
			for (int i = 0; i < this.vectorSize; ++i) {
				sum = sum + (this.data[i][0] * this.data[i][0]);
			}
		}
		//multiplies each element by itself, adds it to sum, and then takes the square root in order to get the absolute value length of the vectors
		return Math.sqrt(sum);
	}
	
	/** 
	 * Generates and returns a normalized version of this vector.
	 */
	public MathVector normalize() {
		MathVector normVec = new MathVector(this.data); //creates new vector with same dimensions
		
		if (this.isRowVector) {  //if the vector type is row it iterates through the first array
			for (int i = 0; i < this.vectorSize; ++i) {  
				//divides each element of array by its the magnitude and inserts it to normVec
				normVec.data[0][i] = normVec.data[0][i] / this.magnitude();
			}
		}
		else {  //if the vector type is col it iterates through the first element of each array
				for (int i = 0; i < this.vectorSize; ++i) {
					//divides each element of array by its the magnitude and inserts it to normVec
					normVec.data[i][0] = normVec.data[i][0] / this.magnitude();
				}
			}
		return normVec;
		
	}
	
	/**
	 * Generates and returns a textual representation of this vector.
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and 
	 * "1.0
	 *  2.0
	 *  3.0
	 *  4.0
	 *  5.0" for a sample column vector of length 5. 
	 *  In both cases, notice the lack of a newline or space after the last number.
	 */
	public String toString() {
		String stringVector = "";
		
		if (this.isRowVector) { //if vector is row type, there is a space between each element
			for (int i = 0; i < this.vectorSize; ++i) {
				if(i == this.vectorSize - 1) {  //if the element is the last element, it does not have a new space after it
					stringVector = stringVector + (this.data[0][i]);
				}
				else {
					stringVector = stringVector + (this.data[0][i]) + ' ';
				}
				
			}
		}
		else {  //if vector is col type, there is a newline between each element
			for (int i = 0; i < this.vectorSize; ++i) {
				if(i == this.vectorSize - 1) {  //if the element is the last element, it does not have a new line after it
					stringVector = stringVector + (this.data[i][0]);
				}
				else {
					stringVector = stringVector + (this.data[i][0]) + '\n';
				}
			}
			
		}
		return stringVector;
		
	}
}