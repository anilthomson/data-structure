package theory.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class Dijkstra {
    Set<Vertex> visited = new HashSet<Vertex>();

    public Map<Vertex, NodePath> FindShortestPath(Graph graph, Vertex start, Vertex end) {
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
            if (visited.contains(nodePath.current))
                continue;
            map.put(nodePath.current, nodePath);
            visited.add(nodePath.current);
            addChildrenToNodepathQ(graph, nodePath, pq, map);
        }
        System.out.println(map);
        return map;
    }

    void addChildrenToNodepathQ(Graph graph, NodePath parentPath, PriorityQueue<NodePath> pq,
            Map<Vertex, NodePath> map) {
        LinkedList<Edge> linkedList = graph.adjacencylist.get(parentPath.current);
        if (linkedList == null)
            return;
        for (Edge edge : linkedList) {
            if (!visited.contains(edge.destination)) {
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
    public static void main(String[] args) {
        Graph weightedGraph = Graph.buildWeightedGraph();
        Dijkstra dijkstra = new Dijkstra();
        Map<Vertex, NodePath> shortestPath = dijkstra.FindShortestPath(weightedGraph, weightedGraph.start, weightedGraph.end);
        NodePath nodePath = shortestPath.get(weightedGraph.end);
        Stack<String> stack = new Stack();
        stack.push(nodePath.current.name + " -> ");
        int i = nodePath.distance;
        while (nodePath.parent != null) {
            stack.push(nodePath.parent.name + " -> ");
            nodePath = shortestPath.get(nodePath.parent);
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }
}
