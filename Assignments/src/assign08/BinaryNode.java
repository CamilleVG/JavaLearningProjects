package assign08;

/**
 * Represents a generically-typed binary tree node. Each binary node contains
 * data, a reference to the left child, and a reference to the right child.
 * 
 * @author Erin Parker and Joshua Hardy & Camille van Ginkel
 * @version March 6, 2020
 */
public class BinaryNode<Type> {

	private Type data;

	private BinaryNode<Type> leftChild;

	private BinaryNode<Type> rightChild;

	public BinaryNode(Type data, BinaryNode<Type> leftChild, BinaryNode<Type> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public BinaryNode(Type data) {
		this(data, null, null);
	}

	/**
	 * @return the node data
	 */
	public Type getData() {
		return data;
	}

	/**
	 * @param data - the node data to be set
	 */
	public void setData(Type data) {
		this.data = data;
	}

	/**
	 * @return reference to the left child node
	 */
	public BinaryNode<Type> getLeftChild() {
		return leftChild;
	}

	/**
	 * @param leftChild - reference of the left child node to be set
	 */
	public void setLeftChild(BinaryNode<Type> leftChild) {
		this.leftChild = leftChild;
	}

	/**
	 * @return reference to the right child node
	 */
	public BinaryNode<Type> getRightChild() {
		return rightChild;
	}

	/**
	 * @param rightChild - reference of the right child node to be set
	 */
	public void setRightChild(BinaryNode<Type> rightChild) {
		this.rightChild = rightChild;
	}

	/**
	 * @return reference to the leftmost node in the binary tree rooted at this node
	 */
	public BinaryNode<Type> getLeftmostNode() {
		if (leftChild == null) {
			return this;
		}
		else {
			return leftChild.getLeftmostNode();
		}
	}

	/**
	 * @return reference to the rightmost node in the binary tree rooted at this node
	 */
	public BinaryNode<Type> getRightmostNode() {
		if (rightChild == null) {
			return this;
		}
		else {
			return rightChild.getRightmostNode();
		}
	}

	/**
	 * @return the height of the binary tree rooted at this node
	 * 
	 * The height of a tree is the length of the longest path to a leaf
	 * node. Consider a tree with a single node to have a height of zero.
	 */
	public int height() {
		if (leftChild == null && rightChild == null) {
			return 0;
		}
		else if (leftChild == null) {
			return 1 + rightChild.height();
		}
		else if (rightChild == null) {
			return 1 + leftChild.height();
		}
		else {
			int leftMax = 1 + leftChild.height();
			int rightMax = 1 + rightChild.height();
			return Math.max(leftMax, rightMax);
		}
	}
	
	/**
	 * Returns a String containing the Dot representation of the BST with this
	 * node as its root
	 */
	public String generateDot() {
		String dot = "";
		if (leftChild != null) {
			dot += data + " -> " + leftChild.getData() + "\n";
		}
		if (rightChild != null) {
			dot += data + " -> " + rightChild.getData() + "\n";
		}
		if (leftChild != null) {
			dot += leftChild.generateDot();
		}
		if (rightChild != null) {
			dot += rightChild.generateDot();
		}		
		return dot;
	}
	
}