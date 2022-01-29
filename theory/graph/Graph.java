package theory.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph {
    Vertex start;
    Vertex end;
    Set<Vertex> vertices = new HashSet<>();
    ArrayList<Edge> edgeList = new ArrayList<Edge>();
    Map<Vertex, LinkedList<Edge>> adjacencylist = new HashMap<Vertex, LinkedList<Edge>>();;

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edgeList.size();
    }

    public void addEdge(Vertex source, Vertex dest) {
        source.getAdjList().add(dest);
        vertices.add(source);
        vertices.add(dest);
    }

    public Vertex getVertex() {
        return vertices.stream().findFirst().get();
    }

    public void addWeightedEdge(Vertex source, Vertex destination, int weight) {
        addDirectedEgde(source, destination, weight);
        addDirectedEgde(destination, source, weight);
    }

    public void addDirectedEgde(Vertex source, Vertex destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);
        vertices.add(source);
        vertices.add(destination);
        LinkedList<Edge> linkedList = adjacencylist.get(source);
        if (linkedList == null) {
            linkedList = new LinkedList<>();
            adjacencylist.put(source, linkedList);
        }
        linkedList.addFirst(edge); // for directed graph
    }

    public static Graph build() {
        Graph g4 = new Graph();

        Vertex v0 = new Vertex("0");
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        Vertex v8 = new Vertex("8");
        Vertex v9 = new Vertex("9");
        g4.addEdge(v0, v1);
        g4.addEdge(v0, v3);
        g4.addEdge(v1, v2);
        g4.addEdge(v1, v4);
        // g4.addEdge(v2, v0);
        g4.addEdge(v2, v6);
        g4.addEdge(v3, v2);
        g4.addEdge(v4, v5);
        g4.addEdge(v3, v5);
        g4.addEdge(v4, v6);
        g4.addEdge(v5, v6);
        g4.addEdge(v5, v7);
        g4.addEdge(v5, v8);
        g4.addEdge(v5, v9);
        g4.addEdge(v6, v7);
        g4.addEdge(v7, v9);
        g4.addEdge(v8, v9);
        // g4.addEdge(v9, v8);
        return g4;
    }

    public static Graph buildWeightedGraph() {
        Graph g4 = new Graph();
        Vertex v0 = new Vertex("0");
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        Vertex v8 = new Vertex("8");
        Vertex v9 = new Vertex("9");
        g4.addWeightedEdge(v0, v1, 4);
        g4.addWeightedEdge(v0, v3, 5);
        g4.addWeightedEdge(v1, v2, 1);
        g4.addWeightedEdge(v1, v4, 7);
        // g4.addWeightedEdge(v2, v0);
        g4.addWeightedEdge(v2, v6, 3);
        g4.addWeightedEdge(v3, v2, 4);
        g4.addWeightedEdge(v4, v5, 6);
        g4.addWeightedEdge(v3, v5, 1);
        g4.addWeightedEdge(v4, v6, 2);
        g4.addWeightedEdge(v5, v6, 8);
        g4.addWeightedEdge(v5, v7, 3);
        g4.addWeightedEdge(v5, v8, 5);
        g4.addWeightedEdge(v5, v9, 2);
        g4.addWeightedEdge(v6, v7, 1);
        g4.addWeightedEdge(v7, v9, 2);
        g4.addWeightedEdge(v8, v9, 8);
        // g4.addWeightedEdge(v9, v8);
        g4.start = v0;
        g4.end = v9;
        g4.printGraph(g4);
        return g4;
    }

    public void printGraph(Graph g4) {

        for (Edge edje : g4.edgeList) {
            System.out.println(edje.source.name + " => " + edje.destination.name);
        }
    }
}