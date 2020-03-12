import java.util.HashMap;
import java.util.Map;

class LRUCache2 {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int nodeCount;
    int capacity;
    Map<Integer, Node> backend;
    Node head = new Node(0, 0);
    Node tail;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        backend = new HashMap(capacity);
    }

   public int get(int key) {
        Node node = backend.get(key);
        if (node != null) {
            if (node == head.next) {
                updateTail(node, false);
            }
            moveToHead(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        Node node = backend.get(key);
        if (node != null) {
            node.value = value;
            if (node == head.next) {
                updateTail(node, false);
            }
            moveToHead(node);
            return;
        }
        node = new Node(key, value);
        addNode(node);
        backend.put(key, node);

        // print(head);
        System.out.println();
    }

    private void addNode(Node node) {
        nodeCount++;
        boolean full = (nodeCount > capacity);
        moveToHead(node);
        if (full) {
            updateTail(node, full);
        } else if (head.next == null) {
            head.next = node;
        }
    }

    private void moveToHead(Node node) {
        if (node == head.prev)
            return;
        if (node.next != null)
            node.next.prev = node.prev;
        if (node.prev != null)
            node.prev.next = node.next;
        node.prev = head.prev;
        node.next = head;
        if (head.prev != null)
            head.prev.next = node;
        head.prev = node;
    }

    private void updateTail(Node node, boolean overflow) {
        Node tmp = head.next.next;
        if (tmp == head)
            return;
        if (overflow)
            backend.remove(head.next.key);
        tmp.prev = null;
        head.next = tmp;
    }


    private void print(Node node) {
        if (node == null)
            return;
        System.out.println(node.value + " ," + node.prev + " ," + node.next);
        print(node.prev);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3/* capacity */ );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4)); // returns 1
        System.out.println(cache.get(3));
        System.out.println(cache.get(2)); // returns -1 (not found)
        System.out.println(cache.get(1));
        cache.put(5, 5);
        System.out.println(cache.get(1)); // returns 1
        System.out.println(cache.get(2));
        System.out.println(cache.get(3)); // returns -1 (not found)
        System.out.println(cache.get(4));
        System.out.println(cache.get(5));
        // cache.put(4, 4); // evicts key 1
        // // returns -1 (not found)
        // System.out.println(cache.get(3)); // returns 3
        // System.out.println(cache.get(4)); // returns 4

    }
}