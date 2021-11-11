package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Tarjan {
    class Vertex {
        String name;
        int nodeId;
        int lowLinkValue;

        public Vertex(String n) {
            name = n;
        }

        List<Vertex> adjList = new ArrayList<Vertex>();

        public List<Vertex> getAdjList() {
            return adjList;
        }

    }

    class Graph {
        Set<Vertex> vertices = new HashSet<>();
        Vertex start;

        public void addEdge(Vertex source, Vertex dest) {
            source.getAdjList().add(dest);
            vertices.add(source);
            vertices.add(dest);
            if (start == null)
                start = source;
        }
    }

    Graph build() {
        Graph g1 = new Graph();
        Vertex v0 = new Vertex("0");
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        g1.addEdge(v0, v1);
        g1.addEdge(v1, v2);
        g1.addEdge(v2, v0);
        g1.addEdge(v3, v4);
        g1.addEdge(v4, v3);
        return g1;
    }

    Graph build2() {
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
        g4.addEdge(v2, v0);
        g4.addEdge(v2, v6);
        g4.addEdge(v3, v2);
        g4.addEdge(v4, v5);
        g4.addEdge(v4, v6);
        g4.addEdge(v5, v6);
        g4.addEdge(v5, v7);
        g4.addEdge(v5, v8);
        g4.addEdge(v5, v9);
        g4.addEdge(v6, v4);
        g4.addEdge(v7, v9);
        g4.addEdge(v8, v9);
        g4.addEdge(v9, v8);
        return g4;
    }

    Stack<Vertex> stack = new Stack();
    HashSet<Vertex> visited = new HashSet<>();
    int globalId;
    // Case1 (Tree Edge): If node v is not visited already, then after DFS of v is
    // complete, then minimum of low[u] and low[v] will be updated to low[u].
    // low[u] = min(low[u], low[v]);
    // Case 2 (Back Edge): When child v is already visited, then minimum of low[u]
    // and Disc[v] will be updated to low[u].
    // low[u] = min(low[u], disc[v]);
    void SCC(Vertex v) {
        if (visited.contains(v))
            return;
        visited.add(v);
        stack.add(v);
        v.lowLinkValue = globalId;
        v.nodeId = globalId;
        globalId++;
        for (Vertex n : v.adjList) {
            SCC(n);
            //if the child is in stack
            if (stack.contains(n))
                v.lowLinkValue = Math.min(n.lowLinkValue, v.lowLinkValue);
        }
        if (v.nodeId == v.lowLinkValue) {

            Vertex node = null;
            while (node != v) {
                node = stack.pop();
                System.out.print(node.name + ",");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tarjan targ = new Tarjan();
        Graph graph = targ.build();
        for (Vertex v : graph.vertices) {
            if (!targ.visited.contains(v)) {
                targ.SCC(v);
            }
        }
        System.err.println();
    }

}
