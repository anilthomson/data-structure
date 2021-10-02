 
package list;



public class AddLinkListNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbers(l1, l2, 0);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2, int carry) {
        ListNode node = new ListNode(0);
        if (l1 == null && l2 == null && carry == 0)
            return null;
        else if (l1 == null && l2 == null) {
            node.val = carry;
        } else if (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + carry;
            int result = sum % 10;
            node.val = result;
            node.next = addTwoNumbers(l1.next, l2.next, sum / 10);
        } else if (l1 != null) {
            int sum = l1.val + carry;
            int result = sum % 10;
            node.val = result;
            node.next = addTwoNumbers(l1.next, l2, sum / 10);
        } else {
            int sum = l2.val + carry;
            int result = sum % 10;
            node.val = result;
            node.next = addTwoNumbers(l1, l2.next, sum / 10);
        }
        return node;
    }
}