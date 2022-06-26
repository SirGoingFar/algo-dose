package preparation.grokking.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>[HARD] Question</b>: Given a string with lowercase letters only, if you are allowed to replace no more than k letters with any letter, find the length of the longest substring having the same letters after replacement.
 * <br/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/R8DVgjq78yR>here</a>
 */
public class CharacterReplacement {
    public static void main(String[] args) {
        System.out.println(CharacterReplacement.findLength("aabccbb", 2));
        System.out.println(CharacterReplacement.findLength("abbcb", 1));
        System.out.println(CharacterReplacement.findLength("abccde", 1));
    }

    /**
     * Time Complexity: O(N)
     * <br/>
     * Space Complexity: O(1)
     */
    public static int findLength(String str, int k) {
        int maxLength = 0, windowStart = 0, charAtFastPointerCurrentWindowRepeatCount = 0, currentSlidingWindowCharacterCount;
        char charAtFastPointer;
        Map<Character, Integer> charFreq = new HashMap<>();

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {

            //Get char on fast pointer
            charAtFastPointer = str.charAt(windowEnd);

            //Update 'charAtFastPointer' frequency
            charFreq.put(charAtFastPointer, charFreq.getOrDefault(charAtFastPointer, 0) + 1);

            charAtFastPointerCurrentWindowRepeatCount = charFreq.get(charAtFastPointer);
            currentSlidingWindowCharacterCount = (windowEnd - windowStart) + 1; //"+ 1" because 'windowStart' and 'windowEnd' are zero-based

            if(currentSlidingWindowCharacterCount - charAtFastPointerCurrentWindowRepeatCount > k){
                //Number of characters to replace to have a substring with the same letters is more than what's allowed (k),
                //move ahead the window

                //Decrement (once) frequency count or remove if 0 after decrementing
                if(charAtFastPointerCurrentWindowRepeatCount == 1){
                    charFreq.remove(charAtFastPointer);
                }else{
                    charFreq.put(charAtFastPointer, charFreq.get(charAtFastPointer) - 1);
                }

                windowStart++;
            }


            maxLength = Math.max(maxLength, currentSlidingWindowCharacterCount);
        }
        return maxLength;
    }
}
