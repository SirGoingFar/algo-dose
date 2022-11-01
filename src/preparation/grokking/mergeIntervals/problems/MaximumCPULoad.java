package preparation.grokking.mergeIntervals.problems;

import java.util.*;

/**
 * <b>[HARD] Question: <b/>We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running.
 * Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.
 * <p/>
 * See question <a href=https://www.educative.io/courses/grokking-the-coding-interview/xVlyyv3rR93>here</a>
 * See solution <a href=https://www.educative.io/courses/grokking-the-coding-interview/YVwln9kYxV2>here</a>
 */
public class MaximumCPULoad {

    /**
     * Time complexity: O(N*logN) for sorting the meetings, while iterating through the meetings, we offer and poll (e.g of O(logN) each) i.e. O[N(logN + logN)] = 2[O(logN)] = O(logN).
     * Hence time complexity = O(NlogN) + O(logN) = O(NlogN) asymptotically
     * <p/>
     * Space complexity: O(N) for sorting and O(N) for the PriorityQueue = 2[O(N)] = O(N)
     */
    public static int findMaxCPULoad(List<Job> jobs) {
        if (jobs == null || jobs.isEmpty()) {
            return 0;
        }

        //sort job by their start time
        jobs.sort(Comparator.comparingInt(a -> a.start));

        //PriorityQueue to keep running jobs
        final PriorityQueue<Job> runningJobs = new PriorityQueue<>(jobs.size(), Comparator.comparingInt(a -> a.end));
        int currentCpuLoad = 0;
        int maxCpuLoad = 0;

        for (Job nextJob : jobs) {

            while (!runningJobs.isEmpty() && nextJob.start >= runningJobs.peek().end) {
                //remove all jobs that their 'end' <= the start of the next job to run
                //subtract the load of the job that is being removed from the CPU from the currentLoad
                currentCpuLoad -= runningJobs.poll().cpuLoad;
            }

            //add the next job to the CPU to run
            runningJobs.offer(nextJob);

            //add the load of the next job that's just added to the CPU
            currentCpuLoad += nextJob.cpuLoad;

            //track the highest load so far
            maxCpuLoad = Math.max(currentCpuLoad, maxCpuLoad);
        }

        return maxCpuLoad;
    }

    public static void main(String[] args) {
        List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));

        input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
        System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoad(input));
    }

    public static class Job {
        int start;
        int end;
        int cpuLoad;

        public Job(int start, int end, int cpuLoad) {
            this.start = start;
            this.end = end;
            this.cpuLoad = cpuLoad;
        }
    }
}
