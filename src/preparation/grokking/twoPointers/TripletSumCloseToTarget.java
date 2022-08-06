package preparation.grokking.twoPointers;

import java.util.Arrays;

/**
 * <b>[MEDIUM] Question: <b/>Given an array of unsorted numbers and a target number, find a triplet in the array whose
 * sum is as close to the target number as possible, return the sum of the triplet. If there are more than one
 * such triplet, return the sum of the triplet with the smallest sum.
 *  <p/>
 *  * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/3YlQz7PE7OA>here</a>
 */
public class TripletSumCloseToTarget {
    public static void main(String[] args) {
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{-2, 0, 1, 2}, 2));
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{-3, -1, 1, 2}, 1));
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{1, 0, 1, 1}, 100));
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{1, -3, 2, -1}, -4));
        System.out.println(TripletSumCloseToTarget.searchTriplet(new int[]{6, -7, 2, -1}, 10));
    }

    /**
     * Time complexity: Sorting the array will take O(N*logN). Overall, the function will take O(N * logN + N^2), which
     * is asymptotically equivalent to O(N^2).
     * <br/>
     * Space Complexity: O(N) excluding one for output array
     */
    public static int searchTriplet(int[] arr, int targetSum) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(arr);

        int smallestDifference = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 2; i++) {

            int startIdx = i + 1, endIdx = arr.length - 1;

            while (startIdx < endIdx) {
                // comparing the sum of three numbers to the 'targetSum' can cause overflow
                // so, we will try to find a target difference
                int tripletSumDifferenceFromTargetSum = targetSum - arr[i] - arr[startIdx] - arr[endIdx];
                if (tripletSumDifferenceFromTargetSum == 0) {
                    //  we've found a triplet with an exact sum
                    return targetSum; // return sum of all the numbers
                }

                // the second part of the above 'if' is to handle the smallest sum when we have more than one of them
                if (Math.abs(tripletSumDifferenceFromTargetSum) < Math.abs(smallestDifference)
                        || (Math.abs(tripletSumDifferenceFromTargetSum) == Math.abs(smallestDifference) && tripletSumDifferenceFromTargetSum > smallestDifference)) {


                    //--- Not part of the solution ---

                    /*
                     * Since this condition is never met for all the sample set, I am skeptical about why it's needed in the condition above
                     * Left to me, "tripletSumDifferenceFromTargetSum > smallestDifference" is inappropriate and won't add it during interview,
                     * as a matter of fact condition "Math.abs(tripletSumDifferenceFromTargetSum) < Math.abs(smallestDifference)" only gives the
                     * same result.
                     *
                     * However, the first condition (Math.abs(tripletSumDifferenceFromTargetSum) < Math.abs(smallestDifference)) is correct having
                     * tested it with many inputs. E.g.
                     *
                     * tripletSumDifferenceFromTargetSum =  -0.03  | -0.03  | -0.03  | -0.03  | -0.03
                     * smallestDifference                =  -1.3   | 0.17   | -1.7   | 0.1    | -0.3
                     *
                     * */

                    if((Math.abs(tripletSumDifferenceFromTargetSum) == Math.abs(smallestDifference) && tripletSumDifferenceFromTargetSum > smallestDifference)){
                        System.out.printf("%s %s %s ---> Condition met", arr[i] , arr[startIdx] , arr[endIdx]);
                    }

                    //--- Not part of the solution ---


                    smallestDifference = tripletSumDifferenceFromTargetSum; // save the closest and the biggest difference
                }

                if (tripletSumDifferenceFromTargetSum > 0)
                    startIdx++; // we need a triplet with a bigger sum
                else
                    endIdx--; // we need a triplet with a smaller sum
            }
        }
        return targetSum - smallestDifference;
    }
}
