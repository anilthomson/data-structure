package theory.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;


public class ShortestPath_UnWeighted_BFS {
    HashSet<Vertex> visited = new HashSet<>();
    
    Map<Vertex, Vertex> ShortestPath(Vertex start, Vertex end) {
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
            visited.add(parent);
            List<Vertex> adjList = parent.getAdjList();
            for (Vertex child : adjList) {
                if (!visited.contains(child)) {
                    queue.add(child);
                    map.put(child, parent);
                    if (child.name.equals(end.name))
                        return map;
                }
            }
        }
        return map;
    }

    public static void main(String[] args) {
        ShortestPath_UnWeighted_BFS sp = new ShortestPath_UnWeighted_BFS();
        Graph graph = Graph.build();
        Vertex first = graph.getVertex();
        Vertex next = graph.vertices.stream().skip(5).findAny().get();
        System.out.println(first.name + " => " + next.name);
        Stack<Vertex> stack = new Stack<Vertex>();
        Map<Vertex, Vertex> shortestPath = sp.ShortestPath(first, next);
        while (next != null) {
            stack.push(next);
            next = shortestPath.get(next);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().name);
        }
    }
}
