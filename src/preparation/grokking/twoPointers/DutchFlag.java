package preparation.grokking.twoPointers;

import java.util.Arrays;

/**
 * <b>[MEDIUM] Question: <b/>Given an array containing 0s, 1s and 2s, sort the array in-place.
 * You should treat numbers of the array as objects, hence, we can’t count 0s, 1s, and 2s to recreate the array.
 * <p/>
 * The flag of the Netherlands consists of three colors: red, white and blue; and since our input array also consists of
 * three different numbers that is why it is called <a href=https://en.wikipedia.org/wiki/Dutch_national_flag_problem>Dutch National Flag</a> problem.
 * <p/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/RMBxV6jz6Q0>here</a>
 */
public class DutchFlag {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 2, 1, 0};
        DutchFlag.sort(arr);
        System.out.print(Arrays.toString(arr));

        System.out.println();

        arr = new int[]{2, 2, 0, 1, 2, 0};
        DutchFlag.sort(arr);
        System.out.print(Arrays.toString(arr));
    }

    /**
     * Time complexity: O(N).
     * <br/>
     * Space Complexity: O(1)
     */
    public static void sort(int[] arr) {
        int ctx,
                low = 0, //current position where 0 can come to
                high = arr.length - 1; //current position where 2 can come to

        //iterate from 0 up to the index preceding where 2 starts in the array
        for (int i = 0; i <= high; ) {
            // "<=" (and not "<" as in normal array iteration) because the last time 2 was swapped,
            // "high" was incremented and value at index "high" is probably not value 2.

            ctx = arr[i];

            if (ctx == 0) {
                //Move 0 to position "low" and advance the iteration
                swap(arr, low++, i++);
            } else if (ctx == 1) {
                //Leave 1 at the middle, don't move it but advance the iteration
                i++;
            } else if (ctx == 2) {
                //Move 2 to position "high" but don't advance the iteration, we need to check
                //that the new value swapped and moved to index "i" is meant to be there.
                swap(arr, high--, i);
            }
        }
    }

    private static void swap(int[] arr, int to, int from) {
        assertBound(arr.length, from);
        assertBound(arr.length, to);

        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }

    private static void assertBound(int length, int idx) {
        if (idx < 0 || idx > length - 1) {
            throw new IllegalArgumentException("Index is out of bound");
        }
    }

}
