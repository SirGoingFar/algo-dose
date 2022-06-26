package preparation.grokking.slidingWindow.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>[HARD] Question</b>: Given a string and a pattern, find the smallest substring in the given string which has all the character occurrences of the given pattern.
 * <p/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/3wDJAYG2pAR>here</a>
 * <br/>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/xoyL4q6ApNE>here</a>
 */
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println("Smallest window containing substring: " + MinimumWindowSubstring.findSubstringTrial("aabdec", "abc"));
        System.out.println("Smallest window containing substring: " + MinimumWindowSubstring.findSubstringTrial("aabdec", "abac"));
        System.out.println("Smallest window containing substring: " + MinimumWindowSubstring.findSubstringTrial("abdbca", "abc"));
        System.out.println("Smallest window containing substring: " + MinimumWindowSubstring.findSubstringTrial("adcad", "abc"));
    }

    /**
     * - Time Complexity: O(N+N+M); '2N' for the forloop and while loop & 'M' for the forloop to populate the patternFreqMap' => O(N+M)
     * <br/>
     * - Space Complexity: O(N+M); M for HashMap, N for the resulting substring, which will happen when the input string is a permutation of the pattern.
     */
    public static String findSubstringTrial(String str, String pattern) {
        if (str == null || str.isEmpty() || pattern == null || pattern.isEmpty()) {
            return "";
        }

        String minimumWindowSubString = "";
        int windowStart = 0, matches = 0;
        Map<Character, Integer> patternFreqMap = new HashMap<>();

        //Get the pattern into a frequency Map
        for (char c : pattern.toCharArray()) {
            patternFreqMap.put(c, patternFreqMap.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char charAtFastPointer = str.charAt(windowEnd);

            //Iterate over 'str' to find 'pattern' permutation match
            if (patternFreqMap.containsKey(charAtFastPointer)) {
                //'charAtFastPointer' is present in the pattern:

                //- decrement its frequency
                patternFreqMap.put(charAtFastPointer, patternFreqMap.get(charAtFastPointer) - 1);

                //- if its frequency is zero, then it's been matched
                if (patternFreqMap.get(charAtFastPointer) == 0) {
                    matches++;
                }
            }

            //If all unique characters in 'pattern' has been matched, then the permutation exists in the current window
            if (matches == patternFreqMap.size()) {

                //To get the smallest window, start shrinking the window from left (windowStart) until the current window no longer
                //contain all the occurrences of the 'pattern' characters
                while (matches >= patternFreqMap.size()) {

                    //Get the current window substring
                    minimumWindowSubString = str.substring(windowStart, windowEnd + 1);

                    char charAtSlowPointer = str.charAt(windowStart);

                    //Check if the character about leaving the window is in the 'pattern' so it can be added back to the character frequency in 'patternCharFreq'.
                    // Also, if it has earlier being matched, marked it unmatched.
                    if (patternFreqMap.containsKey(charAtSlowPointer)) {
                        if (patternFreqMap.get(charAtSlowPointer) == 0) {
                            //'charAtSlowPointer' has been earlier matched, mark it unmatched
                            matches--;
                        }

                        //add/increment 'charAtSlowPointer' back to the character frequency
                        patternFreqMap.put(charAtSlowPointer, patternFreqMap.get(charAtSlowPointer) + 1);
                    }

                    //slide window ahead
                    windowStart++;
                }
            }
        }

        return minimumWindowSubString;
    }

    /**
     * - Time Complexity: O(N+M)
     * <br/>
     * - Space Complexity: O(N+M)
     */
    public static String findSubstringRecommended(String str, String pattern) {
        int windowStart = 0, matched = 0, minLength = str.length() + 1, subStrStart = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) >= 0) // count every matching of a character
                    matched++;
            }

            // shrink the window if we can, finish as soon as we remove a matched character
            while (matched == pattern.length()) {
                if (minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                }

                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the
                    // matched count only when a useful occurrence of a matched character is going out of the window
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--;
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return minLength > str.length() ? "" : str.substring(subStrStart, subStrStart + minLength);
    }
}
