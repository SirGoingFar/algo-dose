package preparation.grokking.twoPointers.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <b>[MEDIUM] Question: <b/>Given an array of unsorted numbers and a target number, find all unique quadruplets in it,
 * whose sum is equal to the target number.
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/gxDOJJ7xAJl>here</a>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/B6XOq8KlkWo>here</a>
 */
public class QuadrupleSumToTarget {

    /**
     * Time complexity: O(N * Log N) + O(N^2 * N) = O(N^3) asymptotically
     * Space complexity O(N) for sorting, without considering the output List size
     * */
    public static List<List<Integer>> searchQuadruplets(int[] arr, int target) {
        if (arr.length < 4) {
            return new ArrayList<>();
        }

        Arrays.sort(arr);

        List<List<Integer>> quadruplets = new ArrayList<>();

        /*
         * The whole idea here is to pre-determine 2 of the 4 'plets, then find the remaining pair.
         * Determining the first pair means having two forloops.
         * */

        //Pre-determine first 'plet
        for (int i = 0; i < arr.length - 3; i++) {

            if (i > 0 && arr[i] == arr[i - 1]) {
                //It's a sorted array, avoid processing for duplicate 'plet
                continue;
            }

            //Pre-determine second 'plet
            for (int j = i + 1; j < arr.length - 2; j++) { // (i+1) to start considering next entry from i

                //Now, we have pre-determined a pair of the quadruplet, let's go search the remaining pair
                searchPairAndAddToList(arr, i, j, target, quadruplets);
            }
        }


        return quadruplets;
    }

    private static void searchPairAndAddToList(int[] arr, int first, int second, int target, List<List<Integer>> quadruplets) {
        int left = second + 1, right = arr.length - 1, quadrupletSum;

        while (left < right) {
            // Quadruplet elements are elements at indices 'first',''second', 'left' and 'right' THAT SUM UP TO 'target'
            quadrupletSum = arr[first] + arr[second] + arr[left] + arr[right];

            if (quadrupletSum == target) {
                //QUADRUPLET FOUND!
                quadruplets.add(Arrays.asList(arr[first], arr[second], arr[left], arr[right]));

                //Advance pointers
                left++;
                right--;

                //Skip already processed numbers
                while (left < right && arr[left] == arr[left - 1]){
                    left++;
                }

                while (left < right && arr[right] == arr[right + 1]){
                    right--;
                }
            }else if(quadrupletSum < target){

                //Advance pointer
                left++;

                //Skip already processed numbers
                while (left < right && arr[left] == arr[left - 1]){
                    left++;
                }
            }else{
                //Advance pointer
                right--;

                //Skip already processed numbers
                while (left < right && arr[right] == arr[right + 1]){
                    right--;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[]{4, 1, 2, -1, 1, -3}, 1));
        System.out.println(QuadrupleSumToTarget.searchQuadruplets(new int[]{2, 0, -1, 1, -2, 2}, 2));
    }

}
