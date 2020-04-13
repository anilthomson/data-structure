public class LinkTest {
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;

        }
    }

    static Node copy(Node head) {
        if (head == null) {
            return null;
        }
        Node node = new Node(head.val);
        node.next = copy(head.next);
        return node;
    }

static void print(Node node){
    if(node==null )return;
    System.out.print(node.val);
    print(node.next);
}

    public static void main(String[] args) {
        
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node1.next=node3;
        node3.next=node5;
        node5.next=node6;
        node6.next=node4;
        node4.next=node2;
        print(node1);
        System.out.println();
        print(copy(node1));
    }
}