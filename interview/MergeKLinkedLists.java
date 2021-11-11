package interview;

import java.util.PriorityQueue;

public class MergeKLinkedLists {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((a, b) -> {
            if (a.val > b.val)
                return +1;
            else if (a.val == b.val)
                return 0;
            return -1;
        });
        for (ListNode node : lists) {
            while (node != null) {
                queue.add(node);
                node = node.next;
            }
        }

        ListNode start = queue.peek();
        ListNode node = queue.poll();
        while (!queue.isEmpty()) {
            ListNode nextnode = queue.poll();
            node.next = nextnode;
            node = nextnode;
        }
        if (node != null)
            node.next = null;
        return start;
    }
}
