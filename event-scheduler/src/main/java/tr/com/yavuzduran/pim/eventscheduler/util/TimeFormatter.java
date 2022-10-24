package tr.com.yavuzduran.pim.eventscheduler.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {

    private static final DateFormat DATE_FORMAT1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    public static Date parse(String date) throws ParseException {
        return DATE_FORMAT1.parse(date);
    }

    public static String format(Date date){
        return DATE_FORMAT1.format(date);
    }

    private TimeFormatter() {
    }
}
