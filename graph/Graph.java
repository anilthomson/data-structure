package graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class Vertex {
	String name;

	public Vertex(String n) {
		name = n;
	}

	List<Vertex> adjList = new ArrayList<Vertex>();

	public List<Vertex> getAdjList() {
		return adjList;
	}

	public String getName() {
		return name;
	}
}

class Graph {
	List<Vertex> adjList = new ArrayList<Vertex>();

	public void addVertex(Vertex v) {
		adjList.add(v);
	}

	public void addEdge(Vertex source, Vertex dest) {
		source.getAdjList().add(dest);
	}
}

class Test {
	public static void main(String[] args) {
		Graph graph = new Graph();
		Vertex v0 = new Vertex("0");
		graph.addVertex(v0);
		Vertex v1 = new Vertex("1");
		graph.addVertex(v1);
		Vertex v2 = new Vertex("2");
		graph.addVertex(v2);
		Vertex v3 = new Vertex("3");
		graph.addVertex(v3);
		Vertex v4 = new Vertex("4");
		graph.addVertex(v4);
		graph.addEdge(v0, v1);
		graph.addEdge(v0, v4);
		graph.addEdge(v1, v2);
		graph.addEdge(v1, v3);
		graph.addEdge(v1, v4);
		graph.addEdge(v1, v0);

		graph.addEdge(v2, v3);
		graph.addEdge(v2, v1);
		graph.addEdge(v3, v4);
		graph.addEdge(v3, v1);
		graph.addEdge(v3, v2);
		graph.addEdge(v4, v0);
		graph.addEdge(v4, v1);
		graph.addEdge(v4, v3);
		printGraph(graph);
		// graph.addVertex(new Vertex("1"));
		// graph.addVertex(new Vertex("1"));
		// graph.addVertex(new Vertex("1"));
		// graph.addVertex(new Vertex("1"));
	}

	static void printGraph(Graph graph) {
		List<Vertex> adj = graph.adjList;
		for (int i = 0; i < adj.size(); i++) {
			Vertex vertex = adj.get(i);
			System.out.println("\nAdjacency list of  " + vertex.getName());
			System.out.print("head");
			for (int j = 0; j < vertex.getAdjList().size(); j++) {
				System.out.print(" -> " + vertex.getAdjList().get(j).getName());
			}
			System.out.println();
		}
	}
	static 	void BFS(Graph graph,int start) 
    { 
        // Mark all the vertices as not visited(By default 
        // set as false) 
        boolean visited[] = new boolean[graph.adjList.size()]; 
  
        // Create a queue for BFS 
		LinkedList<Vertex> queue = new LinkedList<Vertex>(); 
		  // Mark the current node as visited and enqueue it 
		  visited[start]=true; 
		  queue.add(graph.adjList.get(start)); 
	
		  while (queue.size() != 0) 
		  { 
			  // Dequeue a vertex from queue and print it 
			  Vertex v = queue.poll(); 
			  System.out.print(v.getName()+" "); 
	
			  // Get all adjacent vertices of the dequeued vertex s 
			  // If a adjacent has not been visited, then mark it 
			  // visited and enqueue it 
			  Iterator<Vertex> i = v.getAdjList().listIterator(); 
			  while (i.hasNext()) 
			  { 
				 /*int n = i.next(); 
				  if (!visited[n]) 
				  { 
					  visited[n] = true; 
					  queue.add(n); 
				  } */
			  } 
		  } 
	  } 
	}
 