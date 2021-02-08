package assign07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * Contains several methods for solving problems on generic, directed,
 * unweighted, sparse graphs.
 * 
 * @author Erin Parker & Joshua Hardy & Camille van Ginkel
 * @version February 27, 2020
 */
public class GraphUtility {

	public static <Type> boolean isCyclic(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		Graph<Type> graph = new Graph<>(sources, destinations);
		Stack<Vertex<Type>> stack = new Stack<>();
		
		// Run a depth first search with all vertices as the starting
		// point to see if a path exists back to the starting point
		for (Vertex<Type> v : graph.getVertices()) {
			if (depthFirst(stack, v, v)) {
				return true; // If the starting point has a cyclic path the graph is cyclic
			}
			graph.resetVisited();
		}
		return false; // No cyclic paths were found
	}
	
	private static <Type> boolean depthFirst(Stack<Vertex<Type>> stack, Vertex<Type> vert, Vertex<Type> start) {	
		for (Vertex<Type> point: vert.getEndpoints()) {
			if(point.equals(start)) { 
				return true; // A path exists from start to start
			}
			else if (!point.isVisited()) {
				stack.push(point);
			}			
		}
		if (stack.isEmpty()) {
			return false; // All vertices that can be reached from start have been visited
		}		
		else {
			vert.setVisited(true);
			return depthFirst(stack, stack.pop(), start);
		}
	}

	public static <Type> boolean areConnected(List<Type> sources, List<Type> destinations, Type srcData, Type dstData)
			throws IllegalArgumentException {		
		Graph<Type> graph = new Graph<>(sources, destinations);
		
		if (!graph.getDataSet().contains(srcData) || !graph.getDataSet().contains(dstData)) {
			throw new IllegalArgumentException();
		}
		
		Queue<Vertex<Type>> queue = new LinkedList<Vertex<Type>>();
		queue.offer(graph.getVertex(srcData));
		
		// Do a breadth first search until all vertices have been visited
		// or the destination is found
		while (!queue.isEmpty()) {
			Vertex<Type> vert = queue.poll();
			for (Vertex<Type> point: vert.getEndpoints()) {
				if (point.getData().equals(dstData)) {
					return true;
				}
				else if (!point.isVisited()) {
					queue.offer(point);
				}					
			}
			vert.setVisited(true);
		}
		return false;
	}

	public static <Type> List<Type> sort(List<Type> sources, List<Type> destinations) throws IllegalArgumentException {
		if(isCyclic(sources, destinations)) {
			throw new IllegalArgumentException();
		}
		Graph<Type> graph = new Graph<>(sources, destinations);
		ArrayList<Type> sorted = new ArrayList<>();
		Queue<Vertex<Type>> queue = new LinkedList<>();
		
		// Set the indegrees of all vertices in the graph
		for(Type v : destinations) {
			Vertex<Type> temp = graph.getVertex(v);
			int indegree = temp.getIndegree();
			temp.setIndegree(indegree++);
		}
		
		// Add all of the vertices with indegrees of 0 to the queue
		for(Vertex<Type> vert : graph.getVertices()) {
			if(vert.getIndegree() == 0) {
				queue.offer(vert);
			}
		}
		
		
		while (sorted.size() < graph.getNumVertices()) {
			// Take vertices out of the queue and add them to the sorted List
			Vertex<Type> temp = queue.poll();
			sorted.add(temp.getData());
			
			for(Vertex<Type> v: temp.getEndpoints()) {
				// Decrement the indegrees of all vertices that the current vertex leads to
				v.setIndegree(v.getIndegree() - 1); 
				
				// Add a vertex to the queue if its indegree is 0
				if (v.getIndegree() == 0) {
					queue.offer(v);
				}
			}
		}
						
		return sorted;
	}

	/**
	 * Builds "sources" and "destinations" lists according to the edges
	 * specified in the given DOT file (e.g., "a -> b"). Assumes that the vertex
	 * data type is String.
	 * 
	 * Accepts many valid "digraph" DOT files (see examples posted on Canvas).
	 * --accepts \\-style comments 
	 * --accepts one edge per line or edges terminated with ; 
	 * --does not accept attributes in [] (e.g., [label = "a label"])
	 * 
	 * @param filename - name of the DOT file
	 * @param sources - empty ArrayList, when method returns it is a valid
	 *        "sources" list that can be passed to the public methods in this
	 *        class
	 * @param destinations - empty ArrayList, when method returns it is a valid
	 *        "destinations" list that can be passed to the public methods in
	 *        this class
	 */
	public static void buildListsFromDot(String filename, ArrayList<String> sources, ArrayList<String> destinations) {

		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		}
		catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		scan.useDelimiter(";|\n");

		// Determine if graph is directed (i.e., look for "digraph id {").
		String line = "", edgeOp = "";
		while(scan.hasNext()) {
			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");

			if(line.indexOf("digraph") >= 0) {
				edgeOp = "->";
				line = line.replaceFirst(".*\\{", "");
				break;
			}
		}
		if(edgeOp.equals("")) {
			System.out.println("DOT graph must be directed (i.e., digraph).");
			scan.close();
			System.exit(0);

		}

		// Look for edge operator -> and determine the source and destination
		// vertices for each edge.
		while(scan.hasNext()) {
			String[] substring = line.split(edgeOp);

			for(int i = 0; i < substring.length - 1; i += 2) {
				// remove " and trim whitespace from node string on the left
				String vertex1 = substring[0].replace("\"", "").trim();
				// if string is empty, try again
				if(vertex1.equals(""))
					continue;

				// do the same for the node string on the right
				String vertex2 = substring[1].replace("\"", "").trim();
				if(vertex2.equals(""))
					continue;

				// indicate edge between vertex1 and vertex2
				sources.add(vertex1);
				destinations.add(vertex2);
			}

			// do until the "}" has been read
			if(substring[substring.length - 1].indexOf("}") >= 0)
				break;

			line = scan.next();

			// Skip //-style comments.
			line = line.replaceFirst("//.*", "");
		}

		scan.close();
	}
}