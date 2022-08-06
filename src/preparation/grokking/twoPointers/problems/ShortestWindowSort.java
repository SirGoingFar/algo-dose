package preparation.grokking.twoPointers.problems;

/**
 * <b>[MEDIUM] Question: <b/>Given an array, find the length of the smallest subarray in it which when sorted will sort the whole array.
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/N8rOAP6Lmw6>here</a>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/gxL951y9xj3>here</a>
 */
public class ShortestWindowSort {

    /**
     * Time complexity: O(N)
     * Space complexity O(1)
     */
    public static int sort(int[] arr) {
        if (arr.length <= 0) {
            return 0;
        }

        int low = 0, high = arr.length - 1;

        //Get the index of first unsorted value from the start
        while (low < arr.length - 1 && arr[low] <= arr[low + 1]) {
            low++;
        }

        //If there's no disorder, return 0
        if (low == arr.length - 1) {
            return 0; //arr is sorted
        }

        //Get the index of first unsorted value from the end too
        while (high > 0 && arr[high] >= arr[high - 1]) {
            high--;
        }

        //Find the minimum and maximum values within the unsorted sub-array
        int minSubArrayEntry = Integer.MAX_VALUE, maxSubArrayEntry = Integer.MIN_VALUE;
        for (int i = low; i <= high; i++) {
            minSubArrayEntry = Math.min(minSubArrayEntry, arr[i]);
            maxSubArrayEntry = Math.max(maxSubArrayEntry, arr[i]);
        }

        //Extend the current sub-array outwards to capture seemingly sorted values relative to the minimum and maximum values deduced from the unsorted sub-array
        while (low > 0 && arr[low - 1] > minSubArrayEntry) {
            low--;
        }

        while (high < arr.length - 1 && arr[high + 1] < maxSubArrayEntry) {
            high++;
        }

        return high - low + 1;
    }

    public static void main(String[] args) {
        System.out.println(ShortestWindowSort.sort(new int[]{1, 2, 5, 3, 7, 10, 9, 12}));
        System.out.println(ShortestWindowSort.sort(new int[]{1, 3, 2, 0, -1, 7, 10}));
        System.out.println(ShortestWindowSort.sort(new int[]{1, 2, 3}));
        System.out.println(ShortestWindowSort.sort(new int[]{3, 2, 1}));
    }

}
