package com.afse.academy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RandomDateOfBirth {

    public RandomDateOfBirth() {}

    public static Date getRandomDOB() {

        int yyyy = random(1900, 2018);
        int mm = random(1, 12);
        int dd;

        switch(mm) {
            case 2:
                if (isLeapYear(yyyy)) {
                    dd = random(1, 29);
                } else {
                    dd = random(1, 28);
                }
                break;

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                dd = random(1, 31);
                break;

            default:
                dd = random(1, 30);
                break;
        }

        String year = Integer.toString(yyyy);
        String month = Integer.toString(mm);
        String day = Integer.toString(dd);

        if (mm < 10) {
            month = "0" + mm;
        }

        if (dd < 10) {
            day = "0" + dd;
        }

        String dateString = year + '-' + month + '-' + day;
        return convertStringToDate(dateString);
    }

    private static int random(int lowerBound, int upperBound) {
        return (lowerBound + (int) Math.round(Math.random()
                * (upperBound - lowerBound)));
    }

    private static boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        int noOfDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);

        return noOfDays > 365;

    }

    private static Date convertStringToDate(String dateString) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}