import java.util.*;

public class GraphTest {
    static class Vertex {
        int node;
        // adjacency list
        private List<Integer> adjList;

        Vertex(int node) {
            this.node = node;
            adjList = new ArrayList();
        }

    }

    static class Graph {

        // No. of vertices in graph
        private int v;

        Map<Integer, Vertex> vertexMap;

        // Constructor
        public Graph(int vertices) {

            // initialise vertex count
            this.v = vertices;
            vertexMap = new HashMap();

        }

        public Vertex addVertex(int u) {
            Vertex vertex = vertexMap.get(u);
            if (vertex == null) {
                vertex = new Vertex(u);
                vertexMap.put(u, vertex);
            }
            return vertex;
        }

        public void addEdge(int u, int v) {
            Vertex vertex = vertexMap.get(u);
            if (vertex == null) {
                vertex = addVertex(u);
            }
            // Add v to u's list.
            vertex.adjList.add(v);
            vertex = vertexMap.get(v);
            if (vertex == null) {
                addVertex(v);
            }
        }

        public void print() {
            vertexMap.forEach((k, vertex) -> {
                System.out.print(vertex.node + " -> ");
                vertex.adjList.forEach(element -> System.out.print(" " + element));
                System.out.println();
            });

        }

void find(Vertex vertex, int endNode){
    List list = new     ArrayList();
    vertex.adjList.forEach(element->{

    });
   
}

        boolean check(Vertex vertex, int endNode, List<Integer> visitedList) {
            boolean found = false;
            if (vertex == null)
                return false;
            visitedList.add(vertex.node);
            if (vertex.node == endNode) {
                return true;
            } else if (vertex.adjList.size() == 0) {
                return false;
            } else {
                for (int element : vertex.adjList) {
                    if (!visitedList.contains(element)) {
                        boolean x = check(vertexMap.get(element), endNode, visitedList);
                        found = found | x;
                    }
                }
                if (found)
                    System.out.println(visitedList);
            }
            return found;
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 3);
        g.addEdge(2, 0);
        g.addEdge(2, 1);
        g.addEdge(1, 3);
        // g.print();
        g.find(g.vertexMap.get(2), 3));
    }
}