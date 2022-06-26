package preparation.grokking.slidingWindow;

public class MaxSumSubArrayOfSizeK {

    /**
     * <b>[EASY] Question</b>: Given an array of positive numbers and a positive number ‘K’, find the maximum sum of any contiguous subarray of size ‘k’.
     *See <a href=https://www.educative.io/courses/grokking-the-coding-interview/JPKr0kqLGNP>more</a>
     */
    public static void main(String[] args) {
        int result = MaxSumSubArrayOfSizeK.findMaxSumSubArray(3, new int[]{2, 1, 5, 1, 3, 2});
        System.out.println("Maximum sum of subarrays of size K: " + result);
        result = MaxSumSubArrayOfSizeK.findMaxSumSubArray(2, new int[]{2, 3, 4, 1, 5});
        System.out.println("Maximum sum of subarrays of size K: " + result);
    }

    /**
     * Time Complexity: O(N)
     * <br/>
     * Space Complexity: O(1)
     */
    public static int findMaxSumSubArray(int K, int[] arr) {
        int maxSum = 0;
        int windowSum = 0;
        int windowStart = 0;

        for (int i = 0; i < arr.length; i++) {
            windowSum += arr[i];

            if (i >= K - 1) { //subtract 1 because iteration is zero-based
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= arr[windowStart];
                windowStart++;
            }
        }
        return maxSum;
    }

    /**
     * Time Complexity: O((N-K+1)*K) = O(N*K)
     */
    public static int findMaxSumSubArrayBruteForce(int K, int[] arr) {
        int maxSum = 0, windowSum;
        for (int i = 0; i <= arr.length - K; i++) {
            windowSum = 0;
            for (int j = i; j < i + K; j++) {
                windowSum += arr[j];
            }
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }
}
