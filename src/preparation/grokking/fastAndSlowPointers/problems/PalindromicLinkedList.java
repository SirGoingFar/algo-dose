package preparation.grokking.fastAndSlowPointers.problems;

import preparation.grokking.fastAndSlowPointers.ListNode;

/**
 * <b>[Medium] Question: <b/>Given the head of a Singly LinkedList, write a method to check if the LinkedList is a palindrome or not.
 * <p>
 * Your algorithm should use constant space and the input LinkedList should be in the original form once the algorithm is finished.
 * The algorithm should have O(N) time complexity where ‘N’ is the number of nodes in the LinkedList.
 * <br/>
 * <br/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/B1PzmqOKDLQ>here</a>
 * <br/>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/JERG3q0p912>here</a>
 */
public class PalindromicLinkedList {

    /**
     * Time complexity: O(4N) = O(N)
     * Space complexity: O(1)
     */
    public static boolean isPalindrome(ListNode head) {
        //If there's no LinkedList at all OR there's only one node, we have a palindromic LinkedList
        if (head == null || head.next == null) {
            return true;
        }

        //1. Find the middle node
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //2. Split LinkedList into 2 halves based on the middle node and reverse the second half. So:
        // - from 'firstHalfHead' to 'secondHalfHead' => First LinkedList Half
        // - from 'secondHalfHead' to 'tail' => Second LinkedList Half

        ListNode firstHalfHead = head;
        ListNode secondHalfHead = reverse(slow);
        ListNode secondHalfHeadCopy = secondHalfHead; //Keep a copy, so we can reverse back to LinkedList original form

        //3. Compare node values of the first half (start from the given firstHalfHead) with the reversed second half (start from the new half firstHalfHead after node reversal).
        // Break if there's any node value mismatch.
        boolean isPalindrome = true;
        while (firstHalfHead != null && secondHalfHead != null) {
            if (firstHalfHead.value != secondHalfHead.value) {
                isPalindrome = false;
                break; //Values of counterpart nodes from each half do not match - not a palindrome
            }

            //Move to next node of each half
            firstHalfHead = firstHalfHead.next;
            secondHalfHead = secondHalfHead.next;
        }

        //4. Reverse the second half to go back to the original LinkedList
        reverse(secondHalfHeadCopy);

        System.out.printf("Check >>> isPalindrome: %s, Recommended condition: %s\n\n", isPalindrome, (firstHalfHead == null || secondHalfHead == null));
        return isPalindrome;
    }

    /**
     * Time complexity: O(N)
     * Space complexity: O(1)
     */
    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode next;
        while (head != null) {
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
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        PalindromicLinkedList.isPalindrome(head);

        head.next.next.next.next.next = new ListNode(2);
        PalindromicLinkedList.isPalindrome(head);
    }
}