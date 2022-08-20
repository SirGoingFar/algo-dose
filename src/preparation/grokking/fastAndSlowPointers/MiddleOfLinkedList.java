package preparation.grokking.fastAndSlowPointers;

/**
 * <b>[EASY] Question: <b/>Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.
 * If the total number of nodes in the LinkedList is even, return the second middle node.
 *
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/3j5GD3EQMGM>here</a>
 */
class MiddleOfLinkedList {

    /**
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    public static ListNode findMiddle(ListNode head) {
        //Make 'fast' progress twice faster as 'slow'. When 'fast' gets to the end, then 'slow' will be at the middle
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);

        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println("Middle Node: " + MiddleOfLinkedList.findMiddle(head).value);
    }
}
