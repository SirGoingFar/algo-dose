package preparation.cracking_coding_interview.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***

 Given a set of strings, find the longest common prefix.

 Input: {"geeksforgeeks", "geeks", "geek", "geezer"}
 Output: "gee"

 Input: {"apple", "ape", "april"}
 Output: "ap"

 **/
public class LongestPrefix {

    public static void main(String[] args) {
        System.out.println(getLongestPrefix(Arrays.asList("geeksforgeeks", "geeks", "ge", "geezer")));
        System.out.println(getLongestPrefix(Arrays.asList("apple", "ape", "april")));
    }

    private static String getLongestPrefix(List<String> wordList) {
        if(wordList == null || wordList.isEmpty()){
            return ""; //throw exception
        }

        char[] baseWordArray = wordList.get(0).toCharArray();
        StringBuilder perWordSb;
        StringBuilder prefixSsb = new StringBuilder(10);
        char charInContext;
        main:
        for (int index = 0; index < baseWordArray.length; index++){
            charInContext = baseWordArray[index];
            for(String word : wordList){
                perWordSb = new StringBuilder(word);
                if(index > word.length() - 1 || perWordSb.charAt(index) != charInContext){
                    break main;
                }
            }
            prefixSsb.append(charInContext);
        }

        return prefixSsb.toString();
    }

}
