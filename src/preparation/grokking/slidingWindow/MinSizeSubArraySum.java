package preparation.grokking.slidingWindow;

/**
 * <b>[EASY] Question</b>: Given an array of positive integers and a number ‘S’, find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’. Return 0 if no such subarray exists.
 * See <a href=https://www.educative.io/courses/grokking-the-coding-interview/7XMlMEQPnnQ>more</a>
 */
public class MinSizeSubArraySum {

    public static void main(String[] args) {
        int result = MinSizeSubArraySum.findMinSubArray(7, new int[]{2, 1, 5, 2, 3, 2});
        System.out.println("Smallest subarray length: " + result);
        result = MinSizeSubArraySum.findMinSubArray(8, new int[]{3, 4, 1, 1, 6});
        System.out.println("Smallest subarray length: " + result);
        result = MinSizeSubArraySum.findMinSubArray(8, new int[]{2, 1, 5, 2, 3, 2});
        System.out.println("Smallest subarray length: " + result);
    }

    /**
     * Time Complexity: O(N)
     * <br/>
     * Space Complexity: O(1)
     */
    private static int findMinSubArray(int S, int[] a) {
        int lengthOfSmallestSubarray = Integer.MAX_VALUE;
        int windowSum = 0;
        int windowStart = 0;

        for (int i = 0; i < a.length; i++) {
            windowSum += a[i];

            // ">=" is used instead of ">" BECAUSE sub-array <0, 1, 2, 3> will give the same sum as sub-array <1, 2, 3>.
            //Considering sub-array length, the latter is shorter than the former.
            while (windowSum >= S) {
                lengthOfSmallestSubarray = Math.min(lengthOfSmallestSubarray, i - windowStart + 1); //Add 1 because 'i' & 'windowStart' are zero-based
                windowSum -= a[windowStart];
                windowStart++;
            }
        }
        return lengthOfSmallestSubarray == Integer.MAX_VALUE ? 0 : lengthOfSmallestSubarray;
    }
}
