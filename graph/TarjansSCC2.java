package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class TarjansSCC2 {

    static class Vertex {
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

        @Override
        public String toString() {
            return name;
        }
    }

    static class Component {
        Component(Vertex vertex, int lowLinkValue) {
            this.vertex = vertex;
            this.lowLinkValue = lowLinkValue;
        }

        Vertex vertex;
        int lowLinkValue;

        @Override
        public boolean equals(Object obj) {
            Component v2 = (Component) obj;
            return v2.vertex.equals(this.vertex);
        }
    }

    static class Graph {
        Set<Vertex> vertices = new HashSet<>();

        public void addEdge(Vertex source, Vertex dest) {
            source.getAdjList().add(dest);
            vertices.add(source);
            vertices.add(dest);
        }
    }

    static HashMap<Vertex, Integer> discoveryTime = new HashMap<>();
    static HashMap<Vertex, Integer> discoveryTimeLow = new HashMap<>();
    static int Time = 0;

    public static void main(String[] args) {
        Graph g1 = new Graph();
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
        g1.addEdge(v1, v0);
        g1.addEdge(v0, v2);
        g1.addEdge(v2, v1);
        g1.addEdge(v0, v3);
        g1.addEdge(v3, v4);
        print(g1);
        Set<Vertex> visited = new HashSet<>();
        assignLL(v0, visited);
        for (Vertex v : g1.vertices) {
            if (!visited.contains(v)) {
                List<Vertex> sccList = getSCC(v, visited);
                for (Vertex scc : sccList) {
                    System.out.print(scc.name + " ");
                }
                System.out.println();
            }
        }

        Graph g4 = new Graph();
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
        print(g4);
        System.out.println("\nSSC in fourth graph ");
        visited = new HashSet<>();
        for (Vertex v : g1.vertices) {
            if (!visited.contains(v)) {
                List<Vertex> sccList = getSCC(v, visited);
                for (Vertex scc : sccList) {
                    System.out.print(scc.name + " ");
                }
                System.out.println();
            }
        }
    }

    static List<Vertex> getSCC(Vertex v, Set<Vertex> visited) {
        Stack<Component> stack = new Stack<>();
        List<Vertex> result = new ArrayList<>();
        SCCUtil(v, stack, visited, result);
        return result;
    }
    // Case1 (Tree Edge): If node v is not visited already, then after DFS of v is
    // complete, then minimum of low[u] and low[v] will be updated to low[u].
    // low[u] = min(low[u], low[v]);
    // Case 2 (Back Edge): When child v is already visited, then minimum of low[u]
    // and Disc[v] will be updated to low[u].
    // low[u] = min(low[u], disc[v]);

    static void SCCUtil(Vertex u, Stack<Component> stack, Set<Vertex> visited, List<Vertex> result) {
        // System.out.println("visiting " + v.name + " dist " + distance);
        Component comp = new Component(u, Time);
        // Initialize discovery time and low valu
        visited.add(u);
        discoveryTime.put(u, Time);
        discoveryTimeLow.put(u, Time);
        stack.push(comp);
        Time++;
        for (Vertex n : u.adjList) {
            if (!visited.contains(n)) {
                SCCUtil(n, stack, visited, result);
                // Check if the subtree rooted with v
                // has a connection to one of the
                // ancestors of u
                // Case 1 (per above discussion on
                // Disc and Low value)
                discoveryTimeLow.put(u, Math.min(discoveryTimeLow.get(u), discoveryTimeLow.get(n)));
            } else if (stack.contains(n)) {
                // Update low value of 'u' only if 'v' is
                // still in stack (i.e. it's a back edge,
                // not cross edge).
                // Case 2 (per above discussion on Disc
                // and Low value)
                discoveryTimeLow.put(u, Math.min(discoveryTimeLow.get(u), discoveryTime.get(n)));
                System.out.println("u => " + u.name + " n => " + n.name);
                System.out.println("discoveryTimeLow.get(u) => " + discoveryTimeLow.get(u));
                System.out.println("discoveryTimeLow.get(n) => " + discoveryTimeLow.get(n));
                System.out.println("discoveryTime.get(n) => " + discoveryTime.get(n));
                // low[u] = Math.min(low[u], disc[n]);
            }
        }
        if (discoveryTimeLow.get(u) == discoveryTime.get(u)) {
            Component pop = null;
            while (pop == null || !(pop.vertex == u)) {
                pop = stack.pop();
                System.out.print(pop.vertex.name + " ,");
            }
            System.out.println();
        }
    }

    static void print(Graph graph) {
        for (Vertex v : graph.vertices) {
            System.out.print(v.name);
            for (Vertex adj : v.adjList) {
                System.out.print(" => " + adj.name);
            }
            System.out.println();
        }
    }

    static Stack<Component> holder = new Stack<>();

    static Stack<Component> assignLL(Vertex u, Set<Vertex> visited) {
        if (visited.contains(u))
            return holder;
        Component comp = new Component(u, Time);
        System.out.println("u => " + u.name + " low " + Time);
        Time++;
        holder.push(comp);
        visited.add(u);
        for (Vertex adj : u.adjList) {
            assignLL(adj, visited);
        }
       
        return holder;
    }
}
