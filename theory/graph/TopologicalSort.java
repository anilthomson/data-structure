package theory.graph;

import java.util.HashSet;
import java.util.Stack;

public class TopologicalSort {
    /*
     * A topological ordering is an ordering of nodes in a directed graph where for
     * each direted edge from Node A to Node B , node A appears before node B in the
     * ordering
     */
    // implementation by doing a DFS and push the found nodes to a stack.
    // Once finished, pop out the nodes from the stack
    HashSet<Vertex> visited = new HashSet<>();
    Stack<Vertex> stack = new Stack<Vertex>();

    void topSortUtil(Vertex vertex) {
        visited.add(vertex);
        for (Vertex adj : vertex.getAdjList()) {
            if (!visited.contains(adj)) {
                topSortUtil(adj);
            }
        }
        stack.push(vertex);
    }

    public static void main(String[] args) {
        Graph graph = Graph.build();
        TopologicalSort topSort = new TopologicalSort();
        for (Vertex v : graph.vertices) {
            if (!topSort.visited.contains(v)) {
                topSort.topSortUtil(v);
            }
        }
        while (!topSort.stack.isEmpty()) {
            System.out.print(topSort.stack.pop().name + " ");
        }
    }
}