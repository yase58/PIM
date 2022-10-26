package tr.com.yavuzduran.pim.eventscheduler.util;

import tr.com.yavuzduran.pim.exceptionhandler.exception.eventscheduler.EventParseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {

    private static final DateFormat DATE_FORMAT1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    private TimeFormatter() {
    }

    public static Date parse(String date) throws EventParseException {
        try {
            return DATE_FORMAT1.parse(date);
        } catch (ParseException e) {
            throw new EventParseException(e);
        }
    }

    public static String format(Date date) {
        return DATE_FORMAT1.format(date);
    }
}
