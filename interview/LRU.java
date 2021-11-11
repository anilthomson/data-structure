package interview;

import java.util.HashMap;
import java.util.Map;

public class LRU  {
    class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node() {
        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int nodeCount;
    int capacity;
    Map<Integer, Node> backend;
    Node head = new Node();
    Node tail = new Node();

    public LRU(int capacity) {
        this.capacity = capacity;
        backend = new HashMap(capacity);
        head.next = tail;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void removeNode(Node node) {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }

    public void put(int key, int value) {
        Node node = backend.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
        } else {
            node = new Node(key, value);
             nodeCount++;
        }
        addToHead(node);
        backend.put(key, node);
       
        boolean full = (nodeCount > capacity);
         if (full) {
            backend.remove(tail.prev.key);
            removeNode(tail.prev);
            nodeCount --;
        }
    }

    public int get(int key) {
        Node node = backend.get(key);
         
        if (node != null) {
           
            removeNode(node);
            addToHead(node);
            return node.value;
        }else{
             
            return -1;
        }
    }
}
