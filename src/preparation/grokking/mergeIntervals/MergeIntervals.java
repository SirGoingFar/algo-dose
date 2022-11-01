package preparation.grokking.mergeIntervals;

import java.util.*;

/**
 * <b>[MEDIUM] Question: <b/>Given a list of intervals, merge all the overlapping intervals to produce a list that has
 * only mutually exclusive intervals.
 * <p>
 * See MORE <a href=https://www.educative.io/courses/grokking-the-coding-interview/3jyVPKRA8yx>here</a>
 */
public class MergeIntervals {

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.printf("Unsorted Input intervals: %s\n", input);
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.printf("\nHas overlap: %s", MergeIntervals.hasOverlap(input));
        System.out.println();
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.printf("\nHas overlap: %s", MergeIntervals.hasOverlap(input));
        System.out.println();
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.printf("Unsorted Input intervals: %s\n", input);
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.printf("\nHas overlap: %s", MergeIntervals.hasOverlap(input));
        System.out.println();
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(4, 7));
        input.add(new Interval(9, 10));
        System.out.printf("Unsorted Input intervals: %s\n", input);
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.printf("\nHas overlap: %s", MergeIntervals.hasOverlap(input));
        System.out.println();
    }

    /**
     * Time complexity: O(N*logN) for sorting, O(N) to iterate through the List to find interval overlap.
     * Hence:  O(N*logN) + O(N) = O(N*logN)
     * <p>
     * Space complexity: O(N) for list sorting and O(N) - worst case - for the returned list for merged interval.
     * Hence: O(N) + O(N) = 2(O(N)) = O(N)
     */
    private static List<Interval> merge(List<Interval> intervalList) {
        if (intervalList == null || intervalList.size() < 2) {
            return intervalList;
        }

        //Sort the intervals based on their start
        intervalList.sort(Comparator.comparingInt(a -> a.start));

        final List<Interval> mergedIntervals = new LinkedList<>();
        final Iterator<Interval> intervalIterator = intervalList.iterator();
        Interval firstInterval = intervalIterator.next(); //We're bold to do this cos there are more than 1 intervals in the list
        int start = firstInterval.start;
        int end = firstInterval.end;

        while (intervalIterator.hasNext()) {
            final Interval currentInterval = intervalIterator.next();
            //Compare start of current currentInterval with the end of first/merge currentInterval
            if (currentInterval.start <= end) {
                //there is an overlap, determine the end of the merge currentInterval, c
                    end = Math.max(end, currentInterval.end);
            } else {
                //add the merge currentInterval of previous currentInterval(s) - might be one or more
                mergedIntervals.add(new Interval(start, end));
                //move the start and end to the current currentInterval that has no overlap with the first/merge currentInterval
                start = currentInterval.start;
                end = currentInterval.end;
            }
        }

        //add the last interval
        mergedIntervals.add(new Interval(start, end));

        return mergedIntervals;
    }

    private static boolean hasOverlap(List<Interval> intervalList) {
        if (intervalList == null || intervalList.size() < 2) {
            return false;
        }

        //Sort the intervals based on their start
        intervalList.sort(Comparator.comparingInt(a -> a.start));

        final Iterator<Interval> intervalIterator = intervalList.iterator();
        Interval firstInterval = intervalIterator.next(); //We're bold to do this cos there are more than 1 intervals in the list
        int end = firstInterval.end;

        while (intervalIterator.hasNext()) {
            final Interval currentInterval = intervalIterator.next();
            //Compare start of current currentInterval with the end of first/merge currentInterval
            if (currentInterval.start <= end) {
                //there is an overlap, return
                return true;
            } else {
                //move the start and end to the current currentInterval that has no overlap with the first/merge currentInterval
                end = currentInterval.end;
            }
        }

        return false;
    }

}
