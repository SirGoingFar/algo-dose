package preparation.grokking.twoPointers;

import java.util.*;

/**
 * <b>[EASY] Question: <b/>Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
 *  <p/>
 *  * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/gxk639mrr5r>here</a>
 */
public class TripletSumToZero {

    public static void main(String[] args) {
        System.out.println(TripletSumToZero.searchTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2}));
        System.out.println(TripletSumToZero.searchTriplets(new int[]{-5, 2, -1, -2, 3}));
        System.out.println(TripletSumToZero.searchTriplets(new int[]{3, 2, 7, 5, 1, 6}));
    }

    /**
     * Time complexity: O(N * logN) for sorting + O(N * (N - 1)) = O(Nˆ2 - N) = O(Nˆ2) for searching target sum within range (for each number, we iterate through the next (i-1) item to its right in the array)
     * ==> O(N*logN + Nˆ2) ==> O(Nˆ2) asymptomatically
     * <br/>
     * Space Complexity: O(N) excluding one for output array
     */
    private static Set<List<Integer>> searchTriplets(int[] arr) {

        if(arr.length < 3){
            throw new IllegalArgumentException("Array with, at least, 3 entries is required");
        }

        Set<List<Integer>> triplets = new HashSet<>();

        //Equation ==> X + Y + Z = 0 ==> X + Y = -Z; i.e find X and Y (pair) whose sum is -Z

        //Sort the array first to follow the Target Sum pattern and use the Two Pointers approach
        Arrays.sort(arr);

        //Iterate through up to the second to the last
        for (int i = 0; i < arr.length - 2; i++) {
            //"-", to give "-arr[i]" because of the "-" in "-Z" from the equation (X + Y = -Z)
            addAllTargetSumTripletWithinRange(-arr[i], i + 1, arr, triplets);
        }

        return triplets;
    }

    private static void addAllTargetSumTripletWithinRange(int targetSum, int startIndex, int[] arr, Collection<List<Integer>> triplets) {
        int endIndex = arr.length - 1;

        //While the elements on each index are different and haven't crossed themselves
        while (startIndex < endIndex) {
            int elementOnStartIndex = arr[startIndex];
            int elementOnEndIndex = arr[endIndex];
            int currentSum = elementOnStartIndex + elementOnEndIndex;

            if (currentSum == targetSum) {
                //target sum found, i.e. (targetSum + elementOnStartIndex + elementOnEndIndex) = 0

                //"-" is added, as in "-targetSum", because the 'targetSum' value is the negation of the actual value in 'arr'. See line 33
                triplets.add(Arrays.asList(-targetSum, elementOnStartIndex, elementOnEndIndex));

                //advance startIndex to right and endIndex to left
                startIndex++;
                endIndex--;

                //Avoid processing same, contiguous elements - check previously processed element first, if same, slide over
                while (startIndex < endIndex && arr[startIndex] == arr[startIndex - 1]) {
                    startIndex++;
                }
                while (startIndex < endIndex && arr[endIndex] == arr[endIndex + 1]) {
                    endIndex--;
                }
            } else if (currentSum < targetSum) {
                //We need bigger sum, advance startIndex to right
                startIndex++;
            } else {
                //We need smaller sum, advance endIndex to left
                endIndex--;
            }
        }
    }

}
