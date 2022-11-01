package preparation.grokking.mergeIntervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * <b>[MEDIUM] Question: <b/>Given a list of non-overlapping intervals sorted by their start time, insert a given
 * interval at the correct position and merge all necessary intervals to produce a list that has only mutually exclusive intervals.
 * <p>
 * See MORE <a href=https://www.educative.io/courses/grokking-the-coding-interview/3jKlyNMJPEM>here</a>
 */
public class InsertInterval {
    //Recommended solution: This solution does not capture edge cases and can't work for input "[1,3] [2,3] [5,7] [8,12]"
    /**
     * Time complexity: O(N)
     * Space complexity = O(N) for the returned result
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.isEmpty()) {
            return Collections.singletonList(newInterval);
        }

        List<Interval> mergedIntervals = new ArrayList<>();

        int i = 0;
        // skip (and add to output) all intervals that come before the 'newInterval'
        while (i < intervals.size() && intervals.get(i).end < newInterval.start)
            mergedIntervals.add(intervals.get(i++));

        // merge all intervals that overlap with 'newInterval'
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
            i++;
        }

        // insert the newInterval
        mergedIntervals.add(newInterval);

        // add all the remaining intervals to the output
        while (i < intervals.size())
            mergedIntervals.add(intervals.get(i++));

        return mergedIntervals;
    }

    /**
     * NB: If an array is passed as inputs instead of a List that can allow dynamic insertion, it will cost us
     * additional O(N) space complexity to use this approach.
     *
     * Time complexity: O(N) - worst case - to find the right index to put newInterval and O(N) to merge intervals = O(N) + O(N) = 2[O(N)] = O(N)
     * Space complexity = O(N) for the returned result
     */
    public static List<Interval> _insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.isEmpty()) {
            return Collections.singletonList(newInterval);
        }

        //Get the index preceding the interval whose start is greater than or same as the start of newInterval.
        //So, we can put the newInterval at the index and STILL make the List sorted as given
        int i;
        for (i = 0; i < intervals.size(); i++) {
            if (intervals.get(i).start >= newInterval.start) {
                break;
            }
        }
        //insert the newInterval at the index
        intervals.add(i, newInterval);

        //--- Merge overlapping intervals without sorting again ---
        List<Interval> mergeIntervals = new ArrayList<>();
        final Iterator<Interval> intervalIterator = intervals.iterator();
        final Interval firstInterval = intervalIterator.next();
        int start = firstInterval.start;
        int end = firstInterval.end;

        Interval ctxInterval;
        while (intervalIterator.hasNext()) {
            ctxInterval = intervalIterator.next();
            if (ctxInterval.start < end) {
                end = Math.max(ctxInterval.end, end);
            } else {
                mergeIntervals.add(new Interval(start, end));
                start = ctxInterval.start;
                end = ctxInterval.end;
            }
        }
        //add the current value of 'start' and 'end'
        mergeIntervals.add(new Interval(start, end));

        return mergeIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("[RECOMMENDED] Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.print("\n[MINE] Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval._insert(input, new Interval(4, 6)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(1, 3));
        input.add(new Interval(3, 6));
        input.add(new Interval(5, 7));
        input.add(new Interval(8, 12));
        System.out.print("[RECOMMENDED] Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.print("\n[MINE] Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval._insert(input, new Interval(4, 10)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
        System.out.println();

        input = new ArrayList<>();
        input.add(new Interval(2, 3));
        input.add(new Interval(5, 7));
        System.out.print("[RECOMMENDED] Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval.insert(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.print("\n[MINE] Intervals after inserting the new interval: ");
        for (Interval interval : InsertInterval._insert(input, new Interval(1, 4)))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
        System.out.println();
    }
}
