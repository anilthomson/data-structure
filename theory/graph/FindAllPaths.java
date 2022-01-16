package theory.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FindAllPaths {
    HashSet<Vertex> visited = new HashSet<>();

    void findAllPath(Vertex start, Vertex end, List<Vertex> path, List<List<Vertex>> result) {
        path.add(start);
        visited.add(start);
        if (start.equals(end)) {
            List<Vertex> copy = new ArrayList<>(path);
            result.add(copy);
            path.remove(end);
            visited.remove(end);
            // System.out.println(start.name);
        }
        for (Vertex adj : start.getAdjList()) {
            if (!visited.contains(adj)) {
                findAllPath(adj, end, path, result);
                visited.remove(adj);
                path.remove(adj);
            }
        }
        // System.out.println("Done "+start.name);
    }

    public static void main(String[] args) {
        Graph graph = Graph.build();
        FindAllPaths find = new FindAllPaths();
        Vertex first = graph.getVertex();
        Vertex end = graph.vertices.stream().skip(7).findAny().get();
        List<List<Vertex>> result = new ArrayList<>();
        find.findAllPath(first, end, new ArrayList<>(), result);
        for (List<Vertex> list : result) {
            for (Vertex vertex : list) {
                System.out.print(vertex.name + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
