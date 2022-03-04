package interviews.flatworld;

import java.util.*;
import java.util.stream.Collectors;

public class FlatworldJava {
    public static void main(String[] args) {

        Map<String,Integer> map = new HashMap<>();
        map.put("Ademosu", 34);
        map.put("Lukman", 0);
        map.put("Raheem", 0);
        map.put("Raheem Sterling", 0);
        map.put("Zadok", 1);
        map.put("Hitler", 1);
        map.put("Adewale B", 34);
        map.put("Adewale A", 34);

        Map<Integer, ArrayList<String>> ascOrderMap = new HashMap<>(
                map.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
                        .collect(Collectors.toMap(
                                item -> item.get(0).getValue(),
                                item -> new ArrayList<>(
                                        item.stream()
                                                .map(Map.Entry::getKey)
                                                .sorted()
                                                .collect(Collectors.toList())
                                ))
                        ));

        Collection<ArrayList<String>> groupingByValueList = ascOrderMap.values();

        //List of Keys of the highest-value entry
        List<String> highestValueEntryList = (List<String>) groupingByValueList.toArray()[groupingByValueList.size() - 1];

        System.out.println(groupingByValueList);
        System.out.println(highestValueEntryList);

    }

    //1
    int[] solution(int[] numbers) {
        int a = 0;
        int b = 0;
        int c = 0;

        int iteratonCount = numbers.length - 2;
        int[] result = new int[iteratonCount];

        for(int i = 0; i < iteratonCount; i++){
            a = numbers[i];
            b = numbers[i+1];
            c = numbers[i+2];

            if((a < b && b > c) || (a > b && b < c)){
                result[i] = 1;
            }else{
                result[i] = 0;
            }
        }

        return result;
    }


    /**
     *
     *
     * Input:
     * a: [35, 72, 38, 58, 80, 74, 94, 47, 50, 99, 41, 70, 98, 33, 50]
     * Output:
     * [1, 2]
     * Expected Output:
     * [0, 3, 4, 5, 7, 8, 9]
     *
     *
     * Input:
     * a: [31, 60, 53, 54, 25, 87, 33, 95]
     * Output:
     * [0, 1, 2, 4, 6, 7, 8, 9]
     * Expected Output:
     * [3, 5]
     *
     *
     * */
    //2
    int[] solution__(int[] a) {
        Map<Integer,Integer> occurrenceMap = new HashMap<>();
        char[] charArray = null;
        int xInt = 0;
        for(int num : a){
            charArray = String.valueOf(num).toCharArray();
            for(char x:charArray){
                xInt = Integer.parseInt(String.valueOf(x));
                if(occurrenceMap.containsKey(xInt)){
                    occurrenceMap.put(xInt, occurrenceMap.get(xInt)+1);
                }else{
                    occurrenceMap.put(xInt, 1);
                }
            }
        }

        Map<Integer, ArrayList<Integer>> reverseMap = new HashMap<>(
                occurrenceMap.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
                        .collect(Collectors.toMap(
                                item -> item.get(0).getValue(),
                                item -> new ArrayList<>(
                                        item.stream()
                                                .map(Map.Entry::getKey)
                                                .collect(Collectors.toList())
                                ))
                        ));

        Collection<ArrayList<Integer>> groupingByOccurenceList = reverseMap.values();

        List<Integer> list =  (ArrayList<Integer>) groupingByOccurenceList.toArray()[groupingByOccurenceList.size() - 1];

        int[] result = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            result[i] = list.get(i).intValue();
        }
        return result;

    }

    //3
    String solution(String[] arr) {
        StringBuilder sb = new StringBuilder();

        int MAX_LENGTH = Integer.MIN_VALUE;
        for(String s:arr){
            if(s.length() > MAX_LENGTH){
                MAX_LENGTH = s.length();
            }
        }

        for(int i=0; i<MAX_LENGTH; i++){
            for(String entry:arr){
                if(i > entry.length()-1){
                    continue;
                }else{
                    sb.append(entry.charAt(i));
                }
            }
        }
        return sb.toString();
    }
}
