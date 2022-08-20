package preparation.grokking.fastAndSlowPointers;

/**
 * <b>[MEDIUM] Question: <b/>Given the head of a Singly LinkedList that contains a cycle, write a function to find the starting node of the cycle.
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/N7pvEn86YrN>here</a>
 */
public class LinkedListCycleStart {

    /**
     * Time complexity: O(N)
     * Space complexity: O(1)
     * */
    public static ListNode findCycleStart(ListNode head) {
        //Find the cycle length
        int cycleLength = LinkedListCycle.findCycleLength(head);
        if(cycleLength <= 0){
            throw new IllegalStateException("LinkedList has no cycle");
        }

        //Let both pointers start from the head
        ListNode pointer1 = head;
        ListNode pointer2 = head;

        //Move pointer2 'cycleLength' ahead of pointer1.
        //The whole idea here is that pointer2 would have been cycleLength faster than pointer1,
        //such that it would have completed the cycle and back on the chain (cycle/loop is seen as a branch now).
        //By the time pointer1 gets to the start of the cycle, pointer2 will meet it exactly there cos it's faster by cycleLength nodes
        while (cycleLength > 0) {
            pointer2 = pointer2.next;
            cycleLength--;
        }

        //Start moving each of the pointers until they meet
        while (pointer1 != pointer2){
            pointer2 = pointer2.next;
            pointer1 = pointer1.next;
        }

        return pointer2;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + LinkedListCycleStart.findCycleStart(head).value);
    }
}
