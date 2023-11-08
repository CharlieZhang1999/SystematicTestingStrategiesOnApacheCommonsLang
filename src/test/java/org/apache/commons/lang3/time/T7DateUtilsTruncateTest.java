package org.apache.commons.lang3.time;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class T7DateUtilsTruncateTest {
    private static final TimeZone DEFAULT_ZONE = TimeZone.getTimeZone("GMT");

    private static Object cal1, cal2;

    private static final int field = Calendar.MINUTE;



    @BeforeEach
    public void setUp()  {
        cal1 = Calendar.getInstance();
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 23, 55, 27);
        ((Calendar) cal1).set(Calendar.MILLISECOND, 25);
        ((Calendar) cal1).setTimeZone(DEFAULT_ZONE);

        cal2 = Calendar.getInstance();
        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 55, 0);
        ((Calendar) cal2).set(Calendar.MILLISECOND, 0);
        ((Calendar) cal2).setTimeZone(DEFAULT_ZONE);
    }

    @Test
    public void roundsDownACalendarToMinute() {
        Date result = DateUtils.truncate(cal1, field);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsACalendarToMinuteButChangesNothing() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 23, 55, 0);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 55, 0);

        Date result = DateUtils.truncate(cal1, field);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToMillisecond() {
        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 55, 27);
        ((Calendar) cal2).set(Calendar.MILLISECOND, 25);

        Date result = DateUtils.truncate(cal1, Calendar.MILLISECOND);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToSecond() {
        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 55, 27);

        Date result = DateUtils.truncate(cal1, Calendar.SECOND);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToHour_Of_Day() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 23, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 0, 0);

        Date result = DateUtils.truncate(cal1, Calendar.HOUR_OF_DAY);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToHOUR() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 23, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 23, 0, 0);

        Date result = DateUtils.truncate(cal1, Calendar.HOUR);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToDate() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 11, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 0, 0, 0);

        Date result = DateUtils.truncate(cal1, Calendar.DATE);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToDay_Of_Month() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 11, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 0, 0, 0);

        Date result = DateUtils.truncate(cal1, Calendar.DAY_OF_MONTH);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToAM_PM() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 3, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 9, 0, 0, 0);

        Date result = DateUtils.truncate(cal1, Calendar.AM_PM);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToMonth() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 9, 3, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 1, 0, 0, 0);

        Date result = DateUtils.truncate(cal1, Calendar.MONTH);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToSemi_Month() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 18, 3, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.APRIL, 16, 0, 0, 0);

        Date result = DateUtils.truncate(cal1, DateUtils.SEMI_MONTH);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToYear() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 18, 3, 25, 27);

        ((Calendar) cal2).set(2008, Calendar.JANUARY, 1, 0, 0, 0);

        Date result = DateUtils.truncate(cal1, Calendar.YEAR);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToEra() {
        ((Calendar) cal1).set(2008, Calendar.APRIL, 18, 3, 25, 27);

        ((Calendar) cal2).set(1, Calendar.JANUARY, 1, 0, 0, 0);

        Date result = DateUtils.truncate(cal1, Calendar.ERA);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownACalendarToInvalidField() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.truncate(cal1, -1234));
    }

    @Test
    public void roundsDownADateToMinute() {
        Date result = DateUtils.truncate(((Calendar) cal1).getTime(), Calendar.MINUTE);
        assertEquals(((Calendar) cal2).getTime(), result);
    }

    @Test
    public void roundsDownANullObjectToMinute() throws NullPointerException {
        assertThrows(NullPointerException.class, () -> DateUtils.truncate((Object) null, Calendar.MINUTE));
    }

    @Test
    public void roundsDownAnUnsupportedTypeToMinute() throws ClassCastException {
        assertThrows(ClassCastException.class, () -> DateUtils.truncate("Wed, 09 Apr 2008 23:55:38 GMT", Calendar.MINUTE));
    }
}
