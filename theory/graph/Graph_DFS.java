package theory.graph;

import java.util.HashSet;

public class Graph_DFS {
	HashSet<Vertex> visited = new HashSet<>();

	void DFS(Vertex v) {
		System.out.println(v.name);
		visited.add(v);
		for (Vertex adj : v.getAdjList()) {
			if (!visited.contains(adj))
				DFS(adj);
		}
		// System.out.println("Done "+start.name);
	}

	public static void main(String[] args) {
		Graph graph = Graph.build();
		Graph_DFS dfs = new Graph_DFS();

		for (Vertex v : graph.vertices) {
			if (!dfs.visited.contains(v)) {
				dfs.DFS(v);
			}
		}
	}
}
