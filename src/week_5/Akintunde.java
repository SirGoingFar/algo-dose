package week_5;

import org.w3c.dom.Node;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Akintunde {

    private static int minKey = Integer.MIN_VALUE;
    private static Node minNode = null;

    public static void main(String[] args) {
//        Node minNode = findMinNode();
////        System.out.println("Minimum Key: " + minNode.getTextContent());
//        System.out.println(convert(new Date(1596268800000), 0));
        formatDate();
    }


    private static Node findMinNode(Node root) {

        if(root == null)
            throw new NullPointerException("Root node is null");

        if(root.hasChildNodes())
            return findMinNode(root.getFirstChild());

        return root;
    }

    private static String convert(Date date, int numberOfDays){
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numberOfDays);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTime().getTime());
        return calendar.getTime().toString();
    }

    private static void formatDate() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("dd MMM yyyy");
        System.out.println(df.format(date));
    }

}
