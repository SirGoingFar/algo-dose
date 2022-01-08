package interviews.payever;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Payever {

    //Todo: See Payever1.jpg
    public static String solution(String message, int K) {

        if (message == null || message.isEmpty() || message.length() <= K)
            return message;

        //Split Log to individual call history
        String[] messageArray = message.split(" ");

        if (messageArray.length <= 1)
            return message;

        StringBuilder sb = new StringBuilder(message.length());

        for (String word : messageArray) {

            if ((sb.length() + word.length()) > K)
                return sb.toString().trim();

            sb.append(word);
            sb.append(" ");
        }

        return sb.toString().trim();

    }

    //Todo: See Payever2.jpg
    public static int solution(String S) {

        if (S == null || S.isEmpty())
            return 0;

        String[] calls = S.split("\n");

        HashMap<String, Integer> phoneDurationMap = getCallDurationByPhoneNumber(calls);

        String freeCallPhoneNumber = getPhoneNumberForFreeCall(phoneDurationMap);

        int FIVE_MINUTES_IN_SECONDS = 5 * 60;

        int costSum = 0;

        for (Map.Entry<String, Integer> entry : phoneDurationMap.entrySet()) {

            if (entry.getKey().equals(freeCallPhoneNumber))
                continue;

            int value = entry.getValue();

            if (value < FIVE_MINUTES_IN_SECONDS)
                costSum += (value * 3);
            else
                costSum += (Math.ceil(value % 60) * 150);
        }


        return costSum;

    }

    private static String getPhoneNumberForFreeCall(HashMap<String, Integer> map) {

        Map<Integer, ArrayList<String>> reverseMap = new HashMap<>(
                map.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
                        .collect(Collectors.toMap(
                                item -> item.get(0).getValue(),
                                item -> new ArrayList<>(
                                        item.stream()
                                                .map(Map.Entry::getKey)
                                                .collect(Collectors.toList())
                                ))
                        ));

        Collection<ArrayList<String>> groupingByDurationList = reverseMap.values();

        if(groupingByDurationList.isEmpty())
            return null;

        ArrayList<String> phoneNumbersWithHighestDuration = (ArrayList<String>) groupingByDurationList.toArray()[groupingByDurationList.size() - 1];

        if (phoneNumbersWithHighestDuration.size() == 1) {
            return phoneNumbersWithHighestDuration.get(0);
        } else {

            int sum = Integer.MAX_VALUE;
            int smallestIndex = 0;

            for (int i = 0; i < (phoneNumbersWithHighestDuration.size() - 1); i++) {

                String phone = phoneNumbersWithHighestDuration.get(i);

                String[] phoneSplit = phone.split("-");

                int phoneSplitSum = 0;

                for (String split : phoneSplit) {
                    phoneSplitSum += Integer.parseInt(split);
                }

                if (phoneSplitSum < sum) {
                    smallestIndex = i;
                }
            }

            return phoneNumbersWithHighestDuration.get(smallestIndex);
        }
    }

    private static HashMap<String, Integer> getCallDurationByPhoneNumber(String[] calls) {

        HashMap<String, Integer> hm = new HashMap<>();

        for (String call : calls) {

            String[] split = call.split(",");
            String duration = split[0].trim();
            String phoneNumber = split[1].trim();

            if (hm.containsKey(phoneNumber)) {
                int currentDuration = hm.get(phoneNumber);
                int newDuration = currentDuration + convertToSeconds(duration);
                hm.put(phoneNumber, newDuration);
            } else {
                hm.put(phoneNumber, convertToSeconds(duration));
            }

        }

        return hm;
    }

    private static int convertToSeconds(String duration) {

        String[] timeSplit = duration.split(":");
        int hour = Integer.parseInt(timeSplit[0].trim());
        int minute = Integer.parseInt(timeSplit[1].trim());
        int second = Integer.parseInt(timeSplit[2].trim());

        return ((hour * 60 * 60) + (minute * 60) + second);

    }

    //Submission for Payever2
    /*class Solution {
    public int solution(String S) {
        if (S == null || S.isEmpty())
            return 0;

        String[] calls = S.split("\n");

        HashMap<String, Integer> phoneDurationMap = getCallDurationByPhone(calls);

        String freeCallPhoneNumber = getNumberForFreeCall(phoneDurationMap);

        int FIVE_MINUTES_IN_SECONDS = 5 * 60;

        int costSum = 0;

        for (Map.Entry<String, Integer> entry : phoneDurationMap.entrySet()) {

            if(entry.getKey().equals(freeCallPhoneNumber))
                continue;

            int value = entry.getValue();

            if(value < FIVE_MINUTES_IN_SECONDS)
                costSum += (value * 3);
            else
                costSum += ( (value % 60) * 150);
        }


        return costSum;
    }

    private static String getNumberForFreeCall(HashMap<String, Integer> map) {

        Map<Integer, ArrayList<String>> reverseMap = new HashMap<>(
                map.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
                        .collect(Collectors.toMap(
                                item -> item.get(0).getValue(),
                                item -> new ArrayList<>(
                                        item.stream()
                                                .map(Map.Entry::getKey)
                                                .collect(Collectors.toList())
                                ))
                        ));

        Collection<ArrayList<String>> groupingByDurationList = reverseMap.values();
        ArrayList<String> highestDurationNumbers = (ArrayList<String>) groupingByDurationList.toArray()[groupingByDurationList.size() - 1];

        if (highestDurationNumbers.size() == 1) {
            return highestDurationNumbers.get(0);
        } else {

            int sum = Integer.MAX_VALUE;
            int smallestIndex = 0;

            for (int i = 0; i < (highestDurationNumbers.size() - 1); i++) {

                String phone = highestDurationNumbers.get(i);

                String[] phoneSplit = phone.split("-");

                int phoneSplitSum = 0;

                for (String split : phoneSplit) {
                    phoneSplitSum += Integer.parseInt(split);
                }

                if (phoneSplitSum < sum) {
                    smallestIndex = i;
                }
            }

            return highestDurationNumbers.get(smallestIndex);
        }
    }

    private static HashMap<String, Integer> getCallDurationByPhone(String[] calls) {

        HashMap<String, Integer> hm = new HashMap<>();

        for (String call : calls) {

            String[] split = call.split(",");
            String duration = split[0].trim();
            String number = split[1].trim();

            if (hm.containsKey(number)) {
                int currentDuration = hm.get(number);
                int newDuration = currentDuration + convertToSeconds(duration);
                hm.put(number, newDuration);
            } else {
                hm.put(number, convertToSeconds(duration));
            }

        }

        return hm;
    }

    private int convertToSeconds(String duration) {

        String[] timeSplit = duration.split(":");
        int hour = Integer.parseInt(timeSplit[0].trim());
        int minute = Integer.parseInt(timeSplit[1].trim());
        int second = Integer.parseInt(timeSplit[2].trim());

        return ((hour * 60 * 60) + (minute * 60) + second);
    }
}*/

}
