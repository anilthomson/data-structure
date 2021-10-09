package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class Vertex {
	String name;
	boolean visited;
	boolean visiting;

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
	Map<String, Vertex> map = new HashMap<String, Vertex>();
	int nodeCount;

	public void addVertex(Vertex v) {
		adjList.add(v);
		map.put(v.name, v);
		nodeCount++;
	}

	public void addEdge(Vertex source, Vertex dest) {
		source.getAdjList().add(dest);
	}

	public Vertex getVertex(String key) {
		return map.get(key);
	}

	void findAllPath(Vertex start, Vertex end, List<Vertex> path, List<List<Vertex>> result) {

		path.add(start);
		start.visited = true;
		if (start.equals(end)) {
			List<Vertex> copy = new ArrayList<>(path);
			result.add(copy);
			path.remove(end);
			end.visited = false;
			// System.out.println(start.name);
		}
		for (Vertex adj : start.getAdjList()) {
			if (!adj.visited) {
				findAllPath(adj, end, path, result);
				adj.visited = false;
				path.remove(adj);
			}

		}
		// System.out.println("Done "+start.name);
	}

	void findCycle(Vertex vertex, Map<Vertex, Vertex> parentMap, List<List<Vertex>> result) {
		// System.out.println(vertex.name + " visiting " + vertex.visiting);
		vertex.visiting = true;
		vertex.visited = true;
		// boolean checkcycle = checkcycle(parentMap, parentMap.get(vertex), vertex);
		// System.out.println(vertex.name + " " + checkcycle);
		for (Vertex adj : vertex.getAdjList()) {

			// System.out.println(vertex.name + " child " + adj.name + " " + adj.visited);
			if (adj.visiting) {
				System.out.println(vertex.name + " cycle " + adj.name + "  " + adj.visiting);
				parentMap.put(adj, vertex);
				List<Vertex> chain = new ArrayList<>();
				chain.add(adj);
				populateChain(parentMap, adj, adj, chain);
				result.add(chain);
				System.err.println();
			}
			if (!adj.visited) {
				parentMap.put(adj, vertex);
				findCycle(adj, parentMap, result);
			}

		}
		vertex.visiting = false;

	}

	List<Vertex> populateChain(Map<Vertex, Vertex> parentMap, Vertex vertex, Vertex parent, List<Vertex> list) {

		parent = parentMap.get(parent);

		if (parent.equals(vertex))
			return list;
		else {
			list.add(parent);
			return populateChain(parentMap, vertex, parent, list);
		}

	}

	void DFS(Vertex start) {
		System.out.println(start.name);
		start.visited = true;
		for (Vertex adj : start.getAdjList()) {
			if (!adj.visited)
				DFS(adj);
		}
		// System.out.println("Done "+start.name);
	}

	void BFS(Vertex start) {
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(start);
		// queue.addAll(start.getAdjList());
		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			Vertex v = queue.poll();
			if (!v.visited) {
				System.out.println(v.name);
				v.visited = true;
				queue.addAll(v.getAdjList());
			}
		}
	}

	Stack<Vertex> topSort() {
		Stack<Vertex> stack = new Stack<Vertex>();
		for (int i = adjList.size() - 1; i >= 0; i--) {
			Vertex vertex = adjList.get(i);
			if (!vertex.visited)
				topSortUtil(vertex, stack);
		}

		System.out.println();
		return stack;

	}

	void topSortUtil(Vertex vertex, Stack<Vertex> stack) {
		vertex.visited = true;
		for (Vertex adj : vertex.getAdjList()) {
			if (!adj.visited){
				System.out.println("visiting "+adj.name);
				topSortUtil(adj, stack);
			}
		}
		stack.push(vertex);
		System.out.print(vertex.name + " ");
	}
}

class Test {
	public static void main(String[] args) {
		Graph graph = new Graph();

		Vertex v1 = new Vertex("1");
		graph.addVertex(v1);
		
		Vertex v3 = new Vertex("3");
		graph.addVertex(v3);
		
		Vertex v5 = new Vertex("5");
		graph.addVertex(v5);
		Vertex v6 = new Vertex("6");
		graph.addVertex(v6);
		
		Vertex v8 = new Vertex("8");
		graph.addVertex(v8);
		Vertex v4 = new Vertex("4");
		graph.addVertex(v4);
		Vertex v7 = new Vertex("7");
		graph.addVertex(v7);
		Vertex v2 = new Vertex("2");
		graph.addVertex(v2);


		graph.addEdge(v1, v2);
		graph.addEdge(v1, v3);
		graph.addEdge(v1, v4);
		graph.addEdge(v1, v5);

		graph.addEdge(v2, v6);
		graph.addEdge(v2, v7);
		graph.addEdge(v2, v8);
		graph.addEdge(v3, v8);
		graph.addEdge(v7, v8);
		graph.addEdge(v4, v5);
		graph.addEdge(v5, v6);
		graph.addEdge(v6, v7);
		// graph.addEdge(v6, v4);
		// graph.addEdge(v3, v3);
		// graph.addEdge(v5, v1);
		printGraph(graph);
		/*
		 * graph.addEdge(v2, v1); graph.addEdge(v3, v4); graph.addEdge(v3, v1);
		 * graph.addEdge(v3, v2); graph.addEdge(v4, v0); graph.addEdge(v4, v1);
		 * graph.addEdge(v4, v3);
		 */

		// graph.addVertex(new Vertex("1"));
		// graph.addVertex(new Vertex("1"));
		// graph.addVertex(new Vertex("1"));
		// graph.addVertex(new Vertex("1"));
		// Vertex start = graph.getVertex("1");
		// Map<Vertex, Vertex> map = new HashMap<>();
		// List<List<Vertex>> result = new ArrayList<>();
		// graph.findCycle(start, map, result);
		// graph.DFS(start);
		Stack<Vertex> topSort = graph.topSort();
		while (!topSort.isEmpty()) {
			System.out.print(topSort.pop().name + " ");
		}
		System.out.println();
		// graph.BFS(start);
		/*
		 * List<List<Vertex>> result = new ArrayList<>(); graph.findAllPath(v1, v5, new
		 * ArrayList<>(), result); for (List<Vertex> list : result) { for (Vertex vertex
		 * : list) { System.out.print(vertex.name + "  "); } System.out.println(); }
		 * System.out.println();
		 */
	}

	static void printGraph(Graph graph) {
		List<Vertex> adj = graph.adjList;
		for (int i = 0; i < adj.size(); i++) {
			Vertex vertex = adj.get(i);
			System.out.println("\nAdjacency list of  " + vertex.getName());
			for (int j = 0; j < vertex.getAdjList().size(); j++) {
				System.out.println(vertex.getName() + " -> " + vertex.getAdjList().get(j).getName());
			}
			System.out.println();
		}
	}

	 
}
