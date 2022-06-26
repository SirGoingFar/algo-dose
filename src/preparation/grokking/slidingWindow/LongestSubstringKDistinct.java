package preparation.grokking.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>[MEDIUM] Question</b>: Given a string, find the length of the longest substring in it with no more than K distinct characters.
 * <br/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/YQQwQMWLx80>here</a>
 */
class LongestSubstringKDistinct {

    public static void main(String[] args) {
        System.out.println("My trial:");
        System.out.printf("Longest length: %s\n\n", findLengthTrial("araaci", 2));
        System.out.printf("Longest length: %s\n\n", findLengthTrial("araaci", 1));
        System.out.printf("Longest length: %s\n\n", findLengthTrial("cbbebi", 3));
        System.out.printf("Longest length: %s\n\n", findLengthTrial("cbbebi", 10));
        System.out.printf("Longest length: %s\n", findLengthTrial("cbbebixvtycopooqytrruytqww", 20));

        System.out.println("\nRecommended:");
        System.out.printf("Longest length: %s\n\n", findLengthRecommended("araaci", 2));
        System.out.printf("Longest length: %s\n\n", findLengthRecommended("araaci", 1));
        System.out.printf("Longest length: %s\n\n", findLengthRecommended("cbbebi", 3));
        System.out.printf("Longest length: %s\n\n", findLengthRecommended("cbbebi", 10));
        System.out.printf("Longest length: %s\n", findLengthRecommended("cbbebixvtycopooqytrruytqww", 20));
    }

    /**
     * Time complexity = O(N * (N - S)) = O(Nˆ2 + NS) = O(Nˆ2) ==> Brute Force, makes no sense!
     * <br/>
     * Space complexity = O(1)
     */
    public static int findLengthTrial(String str, int k) {

        long start = System.nanoTime();

        if (str == null || str.length() == 0 || k == 0) {
            throw new IllegalArgumentException();
        }

        int maxLength = 0;
        String subStringInContext;
        int windowStart = 0;
        int pointer, substringInContextLongestLength;
        List<Character> uniqueCharacters = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            subStringInContext = str.substring(windowStart);
            pointer = 0;
            substringInContextLongestLength = 0;
            char charInContext;
            uniqueCharacters.clear();

            while (pointer < subStringInContext.length()) { // <--- a forloop in disguise. It increments 'pointer' in its block
                charInContext = subStringInContext.charAt(pointer);
                if (!uniqueCharacters.contains(charInContext)) {
                    if (uniqueCharacters.size() + 1 > k) {
                        //adding 1 more character will take us above distinct character limit? Break!
                        break;
                    }
                    uniqueCharacters.add(charInContext);
                }
                substringInContextLongestLength++;
                pointer++;
            }

            maxLength = Math.max(maxLength, substringInContextLongestLength);

            windowStart++;
        }

        long end = System.nanoTime();

        System.out.println(">> Time taken: " + (end - start) + "ns");

        return maxLength;
    }

    /**
     * Time complexity = O(N + N) = O(2N) = O(N) ==> Makes more sense and faster!
     * <br/>
     * Space complexity = O(k + 1) ==> ços of the HashMap used
     */
    public static int findLengthRecommended(String str, int k) {

        long start = System.nanoTime();

        if (str == null || str.length() == 0 || k == 0) {
            throw new IllegalArgumentException();
        }

        int windowStart = 0, //left/slow pointer
                maxLength = 0;
        char characterAtWindowStart, characterAtWindowEnd;

        Map<Character, Integer> charFrequency = new HashMap<>();

        for (int windowEnd = 0; //right/fast pointer
             windowEnd < str.length(); windowEnd++) {

            //Get character from the fast pointer
            characterAtWindowEnd = str.charAt(windowEnd);

            //Put the character in the Map and increment its frequency
            charFrequency.put(characterAtWindowEnd, charFrequency.getOrDefault(characterAtWindowEnd, 0) + 1);

            //Anytime the distinct character count is more than K, shrink window by:
            while (charFrequency.size() > k) {

                //Get character from the slow pointer
                characterAtWindowStart = str.charAt(windowStart);

                //Remove character from the sliding window
                charFrequency.put(characterAtWindowStart, charFrequency.get(characterAtWindowStart) - 1);

                //If the character is no longer found in the window, remove it from the Map
                if (charFrequency.get(characterAtWindowStart) == 0) {
                    charFrequency.remove(characterAtWindowStart);
                }

                //Slide the window ahead
                windowStart++;
            }

            //Find the max length before the old max length and the newly computed length (difference between fast & slow pointers - add 1 because they are both zero-based)
            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }

        long end = System.nanoTime();

        System.out.println(">> Time taken: " + (end - start) + "ns");

        return maxLength;
    }
}
