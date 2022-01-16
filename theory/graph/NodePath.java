package theory.graph;

public class NodePath {
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
