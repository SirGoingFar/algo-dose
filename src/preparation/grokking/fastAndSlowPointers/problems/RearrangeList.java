package preparation.grokking.fastAndSlowPointers.problems;

import preparation.grokking.fastAndSlowPointers.ListNode;

/**
 * <b>[Medium] Question: <b/>Given the head of a Singly LinkedList, write a method to modify the LinkedList such that
 * the nodes from the second half of the LinkedList are inserted alternately to the nodes from the first half in reverse
 * order. So if the LinkedList has nodes 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, your method should
 * return 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.
 *<br/>
 * Your algorithm should not use any extra space and the input LinkedList should be modified in-place.
 * <br/>
 * <br/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/YQJ4mr7yOrW>here</a>
 * <br/>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/qAo438WozV7>here</a>
 */
public class RearrangeList {

    /**
     * Time complexity: O(4N) = O(N)
     * Space complexity: O(1)
     */
    public static void reorder(ListNode head) {
        if(head == null || head.next == null){
            return;
        }

        //1. Find the middle node
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        //2. Split LinkedList into 2 halves and reverse the second half
        ListNode firstHalfHead = head;
        ListNode secondHalfHead = reverse(slow);

        //3. Do re-ordering
        ListNode tempNode;
        while (firstHalfHead != null && secondHalfHead != null){
            tempNode = firstHalfHead.next;
            firstHalfHead.next = secondHalfHead;
            firstHalfHead = tempNode;

            tempNode = secondHalfHead.next;
            secondHalfHead.next = firstHalfHead;
            secondHalfHead = tempNode;
        }

        if (firstHalfHead != null)
            firstHalfHead.next = null;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode next ;
        while (head != null){
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        RearrangeList.reorder(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

}
