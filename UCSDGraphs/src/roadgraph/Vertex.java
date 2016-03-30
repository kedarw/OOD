package roadgraph;

import java.util.HashSet;

import geography.GeographicPoint;

/**
 * @author Kedar Waghmode
 * 
 * A class which represents a vertex in graph of geographic locations
 * using longitude and latitude(GeographicPoint) and edges
 *
 */
public class Vertex {
	private GeographicPoint vertex;
	private HashSet<Edge> edges;
	
	/** 
	 * Create a new vertex and set of edges associated with it for MapGraph 
	 */
	public Vertex(GeographicPoint vertex) {
		this.vertex 	= vertex;
		this.edges	= new HashSet<Edge>();
	}
	
	/**
	 * Add edge to current(this) vertex
	 * @return nothing
	 */
	public void addEdge(Edge edge) {
		//Check if already an edge
		if(this.edges.contains(edge)) {
			return;
		}
		else {
			this.edges.add(edge);
		}
	}
	
	/**
	 * Get set of neighboring vertices for current(this) vertex
	 * @return The set  of vertices
	 */
	public HashSet<Vertex> getNeighbors() {
		HashSet<Vertex> vertices = new HashSet<Vertex>();
		for(Edge edge: edges) {
			vertices.add(edge.getOtherVertex(this));
		}
		return vertices;
	}
	
	/**
	 * Getter for vertex member variable
	 * @return this vertex
	 */
	public GeographicPoint getVertex() {
		return vertex;
	}

	/**
	 * Return edges for current vertex object, this method will be useful during bfs
	 * @return set of edges associated with this vertex
	 */
	public HashSet<Edge> getEdges(){
		return this.edges;
	}
}
