package preparation.grokking.slidingWindow;

import java.util.Arrays;

/**
 * <b>[EASY] Question</b>: Given an array, find the average of all subarrays of ‘K’ contiguous elements in it.
 * See <a href=https://www.educative.io/courses/grokking-the-coding-interview/7D5NNZWQ8Wr>more</a>
 */
public class AverageOfSubarrayOfSizeK {
    public static void main(String[] args) {
        double[] result = AverageOfSubarrayOfSizeK.findAverages(5, new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2});
        System.out.println("Averages of subarrays of size K: " + Arrays.toString(result));
    }

    /**
     * Time Complexity: O(N)
     */
    static private double[] findAverages(int K, int[] arr) {
        if (K <= 1 || arr.length == 0) {
            return new double[]{};
        }

        int windowSum = 0;
        int windowStart = 0;
        double[] result = new double[arr.length - K + 1];

        for (int i = 0; i < arr.length; i++) {
            //add entry to sum
            windowSum += arr[i];

            if (i >= K - 1) { //subtract 1 because iteration is zero-based
                //find average
                result[windowStart] = (double) windowSum / K;

                //subtract element about to be slid over
                windowSum -= arr[windowStart];

                //slide the window ahead
                windowStart++;
            }
        }
        return result;
    }

    /**
     * Time Complexity: O((N-K+1)*K) = O(N*K)
     */
    static private double[] findAveragesBruteForce(int K, int[] arr) {
        double[] result = new double[arr.length - K + 1];
        for (int i = 0; i <= arr.length - K; i++) {
            // find sum of next 'K' elements
            double sum = 0;
            for (int j = i; j < i + K; j++)
                sum += arr[j];
            result[i] = sum / K; // calculate average
        }
        return result;
    }
}
