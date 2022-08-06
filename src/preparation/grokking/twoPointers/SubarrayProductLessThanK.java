package preparation.grokking.twoPointers;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>[MEDIUM] Question: <b/>Given an array with positive numbers and a positive target number, find all of its
 * contiguous subarrays whose product is less than the target number.
 *  <p/>
 *  * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/RMV1GV1yPYz>here</a>
 */
public class SubarrayProductLessThanK {

    public static void main(String[] args) {
        System.out.println(SubarrayProductLessThanK.findSubarrays(new int[]{2, 5, 3, 10}, 30));
        System.out.println(SubarrayProductLessThanK.findSubarrays(new int[]{8, 2, 6, 5}, 50));
    }

    /**
     * Time complexity: O(N) for managing the sliding window and O(N^2) for creating sub-arrays (worst case) = O(N^3)
     * <p/>
     * Space complexity: O(N) for the tempList; without considering the result which is O(N^2). Hence the algorithm has
     * a space complexity of O(N * N^2) = O(N^3)
     */
    private static List<List<Integer>> findSubarrays(int[] arr, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int slowPointerIdx = 0;
        double windowProduct = 1;

        for (int fastPointerIdx = 0; fastPointerIdx < arr.length; fastPointerIdx++) {
            windowProduct *= arr[fastPointerIdx];

            while (windowProduct >= target && slowPointerIdx <= fastPointerIdx) {
                windowProduct /= arr[slowPointerIdx++];
            }

            List<Integer> tempList = new ArrayList<>();
            for (int i = fastPointerIdx; i >= slowPointerIdx; i--) {
                tempList.add(0, arr[i]);
                result.add(new ArrayList<>(tempList));
            }
        }

        return result;
    }

}
