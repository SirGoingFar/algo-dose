package preparation.grokking.slidingWindow.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>[HARD] Question</b>: Given a string and a pattern, find out if the string contains any permutation of the pattern.
 * <p/>
 * <b>Permutation</b> is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:
 * <ol>
 * <li>abc</li>
 * <li>acb</li>
 * <li>bac</li>
 * <li>bca</li>
 * <li>cab</li>
 * <li>cba</li>
 * </ol>
 * If a string has ‘n’ distinct characters, it will have n! permutations.
 * <p/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/N8vB7OVYo2D>here</a>
 * <br/>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/N0o9QnPLKNv>here</a>
 */
public class StringPermutation {
    public static void main(String[] args) {
        System.out.println("Has pattern permutation: " + findPermutationTrial("oidbcaf", "abc"));
        System.out.println("Has pattern permutation: " + findPermutationTrial("odicf", "dc"));
        System.out.println("Has pattern permutation: " + findPermutationTrial("bcdxabcdy", "bcdyabcdx"));
        System.out.println("Has pattern permutation: " + findPermutationTrial("aaacb", "abc"));
    }

    /**
     * Time Complexity: O(N+M); N=length of 'str' and M=length of 'pattern'
     * <br/>
     * Space Complexity: O(M)
     */
    public static boolean findPermutationTrial(String str, String pattern) {
        if (str == null || str.isEmpty() || pattern == null || pattern.isEmpty()) {
            return false;
        }

        int windowStart = 0, matched = 0;
        Map<Character, Integer> patternCharFreq = new HashMap<>();

        //Add all pattern characters frequency to the map
        for (char c : pattern.toCharArray()) {
            patternCharFreq.put(c, patternCharFreq.getOrDefault(c, 0) + 1);
        }

        //Iterate over 'str' to find 'pattern' permutation match
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char charAtFastPointer = str.charAt(windowEnd);

            if (patternCharFreq.containsKey(charAtFastPointer)) {
                //'charAtFastPointer' is present in the pattern:

                //- decrement its frequency
                patternCharFreq.put(charAtFastPointer, patternCharFreq.get(charAtFastPointer) - 1); //TAKES CHARACTER FREQUENCY TO NEGATIVE

                //- if its frequency is zero, then it's been matched
                if (patternCharFreq.get(charAtFastPointer) == 0) {
                    matched++;
                }
            }

            //If all unique characters in 'pattern' has been matched, then the permutation exists in the current window
            if (matched == patternCharFreq.size()) {
                return true;
            }

            //When the current window size is same as the length of 'pattern', shrink the window or slide ahead
            if ((windowEnd - windowStart) + 1 == pattern.length()) {
                char charAtSlowPointer = str.charAt(windowStart);

                //Check if the character leaving the window is in the 'pattern' so it can be added back to the character frequency in 'patternCharFreq'.
                // Also, if it has earlier being matched, marked it unmatched.
                if (patternCharFreq.containsKey(charAtSlowPointer)) {

                    //'charAtSlowPointer' is a character in the 'pattern'

                    //NB: "== 0 is used and not <= 0" because we only care about characters whose count is currently '0'
                    // and whose count is about to be incremented to '1' because its last occurrence to make the character
                    // match in the pattern is about to be slid out of the window.

                    //Lines 52 and 86 will worry about adjusting the character frequency to move between +ve and -ve values
                    if (patternCharFreq.get(charAtSlowPointer) == 0) {
                        //'charAtSlowPointer' has been earlier matched, mark it unmatched
                        --matched;
                    }

                    //add/increment 'charAtSlowPointer' back to the character frequency
                    patternCharFreq.put(charAtSlowPointer, patternCharFreq.get(charAtSlowPointer) + 1); //TAKES CHARACTER FREQUENCY BACK TO POSITIVE
                }

                //slide window ahead
                windowStart++;
            }
        }

        return false;
    }

    /**
     * Time Complexity: O(N+M); N=length of 'str' and M=length of 'pattern'
     * <br/>
     * Space Complexity: O(M)
     */
    public static boolean findPermutationRecommended(String str, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) // character is completely matched
                    matched++;
            }

            if (matched == charFrequencyMap.size())
                return true;

            if (windowEnd >= pattern.length() - 1) { // shrink the window by one character
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--; // before putting the character back, decrement the matched count
                    // put the character back for matching
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return false;
    }
}