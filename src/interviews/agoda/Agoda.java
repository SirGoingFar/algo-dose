package interviews.agoda;

import java.util.*;

public class Agoda {

    public static List<Integer> getFirstTwoItemsWithoutPair(List<Integer> list) {

        if (list.isEmpty())
            return list;

        HashMap<Integer, Integer> countMap = new HashMap<>();
        List<Integer> uniquePair = new ArrayList<>();

        for (Integer item : list) {

            if (countMap.containsKey(item)) {
                int currentCount = countMap.get(item);
                countMap.put(item, ++currentCount);
            } else {
                countMap.put(item, 1);
            }

        }

        for (Map.Entry<Integer, Integer> item :
                countMap.entrySet()) {

            if (item.getValue() == 1)
                uniquePair.add(item.getKey());
        }

        return uniquePair.subList(0, 3);
    }

    //Reverse Word
    public static String reverseWords(String inputWords) {

        if (inputWords == null || inputWords.isEmpty())
            return inputWords;

        String[] words = inputWords.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int index = words.length - 1; index >= 0; index--) {
            sb.append(words[index]);
            sb.append(" ");
        }

        return sb.toString().trim();
    }

    public static LinkedHashMap<Integer, String> sortHashMapByValues(
            HashMap<Integer, String> passedMap) {
        List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
        List<String> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Integer, String> sortedMap =
                new LinkedHashMap<>();

        Iterator<String> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            String val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                String comp1 = passedMap.get(key);
                String comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

}
