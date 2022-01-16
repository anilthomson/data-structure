package theory.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Graph_BFS {
	HashSet<Vertex> visited = new HashSet<>();

	void BFS(Vertex u) {
		Queue<Vertex> queue = new LinkedList<Vertex>();
		queue.add(u);
		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			Vertex v = queue.poll();
			if (!visited.contains(v)) {
				System.out.println(v.name);
				visited.add(v);
				queue.addAll(v.getAdjList());
			}
		}
	}

	public static void main(String[] args) {
		Graph graph = Graph.build();
		Graph_BFS bfs = new Graph_BFS();

		for (Vertex v : graph.vertices) {
			if (!bfs.visited.contains(v)) {
				bfs.BFS(v);
			}
		}
	}
}
