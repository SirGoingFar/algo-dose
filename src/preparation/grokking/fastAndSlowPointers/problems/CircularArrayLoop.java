package preparation.grokking.fastAndSlowPointers.problems;

/**
 * <b>[HARD] Question: <b/>We are given an array containing positive and negative numbers. Suppose the array contains a
 * number ‘M’ at a particular index. Now, if ‘M’ is positive we will move forward ‘M’ indices and if ‘M’ is negative
 * move backwards ‘M’ indices. You should assume that the array is circular which means two things:
 * <ol>
 *     <li>If, while moving forward, we reach the end of the array, we will jump to the first element to continue the movement.<li/>
 *     <li>If, while moving backward, we reach the beginning of the array, we will jump to the last element to continue the movement.<li/>
 * <ol/>
 * Write a method to determine if the array has a cycle. The cycle should have more than one element and should follow one direction which means the cycle should not contain both forward and backward movements.
 * <p>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/3j5GD3EQMGM>here</a>
 */
public class CircularArrayLoop {

    public static void main(String[] args) {
        System.out.println(CircularArrayLoop.loopExists(new int[]{1, 2, -1, 2, 2}));
        System.out.println(CircularArrayLoop.loopExists(new int[]{2, 2, -1, 2}));
        System.out.println(CircularArrayLoop.loopExists(new int[]{2, 1, -1, -2}));
    }

    /**
     * Time complexity: O(Nˆ2)
     * Space complexity: O(1)
     */
    private static boolean loopExists(int[] arr) {
        boolean isForward;
        int slow, fast;

        for (int i = 0; i < arr.length; i++) {

            // -> determine the direction the element at current index (i) will move
            //i.e. forward when element is >= zero OR backward when element is < zero
            isForward = arr[i] >= 0;

            // -> point both 'slow' and 'fast' to the current index
            slow = i;
            fast = i;

            // -> move 'slow' in the direction once
            // -> move 'fast' in the direction twice (one after the other). If the second movement isn't
            // necessary (maybe when loop needs to break if direction has changed or one-element cycle detected: i.e.
            // when fast = -1)
            do {
                slow = findNextIndex(arr, isForward, slow);
                fast = findNextIndex(arr, isForward, fast);

                if (fast != -1) {
                    //the second fast movement is necessary, move it!
                    fast = findNextIndex(arr, isForward, fast);
                }
            } while (slow != -1 && fast != -1 && slow != fast);

            if (slow != -1 && slow == fast) {
                //valid cycle found, return
                return true;
            }
        }
        return false;
    }

    private static int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
        boolean isCurrentElementForward = arr[currentIndex] >= 0;

        if (isForward != isCurrentElementForward) {
            //the current element will change the direction of the element under review
            return -1;
        }

        //add the value of current element on index 'currentIndex' to currentIndex,
        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
        if (nextIndex < 0) {
            nextIndex += arr.length; // wrap around for negative numbers
        }

        if (nextIndex == currentIndex) {
            //after movement, we still land on the currentIndex. This means we have a one-element cycle. Not allowed!
            return -1;
        }

        return nextIndex;
    }


}
