package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class T7DateUtilsFragmentTest extends AbstractLangTest {

    private static final int months = 1;   // second final prime before 12
    private static final int days = 1;    // second final prime before 31 (and valid)
    private static final int hours = 7;   // second final prime before 24
    private static final int minutes = 15; // second final prime before 60
    private static final int seconds = 10; // third final prime before 60
    private static final int millis = 538; // second final prime before 1000

    private static final long ZERO = 0L;
    private static final long MILLI_SECOND = 538L;
    private static final long MILLI_MINUTE = 10538L;
    private static final long MILLI_HOUR_OF_DAY = 910538L;
    private static final long MILLI_DATE_DAY_OF_YEAR_MONTH = 26110538L;
    private static final long MILLI_YEAR = 2704510538L;
    private static final long SECONDS_MINUTE = 10L;
    private static final long SECONDS_HOUR_OF_DAY = 910L;
    private static final long SECONDS_DATE_DAY_OF_YEAR_MONTH = 26110L;
    private static final long SECONDS_YEAR = 2704510L;
    private static final long MINUTES_HOUR_OF_DAY = 15L;
    private static final long MINUTES_DATE_DAY_OF_YEAR_MONTH = 435L;
    private static final long MINUTES_YEAR = 45075L;
    private static final long HOURS_DATE_DAY_OF_YEAR_MONTH = 7L;
    private static final long HOURS_YEAR = 751L;
    private static final long DAYS_MONTH = 1L;
    private static final long DAYS_YEAR = 32L;

    private Date aDate;
    private Calendar aCalendar;


    @BeforeEach
    public void setUp() {
        aCalendar = Calendar.getInstance();
        aCalendar.set(2008, months, days, hours, minutes, seconds);
        aCalendar.set(Calendar.MILLISECOND, millis);
        aDate = aCalendar.getTime();
    }

    // Tests for getFragmentInMilliseconds

    @Test
    public void testGetFragmentInMillisecondsMILLI() {
        assertEquals(ZERO, DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.MILLISECOND));
        assertEquals(ZERO, DateUtils.getFragmentInMilliseconds(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testGetFragmentInMillisecondsSECOND() {
        final long calendarResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.SECOND);
        final long dateResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.SECOND);
        assertEquals(MILLI_SECOND, calendarResult);
        assertEquals(MILLI_SECOND, dateResult);
    }

    @Test
    public void testGetFragmentInMillisecondsMINUTE() {
        final long calendarResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.MINUTE);
        final long dateResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.MINUTE);
        assertEquals(MILLI_MINUTE, calendarResult);
        assertEquals(MILLI_MINUTE, dateResult);
    }

    @Test
    public void testGetFragmentInMillisecondsHOUR_OF_DAY() {
        final long calendarResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.HOUR_OF_DAY);
        final long dateResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(MILLI_HOUR_OF_DAY, calendarResult);
        assertEquals(MILLI_HOUR_OF_DAY, dateResult);
    }

    @Test
    public void testGetFragmentInMillisecondsDATE() {
        final long calendarResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.DATE);
        final long dateResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.DATE);
        assertEquals(MILLI_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(MILLI_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInMillisecondsDAY_OF_YEAR() {
        final long calendarResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.DAY_OF_YEAR);
        final long dateResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(MILLI_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(MILLI_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInMillisecondsMONTH() {
        final long calendarResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.MONTH);
        final long dateResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.MONTH);
        assertEquals(MILLI_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(MILLI_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInMillisecondsYEAR() {
        final long calendarResult = DateUtils.getFragmentInMilliseconds(aCalendar, Calendar.YEAR);
        final long dateResult = DateUtils.getFragmentInMilliseconds(aDate, Calendar.YEAR);
        assertEquals(MILLI_YEAR, calendarResult);
        assertEquals(MILLI_YEAR, dateResult);
    }

    @Test
    public void testGetFragmentInMillisecondsInvalidFragment() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMilliseconds(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMilliseconds(aDate, 0));
    }

    @Test
    public void testGetFragmentInMillisecondsNullDateCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInMilliseconds((Calendar) null, Calendar.MILLISECOND));
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInMilliseconds((Date) null, Calendar.MILLISECOND));
    }

    // Tests for getFragmentInSeconds

    @Test
    public void testGetFragmentInSecondsMILLI() {
        assertEquals(0, DateUtils.getFragmentInSeconds(aCalendar, Calendar.MILLISECOND));
        assertEquals(0, DateUtils.getFragmentInSeconds(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testGetFragmentInSecondsSECOND() {
        assertEquals(0, DateUtils.getFragmentInSeconds(aCalendar, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInSeconds(aDate, Calendar.SECOND));
    }

    @Test
    public void testGetFragmentInSecondsMINUTE() {
        final long calendarResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.MINUTE);
        final long dateResult = DateUtils.getFragmentInSeconds(aDate, Calendar.MINUTE);
        assertEquals(SECONDS_MINUTE, calendarResult);
        assertEquals(SECONDS_MINUTE, dateResult);
    }

    @Test
    public void testGetFragmentInSecondsHOUR_OF_DAY() {
        final long calendarResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.HOUR_OF_DAY);
        final long dateResult = DateUtils.getFragmentInSeconds(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(SECONDS_HOUR_OF_DAY, calendarResult);
        assertEquals(SECONDS_HOUR_OF_DAY, dateResult);
    }

    @Test
    public void testGetFragmentInSecondsDATE() {
        final long calendarResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.DATE);
        final long dateResult = DateUtils.getFragmentInSeconds(aDate, Calendar.DATE);
        assertEquals(SECONDS_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(SECONDS_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInSecondsDAY_OF_YEAR() {
        final long calendarResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.DAY_OF_YEAR);
        final long dateResult = DateUtils.getFragmentInSeconds(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(SECONDS_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(SECONDS_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInSecondsMONTH() {
        final long calendarResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.MONTH);
        final long dateResult = DateUtils.getFragmentInSeconds(aDate, Calendar.MONTH);
        assertEquals(SECONDS_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(SECONDS_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInSecondsYEAR() {
        final long calendarResult = DateUtils.getFragmentInSeconds(aCalendar, Calendar.YEAR);
        final long dateResult = DateUtils.getFragmentInSeconds(aDate, Calendar.YEAR);
        assertEquals(SECONDS_YEAR, calendarResult);
        assertEquals(SECONDS_YEAR, dateResult);
    }

    @Test
    public void testGetFragmentInSecondsInvalidFragment() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInSeconds(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInSeconds(aDate, 0));
    }

    @Test
    public void testGetFragmentInSecondsNullDateCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInSeconds((Calendar) null, Calendar.MILLISECOND));
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInSeconds((Date) null, Calendar.MILLISECOND));
    }

    // Tests for getFragmentInMinutes

    @Test
    public void testGetFragmentInMinutesMILLISECOND() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aCalendar, Calendar.MILLISECOND));
        assertEquals(0, DateUtils.getFragmentInMinutes(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testGetFragmentInMinutesSECOND() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aCalendar, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInMinutes(aDate, Calendar.SECOND));
    }

    @Test
    public void testGetFragmentInMinutesMINUTE() {
        assertEquals(0, DateUtils.getFragmentInMinutes(aCalendar, Calendar.MINUTE));
        assertEquals(0, DateUtils.getFragmentInMinutes(aDate, Calendar.MINUTE));
    }

    @Test
    public void testGetFragmentInMinutesHOUR_OF_DAY() {
        final long calendarResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.HOUR_OF_DAY);
        final long dateResult = DateUtils.getFragmentInMinutes(aDate, Calendar.HOUR_OF_DAY);
        assertEquals(MINUTES_HOUR_OF_DAY, calendarResult);
        assertEquals(MINUTES_HOUR_OF_DAY, dateResult);
    }

    @Test
    public void testGetFragmentInMinutesDATE() {
        final long calendarResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.DATE);
        final long dateResult = DateUtils.getFragmentInMinutes(aDate, Calendar.DATE);
        assertEquals(MINUTES_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(MINUTES_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInMinutesDAY_OF_YEAR() {
        final long calendarResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.DAY_OF_YEAR);
        final long dateResult = DateUtils.getFragmentInMinutes(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(MINUTES_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(MINUTES_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInMinutesMONTH() {
        final long calendarResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.MONTH);
        final long dateResult = DateUtils.getFragmentInMinutes(aDate, Calendar.MONTH);
        assertEquals(MINUTES_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(MINUTES_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInMinutesYEAR() {
        final long calendarResult = DateUtils.getFragmentInMinutes(aCalendar, Calendar.YEAR);
        final long dateResult = DateUtils.getFragmentInMinutes(aDate, Calendar.YEAR);
        assertEquals(MINUTES_YEAR, calendarResult);
        assertEquals(MINUTES_YEAR, dateResult);
    }

    @Test
    public void testGetFragmentInMinutesInvalidFragment() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMinutes(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInMinutes(aDate, 0));
    }

    @Test
    public void testGetFragmentInMinutesNullDateCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInMinutes((Calendar) null, Calendar.MILLISECOND));
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInMinutes((Date) null, Calendar.MILLISECOND));
    }

    // Tests for getFragmentInHours

    @Test
    public void testGetFragmentInHoursMILLISECOND() {
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.MILLISECOND));
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testGetFragmentInHoursSECOND() {
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.SECOND));
    }

    @Test
    public void testGetFragmentInHoursMINUTE() {
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.MINUTE));
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.MINUTE));
    }

    @Test
    public void testGetFragmentInHoursHOUR_OF_DAY() {
        assertEquals(0, DateUtils.getFragmentInHours(aCalendar, Calendar.HOUR_OF_DAY));
        assertEquals(0, DateUtils.getFragmentInHours(aDate, Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testGetFragmentInHoursDATE() {
        final long calendarResult = DateUtils.getFragmentInHours(aCalendar, Calendar.DATE);
        final long dateResult = DateUtils.getFragmentInHours(aDate, Calendar.DATE);
        assertEquals(HOURS_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(HOURS_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInHoursDAY_OF_YEAR() {
        final long calendarResult = DateUtils.getFragmentInHours(aCalendar, Calendar.DAY_OF_YEAR);
        final long dateResult = DateUtils.getFragmentInHours(aDate, Calendar.DAY_OF_YEAR);
        assertEquals(HOURS_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(HOURS_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInHoursMONTH() {
        final long calendarResult = DateUtils.getFragmentInHours(aCalendar, Calendar.MONTH);
        final long dateResult = DateUtils.getFragmentInHours(aDate, Calendar.MONTH);
        assertEquals(HOURS_DATE_DAY_OF_YEAR_MONTH, calendarResult);
        assertEquals(HOURS_DATE_DAY_OF_YEAR_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInHoursYEAR() {
        final long calendarResult = DateUtils.getFragmentInHours(aCalendar, Calendar.YEAR);
        final long dateResult = DateUtils.getFragmentInHours(aDate, Calendar.YEAR);
        assertEquals(HOURS_YEAR, calendarResult);
        assertEquals(HOURS_YEAR, dateResult);
    }

    @Test
    public void testGetFragmentInHoursInvalidFragment() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInHours(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInHours(aDate, 0));
    }

    @Test
    public void testGetFragmentInHoursNullDateCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInHours((Calendar) null, Calendar.MILLISECOND));
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInHours((Date) null, Calendar.MILLISECOND));
    }

    // Tests for getFragmentInDays

    @Test
    public void testGetFragmentInDaysMILLISECOND() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.MILLISECOND));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.MILLISECOND));
    }

    @Test
    public void testGetFragmentInDaysSECOND() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.SECOND));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.SECOND));
    }

    @Test
    public void testGetFragmentInDaysMINUTE() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.MINUTE));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.MINUTE));
    }

    @Test
    public void testGetFragmentInDaysHOUR_OF_DAY() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.HOUR_OF_DAY));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testGetFragmentInDaysDATE() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.DATE));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.DATE));
    }

    @Test
    public void testGetFragmentInDaysDAY_OF_YEAR() {
        assertEquals(0, DateUtils.getFragmentInDays(aCalendar, Calendar.DAY_OF_YEAR));
        assertEquals(0, DateUtils.getFragmentInDays(aDate, Calendar.DAY_OF_YEAR));
    }

    @Test
    public void testGetFragmentInDaysMONTH() {
        final long calendarResult = DateUtils.getFragmentInDays(aCalendar, Calendar.MONTH);
        final long dateResult = DateUtils.getFragmentInDays(aDate, Calendar.MONTH);
        assertEquals(DAYS_MONTH, calendarResult);
        assertEquals(DAYS_MONTH, dateResult);
    }

    @Test
    public void testGetFragmentInDaysYEAR() {
        final long calendarResult = DateUtils.getFragmentInDays(aCalendar, Calendar.YEAR);
        final long dateResult = DateUtils.getFragmentInDays(aDate, Calendar.YEAR);
        assertEquals(DAYS_YEAR, calendarResult);
        assertEquals(DAYS_YEAR, dateResult);
    }

    @Test
    public void testGetFragmentInDaysInvalidFragment() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInDays(aCalendar, 0));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.getFragmentInDays(aDate, 0));
    }

    @Test
    public void testGetFragmentInDaysNullDateCalendar() {
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInDays((Calendar) null, Calendar.MILLISECOND));
        assertThrows(NullPointerException.class, () -> DateUtils.getFragmentInDays((Date) null, Calendar.MILLISECOND));
    }
}
