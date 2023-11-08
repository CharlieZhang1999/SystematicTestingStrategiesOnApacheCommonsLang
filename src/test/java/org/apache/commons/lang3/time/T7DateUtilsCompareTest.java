package org.apache.commons.lang3.time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;
import java.text.DateFormat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class T7DateUtilsCompareTest {
    private static Calendar calendar_1;
    private static Calendar calendar_1_same;
    private static Calendar calendar_1_diff;

    private static Date date_1;
    private static Date date_1_same;
    private static Date date_1_diff;

    @BeforeEach
    public void setUp() throws Exception {

        calendar_1 = Calendar.getInstance();
        calendar_1.set(2001, 11, 18, 1, 23, 11 ); // Sun Nov 18 01:23:11 PST 2001
        calendar_1.set(Calendar.MILLISECOND, 10);

        calendar_1_same = Calendar.getInstance();
        calendar_1_same.set(2001, 11, 18, 1, 23, 11 );
        calendar_1_same.set(Calendar.MILLISECOND, 10);

        calendar_1_diff = Calendar.getInstance();
        calendar_1_diff.set(2000, 01, 01, 2, 58, 50 );
        calendar_1_diff.set(Calendar.MILLISECOND, 40);

        DateFormat dateTimeParser = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        date_1 = dateTimeParser.parse("November 18, 2001 1:23:11.010");
        date_1_same = dateTimeParser.parse("November 18, 2001 1:23:11.010");
        date_1_diff = dateTimeParser.parse("January 01, 2000 2:58:50.040");


    }

    @Test
    public void canTestIsSameDateforTwoSameDates(){
        assertTrue(DateUtils.isSameDay(date_1, date_1_same));
    }

    @Test
    public void canTestIsSameDateForTwoDifferentDates(){
        assertFalse(DateUtils.isSameDay(date_1, date_1_diff));
    }


    @Test
    public void canTestIsSameInstantforTwoDatesWithSameInstant(){
        assertTrue(DateUtils.isSameInstant(date_1, date_1_same));
    }
    @Test
    public void canTestIsSameInstantforTwoDatesWithDifferentInstant(){
        assertFalse(DateUtils.isSameInstant(date_1, date_1_diff));
    }


    @Test
    public void canTestIsSameFunctionWhenDate1IsNull() {
        assertThrows(NullPointerException.class, () -> DateUtils.isSameDay(null,
                date_1_same));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameInstant(null,
                date_1_same));
    }

    @Test
    public void canTestIsSameFunctionWhenDate2IsNull() {
        assertThrows(NullPointerException.class, () -> DateUtils.isSameDay(date_1,
                null));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameInstant(date_1,
                null));
    }

    @Test
    public void canTestIsSameFunctionWhenDate1AndDate2AreNull() {
        Date nullDate = null;
        assertThrows(NullPointerException.class, () -> DateUtils.isSameDay(nullDate,
                null));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameInstant(nullDate,
                null));
    }


    @Test
    public void canTestIsSameDayforTwoCalendar(){
        assertTrue(DateUtils.isSameDay(calendar_1, calendar_1_same));
    }

    @Test
    public void canTestIsSameDateForTwoDifferentCalendars(){
        assertFalse(DateUtils.isSameDay(calendar_1, calendar_1_diff));
    }

    @Test
    public void canTestIsSameInstantforTwoCalendarsWithSameInstant(){
        assertTrue(DateUtils.isSameInstant(calendar_1, calendar_1_same));
    }

    @Test
    public void canTestIsSameInstantforTwoCalendarsWithDifferentInstant(){
        assertFalse(DateUtils.isSameInstant(calendar_1, calendar_1_diff));
    }

    @Test
    public void canTestIsSameLocaltimeforTwoCalendarsWithSamelocaltime(){
        assertTrue(DateUtils.isSameLocalTime(calendar_1, calendar_1_same));

    }

    @Test
    public void canTestIsSameLocaltimeforTwoCalendarsWithDifferentlocaltime(){
        assertFalse(DateUtils.isSameLocalTime(calendar_1, calendar_1_diff));

    }

    @Test
    public void canTestIsSameFunctionWhenCal1IsNull() {
        assertThrows(NullPointerException.class, () -> DateUtils.isSameDay(null,
                calendar_1_same));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameInstant(null,
                calendar_1_same));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameLocalTime(null,
                calendar_1_same));

    }

    @Test
    public void canTestIsSameFunctionWhenCal2IsNull() {
        assertThrows(NullPointerException.class, () -> DateUtils.isSameDay(calendar_1,
                null));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameInstant(calendar_1,
                null));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameLocalTime(calendar_1,
                null));
    }

    @Test
    public void canTestIsSameFunctionWhenCal1AndCal2AreNull() {
        Calendar nullCal = null;
        assertThrows(NullPointerException.class, () -> DateUtils.isSameDay(nullCal,
                null));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameInstant(nullCal,
                null));
        assertThrows(NullPointerException.class, () -> DateUtils.isSameLocalTime(nullCal,
                null));
    }


}
