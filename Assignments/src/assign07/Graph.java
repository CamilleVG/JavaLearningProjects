package assign07;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author Camille van Ginkel & Joshua Hardy
 *
 * @param <E>
 */
public class Graph<E> {	
	private HashMap<E, Vertex<E>> vertices;
	private int numVertices;
	
	/**
	 * Creates a graph with the given sources and destinations of edges
	 * @param source
	 * @param destination
	 */
	public Graph(List<E> source, List<E> destination) {
		if (source.size() != destination.size()) {
			throw new IllegalArgumentException();
		}
		
		vertices = new HashMap<>();
		
		Iterator<E> sourceIt = source.iterator();
		Iterator<E> destIt = destination.iterator();
		
		while (sourceIt.hasNext()) {
			E sourceNext = sourceIt.next();
			E destNext = destIt.next();
			
			// If a vertex containing the current data doesn't exist, create one
			if (!vertices.containsKey(sourceNext)) {
				Vertex<E> temp = new Vertex<E>(sourceNext);
				vertices.put(sourceNext, temp);
				numVertices++;
			}
			if (!vertices.containsKey(destNext)) {
				Vertex<E> temp = new Vertex<E>(destNext);
				vertices.put(destNext, temp);
				numVertices++;
			}
			
			// Add the "edge" by adding the destination vertex to the source vertex's list of endpoints
			vertices.get(sourceNext).addEndpoint(vertices.get(destNext));
		}
	}
	
	/**
	 * Resets 'isVisited' for all vertices in the graph
	 */
	public void resetVisited() {
		for(Vertex<E> v: vertices.values()) {
			v.setVisited(false);
		}
	}
	
	/**
	 * Returns the vertex containing the given data
	 * @return
	 */
	public Vertex<E> getVertex(E data) {
		return vertices.get(data);
	}
	
	/**
	 * Returns the set of all vertices in the graph
	 * @return
	 */
	public Collection<Vertex<E>> getVertices() {
		return vertices.values();
	}
	
	/**
	 * Returns the set of all data in the graph
	 * @return
	 */
	public Collection<E> getDataSet() {
		return vertices.keySet();
	}
	
	/**
	 * Returns the number of vertices in the graph
	 * @return
	 */
	public int getNumVertices() {
		return numVertices;
	}

}
