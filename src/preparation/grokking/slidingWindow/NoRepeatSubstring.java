package preparation.grokking.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>[HARD] Question</b>: Given a string, find the length of the longest substring, which has all distinct characters.
 * <br/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/YMzBx1gE5EO>here</a>
 */
public class NoRepeatSubstring {

    public static void main(String[] args) {
        System.out.println("Length of the longest substring with distinct character(s): " + NoRepeatSubstring.findLength("aabccbb"));
        System.out.println("Length of the longest substring with distinct character(s): " + NoRepeatSubstring.findLength("abbbb"));
        System.out.println("Length of the longest substring with distinct character(s): " + NoRepeatSubstring.findLength("abccde"));
    }

    /**
     * Time complexity = O(N)
     * <br/>
     * Space complexity = O(N) - worst case ==> ços of the HashMap used
     */
    public static int findLength(String str) {

        long start = System.nanoTime();

        if (str == null) {
            throw new IllegalArgumentException();
        }
        int maxLength = 0, windowStart = 0;
        Map<Character, Integer> charFreq = new HashMap<>();
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            //Get the character on the fast pointer
            char charAtFastPointer = str.charAt(windowEnd);

            if (charFreq.containsKey(charAtFastPointer)) {
                //Within the current window, 'charAtFastPointer' has appeared the second time,
                //advance the windowStart because the current window does not meet the criteria any longer
                //i.e. sliding window MUST contain distinct characters
                windowStart = Math.max(windowStart, charFreq.get(charAtFastPointer) + 1 /* "+ 1"  so we can move to the next index after the last occurrence of "charAtFastPointer" as "windowEnd" is pointing to a similar character*/);
            }

            //Persist the index of where last 'charAtfastPointer' is seen so we can advance windowStart there
            //should it re-appear in the current sliding window
            charFreq.put(charAtFastPointer, windowEnd);

            //Compute the longest sub-string so far
            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }

        long end = System.nanoTime();

        System.out.println(">> Time taken: " + (end - start) + "ns");

        return maxLength;
    }
}