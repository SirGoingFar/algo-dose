package preparation.grokking.mergeIntervals.problems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <b>[HARD] Question: <b/>Given a list of intervals representing the start and end time of ‘N’ meetings, find the minimum number of rooms required to hold all the meetings.
 * <p/>
 * See question <a href=https://www.educative.io/courses/grokking-the-coding-interview/xVoBRZz7RwP>here</a>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/JQMAmrVPL7l>here</a>
 */
public class MinimumMeetingRooms {

    /**
     * Time complexity: O(N*logN) for sorting the meetings, while iterating through the meetings, we offer and poll (e.g of O(logN) each) i.e. O[N(logN + logN)] = 2[O(logN)] = O(logN).
     * Hence time complexity = O(NlogN) + O(logN) = O(NlogN) asymptotically
     * <p/>
     * Space complexity: O(N) for sorting and O(N) for the PriorityQueue = 2[O(N)] = O(N)
     */
    public static int findMinimumMeetingRooms(List<Meeting> meetings) {
        if (meetings == null || meetings.isEmpty()) {
            return 0;
        }

        int minRoom = 0;

        //sort meetings by start time
        meetings.sort(Comparator.comparingInt(a -> a.start));

        //PriorityQueue will old all active meetings (i.e. meetings that overlap)
        final PriorityQueue<Meeting> activeMeetings = new PriorityQueue<>(meetings.size(), Comparator.comparingInt(a -> a.end));

        //Loop through the meetings and assign them meeting rooms
        for (Meeting nextMeeting : meetings) {

            //remove all meetings that would end before 'nextMeeting' starts - just so we know if we can re-use existing nextMeeting rooms
            //i.e. meetings whose 'end' <= start of 'nextMeeting'
            while (!activeMeetings.isEmpty() && nextMeeting.start >= activeMeetings.peek().end) {
                //remove such meetings
                activeMeetings.poll();
            }

            //assign 'nextMeeting' to a meeting room
            activeMeetings.offer(nextMeeting);

            //Track the number of meeting rooms currently in use
            minRoom = Math.max(minRoom, activeMeetings.size());
        }

        return minRoom;
    }

    public static void main(String[] args) {
        List<Meeting> input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        int result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 5));
                add(new Meeting(7, 9));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(6, 7));
                add(new Meeting(2, 4));
                add(new Meeting(8, 12));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(1, 4));
                add(new Meeting(2, 3));
                add(new Meeting(3, 6));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);

        input = new ArrayList<Meeting>() {
            {
                add(new Meeting(4, 5));
                add(new Meeting(2, 3));
                add(new Meeting(2, 4));
                add(new Meeting(3, 5));
            }
        };
        result = MinimumMeetingRooms.findMinimumMeetingRooms(input);
        System.out.println("Minimum meeting rooms required: " + result);
    }

    public static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
