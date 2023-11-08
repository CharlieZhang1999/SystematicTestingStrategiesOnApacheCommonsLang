package org.apache.commons.lang3.time;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateUtilsCeilingTest {
    private static final TimeZone DEFAULT_ZONE = TimeZone.getTimeZone("GMT");

    private static Object cal1, cal2;

    private static final int field = Calendar.MINUTE;



    @BeforeEach
    public void setUp()  {
        cal1 = Calendar.getInstance();
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 23, 55, 38);
        ((Calendar) cal1).set(Calendar.MILLISECOND, 25);
        ((Calendar) cal1).setTimeZone(DEFAULT_ZONE);

        cal2 = Calendar.getInstance();
        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 56, 0);
        ((Calendar) cal2).set(Calendar.MILLISECOND, 0);
        ((Calendar) cal2).setTimeZone(DEFAULT_ZONE);
    }

    @Test
    public void roundsUpACalendarToMinuteButDoesNotChangeHigherFields() {
        Date result = DateUtils.ceiling(cal1, field);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToMinuteAndChangesHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.DECEMBER, 31, 23, 59, 45);

        ((Calendar) cal2).set(2009, Calendar.JANUARY, 1, 0, 0, 0);

        Date result = DateUtils.ceiling(cal1, field);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToMillisecondButDoesNotChangeHigherFields() {
        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 55, 38);
        ((Calendar) cal2).set(Calendar.MILLISECOND, 25);

        Date result = DateUtils.ceiling(cal1, Calendar.MILLISECOND);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToSecondButDoesNotChangeHigherFields() {
        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 55, 39);

        Date result = DateUtils.ceiling(cal1, Calendar.SECOND);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToHour_Of_DayButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 22, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 0, 0);

        Date result = DateUtils.ceiling(cal1, Calendar.HOUR_OF_DAY);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToHourButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 22, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 0, 0);

        Date result = DateUtils.ceiling(cal1, Calendar.HOUR);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToDateButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 11, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 10, 0, 0, 0);

        Date result = DateUtils.ceiling(cal1, Calendar.DATE);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToDay_Of_MonthButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 11, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 10, 0, 0, 0);

        Date result = DateUtils.ceiling(cal1, Calendar.DAY_OF_MONTH);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToAM_PMButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 18, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 10, 0, 0, 0);

        Date result = DateUtils.ceiling(cal1, Calendar.AM_PM);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToMonthButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 3, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.MAY, 1, 0, 0, 0);

        Date result = DateUtils.ceiling(cal1, Calendar.MONTH);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToSemi_MonthButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 28, 3, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.MAY, 1, 0, 0, 0);

        Date result = DateUtils.ceiling(cal1, DateUtils.SEMI_MONTH);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToYearButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 18, 3, 25, 27);

        ((Calendar) cal2).set(2009, Calendar.JANUARY, 1, 0, 0, 0);

        Date result = DateUtils.ceiling(cal1, Calendar.YEAR);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToEraButDoesNotChangeHigherFields() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 18, 3, 25, 27);

        ((Calendar) cal2).set(1, Calendar.JANUARY, 1, 0, 0, 0);

        Date result = DateUtils.ceiling(cal1, Calendar.ERA);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpACalendarToInvalidField() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.ceiling(cal1, -1234));
    }

    @Test
    public void roundsUpADateToMinuteButDoesNotChangeHigherFields() {
        Date result = DateUtils.ceiling(((Calendar) cal1).getTime(), Calendar.MINUTE);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsUpANullObjectToMinute() throws NullPointerException {
        assertThrows(NullPointerException.class, () -> DateUtils.ceiling((Object) null, Calendar.MINUTE));
    }

    @Test
    public void roundsUpAnUnsupportedTypeToMinute() throws ClassCastException {
        assertThrows(ClassCastException.class, () -> DateUtils.ceiling("Wed, 09 Apr 2008 23:55:38 GMT", Calendar.MINUTE));
    }
}
