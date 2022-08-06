package preparation.grokking.twoPointers;

import java.util.Arrays;

/**
 * <b>[EASY] Question: <b/>Given an array of sorted numbers, remove all duplicate number instances from it in-place, such that each element appears only once. The relative order of the elements should be kept the same and you should not use any extra space so that that the solution have a space complexity of O(1).
 *<p/>
 * Move all the unique elements at the beginning of the array and after moving return the length of the subarray that has no duplicate in it.
 *  <p/>
 *  * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/mEEA22L5mNA>here</a>
 * */
class RemoveDuplicates {

    public static void main(String[] args) {
        System.out.println("---- Unique element count ----");
        int[] arr = new int[]{2, 3, 3, 3, 6, 9, 9};
        System.out.println(RemoveDuplicates.removeTrial(arr));

        arr = new int[]{2, 2, 2, 11};
        System.out.println(RemoveDuplicates.removeTrial(arr));

        System.out.println("\n---- Element movement ----");
        arr = new int[]{2, 3, 3, 3, 6, 9, 9};
        System.out.println(RemoveDuplicates.removeTrial(arr));

        arr = new int[]{2, 2, 2, 11};
        System.out.println(RemoveDuplicates.removeTrial(arr));
    }

    /**
     * Time complexity: O(N)
     * <br/>
     * Space Complexity: O(1)
     * <p>
     * --> Get count of unique elements
     */
    public static int getUniqueElementCount(int[] arr) {
        if (arr.length <= 0) {
            return 0;
        }

        long start = System.nanoTime();

        int uniqueElementCount = 1, lastUniqueElementIndex = 0;

        for (int i = 0; i < arr.length; i++) {

            //If current element is not the same as the last unique element
            if (arr[i] != arr[lastUniqueElementIndex]) {

                //Increment the unique element count
                uniqueElementCount++;

                //Move the LAST unique element index to the NEW unique element index
                lastUniqueElementIndex = i;
            }
        }

        System.out.printf("---- Time taken: %s ----\n", System.nanoTime() - start);

        return uniqueElementCount;
    }

    /**
     * Time complexity: O(N)
     * <br/>
     * Space Complexity: O(1)
     * <p>
     * --> Move unique elements to beginning of array, preserving the sort order
     */
    public static int removeTrial(int[] arr) {
        if (arr.length <= 0) {
            return 0;
        }

        int nextReplaceableElementIndex = 1;

        long start = System.nanoTime();

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] != arr[i - 1]) {
                arr[nextReplaceableElementIndex] = arr[i];
                nextReplaceableElementIndex++;
            }
        }

        System.out.printf("---- Time taken: %s ----\n", System.nanoTime() - start);
        System.out.println("Current Array Look: " + Arrays.toString(arr));

        return nextReplaceableElementIndex;
    }
}
