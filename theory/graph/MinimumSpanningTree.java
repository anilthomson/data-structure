package theory.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningTree {

    HashSet<Vertex> visited = new HashSet<>();
    PriorityQueue<Edge> pq = new PriorityQueue<>((e, f) -> {
        if (e.weight > f.weight)
            return +1;
        else if (e.weight == f.weight)
            return 0;
        return -1;
    });

    List<Edge> GenerateMinimumSpanningTree(Graph graph) {
        // Do a dfs with a priority queue
        Vertex vertex = graph.getVertex();
        List<Edge> mst = new ArrayList<Edge>();
        visited.add(vertex);
        addChildrenToPriorityQ(graph, vertex);
        // && mst.size() < graph.vertices - 1 Edges =verteces-1
        // Repeat untill it covers all vertices using vertex count
        while (!pq.isEmpty() && mst.size() < graph.getVertexCount() - 1) {
            System.err.println("Edges " + mst.size());
            Edge edge = pq.poll();
            // System.err.println(edge.destination.name + edge.destination.visited);
            if (visited.contains(edge.destination))
                continue;
            mst.add(edge);
            visited.add(edge.destination);
            addChildrenToPriorityQ(graph, edge.destination);
        }
        return mst;
    }

    void addChildrenToPriorityQ(Graph graph, Vertex vertex) {
        LinkedList<Edge> linkedList = graph.adjacencylist.get(vertex);
        if (linkedList == null)
            return;
        for (Edge edge : linkedList) {
            if (!visited.contains(edge.destination))
                pq.add(edge);
        }
    }

    void printMST(List<Edge> edgeList) {
        int i = 0;
        for (Edge edge : edgeList) {
            System.out.println(
                    edge.source.name + " is connected to " + edge.destination.name + " with weight " + edge.weight);
            i += edge.weight;
        }
        System.out.println("Total weight = " + i);
    }

    public static void main(String[] args) {
        Graph graph = Graph.buildWeightedGraph();
        MinimumSpanningTree mst = new MinimumSpanningTree();
        List<Edge> result = mst.GenerateMinimumSpanningTree(graph);
        mst.printMST(result);
    }
}
