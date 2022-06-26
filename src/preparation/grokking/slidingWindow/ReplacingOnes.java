package preparation.grokking.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>[HARD] Question</b>: Given an array containing 0s and 1s, if you are allowed to replace no more than ‘k’ 0s with 1s, find the length of the longest contiguous subarray having all 1s.
 * <br/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/B6VypRxPolJ</a>
 */
public class ReplacingOnes {
    public static void main(String[] args) {
        System.out.println(ReplacingOnes.findLength(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
        System.out.println(ReplacingOnes.findLength(new int[]{0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1}, 3));
    }

    /**
     * Time Complexity: O(N)
     * <br/>
     * Space Complexity: O(1)
     */
    public static int findLength(int[] arr, int k) {
        int windowStart = 0, maxLength = 0, windowSize, digitOnFastPointer, digitOnSlowPointer;
        Map<Integer, Integer> digitFreq = new HashMap<>();
        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            digitOnFastPointer = arr[windowEnd];
            digitOnSlowPointer = arr[windowStart];

            digitFreq.put(digitOnFastPointer, digitFreq.getOrDefault(digitOnFastPointer, 0) + 1);
            windowSize = (windowEnd - windowStart) + 1; //"+ 1" because 'windowStart' and 'windowEnd' are zero-based

            if (windowSize - digitFreq.getOrDefault(1, 0) > k) {
                //Number of 0's to replace in the current window size is more than the allowed number of replaceable 0's,
                //slide the window ahead and reduce count of characters slid out of window
                if (digitFreq.get(digitOnSlowPointer) - 1 == 0) {
                    digitFreq.remove(digitOnSlowPointer);
                } else {
                    digitFreq.put(digitOnSlowPointer, digitFreq.get(digitOnSlowPointer) - 1);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowSize);
        }
        return maxLength;
    }
}
