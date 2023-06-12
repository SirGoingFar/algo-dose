package interviews.vodafone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static interviews.vodafone.Vodafone.getNumberOfOccurrence;

class VodafoneTest {

    @Test
    public void testIsPrimeNumber_numberIsLessThanOne_returnsFalse() {
        Assertions.assertFalse(Vodafone.isPrimeNumber(-1));
    }

    @Test
    public void testIsPrimeNumber_numberIsZero_returnsFalse() {
        Assertions.assertFalse(Vodafone.isPrimeNumber(0));
    }

    @Test
    public void testIsPrimeNumber_numberIsOne_returnsFalse() {
        Assertions.assertFalse(Vodafone.isPrimeNumber(1));
    }

    @Test
    public void testIsPrimeNumber_numberIsThree_returnsTrue() {
        Assertions.assertTrue(Vodafone.isPrimeNumber(3));
    }

    @Test
    public void testIsPrimeNumber_numberIsSeven_returnsTrue() {
        Assertions.assertTrue(Vodafone.isPrimeNumber(7));
    }

    @Test
    public void testIsPrimeNumber_numberIsFiftySeven_returnsFalse() {
        Assertions.assertFalse(Vodafone.isPrimeNumber(57));
    }


    //Test 2
    @Test
    public void getWordCount_sentenceIsNull_threwIllegalArgumentException() {
        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> getNumberOfOccurrence(null));
    }

    @Test
    public void getWordCount_sentenceIsEmpty_returnsEmptyMap() {
        final Map<String, Integer> wordMap = Vodafone.getNumberOfOccurrence("");
        Assertions.assertNotNull(wordMap);
        Assertions.assertTrue(wordMap.isEmpty());
    }

    @Test
    public void getWordCount_sentenceIsNotEmpty_returnsNoneEmptyMap() {
        final Map<String, Integer> wordMap = Vodafone.getNumberOfOccurrence("not empty");
        Assertions.assertNotNull(wordMap);
        Assertions.assertFalse(wordMap.isEmpty());
    }

    @Test
    public void getWordCount_sentenceContainsWord_returnsValidWordCount() {
        final Map<String, Integer> wordMap = Vodafone.getNumberOfOccurrence("Vodafone is a good company. Yes, a good company.");
        Assertions.assertNotNull(wordMap);
        Assertions.assertFalse(wordMap.isEmpty());
        Assertions.assertTrue(wordMap.containsKey("company"));
        Assertions.assertEquals(2, wordMap.get("company"));
    }

//    @Test
    public void getWordCount_sentenceDoesNotContainWord_returnsValidWordCount() {
        final Map<String, Integer> wordMap = Vodafone.getNumberOfOccurrence("Vodafone is a good company. Yes, a good company.");
        Assertions.assertNotNull(wordMap);
        Assertions.assertFalse(wordMap.isEmpty());
        Assertions.assertTrue(wordMap.containsKey("London"));
        Assertions.assertEquals(2, wordMap.get("London"));
    }

}