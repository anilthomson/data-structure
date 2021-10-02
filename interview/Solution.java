package interview;

public class Solution {
    public static void main(String[] args) {
        // 5, 11, 3, 79, 36
        Node root = new Node();
        root.data = 5;
        Node child = addNode(root, 11);
        Node child1 = addNode(child, 13);
        Node child2 = addNode(child1, 79);
        Node child3 = addNode(child2, 36);
        print(root);
        removeNode(child);
        System.out.println("Hello");
        print(root);
    }

    static class Node {
        int data;
        Node next;
        Node prev;
    }

    private static void print(Node node) {
        if (node == null)
            return;
        System.out.println(node.data);
        print(node.next);
    }

    private static void removeNode(Node node) {
        node.prev.next = node.next;
        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }

    private static Node addNode(Node parent, int data) {
        if (parent != null) {
            Node child = new Node();
            child.data = data;
            parent.next = child;
            child.prev = parent;
            return child;
        }
        return null;
    }
}