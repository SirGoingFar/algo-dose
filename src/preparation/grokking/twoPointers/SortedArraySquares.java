package preparation.grokking.twoPointers;

/**
 * <b>[EASY] Question: <b/>Given a sorted array, create a new array containing squares of all the numbers of the input array in the sorted order.
 *  <p/>
 *  * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/R1ppNG3nV9R>here</a>
 */
public class SortedArraySquares {

    public static void main(String[] args) {

        int[] result = SortedArraySquares.makeSquares(new int[]{-2, -1, 0, 2, 3});
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = SortedArraySquares.makeSquares(new int[]{-3, -1, 0, 1, 2});
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }

    /**
     * Time complexity: O(N)
     * <br/>
     * Space Complexity: O(N)
     * <p>
     */
    public static int[] makeSquares(int[] arr) {
        if (arr.length <= 0) {
            return new int[]{};
        }

        int highestSquaredElementIdx = arr.length - 1, leftPointerPosition = 0, rightPointerPosition = highestSquaredElementIdx;
        int[] squares = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int squareOfElementOnLeftIdx = arr[leftPointerPosition] * arr[leftPointerPosition];
            int squareOfElementOnRightIdx = arr[rightPointerPosition] * arr[rightPointerPosition];

            if (squareOfElementOnLeftIdx > squareOfElementOnRightIdx) {
                squares[highestSquaredElementIdx--] = squareOfElementOnLeftIdx;
                leftPointerPosition++;
            } else {
                squares[highestSquaredElementIdx--] = squareOfElementOnRightIdx;
                rightPointerPosition--;
            }
        }

        return squares;
    }

}
