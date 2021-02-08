package assign07;

import java.util.LinkedList;

/**
 * 
 * @author Camille van Ginkel & Joshua Hardy
 *
 * @param <E>
 */
public class Vertex<E> {
	private E data;
	
	// The list of all vertices that are endpoints of edges that start with this vertex 
	private LinkedList<Vertex<E>> endpoints; 
	
	private boolean visited;
	private int indegree;
	
	/**
	 * Creates a Vertex containing the given data
	 * @param data
	 */
	public Vertex (E data) {
		this.data = data;
		endpoints = new LinkedList<Vertex<E>>();
		visited = false;
		indegree = 0;
	}
	
	/**
	 * Sets the data contained in this Vertex
	 * @return
	 */
	public void setData(E data) {
		this.data = data;
	}
	
	/**
	 * Returns the data contained in this Vertex
	 * @return
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * Adds an endpoint representing the destination of an edge
	 * whose source is this Vertex
	 * @param v
	 */
	public void addEndpoint(Vertex<E> v) {
		endpoints.add(v);
	}
	
	/**
	 * Returns list of all Vertices that are destinations of edges 
	 * starting from this Vertex
	 * @return
	 */
	public LinkedList<Vertex<E>> getEndpoints() {
		return endpoints;
	}
	
	/**
	 * Sets the value of visited to the given value
	 * @param b
	 */
	public void setVisited(boolean b) {
		visited = b;
	}
	
	/**
	 * Returns whether or not this Vertex has been visited
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}
	
	/**
	 * Sets the value of indegree to the given value
	 * @param b
	 */
	public void setIndegree(int ind) {
		indegree = ind;
	}
	
	/**
	 * Returns indegree of this Vertex
	 * @return
	 */
	public int getIndegree() {
		return indegree;
	}

}
