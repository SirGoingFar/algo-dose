package preparation.grokking.fastAndSlowPointers;

/**
 * <b>[EASY] Question: <b/>Given the head of a Singly LinkedList, write a function to determine if the LinkedList has a cycle in it or not.
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/N7rwVyAZl6D>here</a>
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        //If cycle exist:
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + LinkedListCycle.hasCycle(head));

        System.out.println("...............");

        //Get cycle length:
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycle.findCycleLength(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle length: " + LinkedListCycle.findCycleLength(head));
    }

    /**
     * Time complexity: O(N). Fast will meet up with slow in the same iteration
     * Space complexity: O(1)
     */
    private static boolean hasCycle(ListNode head) {
        ListNode fast = head.next.next; //moving twice
        ListNode slow = head.next; //moving once

        //'fast' will definitely get to the end faster than 'slow'. If there's a loop/cycle, 'fast' will never be null.
        //If there's no loop/cycle, 'fast' will be null before 'slow'. So:
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                return true; //there's a cycle. Fast has caught up with slow from behind
            }
        }

        return false;
    }

    /**
     * Similar problem: Given the head of a LinkedList with a cycle, find the length of the cycle.
     *
     * Time complexity: O(N).
     * Space complexity: O(1)
     */
    public static int findCycleLength(ListNode head) {
        ListNode fast = head.next.next;
        ListNode slow = head.next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                //cycle detected, calculate length from here
                return calculateCycleLength(slow);
            }
        }
        return 0;
    }

    private static int calculateCycleLength(ListNode referenceNode) {
        ListNode currentNode = referenceNode;
        int cycleLength = 0;
        do {
            cycleLength++;
            currentNode = currentNode.next;
        } while (currentNode != referenceNode);
        return cycleLength;
    }

}
