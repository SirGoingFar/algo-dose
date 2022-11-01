package preparation.grokking.mergeIntervals.problems;

import preparation.grokking.mergeIntervals.Interval;

import java.util.*;

/**
 * <b>[HARD] Question: <b/>For ‘K’ employees, we are given a list of intervals representing each employee’s working hours.
 * Our goal is to determine if there is a free interval which is common to all employees. You can assume that each list
 * of employee working hours is sorted on the start time.
 * <p/>
 * See question <a href=https://www.educative.io/courses/grokking-the-coding-interview/YQykDmBnvB0>here</a>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/RLwKZWgMJ1q>here</a>
 */
public class EmployeeFreeTime {

    public static List<Interval> findEmployeeFreeTime1(List<List<Interval>> schedule) {
        if (schedule == null || schedule.isEmpty()) {
            return new ArrayList<>();
        }

        //Merge all employee schedules into 1 List and sort by interval start
        List<Interval> allEmployeeSchedules = new ArrayList<>();
        for (List<Interval> employeeInterval : schedule) {
            allEmployeeSchedules.addAll(new ArrayList<>(employeeInterval));
        }
        allEmployeeSchedules.sort(Comparator.comparingInt(a -> a.start));

        List<Interval> result = new ArrayList<>();
        Interval previous;
        Interval current;

        //compare intervals to determine a free time
        for (int i = 1; i < allEmployeeSchedules.size(); i++) {
            previous = allEmployeeSchedules.get(i - 1);
            current = allEmployeeSchedules.get(i);

            if (previous.end < current.start){
                //no overlap, add to free time
                result.add(new Interval(previous.end, current.start));
            }
        }

        return result;
    }

    /**
     * Time complexity: O(N) where N is the total interval for all employees
     * <p/>
     * Space complexity: O(N) for sorting and O(N) for the PriorityQueue = 2[O(N)] = O(N)
     */
    public static List<Interval> findEmployeeFreeTime2(List<List<Interval>> schedule) {
        if (schedule == null || schedule.isEmpty()) {
            return new ArrayList<>();
        }

        //NB: This approach leverages the fact that individual employee's interval is sorted
        final PriorityQueue<EmployeeInterval> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.interval.start));
        List<Interval> result = new ArrayList<>();

        //add the first interval of each employee to the queue
        for (int i = 0; i < schedule.size(); i++) {
            queue.offer(new EmployeeInterval(schedule.get(i).get(0), i, 0));
        }

        Interval previous = Objects.requireNonNull(queue.peek()).interval;
        EmployeeInterval queueTop;

        //start comparing intervals from the queue as the schedule with the smallest interval will be at the head of the queue
        while (!queue.isEmpty()) {
            queueTop = queue.poll();

            //check for overlap between the previous interval and the interval at the top of the queue
            if (previous.end < queueTop.interval.start) {
                //no overlap, add free time
                result.add(new Interval(previous.end, queueTop.interval.start));

                //update previous interval to the interval at the top of the queue (that is now removed)
                previous = queueTop.interval;
            } else {
                //no overlap - update previous interval if necessary
                if (previous.end < queueTop.interval.end) {
                    //end of the interval at the queueTop is longer than that of the previous interval, update!
                    previous = queueTop.interval;
                }
            }

            //add another interval from the employee schedule to the queue to replace the queueTop removed.
            //Next interval from the employee that own queueTop is to be added
            if (schedule.get(queueTop.employeeIdx).size() > queueTop.intervalIdx + 1) {
                queue.add(new EmployeeInterval(schedule.get(queueTop.employeeIdx).get(queueTop.intervalIdx + 1), queueTop.employeeIdx, queueTop.intervalIdx + 1));
            }
            //NB: If we're unable to add any interval from the employee that owns the queueTop, the while loop will move to the next
            //interval (for another employee) on the queue and new interval will be added to the queue (provided the next employee has
            //pending interval(s) too. If no interval is added to the queue (i.e. all employee's interval has been processed) and the queue
            //is empty, the algorithm will halt and result will be returned.
        }
        return result;
    }

    public static void main(String[] args) {

        List<List<Interval>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        List<Interval> result = EmployeeFreeTime.findEmployeeFreeTime2(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime2(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTime2(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(5, 6))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 3), new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime1(input);
        System.out.print("\n\nFree intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3), new Interval(9, 12))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(6, 8))));
        result = EmployeeFreeTime.findEmployeeFreeTime1(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
        System.out.println();

        input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(new Interval(1, 3))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(2, 4))));
        input.add(new ArrayList<>(Arrays.asList(new Interval(3, 5), new Interval(7, 9))));
        result = EmployeeFreeTime.findEmployeeFreeTime1(input);
        System.out.print("Free intervals: ");
        for (Interval interval : result)
            System.out.print("[" + interval.start + ", " + interval.end + "] ");
    }

    private static class EmployeeInterval {
        Interval interval;
        int employeeIdx;
        int intervalIdx;

        public EmployeeInterval(Interval interval, int employeeIdx, int intervalIdx) {
            this.interval = interval;
            this.employeeIdx = employeeIdx;
            this.intervalIdx = intervalIdx;
        }
    }

}
