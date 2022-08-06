package preparation.grokking.twoPointers;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>[EASY] Question</b>: Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
 * <p/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/xog6q15W9GP>here</a>
 */
public class PairWithTargetSum {
    public static void main(String[] args) {
        int[] result = PairWithTargetSum.search(new int[]{1, 2, 3, 4, 6}, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = PairWithTargetSum.search(new int[]{2, 5, 9, 11}, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        System.out.println("---- Alternative ----");
        result = PairWithTargetSum.searchAlternative(new int[]{1, 2, 3, 4, 6}, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = PairWithTargetSum.searchAlternative(new int[]{2, 5, 9, 11}, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
    }

    /**
     * Time complexity: O(N) ==> worst case
     * <br/>
     * Space Complexity: O(2) = O(1)
     * <p>
     * ---> FASTER
     */
    private static int[] search(int[] arr, int targetSum) {
        if (arr.length == 0) {
            return new int[]{};
        }

        long start = System.nanoTime();

        int leftPointer = 0, rightPointer = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            int pointerValueSum = arr[leftPointer] + arr[rightPointer];
            if (pointerValueSum == targetSum) {
                System.out.printf("---- Time taken: %s ----\n", System.nanoTime() - start);
                return new int[]{leftPointer, rightPointer};
            } else if (pointerValueSum < targetSum) {
                leftPointer++;
            } else {
                rightPointer--;
            }
        }

        System.out.printf("---- Time taken: %s ----\n", System.nanoTime() - start);

        return new int[]{-1, -1};
    }

    /**
     * Time complexity: O(N) ==> worst case
     * <br/>
     * Space Complexity: O(N) + O(2) = O(N) ==> O(N) for arrEntryToIndexMap & O(2) for storing the returned indices
     * <p>
     * ---> SLOWER
     */
    private static int[] searchAlternative(int[] arr, int targetSum) {
        if (arr.length == 0) {
            return new int[]{};
        }

        long start = System.nanoTime();

        Map<Integer, Integer> arrEntryToIndexMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            arrEntryToIndexMap.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            //Target Sum = X + Y ==> Y = Target Sum - X
            int X = arr[i];
            int Y = targetSum - X;

            if (arrEntryToIndexMap.containsKey(Y)) {
                System.out.printf("---- Time taken: %s ----\n", System.nanoTime() - start);
                return new int[]{i, arrEntryToIndexMap.get(Y)};
            }
        }

        System.out.printf("---- Time taken: %s ----\n", System.nanoTime() - start);

        return new int[]{-1, -1};
    }
}
