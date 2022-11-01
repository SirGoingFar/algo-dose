package preparation.grokking.mergeIntervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <b>[MEDIUM] Question: <b/>Given an array of intervals representing ‘N’ appointments, find out if a person can attend all the appointments.
 * <p/>
 * See MORE <a href=https://www.educative.io/courses/grokking-the-coding-interview/JExVVqRAN9D>here</a>
 */
public class ConflictingAppointments {

    /**
     * Time complexity: O(N * Log N) to sort the array
     * Space complexity = O(N) to sort the array
     */
    public static boolean canAttendAllAppointments(Interval[] intervals) {
        if (intervals.length == 0) {
            throw new IllegalArgumentException("Appointments interval cannot be empty");
        }

        Arrays.sort(intervals, Comparator.comparingInt(value -> value.start));
        for (int i = 1; i < intervals.length; i++) {
            // please note the comparison above, it is "<" and not "<="
            // while merging we needed "<=" comparison, as we will be merging the two
            // intervals having condition "intervals[i].start == intervals[i - 1].end" but
            // such intervals don't represent conflicting appointments as one starts right
            // after the other

            //also, we can use this condition because the array is sorted by interval's start
            if (intervals[i].start < intervals[i - 1].end) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Interval[] intervals = { new Interval(1, 4), new Interval(2, 5), new Interval(7, 9) };
        boolean result = ConflictingAppointments.canAttendAllAppointments(intervals);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4), new Interval(4, 5), new Interval(8, 12)};
        result = ConflictingAppointments.canAttendAllAppointments(intervals1);
        System.out.println("Can attend all appointments: " + result);

        Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6) };
        result = ConflictingAppointments.canAttendAllAppointments(intervals2);
        System.out.println("Can attend all appointments: " + result);
    }
}
