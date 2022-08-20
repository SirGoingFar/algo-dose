package preparation.grokking.fastAndSlowPointers;

/**
 * <b>[MEDIUM] Question: <b/>Any number will be called a happy number if, after repeatedly replacing it with a number
 * equal to the sum of the square of all of its digits, leads us to number ‘1’. All other (not-happy) numbers will
 * never reach ‘1’. Instead, they will be stuck in a cycle of numbers which does not include ‘1’.
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/39q3ZWq27jM>here</a>
 */
class HappyNumber {

    /**
     * Time complexity: O(logN)
     * Space complexity: O(1)
     */
    public static boolean find(int num) {
        int fast = num, slow = num; //let both fast and slow start from the same point/number
        do {
            //Since whether a number is happy or 'sad' will definitely end up in a cycle (either cycle of 1 or of its previously computed number),
            //let's push the two pointers (fast and slow) to a value where a cycle is detected for 'num'
            fast = findSumOfDigitSquared(findSumOfDigitSquared(fast)); //move twice
            slow = findSumOfDigitSquared(slow); //move once
        } while (fast != slow); //found the cycle
        return slow == 1; //determine whether 'num' is happy or not based on the value where cycle is detected
    }

    /**
     * when num = 25, function returns (2ˆ2 + 5ˆ2) = 29
     * when num = 1, function returns (1ˆ2) = 1
     * */
    private static int findSumOfDigitSquared(int num) {
        int sum = 0, digit;
        while (num > 0){
            digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(HappyNumber.find(23));
        System.out.println(HappyNumber.find(12));
    }
}
