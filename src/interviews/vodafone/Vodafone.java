package interviews.vodafone;

import java.util.HashMap;
import java.util.Map;

public class Vodafone {

    public static void main(String[] args) {
        /*System.out.println(isPrimeNumber(1)); //Prime Number -> False
        System.out.println(isPrimeNumber(7)); //Prime Number -> True
        System.out.println(isPrimeNumber(10)); //Not prime number; 1, 2, 5, 10
        System.out.println(isPrimeNumber(35)); //Not prime number; 1, 5, 7, 35
        System.out.println(isPrimeNumber(57)); //Not prime number; 1, 5, 7, 35*/
    }

    /*
     * Write a method that for a given integer will return whether the integer is a prime number.
     * Write unit tests to prove the method is working correctly.
     */
    public static boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i < number / 2; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    /*
     *
     * Using the sentence below create an application to count the number of times each word appears in the sentence.  The application should output the number of times each word occurs.
     *
     * "This is an example paragraph in which words appear multiple times.  However, it is important that we consider all possibilities in our count of words.
     * Please feel free to ask questions based on the requirements as this is an interactive interview!"
     * */
    public static Map<String, Integer> getNumberOfOccurrence(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        if (sentence.isEmpty()) {
            return new HashMap<>();
        }

        final Map<String, Integer> resultMap = new HashMap<>();

        //Use regex to clean the input
        sentence = sentence.replace("!", "");
        sentence = sentence.replace(",", "");
        sentence = sentence.replace(".", "");
        sentence = sentence.toLowerCase();

        for (String word : sentence.split(" ")) {
            if (resultMap.containsKey(word)) {
                resultMap.put(word, resultMap.get(word) + 1);
            } else {
                resultMap.put(word, 1);
            }
        }
        return resultMap;
    }

}
