package org.apache.commons.lang3.time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.GregorianCalendar;
public class T7DateUtilsCompareDateWithSpecifiedPrecisionTest {
    private static Calendar calendar_1;
    private static Calendar calendar_1_same;
    private static Calendar calendar_1_diff;
    private static Date date_1;
    private static Date date_1_same;
    private static Date date_1_diff;

    @BeforeEach
    public void setUp() throws Exception {
        DateFormat dateTimeParser = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        date_1 = dateTimeParser.parse("November 18, 2001 1:23:11.010");
        date_1_same = dateTimeParser.parse("November 18, 2001 1:23:11.010");
        date_1_diff = dateTimeParser.parse("October 12, 2002 15:29:53.379");

        calendar_1 = Calendar.getInstance();
        calendar_1.set(2001, 11, 18, 1, 23, 11 );
        calendar_1.set(Calendar.MILLISECOND, 10);
        calendar_1.set(Calendar.ERA, GregorianCalendar.AD);

        calendar_1_same = Calendar.getInstance();
        calendar_1_same.set(2001, 11, 18, 1, 23, 11 );
        calendar_1_same.set(Calendar.MILLISECOND, 10);
        calendar_1_same.set(Calendar.ERA, GregorianCalendar.AD);

        calendar_1_diff = Calendar.getInstance();
        calendar_1_diff.set(2000, 01, 01, 2, 58, 50 );
        calendar_1_diff.set(Calendar.MILLISECOND, 40);
        calendar_1_diff.set(Calendar.ERA, GregorianCalendar.BC);
    }


    @Test
    public void canTestComparisonInMinutesforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.MINUTE));
    }

    @Test
    public void canTestComparisonInMinutesforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.MINUTE));
    }

    @Test
    public void shouldThrowExceptionInComparisonInMinutesWhenCalendar1IsNull(){
        assertThrows(NullPointerException.class, () -> DateUtils.truncatedEquals(null, calendar_1_diff, Calendar.MINUTE));
    }

    @Test
    public void shouldThrowExceptionInComparisonInMinutesWhenCalendar2IsNull(){
        assertThrows(NullPointerException.class, () -> DateUtils.truncatedEquals(calendar_1, null, Calendar.MINUTE));
    }
    @Test
    public void canTestComparisonInEraforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.ERA));
    }

    @Test
    public void canTestComparisonInYearforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.YEAR));
    }

    @Test
    public void canTestComparisonInMonthforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.MONTH));
    }

    @Test
    public void canTestComparisonInDateforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.DATE));
    }
    @Test
    public void canTestComparisonInDayOfMonthforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.DAY_OF_MONTH));
    }

    @Test
    public void canTestComparisonInAMPMforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.AM_PM));
    }

    @Test
    public void canTestComparisonInHourforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.HOUR));
    }

    @Test
    public void canTestComparisonInHourOfDayforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.HOUR_OF_DAY));
    }

    @Test
    public void canTestComparisonInSecondforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.SECOND));
    }

    @Test
    public void canTestComparisonInMilliesecondforTwoSameCalendars(){
        assertTrue(DateUtils.truncatedEquals(calendar_1, calendar_1_same, Calendar.MILLISECOND));
    }

    @Test
    public void shouldThrowExceptionWhenComparingInInvalidField(){
        assertThrows(IllegalArgumentException.class, () -> DateUtils.truncatedEquals(calendar_1, calendar_1_same, -1234));
    }

    @Test
    public void canTestComparisonInEraforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.ERA));
    }

    @Test
    public void canTestComparisonInYearforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.YEAR));
    }

    @Test
    public void canTestComparisonInMonthforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.MONTH));
    }

    @Test
    public void canTestComparisonInDateforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.DATE));
    }

    @Test
    public void canTestComparisonInDayOfMonthforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.DAY_OF_MONTH));

    }

    @Test
    public void testComparisonInAMPMforTwoDifferentCalendarsShouldThrowException(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.AM_PM));

    }

    @Test
    public void canTestComparisonInHourforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.HOUR));

    }

    @Test
    public void canTestComparisonInHourOfDayforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.HOUR_OF_DAY));

    }

    @Test
    public void canTestComparisonInMilliesecondforTwoDifferentCalendars(){
        assertFalse(DateUtils.truncatedEquals(calendar_1, calendar_1_diff, Calendar.MILLISECOND));
    }



    /*
    Tests for dates start here
     */
    @Test
    public void canTestComparisonInMinutesforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.MINUTE));
    }

    @Test
    public void canTestComparisonInMinutesforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.MINUTE));
    }

    @Test
    public void shouldThrowExceptionInComparisonInMinutesWhenDate1IsNull(){
        assertThrows(NullPointerException.class, () -> DateUtils.truncatedEquals(null, date_1_diff, Calendar.MINUTE));
    }

    @Test
    public void shouldThrowExceptionInComparisonInMinutesWhenDate2IsNull(){
        assertThrows(NullPointerException.class, () -> DateUtils.truncatedEquals(date_1, null, Calendar.MINUTE));
    }
    @Test
    public void canTestComparisonInEraforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.ERA));
    }

    @Test
    public void canTestComparisonInYearforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.YEAR));
    }

    @Test
    public void canTestComparisonInMonthforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.MONTH));
    }


    @Test
    public void canTestComparisonInDateforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.DATE));
    }
    @Test
    public void canTestComparisonInDayOfMonthforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.DAY_OF_MONTH));
    }

    @Test
    public void canTestComparisonInAMPMforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.AM_PM));
    }

    @Test
    public void canTestComparisonInHourforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.HOUR));
    }

    @Test
    public void canTestComparisonInHourOfDayforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.HOUR_OF_DAY));
    }

    @Test
    public void canTestComparisonInSecondforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.SECOND));
    }

    @Test
    public void canTestComparisonInMilliesecondforTwoSameDates(){
        assertTrue(DateUtils.truncatedEquals(date_1, date_1_same, Calendar.MILLISECOND));
    }

    @Test
    public void shouldThrowExceptionWhenComparingInInvalidFieldforTwoDates(){
        assertThrows(IllegalArgumentException.class, () -> DateUtils.truncatedEquals(date_1, date_1_same, -1234));
    }

    @Test
    public void canTestComparisonInEraforTwoDifferentDates(){
        Date date_AD = calendar_1.getTime();
        Date date_BC = calendar_1_diff.getTime();
        assertFalse(DateUtils.truncatedEquals(date_AD, date_BC , Calendar.ERA));
    }

    @Test
    public void canTestComparisonInYearforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.YEAR));
    }

    @Test
    public void canTestComparisonInMonthforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.MONTH));
    }

    @Test
    public void canTestComparisonInDateforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.DATE));
    }

    @Test
    public void canTestComparisonInDayOfMonthforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.DAY_OF_MONTH));

    }

    @Test
    public void cantestComparisonInAMPMforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.AM_PM));

    }

    @Test
    public void canTestComparisonInHourforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.HOUR));

    }

    @Test
    public void canTestComparisonInHourOfDayforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.HOUR_OF_DAY));

    }

    @Test
    public void canTestComparisonInMilliesecondforTwoDifferentDates(){
        assertFalse(DateUtils.truncatedEquals(date_1, date_1_diff, Calendar.MILLISECOND));
    }









}
