package roadgraph;

import geography.GeographicPoint;

/**
 * @author Kedar Waghmode
 * 
 * A class which represents a edge in graph of geographic locations
 * using name(name of road), type(residential, freeway etc), weight(length) in km,
 * start and end vertex of edge
 *
 */

public class Edge {
	private Vertex from;
	private Vertex to;
	private String name;
	private String type;
	private double length;
	
	/** 
	 * Create a new edge for MapGraph as per given inputs 
	 * User is expected to handle direction from->to
	 */
	public Edge(Vertex from, Vertex to, String name, String type, double length) {
		this.from = from;
		this.to = to;
		this.name = name; // name of edge e.g: main, first
		this.type = type;
		this.length = length;
	}
		
	/**
	 * Given vertex return other end of that vertex on same edge
	 * @return vertex
	 */
	public Vertex getOtherVertex(Vertex vertex) {
		if(vertex.equals(from)) {
			return to;
		}
		else if(vertex.equals(to)) {
			return from;
		}
		throw new IllegalArgumentException("Vertex "+ vertex +" is not on the edge");
	}
	
	/**
	 * Given from vertex location or GeographicPoint in other words
	 * @return vertex
	 */
	public GeographicPoint getFromLocation() {
		return from.getVertex();
	}

	/**
	 * Given to vertex location or GeographicPoint in other words
	 * @return vertex
	 */
	public GeographicPoint getToLocation() {
		return to.getVertex();
	}
}
