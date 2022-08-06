package preparation.grokking.twoPointers.problems;

import org.junit.platform.commons.util.StringUtils;

import java.util.Objects;

/**
 * <b>[MEDIUM] Question: <b/>Given two strings containing backspaces (identified by the character ‘#’), check if the two strings are equal.
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/g7pBzR12YPl>here</a>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/xVKE8MJDlzq>here</a>
 */
public class BackspaceCompare {

    /**
     * Time complexity: O(N + M)
     * Space complexity O(N)
     * */
    public static boolean compare(String str1, String str2) {
        final String c1 = applyBackSpace(str1);
        final String c2 = applyBackSpace(str2);
        final boolean areSame = Objects.equals(c1, c2);
        System.out.printf("First: %s -> %s\nSecond: %s -> %s\n", str1, c1, str2, c2);
        return areSame;
    }

    //MINE

    /**
     * Time complexity: O(2N) for toString and looping the array = O(N) asymptotically
     * Space complexity O(2N) for the StringBuilder & Char array = O(N) asymptotically
     * */
    private static String applyBackSpace(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }

        int slowPointer = 0;
        char[] chars = str.toCharArray();

        for (int fastPointer = 0; fastPointer < chars.length; fastPointer++) {

            if (str.charAt(fastPointer) != '#') {
                slowPointer = fastPointer;
            } else {
                chars[slowPointer] = Character.MIN_VALUE; //Delete character due to backspace
                chars[fastPointer] = Character.MIN_VALUE; //Take out the backspace itself

                if (slowPointer > 0) {
                    slowPointer--;
                }
            }
        }
        return toString(chars);
    }

    private static String toString(char[] chars) {
        StringBuilder sb = new StringBuilder(chars.length);
        for (char c : chars) {
            if (c != Character.MIN_VALUE) {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    //RECOMMENDED

    /**
     * Time complexity: O(N + M)
     * Space complexity O(1)
     * */
    public static boolean _compare(String str1, String str2) {
        // use two pointer approach to compare the strings
        int index1 = str1.length() - 1, index2 = str2.length() - 1;
        while (index1 >= 0 || index2 >= 0) {

            int i1 = getNextValidCharIndex(str1, index1);
            int i2 = getNextValidCharIndex(str2, index2);

            if (i1 < 0 && i2 < 0) // reached the end of both the strings
                return true;

            if (i1 < 0 || i2 < 0) // reached the end of one of the strings
                return false;

            if (str1.charAt(i1) != str2.charAt(i2)) // check if the characters are equal
                return false;

            index1 = i1 - 1;
            index2 = i2 - 1;
        }

        return true;
    }

    private static int getNextValidCharIndex(String str, int index) {
        int backspaceCount = 0;
        while (index >= 0) {
            if (str.charAt(index) == '#') // found a backspace character
                backspaceCount++;
            else if (backspaceCount > 0) // a non-backspace character
                backspaceCount--;        // get rid of it
            else
                break;                   //a non-backspace character and no backspace backlog

            index--; // skip a backspace or a valid character
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println("Are same: " + BackspaceCompare.compare("#xy#z", "xzz#"));
        System.out.println();
        System.out.println("Are same: " + BackspaceCompare.compare("xy#z", "xyz#"));
        System.out.println();
        System.out.println("Are same: " + BackspaceCompare.compare("xp#", "xyz##"));
        System.out.println();
        System.out.println("Are same: " + BackspaceCompare.compare("xywrrmp", "xywrrmu#p"));
        System.out.println();
    }

}
