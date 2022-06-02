package preparation.crackingCodingInterview.examples;

import java.util.Arrays;

public class StringsAndArrays {

    public static void main(String[] args) {

        //Checking if characters in a string are unique
        String stringOne = "Abule-Egba";
        System.out.printf("%s%s%s%s", "1. Are characters in ", stringOne, " unique? ", isUniqueCharacters(stringOne));

        System.out.println();

        String stringTwo = "Esther";
        System.out.printf("%s%s%s%s", "2. Are characters in ", stringTwo, " unique? ", isUniqueCharactersV2(stringTwo));

        System.out.println();

        String stringThree = "Funso";
        System.out.printf("%s%s%s%s", "3. Are characters in ", stringThree, " unique? ", isUniqueCharacters(stringThree));

        System.out.println();
        System.out.println();

        //Check if the two strings are permutations of each other
        String a = "Aderogba";
        String b = "abgoreda";
        System.out.printf("%s%s%s%s%s%s", "1. Are strings ", a, " and ", b, " permutations of each other? ", isPermutationOf(a, b));

        System.out.println();

        a = "aderogba";
        b = "abgoreda";
        System.out.printf("%s%s%s%s%s%s", "2. Are strings ", a, " and ", b, " permutations of each other? ", isPermutationOf(a, b));

        System.out.println();

        a = "isaac";
        b = "abgoreda";
        System.out.printf("%s%s%s%s%s%s\n", "3. Are strings ", a, " and ", b, " permutations of each other? ", isPermutationOf(a, b));

        System.out.println();

        String string = "abba";
        System.out.printf("%s%s%s%s\n", "Is ", string, " a palindrome permutation? - ", isPalindromePermutation(string));

        System.out.println();

        String first = "Peju";
        String second = "pej";
        System.out.printf("%s%s%s%s%s%s", "V1: One of strings '", first, "' and '", second, "' requires ONE edit to be same? ", oneEditAwayV1(first, second));
        System.out.println();
        System.out.printf("%s%s%s%s%s%s\n", "V2: One of strings '", first, "' and '", second, "' requires ONE edit to be same? ", oneEditAwayV2(first, second));

        System.out.println();

        String compressString = "aabcccccaaa";
        System.out.printf("%s%s%s%s\n", "Compressed '", compressString, "' gives ", doStringCompression(compressString));

        int[][] matrix = new int[][]{{2, 2, 1, 0}, {3, 3, 0, 6}, {3, 3, 0, 7}, {3, 3, 0, 9}};
        System.out.println("Rotate Matrix by :");
        System.out.println("\nBefore " + Arrays.deepToString(matrix));
        rotateMatrix(matrix);
        System.out.println("After " + Arrays.deepToString(matrix));

        int[][] anotherMatrix1 = new int[][]{{2, 2, 1, 3}, {3, 0, 3, 6}, {3, 3, 7, 0}, {3, 3, 0, 9}};
        System.out.println("\nSet Zero Matrix: (V1)");
        System.out.println("Before " + Arrays.deepToString(anotherMatrix1));
        setZeroMatrixV1(anotherMatrix1);
        System.out.println("After " + Arrays.deepToString(anotherMatrix1));

        int[][] anotherMatrix2 = new int[][]{{2, 2, 1, 3}, {3, 0, 3, 6}, {3, 3, 7, 0}, {3, 3, 0, 9}};
        System.out.println("\nSet Zero Matrix: (V2)");
        System.out.println("Before " + Arrays.deepToString(anotherMatrix2));
        setZeroMatrixV2(anotherMatrix2);
        System.out.println("After " + Arrays.deepToString(anotherMatrix2));
    }


    /**
     * This algorithm checks if the characters in a string are all unique.
     * <p>
     * Assume no other data structures can be used.
     * <p>
     * Time complexity = O(N^2)
     */
    private static boolean isUniqueCharacters(String string) {

        //Input Validation
        if (string == null || string.isEmpty())
            return false;

        int stringLen = string.length();

        //Iterate through the string characters and compare
        for (int outerIndex = 0; outerIndex < stringLen; outerIndex++) {

            for (int innerIndex = 0; innerIndex < stringLen; innerIndex++) {

                char outerIndexChar = string.charAt(outerIndex);
                char innerIndexChar = string.charAt(innerIndex);

                if (outerIndex != innerIndex && outerIndexChar == innerIndexChar)
                    return false;

            }

        }

        return true;
    }


    /**
     * This algorithm checks if the characters in a string are all unique.
     * <p>
     * Assume no other data structures can be used
     *
     * <p>
     * <p>
     * Time complexity = O(N)
     */
    private static boolean isUniqueCharactersV2(String string) {

        //Input Validation
        if (string == null || string.isEmpty())
            return false;

        //Todo: Know what String it is: ASCII or UniCode
        //ASCII has 128 characters in its character set
        //Extended ASCII has 256 characters in its alphabet (character set)
        boolean[] char_set = new boolean[128];

        for (int index = 0; index < string.length(); index++) {

            int character = string.charAt(index);

            if (char_set[character])
                return false;

            char_set[character] = true;
        }

        return true;
    }

    /**
     * This algorithm checks if two strings are the permutations of each other.
     * <p>
     * <p>
     * A Permutation of a string is another string that contains same character(s),
     * only the order of character(s) can be different.
     * For example, “abcd” and “dabc” are Permutation of each other.
     * <p>
     * <p>
     * Time Complexity = O(N)
     */
    private static boolean isPermutationOf(String a, String b) {

        /*
         *
         * Get clarity from the interviewer first; if:
         * - the comparison is case-sensitive
         * - white space matters
         *
         *
         * NB: This algorithm makes the aforementioned compulsory though
         *
         * */

        //If the inputs are invalid, return FALSE
        if (a == null || b == null || a.isEmpty() || b.isEmpty())
            return false;

        //NB: For TWO strings to be permutations of each other, they MUST be
        //of the same length.
        if (a.length() != b.length())
            return false;

        char[] aCharArray = a.toCharArray();
        char[] bCharArray = b.toCharArray();

        //Sort both arrays
        Arrays.sort(aCharArray);
        Arrays.sort(bCharArray);

        /*for (int index = 0; index < aCharArray.length; index++) {
            if (aCharArray[index] != bCharArray[index])
                return false;
        }

        return true;*/

        //OR

        return Arrays.equals(aCharArray, bCharArray);
    }

    private static boolean isPalindromePermutation(String string) {

        int oddCharCount = 0;

        int[] char_table =
                new int[
                        Character.getNumericValue('Z') - Character.getNumericValue('a')
                                + 1
                        ];

        if (string == null || string.isEmpty())
            return false;

        for (Character c : string.toCharArray()) {

            int charNumericalValue = getCharacterNumericValue(c);

            if (charNumericalValue == -1)
                continue;

            char_table[charNumericalValue]++;

            if (char_table[charNumericalValue] % 2 == 1)
                oddCharCount++; //do 'addition'
            else
                oddCharCount--; //undo 'addition'

        }

        return oddCharCount <= 1;
    }

    //Time Complexity: O(2N) = O(N)
    private static boolean isPalindromePermutationV2(String string) {


        if (string == null || string.isEmpty())
            return false;

        int[] char_table = getCharacterCountFreqTable(string);

        return hasMaxOneOddCountCharacter(char_table);

    }

    private static boolean hasMaxOneOddCountCharacter(int[] char_table) {

        boolean foundOddCount = false;

        for (int count : char_table) {

            if (count % 2 == 1) {

                if (foundOddCount)
                    return false;

                foundOddCount = true;
            }

        }

        return true;

    }

    private static int[] getCharacterCountFreqTable(String string) {
        int[] char_table =
                new int[
                        Character.getNumericValue('Z') - Character.getNumericValue('a')
                                + 1
                        ];

        for (Character c : string.toCharArray()) {

            int charNumericVal = getCharacterNumericValue(c);

            if (charNumericVal != -1)
                ++char_table[charNumericVal];
        }

        return char_table;
    }

    private static int getCharacterNumericValue(Character c) {

        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('Z');
        int character = Character.getNumericValue(c);

        if (a <= character && character <= z)
            return character - a;

        return -1;

    }

    private static boolean oneEditAwayV1(String first, String second) {

        if (
                first == null || second == null
                        || (first.isEmpty() && !second.isEmpty())
                        || (!first.isEmpty() && second.isEmpty())
        )
            return false;

        /*  Length check  */
        if (Math.abs(first.length() - second.length()) > 1)
            return false;

        /* Get shorter and longer string.*/
        String s1 = first.length() < second.length() ? first : second;
        String s2 = first.length() < second.length() ? second : first;

        int index1 = 0;
        int index2 = 0;
        boolean foundDifference = false;

        while (index1 < s1.length() && index2 < s2.length()) {

            if (s1.charAt(index1) != s2.charAt(index2)) {

                if (foundDifference)
                    return false; //executed on second loop (or future loop)

                foundDifference = true;

                if (s1.length() == s2.length())
                    index1++;  //always move the pointer for the short string

            } else {
                index1++; //move the pointer for the short string when the characters are the same
            }

            index2++; //Always move the pointer for long string
        }

        return true;
    }

    public static boolean oneEditAwayV2(String first, String second) {

        if (
                first == null || second == null
                        || (first.isEmpty() && !second.isEmpty())
                        || (!first.isEmpty() && second.isEmpty())
        )
            return false;

        if (first.length() == second.length())
            return onEditReplace(first, second);
        else if ((first.length() + 1) == second.length())
            return onEditRemoveOrInsert(first, second);
        else if ((first.length() - 1) == second.length())
            return onEditRemoveOrInsert(second, first);

        return false;
    }

    private static boolean onEditRemoveOrInsert(String shorter, String longer) {

        int index1 = 0;
        int index2 = 0;

        while (index1 < longer.length() && index2 < shorter.length()) {

            if (longer.charAt(index2) != shorter.charAt(index1)) {

                //characters not the same

                //at first entry into this block, the indices are the same, give a benefit of doubt THAT
                //it's the edit to make; second entry makes it a second edit needed: but, yo! It's ONE EDIT!
                if (index1 != index2)
                    return false;

                //increment pointer for Longer string
                index2++;

            } else {

                //Characters are the same, move to the next index on both string
                index1++;
                index2++;

            }

        }

        return true;
    }

    private static boolean onEditReplace(String first, String second) {

        boolean foundDifference = false;

        for (int index = 0; index < first.length(); index++) {

            if (first.charAt(index) != second.charAt(index)) {

                if (foundDifference)
                    return false;

                foundDifference = true;
            }
        }

        return true;
    }

    private static String doStringCompression(String source) {

        if (source == null || source.isEmpty())
            return source;

        //Instead of creating the compression string before knowing it won't be needed (when its length is GREATER THAN the source string's length),
        //it's better to determine the compression string length ahead of time.
        //This length helps define the size of the StringBuilder too (if later needed) - this helps avoid StringBuffer background capacity enlargement.

        //The downside of this approach is code duplication
        int finalCompressionLength = computeCompressionStringLength(source);

        if (finalCompressionLength >= source.length())
            return source;

        StringBuilder sb = new StringBuilder(finalCompressionLength);
        int count = 0;

        for (int i = 0; i < source.length(); i++) {

            count++;

            if ((i + 1) >= source.length() || source.charAt(i) != source.charAt(i + 1)) {
                sb.append(source.charAt(i));
                sb.append(count);
                count = 0;
            }

        }

        return sb.toString();
    }

    private static int computeCompressionStringLength(String s) {

        int count = 0;
        int compressionCount = 0;

        for (int i = 0; i < s.length(); i++) {
            count++;

            if ((i + 1) >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                compressionCount += 1 + String.valueOf(count).length();
            }
        }

        return compressionCount;
    }

    /**
     * Time complex
     */
    private static boolean rotateMatrix(int[][] matrix) {

        int length = matrix.length;

        if (length <= 0 /* Truly has element(s) */ || length < matrix[0].length /* Truly a NxN matrix */)
            return false;

        /* Loop through from outer later to inner layer */
        for (int layer = 0; layer < length / 2; layer++) {

            /* First index in the current layer */
            int first = layer;

            /* Last index in the current layer  */
            int last = length - 1 - layer;


            /* Loop through the first to last index - includes many index variants */
            for (int i = first; i < last; i++) {

                int offset = i - first; //so it starts from 0, subtract 'first' - least value of 'pos' is 'first'

                // save top
                int top = matrix[first][i];

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }

        }

        return true;
    }


    //Space Complexity: O(N)
    //Time Complexity: O(N)
    public static void setZeroMatrixV1(int[][] matrix) {

        boolean[] row = new boolean[matrix.length];
        boolean[] column = new boolean[matrix[0].length];

        //iterate through the array element to know what row and column a zero value belong
        for (int i = 0; i < row.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }

            }

        }

        //iterate through matrix and nullify (requires NxN times) - MTD 1
        /*for (int i = 0; i < row.length; i++) {

            for (int j = 0; j < column.length; j++) {

                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }

            }

        }*/

        //iterate through row and column and nullify (requires M+N times) - MTD 2
        for (int i = 0; i < row.length; i++) {
            if (row[i])
                nullifyRow(matrix, i, 0);
        }

        for (int j = 0; j < column.length; j++) {
            if (column[j])
                nullifyColumn(matrix, j, 0);
        }
    }

    //Space Complexity: O(1)
    //Time Complexity: O(N^2)
    private static void setZeroMatrixV2(int[][] matrix) {

        boolean firstRowHasZero = false;
        boolean firstColumnHasZero = false;

        //know if the first row has any element with value 0
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[0][j] == 0) {
                firstRowHasZero = true;
                break;
            }

        }

        //know if the first column has any element with value 0
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[i][0] == 0) {
                firstColumnHasZero = true;
                break;
            }
        }

        //iterate through the matrix and set the flag on row and column one ANY element on the row or column (respectively) is ZERO
        for (int row = 1; row < matrix.length; row++) {

            for (int column = 0; column < matrix[row].length; column++) {

                if (matrix[row][column] == 0) {
                    matrix[0][column] = 0;
                    matrix[row][0] = 0;

                    //break the loop for performance - at least, there's one 0 that's sufficient for our case
                    break;
                }
            }

        }

        //nullify appropriate rows where matrix[row][0] == 0
        for (int row = 1; row < matrix.length; row++) {

            if (matrix[row][0] == 0) {
                nullifyRow(matrix, row, 1);
            }
        }

        //nullify appropriate rows where matrix[0][column] == 0
        for (int column = 1; column < matrix.length; column++) {

            if (matrix[0][column] == 0) {
                nullifyColumn(matrix, column, 1);
            }

        }

        //Since the elements of "row = 0" and "column = 0" have been preserved till now, nullify them too if appropriate
        if (firstColumnHasZero)
            nullifyColumn(matrix, 0, 0);

        if (firstRowHasZero)
            nullifyRow(matrix, 0, 0);

    }

    private static void nullifyColumn(int[][] matrix, int j, int startIndex) {
        for (int i = startIndex; i < matrix[j].length; i++) {
            matrix[i][j] = 0;
        }
    }

    private static void nullifyRow(int[][] matrix, int i, int startIndex) {
        for (int j = startIndex; j < matrix.length; j++) {
            matrix[i][j] = 0;
        }
    }
}
