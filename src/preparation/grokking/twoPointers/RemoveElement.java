package preparation.grokking.twoPointers;

import java.util.Arrays;

/**
 * <b>[EASY] Question: <b/>Given an array of sorted numbers, remove all duplicate number instances from it in-place, such that each element appears only once. The relative order of the elements should be kept the same and you should not use any extra space so that the solution have a space complexity of O(1).
 * <p/>
 * Move all the unique elements at the beginning of the array and after moving return the length of the subarray that has no duplicate in it.
 *  <p/>
 *  * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/mEEA22L5mNA>here</a>
 */
public class RemoveElement {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 3, 6, 3, 10, 9, 3};
        System.out.println(RemoveElement.remove(arr, 3));

        arr = new int[]{2, 11, 2, 2, 1};
        System.out.println(RemoveElement.remove(arr, 2));
    }

    /**
     * Time complexity: O(N)
     * <br/>
     * Space Complexity: O(1)
     * <p>
     */
    private static int remove(int[] arr, int key) {

        int nonKeyElementIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != key) {
                arr[nonKeyElementIndex] = arr[i];
                nonKeyElementIndex++;
            }
        }

        return nonKeyElementIndex;
    }

}
