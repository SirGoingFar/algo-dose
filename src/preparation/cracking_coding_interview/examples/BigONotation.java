package preparation.cracking_coding_interview.examples;

import static java.lang.Math.sqrt;

public class BigONotation {

    public static void main(String[] args) {
    }


    //Reverse an Array - O(N)
    void reverseArray(int[] array) {
        int length = array.length;
        for (int x = 0; x < length / 2; x++) {
            int xCounter = length - x - 1; // index x counterpart
            int temp = array[xCounter];
            array[xCounter] = array[x];
            array[x] = temp;
        }
    }


    //If a number is a Prime Number - O(sqrt(n))
    boolean isPrimeNumber(int n) {
        int sqrtVal = (int) sqrt(n);
        for (int x = 2; x < sqrtVal; x++) {
            if (n % x == 0)
                return false;
        }
        return true;
    }

    // n! - O(n)
    int factorial(int n) {
        if (n < 0)
            return -1;
        else if (n == 0)
            return 1;
        else
            return n * factorial(n - 1);

    }



}
