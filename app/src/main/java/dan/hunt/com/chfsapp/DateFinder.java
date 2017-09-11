package dan.hunt.com.chfsapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dan on 28/08/17.
 */

public class DateFinder {

    public DateFinder() {
        //No initialisation at present
    }

    public String getEnd() {
        //Need to intialise the calendar in the method otherwise the end dates are effected.
        Calendar cal = Calendar.getInstance();

        //Get current week and year values to work with
        int week = cal.get(Calendar.WEEK_OF_YEAR) - 1;
        //int year = cal.get(Calendar.YEAR) - 4;

        //Set the date with the new updated settings
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        //cal.set(Calendar.YEAR, year);

        //Get a date object form the curent time to format
        Date date = cal.getTime();
        //Set up our formatter with the format for our SQL query
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = format.format(date).toString();

        return formattedDate;
    }

    public String getStart() {
        //Need to intialise the calendar in the method otherwise the start dates are effected.
        Calendar cal = Calendar.getInstance();

        //Get current week and year values to work with
        int week = cal.get(Calendar.WEEK_OF_YEAR) - 6;
        //int year = cal.get(Calendar.YEAR) - 4;

        //Set the date with the new updated settings
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        //cal.set(Calendar.YEAR, year);

        //Get a date object form the curent time to format
        Date date = cal.getTime();
        //Set up our formatter with the format for our SQL query
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-d", Locale.ENGLISH);
        String formattedDate = format.format(date).toString();

        return formattedDate;
    }

    public static int[] getWeekNumbers() {
        int[] weekNumbers = new int[6];
        int start = getStartWeekNum();
        //int end = Integer.parseInt(getEnd());
        weekNumbers[0] = start + 1;
        weekNumbers[1] = start + 2;
        weekNumbers[2] = start + 3;
        weekNumbers[3] = start + 4;
        weekNumbers[4] = start + 5;
        weekNumbers[5] = start + 6;

        return weekNumbers;
    }

    public static int getStartWeekNum() {
        //Get calendar instance
        Calendar cal = Calendar.getInstance();
        //Get current week and year values to work with
        int week = cal.get(Calendar.WEEK_OF_YEAR) - 6;
        int year = cal.get(Calendar.YEAR) - 5;

        //Set the date with the new updated settings
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.YEAR, year);

        //Get a date object form the curent time to format
        int startWeek = cal.get(Calendar.WEEK_OF_YEAR);

        return startWeek;

    }

}
