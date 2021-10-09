package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class WeightedGraph {
    static PriorityQueue<Edge> pq = new PriorityQueue<>((e, f) -> {
        if (e.weight > f.weight)
            return +1;
        else if (e.weight == f.weight)
            return 0;
        return -1;
    });

    static class Edge {
        Vertex source;
        Vertex destination;
        int weight;

        public Edge(Vertex source, Vertex destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Vertex {
        public Vertex(String name) {
            this.name = name;
        }

        boolean visited;
        String name;

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return name.equals(((Vertex) obj).name);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class NodePath {
        Vertex current;
        Vertex parent;
        int distance = Integer.MAX_VALUE;

        NodePath(Vertex current, Vertex parent, int dist) {
            this.current = current;
            this.parent = parent;
            distance = dist;
        }

        @Override
        public String toString() {
            return String.valueOf(distance);
        }
    }

    static class Graph {
        int vertices;
        int edges;
        Map<Vertex, LinkedList<Edge>> adjacencylist;
        ArrayList<Edge> edgeList = new ArrayList<Edge>();

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencylist = new HashMap<Vertex, LinkedList<Edge>>();
        }

        public void addEgde(Vertex source, Vertex destination, int weight) {
            addDirectedEgde(source, destination, weight);
            //addDirectedEgde(destination, source, weight);
            edges++;
        }

        public void addDirectedEgde(Vertex source, Vertex destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            edgeList.add(edge);
            LinkedList<Edge> linkedList = adjacencylist.get(source);
            if (linkedList == null) {
                linkedList = new LinkedList<>();
                adjacencylist.put(source, linkedList);
            }
            linkedList.addFirst(edge); // for directed graph
        }

        // public void addEgde(String source, String destination, int weight) {
        // addEgde(new Vertex(source), new Vertex(destination), weight);
        // }

        public Vertex getVertex() {
            return adjacencylist.keySet().iterator().next();
        }

        public void printGraph() {
            Set<Vertex> keySet = adjacencylist.keySet();
            for (Vertex vertex : keySet) {
                LinkedList<Edge> list = adjacencylist.get(vertex);
                for (Edge edge : list) {
                    System.out.println("vertex - " + edge.source.name + " is connected to " + edge.destination.name
                            + " with weight " + edge.weight);
                    // pq.add(edge);
                }
            }
        }
    }

    static void priorityQueue(Graph graph) {
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            System.out.println(
                    edge.source.name + " is connected to " + edge.destination.name + " with weight " + edge.weight);
        }
    }

    static List<Edge> MinimumSpanningTree(Graph graph) {
        Vertex vertex = graph.getVertex();
        List<Edge> mst = new ArrayList<Edge>();
        vertex.visited = true;
        addChildrenToPriorityQ(graph, vertex);
        // && mst.size() < graph.vertices - 1 Edges =verteces-1
        while (!pq.isEmpty() && mst.size() < graph.vertices - 1) {
            System.err.println("Edges " + mst.size());
            Edge edge = pq.poll();
            // System.err.println(edge.destination.name + edge.destination.visited);
            if (edge.destination.visited)
                continue;
            mst.add(edge);
            edge.destination.visited = true;
            addChildrenToPriorityQ(graph, edge.destination);
        }
        return mst;
    }

    static void addChildrenToPriorityQ(Graph graph, Vertex vertex) {
        LinkedList<Edge> linkedList = graph.adjacencylist.get(vertex);
        if (linkedList == null)
            return;
        for (Edge edge : linkedList) {
            if (!edge.destination.visited)
                pq.add(edge);
        }
    }

    static void printMST(List<Edge> edgeList) {
        int i = 0;
        for (Edge edge : edgeList) {
            System.out.println(
                    edge.source.name + " is connected to " + edge.destination.name + " with weight " + edge.weight);
            i += edge.weight;
        }
        System.out.println("Total weight = " + i);
    }

    static void findAllPath(Vertex start, Vertex end, Graph graph, List<Vertex> path, List<List<Vertex>> result) {

        path.add(start);
        start.visited = true;
        LinkedList<Edge> edgedList = graph.adjacencylist.get(start);

        if (start.equals(end)) {
            List<Vertex> copy = new ArrayList<>(path);
            result.add(copy);
            path.remove(end);
            end.visited = false;
            // System.out.println(start.name);
        }
        for (Edge edge : edgedList) {
            Vertex adj = edge.destination;
            if (!adj.visited) {
                findAllPath(adj, end, graph, path, result);
                adj.visited = false;
                path.remove(adj);
            }
        }

        // System.out.println("Done "+start.name);
    }

    static Map<Vertex, Edge> ShortestPath(Graph graph, Vertex start, Vertex end) {
        List<Vertex> path = new ArrayList<>();
        // Queue<Vertex> queue = new LinkedList<Vertex>();
        Map<Vertex, Edge> map = new HashMap<>();
        map.put(start, null);
        path.add(start);
        start.visited = true;
        // queue.add(start);
        addChildrenToPriorityQ(graph, start);
        List<Vertex> list = new ArrayList<>();
        list.add(start);
        while (pq.size() != 0) {
            Edge edge = pq.poll();
            if (edge.destination.visited)
                continue;
            map.put(edge.destination, edge);
            if (edge.destination.name.equals(end.name))
                return map;
            // mst.add(edge);
            edge.destination.visited = true;
            addChildrenToPriorityQ(graph, edge.destination);
        }
        return map;
    }

    static Map<Vertex, NodePath> Dijkstra(Graph graph, Vertex start, Vertex end) {
        PriorityQueue<NodePath> pq = new PriorityQueue<>((e, f) -> {
            if (e.distance > f.distance)
                return +1;
            else if (e.distance == f.distance)
                return 0;
            return -1;
        });
        Map<Vertex, NodePath> map = new HashMap<>();
        NodePath nodePath = new NodePath(start, null, 0);
        pq.add(nodePath);
        while (!pq.isEmpty()) {
            nodePath = pq.poll();
            if (nodePath.current.name.equals(end.name))
                return map;
            if (nodePath.current.visited)
                continue;
            map.put(nodePath.current, nodePath);
            nodePath.current.visited = true;
            addChildrenToNodepathQ(graph, nodePath, pq, map);
        }
        System.out.println(map);
        return map;
    }

    static void addChildrenToNodepathQ(Graph graph, NodePath parentPath, PriorityQueue<NodePath> pq,
            Map<Vertex, NodePath> map) {
        LinkedList<Edge> linkedList = graph.adjacencylist.get(parentPath.current);
        if (linkedList == null)
            return;
        for (Edge edge : linkedList) {
            if (!edge.destination.visited) {
                NodePath childPath = map.get(edge.destination);
                if (childPath == null) {
                    childPath = new NodePath(edge.destination, edge.source, edge.weight + parentPath.distance);
                    map.put(edge.destination, childPath);
                } else if (childPath.distance > (edge.weight + parentPath.distance)) {
                    childPath.distance = edge.weight + parentPath.distance;
                    childPath.parent = edge.source;
                }
                pq.add(childPath);
            }
        }

    }

    static void BellmanFord(Graph graph, Vertex src) {

        Map<Vertex, NodePath> map = new HashMap<>();
        NodePath nodePath = new NodePath(src, null, 0);
        map.put(nodePath.current, nodePath);
        for (int i = 1; i < graph.vertices; ++i) {
            System.out.println(" Pass " + i);
            for (Edge edge : graph.edgeList) {
                // System.out.println("Source "+edge.source + " -> Destination " +
                // edge.destination);
                NodePath childPath = map.get(edge.destination);
                NodePath parentPath = map.get(edge.source);
                if (childPath == null) {
                    childPath = new NodePath(edge.destination, edge.source, edge.weight + parentPath.distance);
                    map.put(edge.destination, childPath);
                    // System.out.println(
                    // edge.source + " -> " + edge.destination + " -> " + (edge.weight +
                    // parentPath.distance));
                } else if (childPath.distance > (edge.weight + parentPath.distance)) {
                    childPath.distance = edge.weight + parentPath.distance;
                    childPath.parent = edge.source;
                }
            }
            System.out.println(map);
        }
        for (Edge edge : graph.edgeList) {
            NodePath childPath = map.get(edge.destination);
            NodePath parentPath = map.get(edge.source);
            if (parentPath.distance + edge.weight < childPath.distance) {
                System.out.println("CreateGraph contains negative  cycle " + edge.source + " -> " + edge.destination);
            }

        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        int vertices = 8;
        Graph graph = new Graph(vertices);
        Vertex v0 = new Vertex("0");
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        Vertex v5 = new Vertex("5");
        Vertex v6 = new Vertex("6");
        Vertex v7 = new Vertex("7");
        graph.addEgde(v0, v1, 10);
        graph.addEgde(v0, v2, 1);
        graph.addEgde(v0, v3, 4);
        graph.addEgde(v1, v2, 33);
        graph.addEgde(v1, v4, 0);
        graph.addEgde(v2, v5, 8);
        graph.addEgde(v2, v3, 2);
        graph.addEgde(v3, v5, 2);
        graph.addEgde(v3, v6, 7);
        graph.addEgde(v4, v5, 1);
        graph.addEgde(v4, v7, 8);
        graph.addEgde(v5, v7, 9);
        graph.addEgde(v5, v6, 6);
        graph.addEgde(v6, v7, 16);
        graph.addEgde(v6, v2, -18);
        graph.printGraph();
        // priorityQueue(graph);
        // List<Edge> mst = MinimumSpanningTree(graph);
        // printMST(mst);
        List<List<Vertex>> result = new ArrayList<>();
        // findAllPath(v0, v7, graph, new ArrayList<>(), result);
        // print(result);
        // Map<Vertex, Edge> shortestPath = ShortestPath(graph, v1, v0);
        // int weight = 0;
        // while (v0 != null) {
        // System.out.print(v0 + " -> ");
        // Edge edge = shortestPath.get(v0);
        // if (edge == null)
        // break;
        // v0 = edge.source;
        // weight += edge.weight;
        // }
        // System.out.println("Total weight = " + weight);
        BellmanFord(graph, v0);
        Map<Vertex, NodePath> dijkstra = Dijkstra(graph, v0, v7);
        NodePath nodePath = dijkstra.get(v7);
        System.out.print(nodePath.current + " -> ");
        int i = nodePath.distance;
        while (nodePath.parent != null) {
            System.err.print(nodePath.parent + " -> ");
            nodePath = dijkstra.get(nodePath.parent);
            // i += nodePath.distance;
        }
        System.err.println();
        System.err.println(i);
    }

    static void print(List<List<Vertex>> result) {
        for (List<Vertex> list : result) {
            for (Vertex vertex : list) {
                System.out.print(vertex + " -> ");
            }
            System.out.println();
        }
    }
}