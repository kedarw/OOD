/**
 *
 */
package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import util.GraphLoader;

/**
 * @author Your name here.
 *
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {
	private int numVertices;
	private int numEdges;

	//Map to represent vertex and edges pointing to other vertices 
	private Map<Integer,ArrayList<Integer>> adjListsMap;

	//Create an empty graph
	public CapGraph () {
		adjListsMap = new HashMap<Integer,ArrayList<Integer>>();
		numVertices = 0;
		numEdges = 0;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		//System.out.println("Adding vertex "+num);
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		adjListsMap.put(num,  neighbors);
		numVertices++;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		(adjListsMap.get(from)).add(to);
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		CapGraph graph = new CapGraph();
		Iterator<Entry<Integer, ArrayList<Integer>>> it = adjListsMap.entrySet().iterator();
		Boolean found = false;
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		ArrayList<Integer> indirect_neighbors = new ArrayList<Integer>();

		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			if(center == (int) pair.getKey()) {
				graph.addVertex(center);
				neighbors = (ArrayList<Integer>) pair.getValue();
				for(int n:neighbors) {
					graph.addEdge(center, n);
				}
				found = true;
			}
		}
		if(found) {
			Iterator<Entry<Integer, ArrayList<Integer>>> itr = adjListsMap.entrySet().iterator();

			while(itr.hasNext()) {
				Map.Entry pair = (Map.Entry)itr.next();
				for(int vs:neighbors ) {
					if(vs == (int) pair.getKey()) {
						graph.addVertex(vs);
						graph.addEdge(vs, center);
						indirect_neighbors = (ArrayList<Integer>) pair.getValue();
						for(int i_n:indirect_neighbors) {
							if(neighbors.contains(i_n)) {
								graph.addEdge(vs, i_n);
							}
						}
					}
				}
			}
		}

		return graph;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		HashMap<Integer, HashSet<Integer>> complete_graph = new HashMap<Integer, HashSet<Integer>>();

		Iterator<Entry<Integer, ArrayList<Integer>>> it = adjListsMap.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			int vertex = (int) pair.getKey();
			ArrayList<Integer> edges = (ArrayList<Integer>) pair.getValue();
			HashSet<Integer> neighbors = new HashSet<Integer>(edges);

			complete_graph.put(vertex, neighbors);
		}

		return complete_graph;
	}

	public void displayGraph() {
		for(int v : adjListsMap.keySet())
		{
			System.out.print(v + " --> ");
			ArrayList<Integer> neighbors = adjListsMap.get(v);
			for(int n:neighbors){
				System.out.print(n+" ");
			}
			System.out.println();
		}
	}

	public static void main(String [] args)
	{
		CapGraph small_graph = new CapGraph();

		small_graph.addVertex(32);
		small_graph.addVertex(44);
		small_graph.addVertex(50);
		small_graph.addVertex(25);
		small_graph.addVertex(23);
		small_graph.addVertex(65);
		small_graph.addVertex(18);

		small_graph.addEdge(32, 50);
		small_graph.addEdge(32, 44);
		small_graph.addEdge(44, 50);
		small_graph.addEdge(44, 32);
		small_graph.addEdge(44, 18);
		small_graph.addEdge(50, 32);
		small_graph.addEdge(50, 44);
		small_graph.addEdge(50, 23);
		small_graph.addEdge(23, 50);
		small_graph.addEdge(23, 25);
		small_graph.addEdge(23, 18);
		small_graph.addEdge(23, 65);
		small_graph.addEdge(25, 18);
		small_graph.addEdge(25, 23);
		small_graph.addEdge(25, 65);
		small_graph.addEdge(65, 25);
		small_graph.addEdge(65, 23);
		small_graph.addEdge(18, 23);
		small_graph.addEdge(18, 25);
		small_graph.addEdge(18, 44);

		System.out.println("Displaying very small raw graph");
		small_graph.displayGraph();

		/*CapGraph small_test_graph = new CapGraph();
		GraphLoader.loadGraph(small_test_graph, "data/small_test_graph.txt");
		System.out.println("Displaying small test graph");
		small_test_graph.displayGraph();*/

		CapGraph egonet_graph = (CapGraph) small_graph.getEgonet(25);
		System.out.println("<--------- Displaying ego net graph --------->");
		egonet_graph.displayGraph();
	}
}
