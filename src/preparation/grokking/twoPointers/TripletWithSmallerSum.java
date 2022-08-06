package preparation.grokking.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>[MEDIUM] Question: <b/>Given an array arr of unsorted numbers and a target sum, count all triplets in it such that
 * arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices. Write a function to return the count of such triplets.
 *  <p/>
 *  * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/mElknO5OKBO>here</a>
 */
public class TripletWithSmallerSum {

    /**
     * Time complexity: O(N * LogN) for sorting and O(Nˆ2) for iterating through the entire (worst case) array for each array element to find pair.
     * Hence asymptotic time complexity is O(Nˆ3).
     * <br/>
     * Space Complexity: O(N) for sorting
     * <p>
     */
    public static int searchTripletsReturnCount(int[] arr, int target) {

        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("At least 3 elements are required");
        }

        int count = 0;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {

            int startIndex = i + 1;
            int endIndex = arr.length - 1;

            while (startIndex < endIndex) {
                int tripletSum = arr[i] + arr[startIndex] + arr[endIndex];
                if (target > tripletSum) {
                    // since arr[endIndex] >= arr[startIndex] cos arr is a sorted array, therefore, we can replace arr[endIndex] by any number between
                    // startIndex and endIndex to get a sum less than the target
                    count += endIndex - startIndex;
                    startIndex++;
                } else {
                    endIndex--;
                }
            }
        }
        return count;
    }

    /**
     * Time complexity: O(N * LogN) for sorting, O(Nˆ2) for iterating through the entire (worst case) array for each array element to find pair and the for-loop inside
     * the while loop takes O(N) in worst case making the outer for-loop, while-loop and inner for-loop have time complexity of O(Nˆ3).
     * <p>
     * The algorithm's time complexity is O(N * LogN) + (Nˆ3) which is asymptotically equal to O(Nˆ3).
     * <p>
     * In this case, a simpler approach is to use 3 nested for-loops to still have a time complexity of O(Nˆ3).
     * <br/>
     * Space Complexity: O(N) for sorting (ignoring that for the returned List)
     * <p>
     */
    public static List<List<Integer>> searchTripletsReturnList(int[] arr, int target) {

        List<List<Integer>> triplets = new ArrayList<>();

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {

            int startIndex = i + 1;
            int endIndex = arr.length - 1;

            while (startIndex < endIndex) {
                int tripletSum = arr[i] + arr[startIndex] + arr[endIndex];
                if (target > tripletSum) {
                    // since arr[endIndex] >= arr[startIndex] cos arr is a sorted array, therefore, we can replace arr[endIndex] by any number between
                    // startIndex and endIndex to get a sum less than the target
                    for (int pos = endIndex; pos > startIndex; pos--) {
                        //We iterate from endIndex up to (startIndex + 1) because arr[startIndex] is already part of the triplet so that the
                        // condition (where i, j, and k are three different indices) in the question is not broken.
                        triplets.add(Arrays.asList(arr[i], arr[startIndex], arr[pos]));
                    }
                    startIndex++;
                } else {
                    endIndex--;
                }
            }
        }
        return triplets;
    }

    public static void main(String[] args) {
        System.out.println(TripletWithSmallerSum.searchTripletsReturnCount(new int[]{-1, 0, 2, 3}, 3));
        System.out.println(TripletWithSmallerSum.searchTripletsReturnCount(new int[]{-1, 4, 2, 1, 3}, 5));
        System.out.println(TripletWithSmallerSum.searchTripletsReturnList(new int[]{-1, 0, 2, 3}, 3));
        System.out.println(TripletWithSmallerSum.searchTripletsReturnList(new int[]{-1, 4, 2, 1, 3}, 5));
    }

}
