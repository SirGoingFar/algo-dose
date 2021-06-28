package week_3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Akintunde {

    public static void main(String[] args) {
        System.out.println(dayOfProgrammer(2017));
        System.out.println(dayOfProgrammer(1800));
        System.out.println(dayOfProgrammer(2016));
    }

    private static String dayOfProgrammer(int year) {

        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.set(Calendar.DAY_OF_YEAR, 256);
        cal.set(Calendar.YEAR, year);

        return new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(cal.getTime());
    }

}
