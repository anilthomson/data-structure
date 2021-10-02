package list;
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

class MergeTwoSortedLinkedLists {
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
        new MergeTwoSortedLinkedLists().mergeTwoLists(l1, l4);
        System.out.println();
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        else if (l1 == null)
            return l2;
        else if (l2 == null)
            return l1;
        else {
            if (l1.val < l2.val) {

                l1.next = mergeTwoLists(l1.next, l2);
                return l1;
            } else {

                l2.next = mergeTwoLists(l1, l2.next);
                return l2;
            }
        }
    }
}