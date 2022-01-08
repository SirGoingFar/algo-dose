package tasks.kudi_weekly.week_2;

import java.util.*;
import java.util.stream.Collectors;

public class Akintunde_2 {

    public static void main(String[] args) {

        final List<String> possibleFeatures =
                Arrays.asList("Storage", "Battery", "hover", "alexa", "waterproof", "solar");

        final List<String> featureRequests = Arrays.asList(
                "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.",
                "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.",
                "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.",
                "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.",
                "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.",
                "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.",
                "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.", "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.", "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.", "I wish my kindle even had even more storage!",
                "I wish the battery life on my kindle lasted 2 years",
                "I read in the bath and would enjoy a waterproof kindle",
                "Waterproof and increased and battery are my top two requests",
                "I want to take my kindle into the shower. Waterproof please waterproof!",
                "It would be neat if my kindle would hover on my desk when not in use.",
                "How cool would it be my Kindle charged in the sun via solar power?"
        );

        long then = System.currentTimeMillis();
        List<String> result = getUniqueFeatureRequest(
                possibleFeatures.size(),
                2,
                featureRequests.size(),
                possibleFeatures,
                featureRequests
        );
        System.out.println("Execution Time for " + featureRequests.size() + " feature requests (ms) : " + (System.currentTimeMillis() - then));
        System.out.println();
        System.out.println(result);

    }

    //Time Complexity - O(N^2)
    static List<String> getUniqueFeatureRequest(
            int numFeature,
            int topFeature,
            int numFeatureReq,
            List<String> possibleFeatures,
            List<String> featureRequests
    ) {

        //No point moving forward if any of these conditions is TRUE
        if (possibleFeatures == null || possibleFeatures.isEmpty() || featureRequests == null || featureRequests.isEmpty())
            return new ArrayList<>();

        //Take the possible features list to lower case
        possibleFeatures = turnToLower(possibleFeatures);

        //This holds the mentioned feature requests
        List<String> mentionedFeature = new ArrayList<>();

        //This holds the Feature Requests count
        Map<String, Integer> featureCount = doFeatureCountWith(possibleFeatures, featureRequests);

        //Get topFeature number of possible requests from highest count to lowest
        mentionedFeature = getMentionedFeatureOfSize(topFeature, featureCount);

        return mentionedFeature;
    }

    private static List<String> getMentionedFeatureOfSize(int neededSize, Map<String, Integer> featureCount) {

        ArrayList<String> mentionedFeature = new ArrayList<>();

        //Group features by their count and collect list in ASCENDING order
        Map<Integer, ArrayList<String>> groupMap = new HashMap<>(
                featureCount.entrySet().stream()
                        .collect(Collectors.groupingBy(Map.Entry::getValue)).values().stream()
                        .collect(Collectors.toMap(
                                item -> item.get(0).getValue(),
                                item -> new ArrayList<>(
                                        item.stream()
                                                .map(Map.Entry::getKey)
                                                .collect(Collectors.toList())
                                ))
                        ));

        //Sieve the words into a Collection of ArrayLists
        Collection<ArrayList<String>> featureGroupingByCount = groupMap.values();

        if (featureGroupingByCount.isEmpty())
            return mentionedFeature;


        //Loop through (neededSize - 1) to 0
        for (int index = (featureGroupingByCount.size() - 1); index >= 0; index--) {

            ArrayList<String> featureGroup = (ArrayList<String>) featureGroupingByCount.toArray()[index];

            //Sort the List alphabetically
            if (featureGroup.size() > 1)
                Collections.sort(featureGroup);

            //Loop through each feature in the feature group
            for (String feature : featureGroup) {

                //If the needed size has been reached, return the list
                if (mentionedFeature.size() == neededSize)
                    return mentionedFeature;

                //add the feature to the 'victorious' list :)
                mentionedFeature.add(feature);
            }

        }

        //Return the feature(s) that made the condition
        return mentionedFeature;
    }

    private static Map<String, Integer> doFeatureCountWith(List<String> possibleFeatures, List<String> featureRequests) {

        Map<String, Integer> featureCount = new HashMap<>();

        for (String featureRequest : featureRequests) {

            //This consists of all space-separated words in a feature request
            String[] featureRequestSplit = featureRequest.split(" ");

            //This tracks if a feature request has been tracked for a particular feature request
            boolean[] featureTrack = new boolean[possibleFeatures.size()];

            //This tracks the number of features tracked in a feature request
            int numberOfFeatureTracked = 0;

            //Iterate through each word split in a feature request
            for (String split : featureRequestSplit) {

                //Ooops! All the possible features have been tracked for this feature request, save the execution time
                //since multiple occurrence of a feature should be treated as ONE
                if (numberOfFeatureTracked == possibleFeatures.size())
                    break;

                //Turn the current word split to lower case - remove non-letters
                String splitLowered = lettersOnly(split).toLowerCase();

                //Is the current word split a feature?
                if (possibleFeatures.contains(splitLowered)) {

                    //Get its index in the possible feature list
                    int index = possibleFeatures.indexOf(splitLowered);

                    //Has the feature being tracked for this feature request?
                    if (featureTrack[index])
                        continue;

                    //increment the number of feature tracked in this feature request
                    numberOfFeatureTracked++;

                    //indicate that a possible feature has been tracked for this feature request
                    featureTrack[index] = true;

                    //update the feature occurrence count
                    if (featureCount.containsKey(splitLowered)) {
                        int currentCount = featureCount.get(splitLowered);
                        featureCount.put(splitLowered, ++currentCount);
                    } else {
                        featureCount.put(splitLowered, 1);
                    }

                }

            }

        }

        return featureCount;
    }

    private static List<String> turnToLower(List<String> words) {

        if (words == null || words.isEmpty())
            return words;

        ArrayList<String> listElementInLower = new ArrayList<>(words.size());

        for (String word : words)
            listElementInLower.add(word.toLowerCase(Locale.getDefault()));

        return listElementInLower;
    }

    static String lettersOnly(String input) {

        if (input == null || input.isEmpty())
            return input;

        StringBuilder sb = new StringBuilder(input.length());

        for (Character c : input.toCharArray()) {
            if (Character.isLetter(c))
                sb.append(c);
        }

        return sb.toString();
    }
}
