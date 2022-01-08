package tasks.kudi_weekly.week_4;

//Submission By Mesh
class PhonePolicy {

    private String time;
    private String phoneNumber;

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private int getHourInSeconds() {
        final int hour = Integer.parseInt(time.substring(0, 2));
        return hour * 60 * 60;
    }

    private int getMinuteInSeconds() {
        final int minutes = Integer.parseInt(time.substring(3, 5));
        return minutes * 60;
    }

    private int getSeconds() {
        return Integer.parseInt(time.substring(6, 7));
    }

    private int getTotalTime() {
        return getHourInSeconds() + getMinuteInSeconds() + getSeconds();
    }

    public int calculateCost() {

        final int costPerSeconds = 3;
        final int fiveMinutesInSeconds = 5 * 60;

        final int totalTimeEstimated = getTotalTime();
        int totalEstimatedCost = totalTimeEstimated * costPerSeconds;

        if (totalTimeEstimated > fiveMinutesInSeconds) {
            totalEstimatedCost = totalTimeEstimated * 150;
        }

        return totalEstimatedCost;
    }

}

public class PhonePolicyApplication {

    private static int testPolicy(final String testCase) {
        final String[] rows = testCase.split(" ");

        int sum = 0;
        for (final String row : rows) {
            final String[] elements = row.split(";");

            final PhonePolicy phonePolicy = new PhonePolicy();
            phonePolicy.setTime(elements[0]);
            phonePolicy.setPhoneNumber(elements[1]);

            sum += phonePolicy.calculateCost();
        }

        return sum;
    }

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
        double cost = PhonePolicyApplication.testPolicy(callLogStream);
        long now = System.currentTimeMillis();

        System.out.println(
                "Total Call Sum: "
                        .concat(String.valueOf(cost / 100))
                        .concat(" NGN")
        );

        System.out.println("Time Taken: ".concat(String.valueOf(now - then)));

    }
}

