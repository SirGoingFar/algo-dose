package preparation.grokking.slidingWindow.problems;

import java.util.*;

/**
 * <b>[HARD] Question</b>: Given a string and a pattern, find all anagrams of the pattern in the given string.
 * <p/>
 * Every anagram is a permutation of a string. As we know, when we are not allowed to repeat characters while finding permutations of a string, we get N! permutations (or anagrams) of a string having N characters. For example, here are the six anagrams of the string “abc”:
 * <ol>
 * <li>abc</li>
 * <li>acb</li>
 * <li>bac</li>
 * <li>bca</li>
 * <li>cab</li>
 * <li>cba</li>
 * </ol>
 * Write a function to return a list of starting indices of the anagrams of the pattern in the given string.
 * <p/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/YQ8N2OZq0VM>here</a>
 * <br/>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/xl2g3vxrMq3>here</a>
 */
public class StringAnagrams {
    public static void main(String[] args) {
        StringAnagrams.findStringAnagrams("ppqp", "pq");
        System.out.println("-------------------");
        StringAnagrams.findStringAnagrams("abbcabc", "abc");
    }

    /**
     * Time Complexity: O(N+M); N=length of 'str' and M=length of 'pattern'
     * <br/>
     * Space Complexity: O(N+M); M for HashMap, N for the resulting substring, which will happen when the input string is a permutation of the pattern.
     * NB: Forget about space complexity for "List<String> anagrams = new ArrayList<>()", it's not needed to make the algorithm work. It can be removed
     */
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        if (str == null || str.isEmpty() || pattern == null || pattern.isEmpty()) {
            return Collections.emptyList();
        }

        List<Integer> resultIndices = new ArrayList<>();
        List<String> anagrams = new ArrayList<>();
        int windowStart = 0, matches = 0;

        //Get the pattern into a frequency Map
        Map<Character, Integer> patternFreqMap = new HashMap<>();
        for (char c : pattern.toCharArray()) {
            patternFreqMap.put(c, patternFreqMap.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {

            char charAtFastPointer = str.charAt(windowEnd);

            if (patternFreqMap.containsKey(charAtFastPointer)) {
                //decrement character frequency
                patternFreqMap.put(charAtFastPointer, patternFreqMap.get(charAtFastPointer) - 1);

                //confirm if character occurrence(s) has(ve) been matched
                if (patternFreqMap.get(charAtFastPointer) == 0) {
                    matches++;
                }
            }

            //Keep record of this anagram as the 'pattern' has been matched
            if (matches == patternFreqMap.size()) {
                resultIndices.add(windowStart);
                anagrams.add(str.substring(windowStart, windowEnd + 1));
            }

            if (windowEnd - windowStart + 1 == pattern.length()) {
                char charAtSlowPointer = str.charAt(windowStart);
                if (patternFreqMap.containsKey(charAtSlowPointer)) {

                    if (patternFreqMap.get(charAtSlowPointer) == 0) {
                        matches--;
                    }

                    patternFreqMap.put(charAtSlowPointer, patternFreqMap.get(charAtSlowPointer) + 1);
                }
                windowStart++;
            }
        }

        System.out.println(String.format("Anagram(s) of %s in %s: %s", pattern, str, anagrams));
        System.out.println(String.format("Anagram(s) start index(ices) of %s in %s: %s", pattern, str, resultIndices));

        return resultIndices;
    }
}
