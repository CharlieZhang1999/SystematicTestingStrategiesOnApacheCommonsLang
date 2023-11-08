package org.apache.commons.lang3.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class T7DateUtilsConvertToCalendarTest {
    private static TimeZone DEFAULT_ZONE;
    private static Calendar calendar_1;
    private static Date date_1;

    @BeforeEach
    public void setUp() throws ParseException {
        DateFormat dateTimeParser = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        date_1 = dateTimeParser.parse("November 18, 2001 1:23:11.010");
        calendar_1 = Calendar.getInstance();
        calendar_1.set(2001, 11, 18, 1, 23, 11 );
        calendar_1.set(Calendar.MILLISECOND, 10);
        DEFAULT_ZONE = TimeZone.getDefault();
    }

    @Test
    public void canTestToCalendarWithNormalDateAndNoSpecificTimezone(){
        Calendar convertedCal = DateUtils.toCalendar(date_1);
        assertEquals(convertedCal.getTime(), date_1);
    }

    @Test
    public void canTestToCalendarWithNullDateAndNoSpecificTimezone(){
        assertThrows(NullPointerException.class, () -> DateUtils.toCalendar(null));
    }

    @Test
    public void canTestToCalendarWithNormalDateAndDefaultTimezone(){
        Calendar convertedCal = DateUtils.toCalendar(date_1, DEFAULT_ZONE);
        assertEquals(convertedCal.getTime(), date_1);
        assertEquals(convertedCal.getTimeZone(), DEFAULT_ZONE);
    }

    @Test
    public void canTestToCalendarWithNullDateAndDefaultTimezone(){
        assertThrows(NullPointerException.class, () -> DateUtils.toCalendar(null, DEFAULT_ZONE));
    }

    @Test
    public void canTestToCalendarWithNormalDateAndNullTimezone(){
        assertThrows(NullPointerException.class, () -> DateUtils.toCalendar(date_1, null));
    }
    @Test
    public void canTestToCalendarWithNullDateAndNullTimezone(){
        assertThrows(NullPointerException.class, () -> DateUtils.toCalendar(null, null));
    }







}
