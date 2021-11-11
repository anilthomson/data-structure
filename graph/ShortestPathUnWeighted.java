package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ShortestPathUnWeighted {
    static class Vertex {
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

        public void addEdge(Vertex source, Vertex dest) {
            source.getAdjList().add(dest);
            dest.getAdjList().add(source);
        }

        public Vertex getVertex(String key) {
            return map.get(key);
        }
    }

    public static void main(String[] args) {
        Graph graph = build();
        
     /*   Vertex v3 = graph.getVertex("8");
        Vertex v7 = graph.getVertex("5");
        Map<Vertex, Vertex> shortestPath = ShortestPath(v7, v3);
        while (v3 != null) {
            System.out.println(v3.name);
            v3 = shortestPath.get(v3);
        }*/
    }

    static Map<Vertex, Vertex> ShortestPath(Vertex start, Vertex end) {
        List<Vertex> path = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<Vertex>();
        Map<Vertex, Vertex> map = new HashMap<>();
        map.put(start, null);
        path.add(start);
        queue.add(start);
        List<Vertex> list = new ArrayList<>();
        list.add(start);
        while (queue.size() != 0) {
            Vertex parent = queue.poll();
            parent.visited = true;
            List<Vertex> adjList = parent.getAdjList();
            for (Vertex child : adjList) {
                if (!child.visited) {
                    queue.add(child);
                    map.put(child, parent);
                    if (child.name.equals(end.name))
                        return map;
                }
            }

        }
        return map;
    }

    
    static Graph build() {
        Graph graph = new Graph();
        Vertex v0 = new Vertex("0");
        graph.addVertex(v0);

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

        graph.addEdge(v0, v1);
        graph.addEdge(v0, v3);
        graph.addEdge(v0, v8);
        graph.addEdge(v1, v7);

        graph.addEdge(v2, v3);
        graph.addEdge(v2, v7);
        graph.addEdge(v2, v5);
        graph.addEdge(v3, v4);
        graph.addEdge(v4, v8);
        graph.addEdge(v5, v6);

        return graph;

    }
}
