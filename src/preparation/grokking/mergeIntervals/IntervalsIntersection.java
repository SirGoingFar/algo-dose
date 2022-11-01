package preparation.grokking.mergeIntervals;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>[MEDIUM] Question: <b/>Given two lists of intervals, find the intersection of these two lists. Each list consists
 * of disjoint intervals sorted on their start time.
 * <p>
 * See MORE <a href=https://www.educative.io/courses/grokking-the-coding-interview/JExVVqRAN9D>here</a>
 */
public class IntervalsIntersection {

    public static void main(String[] args) {
        Interval[] input1 = new Interval[]{new Interval(1, 3), new Interval(5, 6), new Interval(7, 9)};
        Interval[] input2 = new Interval[]{new Interval(2, 3), new Interval(5, 7)};
        Interval[] result = IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input1 = new Interval[]{new Interval(1, 3), new Interval(5, 7), new Interval(9, 12)};
        input2 = new Interval[]{new Interval(5, 10)};
        result = IntervalsIntersection.merge(input1, input2);
        System.out.print("Intervals Intersection: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + "," + interval.end + "] ");
    }

    /**
     * Time complexity: O(N + M)
     * Space complexity = O(1) ignoring the returned result
     */
    private static Interval[] merge(Interval[] arr1, Interval[] arr2) {
        if (arr1.length == 0 || arr2.length == 0) {
            return new Interval[0];
        }

        //NB: The question says arr1 and arr2 are already sorted by the 'start' of the
        //interval element(s). If this isn't specified, we have to sort arr1 and arr2
        // with the interval element start time first

        int i = 0, j = 0;
        List<Interval> result = new ArrayList<>();

        Interval iInterval;
        Interval jInterval;

        while (i < arr1.length && j < arr2.length) {
            iInterval = arr1[i];
            jInterval = arr2[j];

            // check if one of the intervals' start time lies within the other interval
            if (iInterval.start >= jInterval.start && iInterval.start <= jInterval.end ||
                    jInterval.start >= iInterval.start && jInterval.start <= iInterval.end) {
                result.add(new Interval(Math.max(iInterval.start, jInterval.start), Math.min(iInterval.end, jInterval.end)));
            }

            //NB: If each interval array elements are not of disjoint intervals, this condition will not suffice.

            // move next from the interval which is finishing first
            if (iInterval.end < jInterval.end) {
                i++;
            } else {
                j++;
            }

        }

        return result.toArray(new Interval[0]);
    }

}
