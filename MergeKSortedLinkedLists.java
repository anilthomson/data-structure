/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class MergeKSortedLinkedLists {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);

        ListNode l2 = new ListNode(3);
        l1.next = l2;
        ListNode l3 = new ListNode(5);
        l2.next = l3;
        ListNode l4 = new ListNode(2);

        ListNode l5 = new ListNode(4);
        l4.next = l5;
        ListNode l6 = new ListNode(6);
        l5.next = l6;
        ListNode[] arr = { l1, l4 };
    
        MergeKSortedLinkedLists mergeKSortedLinkedLists = new MergeKSortedLinkedLists();
        Node node = mergeKSortedLinkedLists.getMinNode(arr);
        arr[node.index] = arr[node.index].next;
        mergeKSortedLinkedLists.mergeTwoLists(node.node, arr);
        System.out.println();
    }

    class Node {
        ListNode node;
        int index;
    }

    private Node getMinNode(ListNode[] lists) {
        ListNode minNode = lists[0];
        Node ret = new Node();
        int len = lists.length;
        for (int i = 0; i < len; i++) {
            ListNode node = lists[i];
            if (node.val < minNode.val) {
                minNode = node;
                ret.index = i;
            }
        }
        ret.node = minNode;
        return ret;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode[] arr) {
        Node minNode = getMinNode(arr);
        ListNode l2 = minNode.node;
        arr[minNode.index] = arr[minNode.index].next;
        l1.next = l2;
        mergeTwoLists(l2.next, arr);
        return l1;

    }
}