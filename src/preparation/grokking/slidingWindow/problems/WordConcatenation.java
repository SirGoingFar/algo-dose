package preparation.grokking.slidingWindow.problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>[HARD] Question</b>: Given a string and a list of words, find all the starting indices of substrings in the given string that are a concatenation of all the given words exactly once without any overlapping of words. It is given that all words are of the same length.
 * <p/>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/Y5YDWzqPn7O>here</a>
 * <br/>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/N8nMBvDQJ0m>here</a>
 */
public class WordConcatenation {
    public static void main(String[] args) {
        List<Integer> result = WordConcatenation.findWordConcatenationTrial("catfoxcat", new String[]{"cat", "fox"});
        System.out.println(result);
        result = WordConcatenation.findWordConcatenationTrial("catcatfoxfox", new String[]{"cat", "fox"});
        System.out.println(result);
        result = WordConcatenation.findWordConcatenationTrial("catcatfoxnownowfoxcat", new String[]{"cat", "fox", "now"});
        System.out.println(result);
        System.out.println("-------------------------");
        result = WordConcatenation.findWordConcatenationRecommended("catfoxcat", new String[]{"cat", "fox"});
        System.out.println(result);
        result = WordConcatenation.findWordConcatenationRecommended("catcatfoxfox", new String[]{"cat", "fox"});
        System.out.println(result);
        result = WordConcatenation.findWordConcatenationRecommended("catcatfoxnownowfoxcat", new String[]{"cat", "fox", "now"});
        System.out.println(result);
    }

    /**
     * Time complexity: O(((N/S)*2S)+M) = O(N+M) =; N is the length of 'str', S is the length of each word and M is the size of 'words' array, that is:
     * <br/>
     * -> (N/S) => iteration through 'str' in wordLength(S) successions
     * <br/>
     * -> 2S (worst case) => for the 2 subString() calls
     * <br/>
     * -> M => for iterating through 'words' array
     * <br/>
     * Space complexity: O(N+M); for 'words' HashMap and 'resultIndices' List
     * */
    public static List<Integer> findWordConcatenationTrial(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<>();
        int windowStart = 0, matches = 0, wordCount = words.length, wordLength = words[0].length();
        Map<String, Integer> patternFreqMap = new HashMap<>();
        for (String s : words) {
            patternFreqMap.put(s, patternFreqMap.getOrDefault(s, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd += wordLength) {
            String wordAtFastPointer = str.substring(windowEnd, windowEnd + wordLength);
            if (patternFreqMap.containsKey(wordAtFastPointer)) {
                patternFreqMap.put(wordAtFastPointer, patternFreqMap.get(wordAtFastPointer) - 1);
                if (patternFreqMap.get(wordAtFastPointer) == 0) {
                    matches++;
                }
            }

            if (matches == patternFreqMap.size()) {
                resultIndices.add(windowStart);
            }

            if ((windowEnd / wordLength) + 1 >= wordCount) {
                String chatAtSlowPointer = str.substring(windowStart, windowStart + wordLength);

                if (patternFreqMap.containsKey(chatAtSlowPointer)) {
                    if (patternFreqMap.get(chatAtSlowPointer) == 0) {
                        matches--;
                    }
                    patternFreqMap.put(chatAtSlowPointer, patternFreqMap.get(chatAtSlowPointer) + 1);
                }

                windowStart += wordLength;
            }
        }
        return resultIndices;
    }

    /**
     * Time complexity - O(N*M*Len); 'N’ is the number of characters in the given string, ‘M’ is the total number of words, and ‘Len’ is the length of a word
     * <br/><br/>
     * Space complexity - The space complexity of the algorithm is O(M) since at most, we will be storing all the words in the two HashMaps. In the worst case, we also need O(N) space for the resulting list. So, the overall space complexity of the algorithm will be O(M+N).O(M+N).
     * */
    public static List<Integer> findWordConcatenationRecommended(String str, String[] words) {
        Map<String, Integer> wordFrequencyMap = new HashMap<>();
        for (String word : words)
            wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);

        List<Integer> resultIndices = new ArrayList<Integer>();
        int wordsCount = words.length, wordLength = words[0].length();

        for (int i = 0; i <= str.length() - wordsCount * wordLength; i++) {
            Map<String, Integer> wordsSeen = new HashMap<>();
            for (int j = 0; j < wordsCount; j++) {
                int nextWordIndex = i + j * wordLength;
                // get the next word from the string
                String word = str.substring(nextWordIndex, nextWordIndex + wordLength);
                if (!wordFrequencyMap.containsKey(word)) // break if we don't need this word
                    break;

                wordsSeen.put(word, wordsSeen.getOrDefault(word, 0) + 1); // add the word to the 'wordsSeen' map

                // no need to process further if the word has higher frequency than required
                if (wordsSeen.get(word) > wordFrequencyMap.getOrDefault(word, 0))
                    break;

                if (j + 1 == wordsCount) // store index if we have found all the words
                    resultIndices.add(i);
            }
        }

        return resultIndices;
    }
}
