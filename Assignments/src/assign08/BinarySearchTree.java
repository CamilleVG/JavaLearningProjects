package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * 
 * @author Joshua Hardy & Camille van Ginkel
 *
 * @param <Type>
 */
public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type> {
	
	private BinaryNode<Type> root;
	private int size;
	
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	@Override
	public boolean add(Type item) {
		boolean changed; // Whether or not the BST has changed
		if (size == 0) {
			root = new BinaryNode<Type>(item);
			changed = true;
		}
		else {
			changed = add(root, item);
		}
		
		if (changed) {
			size++;
		}
		return changed;
	}
	
	/**
	 * Searches the BST for the location 'item' should be and places
	 * a new node there if one doen't already exist
	 */
	private boolean add(BinaryNode<Type> node, Type item) {
		
		// The 'item' belongs on the right side of 'node'
		if (node.getData().compareTo(item) < 0) {
			
			// Location is vacant; add new node
			if (node.getRightChild() == null) {
				node.setRightChild(new BinaryNode<Type>(item));
				return true;
			}
			
			else {
				return add(node.getRightChild(), item);
			}
		}
		
		// The 'item belongs on the left side of 'node'
		else if (node.getData().compareTo(item) > 0) {
			
			// Location is vacant; add new node
			if (node.getLeftChild() == null) {
				node.setLeftChild(new BinaryNode<Type>(item));
				return true;
			}
			
			else {
				return add(node.getLeftChild(), item);
			}
		}
		
		// The 'item' is already in the BST
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		boolean changed = false; // Whether or not the BST has changed
		for (Type item : items) {
			if (add(item)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(Type item) {
		return contains(root, item);
	}
	
	private boolean contains(BinaryNode<Type> node, Type item) {		
		if (node.getData().compareTo(item) < 0) {
			if (node.getRightChild() != null)
				return contains(node.getRightChild(), item);
			else {
				return false;
			}
		}
		else if (node.getData().compareTo(item) > 0) {
			if (node.getLeftChild() != null)
				return contains(node.getLeftChild(), item);
			else {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		for (Type item : items) {
			if (!contains(item)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Type first() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return root.getLeftmostNode().getData();
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public Type last() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return root.getRightmostNode().getData();
	}

	@Override
	public boolean remove(Type item) {
		if (root == null) {
			return false;
		}
		
		boolean changed; // Whether or not the BST has changed
		
		if (root.getData().compareTo(item) == 0) {
			BinaryNode<Type> rootParent = new BinaryNode<>(null);
			rootParent.setLeftChild(root);
			removeHelp(rootParent, root, true);
			root = rootParent.getLeftChild();
			changed = true;
		}
		else {
			changed = remove(root, item);
		}		
		if (changed) {
			size--;
		}
		return changed;
	}
	
	/**
	 * Determines if a child of 'node' contains the 'item' and removes it from the BST
	 */
	private boolean remove(BinaryNode<Type> node, Type item) {
		
		// The node only has no right child
		if (node.getRightChild() == null) {
			
			// The node is a leaf
			if (node.getLeftChild() == null) {
				return false;
			}
			
			// The item has been found; remove it
			else if (node.getLeftChild().getData().compareTo(item) == 0) {
				removeHelp(node, node.getLeftChild(), true);
				return true;
			}
			
			// Repeat process for left child if item isn't found
			else {
				return remove(node.getLeftChild(), item);
			}
		}
		
		// The node has no left child
		else if (node.getLeftChild() == null) {
			
			// The item has been found; remove it
			if (node.getRightChild().getData().compareTo(item) == 0) {
				removeHelp(node, node.getRightChild(), false);
				return true;
			}
			
			// Repeat process for right child if item isn't found
			else {
				return remove(node.getRightChild(), item);
			}
		}
		
		// The node has both right and left children
		else {
			
			// The item has been found; remove it
			if (node.getRightChild().getData().compareTo(item) == 0) {
				removeHelp(node, node.getRightChild(), false);
				return true;
			}
			
			// The item has been found; remove it
			if (node.getLeftChild().getData().compareTo(item) == 0) {
				removeHelp(node, node.getLeftChild(), true);
				return true;
			}
			
			// The item has not been found repeat process on left or right child
			else {
				if (node.getData().compareTo(item) > 0){
					return remove(node.getLeftChild(), item);
				}
				else {
					return remove(node.getRightChild(), item);
				}
			}
				
		}
		
	}
	/**
	 * Removes the given node from the tree;
	 * 'child' is the node being removed;
	 * boolean is true if 'child' is left of 'parent' and false if 'child' is right of 'parent';
	 */
	private void removeHelp(BinaryNode<Type> parent, BinaryNode<Type> child, boolean leftNode) {
		
		//Case 1: 'child' is a leaf
		if (child.getLeftChild() == null && child.getRightChild() == null) {
			if (leftNode) {
				parent.setLeftChild(null);
			}
			else {
				parent.setRightChild(null);
			}
		}
		
		//Case 2: 'child' has one child
		else if (child.getLeftChild() == null) {
			if (leftNode) {
				parent.setLeftChild(child.getRightChild());
			}
			else {
				parent.setRightChild(child.getRightChild());
			}
		}
		else if (child.getRightChild() == null) {
			if (leftNode) {
				parent.setLeftChild(child.getLeftChild());
			}
			else {
				parent.setRightChild(child.getLeftChild());
			}
		}
		
		//Case 3: 'child' it has two children
		else {
			BinaryNode<Type> succesor = child.getRightChild().getLeftmostNode();
			remove(child, succesor.getData());
			child.setData(succesor.getData());
		}
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		boolean changed = false; // Whether or not the BST has changed
		for (Type item : items) {
			if (remove(item)) {
				changed = true;
			}
		}
		return changed;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an ArrayList with the inorder ordering of the items in the BST
	 */
	@Override
	public ArrayList<Type> toArrayList() {
		ArrayList<Type> list = new ArrayList<>();
		if (!(root == null)) {
			inorder(list, root);
		}		
		return list;
	}
	
	/**
	 * Travels through the BST inorder and adds the data in each node to the
	 * given ArrayList
	 */
	private void inorder(ArrayList<Type> list, BinaryNode<Type> node) {
		if (node.getLeftChild() != null) {
			inorder(list, node.getLeftChild());
		}
		list.add(node.getData());
		if (node.getRightChild() != null) {
			inorder(list, node.getRightChild());
		}
	}
	
	public void generateDotFile(String filename) {
		PrintWriter out = null;
	    try {
	      out = new PrintWriter(filename);
	      out.println("digraph Tree {\n\tnode [shape=record]\n");
	      
	      if(root == null)
	    	  out.println("");
	      else
	    	  out.print(root.generateDot());
	      
	      out.println("}");
	      out.close();
	    } 
	    catch (IOException e) {
	      System.out.println(e);
	    }
	}


}
