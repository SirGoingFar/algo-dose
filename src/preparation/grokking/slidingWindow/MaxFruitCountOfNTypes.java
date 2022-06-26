package preparation.grokking.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>[MEDIUM] Question</b>:
 * <br/>
 * You are visiting a farm to collect fruits. The farm has a single row of fruit trees. You will be given two baskets, and your goal is to pick as many fruits as possible to be placed in the given baskets.
 * You will be given an array of characters where each character represents a fruit tree. The farm has following restrictions:
 *
 * <ol>
 *     <li>Each basket can have only one type of fruit. There is no limit to how many fruit a basket can hold.</li>
 *     <li>You can start with any tree, but you can’t skip a tree once you have started.</li>
 *     <li>You will pick exactly one fruit from every tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.</li>
 * </ol>
 * See more <a href=https://www.educative.io/courses/grokking-the-coding-interview/Bn2KLlOR0lQ>here</a>
 * */
public class MaxFruitCountOfNTypes {
    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " + findLengthTrial(new char[]{'A', 'B', 'C', 'A', 'C'}, 2) + "\n");
        System.out.println("Maximum number of fruits: " + findLengthTrial(new char[]{'A', 'B', 'C', 'B', 'B', 'C'}, 2) + "\n");
    }

    /**
     * Time complexity = O(N + N) = O(2N) = O(N) ==> Same as recommended
     * <br/>
     * Space complexity = O(k + 1) ==> ços of the HashMap used
     */
    public static int findLengthTrial(char[] arr, int numOfBasket) {

        long start = System.nanoTime();

        if(arr.length <= 0 || numOfBasket <=0){
            throw new IllegalArgumentException();
        }

        int windowStart = 0, maxLength = 0;
        Map<Character, Integer> fruitTypeFreq = new HashMap<>();

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            char fruitTypeOnFastPointer = arr[windowEnd];
            fruitTypeFreq.put(fruitTypeOnFastPointer, fruitTypeFreq.getOrDefault(fruitTypeOnFastPointer, 0) + 1);

            while (fruitTypeFreq.size() > numOfBasket){
                char fruitTypeOnSlowPointer = arr[windowStart];
                fruitTypeFreq.put(fruitTypeOnSlowPointer, fruitTypeFreq.get(fruitTypeOnSlowPointer) - 1);
                if(fruitTypeFreq.get(fruitTypeOnSlowPointer) == 0){
                    fruitTypeFreq.remove(fruitTypeOnSlowPointer);
                }
                windowStart++;
            }

            maxLength = Math.max(maxLength, (windowEnd - windowStart) + 1);
        }

        long end = System.nanoTime();

        System.out.println(">> Time taken: " + (end - start) + "ns");

        return maxLength;
    }
}
