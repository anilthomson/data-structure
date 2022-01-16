package theory.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
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