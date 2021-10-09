package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class GraphLCA {
    static class Vertex {
        String name;
        boolean visited;
        boolean visiting;
        Vertex parent;

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
        @Override
        public String toString() {
             return name;
        }
    }

    static class Graph {
        List<Vertex> adjList = new ArrayList<Vertex>();
        Map<String, Vertex> map = new HashMap<String, Vertex>();
        int nodeCount;

        public void addVertex(Vertex v) {
            adjList.add(v);
            map.put(v.name, v);
            nodeCount++;
        }

        // undirected
        public void addEdge(Vertex source, Vertex dest) {
            source.getAdjList().add(dest);
            dest.getAdjList().add(source);
            dest.parent = source;
        }

        public Vertex getVertex(String key) {
            return map.get(key);
        }

        boolean findPath(Vertex start, Vertex end, List<Vertex> path) {
            path.add(start);
            // System.out.println("adding " + start.name);
            start.visited = true;
            if (start.equals(end)) {
                return true;
            }
            for (Vertex adj : start.getAdjList()) {
                if (!adj.visited) {
                    boolean found = findPath(adj, end, path);
                    if (found)
                        return found;
                    adj.visited = false;
                    path.remove(adj);
                    // System.out.println("removing " + adj.name);
                }

            }
            return false;
        }
    }

    static List<Vertex> fillGraph(Graph graph, int vertexCount) {
        List<Vertex> vertices = new ArrayList<Vertex>();
        vertices.add(null);
        for (int i = 1; i <= vertexCount; i++) {
            Vertex v = new Vertex(String.valueOf(i));
            graph.addVertex(v);
            vertices.add(v);
        }
        return vertices;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        List<Vertex> vertices = fillGraph(graph, 19);
        graph.addEdge(vertices.get(1), vertices.get(2));
        graph.addEdge(vertices.get(1), vertices.get(3));
        graph.addEdge(vertices.get(2), vertices.get(4));
        graph.addEdge(vertices.get(2), vertices.get(5));
        graph.addEdge(vertices.get(3), vertices.get(6));
        graph.addEdge(vertices.get(3), vertices.get(7));
        graph.addEdge(vertices.get(4), vertices.get(8));
        graph.addEdge(vertices.get(4), vertices.get(9));
        graph.addEdge(vertices.get(5), vertices.get(10));
        graph.addEdge(vertices.get(5), vertices.get(11));
        graph.addEdge(vertices.get(8), vertices.get(12));
        graph.addEdge(vertices.get(8), vertices.get(13));
        graph.addEdge(vertices.get(9), vertices.get(14));
        graph.addEdge(vertices.get(9), vertices.get(15));
        graph.addEdge(vertices.get(10), vertices.get(16));
        graph.addEdge(vertices.get(10), vertices.get(17));
        graph.addEdge(vertices.get(11), vertices.get(18));
        graph.addEdge(vertices.get(11), vertices.get(19));
        printGraph(graph);
        List<Vertex> path = new ArrayList<>();
        graph.findPath(vertices.get(16), vertices.get(7), path);
        printList(path);
        Vertex lca = findParent(path);
        System.err.println(lca.name);
    }

    static Vertex findParent(List<Vertex> path) {
        int count = path.size();
        for (int i = 0; i <= count / 2; i++) {
            Vertex v1 = path.get(i);
            Vertex v2 = path.get(count - i - 1);
            if (v1 == v2)
                return v1;
            System.out.println("\nv1  " + v1.getName() + " v2 " + v2.name);
            if (v1.parent == v2.parent)
                return v1.parent;
            if (v1.parent == v2)
                return v2;
            if (v1 == v2.parent)
                return v1;

        }
        return null;
    }

    static void printList(List<Vertex> path) {
        for (Vertex vertex : path) {
            System.out.println("\nPath  " + vertex + " Parent " + vertex );
        }
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
