package tasks.kudi_weekly.week_4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Akintunde {

    public static void main(String[] args) {

        //Test Case 1
        // Correct Result --->  Phone Number for free call: 0909-367-932 | Total Call Sum: 72 NGN
        String callLogStream = "03:26:05;0909-367-932\n" +
                "00:26:05;0909-357-932\n" +
                "03:26:04;0907-367-932\n";

        //Test Case 2
        // Correct Result --->  Phone Number for free call: 0709-357-932 | Total Call Sum: 1840 NGN
        String callLogStreamBig = "03:26:05;0909-367-932\n" +
                "00:26:05;0909-357-932\n" +
                "00:26:05;0909-357-232\n" +
                "08:26:05;0805-227-932\n" +
                "02:16:05;0709-357-932\n" +
                "00:01:05;0909-357-932\n" +
                "00:26:05;0809-357-932\n" +
                "01:59:59;0808-357-932\n" +
                "00:26:05;0701-357-932\n" +
                "00:36:05;0709-357-932\n" +
                "00:26:25;0909-357-932\n" +
                "00:09:02;0903-357-932\n" +
                "00:59:05;0709-357-932\n" +
                "02:26:05;0909-397-932\n" +
                "00:26:05;0819-357-932\n" +
                "06:26:04;0907-367-922\n" +
                "03:26:05;0909-367-932\n" +
                "00:26:05;0909-357-932\n" +
                "00:26:05;0909-357-232\n" +
                "08:26:05;0805-227-932\n" +
                "02:16:05;0709-357-932\n" +
                "00:01:05;0909-357-932\n" +
                "00:26:05;0809-357-932\n" +
                "01:59:59;0808-357-932\n" +
                "00:26:05;0701-357-932\n" +
                "00:36:05;0709-357-932\n" +
                "00:26:25;0909-357-932\n" +
                "00:09:02;0903-357-932\n" +
                "00:59:05;0709-357-932\n" +
                "02:26:05;0909-397-932\n" +
                "00:26:05;0819-357-932\n" +
                "06:26:04;0907-367-922\n" +
                "03:26:05;0909-367-932\n" +
                "00:26:05;0909-357-932\n" +
                "00:26:05;0909-357-232\n" +
                "08:26:05;0805-227-932\n" +
                "02:16:05;0709-357-932\n" +
                "00:01:05;0909-357-932\n" +
                "00:26:05;0809-357-932\n" +
                "01:59:59;0808-357-932\n" +
                "00:26:05;0701-357-932\n" +
                "00:36:05;0709-357-932\n" +
                "00:26:25;0909-357-932\n" +
                "00:09:02;0903-357-932\n" +
                "00:59:05;0709-357-932\n" +
                "02:26:05;0909-397-932\n" +
                "00:26:05;0819-357-932\n" +
                "06:26:04;0907-367-922\n" +
                "03:26:05;0909-367-932\n" +
                "00:26:05;0909-357-932\n" +
                "00:26:05;0909-357-232\n" +
                "08:26:05;0805-227-932\n" +
                "02:16:05;0709-357-932\n" +
                "00:01:05;0909-357-932\n" +
                "00:26:05;0809-357-932\n" +
                "01:59:59;0808-357-932\n" +
                "00:26:05;0701-357-932\n" +
                "00:36:05;0709-357-932\n" +
                "00:26:25;0909-357-932\n" +
                "00:09:02;0903-357-932\n" +
                "00:59:05;0709-357-932\n" +
                "02:26:05;0909-397-932\n" +
                "00:26:05;0819-357-932\n" +
                "06:26:04;0907-367-922\n";

        long then = System.currentTimeMillis();
        double cost = totalCallSumAccordingToPolicy(callLogStreamBig);
        long now = System.currentTimeMillis();

        System.out.println(
                "Total Call Sum: "
                        .concat(String.valueOf(cost / 100))
                        .concat(" NGN")
        );

        System.out.println("Time Taken: ".concat(String.valueOf(now - then)));

    }

    public static int totalCallSumAccordingToPolicy(String callLogStream) {

        if (callLogStream == null || callLogStream.isEmpty())
            return 0;

        String[] calls = callLogStream.split("\n");

        Map<String, Integer> phoneDurationMap = getCallDurationByPhoneNumber(calls);

        String freeCallPhoneNumber = getPhoneNumberForFreeCall(phoneDurationMap);

        System.out.println(phoneDurationMap);
        //var_dump phone number
        System.out.println("Phone Number for free call: ".concat(freeCallPhoneNumber));

        int FIVE_MINUTES_IN_SECONDS = 5 * 60;
        double FIVE_MINUTES_IN_SECONDS_FLOAT = 5 * 60.0;

        int costSum = 0;

        for (Map.Entry<String, Integer> entry : phoneDurationMap.entrySet()) {

            if (entry.getKey().equals(freeCallPhoneNumber))
                continue;

            int value = entry.getValue();

            if (value < FIVE_MINUTES_IN_SECONDS)
                costSum += (value * 3);
            else
                costSum += (Math.ceil(value / 60.0) * 150);
        }


        return costSum;

    }

    private static String getPhoneNumberForFreeCall(Map<String, Integer> map) {

        //Group Phone Number by the Call Duration - ASC
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

        if (groupingByDurationList.isEmpty())
            return null;

        //List of Phone Numbers with highest call durations will be at index [list.size() - 1] - grouping arranged and sorted in ASC order
        ArrayList<String> phoneNumbersWithHighestDuration = (ArrayList<String>) groupingByDurationList.toArray()[groupingByDurationList.size() - 1];

        int smallestIndex = 0;
        int sum = Integer.MAX_VALUE;

        //If there are many phone numbers with the SAME call duration in the list containing the phone numbers with HIGHEST call duration
        if (phoneNumbersWithHighestDuration.size() > 1) {

            //Loop through the list of phone numbers with the highest call duration
            for (int i = 0; i <= (phoneNumbersWithHighestDuration.size() - 1); i++) {

                String phone = phoneNumbersWithHighestDuration.get(i);

                //Split the phone number into segments
                String[] phoneSegmentSplit = phone.split("-");

                int phoneSplitSum = 0;

                //Sum each segments
                for (String split : phoneSegmentSplit) {
                    phoneSplitSum += Integer.parseInt(split);
                }

                //If the segment sum is LESSER than the segment sum of the last phone number with LOWEST SUM, pick current phone number
                if (phoneSplitSum < sum) {
                    smallestIndex = i;
                    sum = phoneSplitSum;
                }
            }

        }

        return phoneNumbersWithHighestDuration.get(smallestIndex);

    }

    private static Map<String, Integer> getCallDurationByPhoneNumber(String[] calls) {

        Map<String, Integer> hm = new HashMap<>();

        for (String call : calls) {

            String[] split = call.split(";");
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
}
