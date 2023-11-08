package org.apache.commons.lang3.time;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class T7DataUtilsAddSetTest extends AbstractLangTest {

    private static Date BASE_DATE;
    private static TimeZone DEFAULT_ZONE;

    /**
     * Used to check that Calendar objects are close enough
     * delta is in milliseconds
     */
    private static void assertCalendarsEquals(final String message, final Calendar cal1, final Calendar cal2, final long delta) {
        assertFalse(Math.abs(cal1.getTime().getTime() - cal2.getTime().getTime()) > delta,
                message + " expected " + cal1.getTime() + " but got " + cal2.getTime());
    }

    /**
     * This checks that this is a 7 element iterator of Calendar objects
     * that are dates (no time), and exactly 1 day spaced after each other.
     */
    private static void assertWeekIterator(final Iterator<?> it, final Calendar start) {
        final Calendar end = (Calendar) start.clone();
        end.add(Calendar.DATE, 6);

        assertWeekIterator(it, start, end);
    }

    /**
     * This checks that this is a 7 divisible iterator of Calendar objects
     * that are dates (no time), and exactly 1 day spaced after each other
     * (in addition to the proper start and stop dates)
     */
    private static void assertWeekIterator(final Iterator<?> it, final Calendar start, final Calendar end) {
        Calendar cal = (Calendar) it.next();
        assertCalendarsEquals("", start, cal, 0);
        Calendar last = null;
        int count = 1;
        while (it.hasNext()) {
            //Check this is just a date (no time component)
            assertCalendarsEquals("", cal, DateUtils.truncate(cal, Calendar.DATE), 0);

            last = cal;
            cal = (Calendar) it.next();
            count++;

            //Check that this is one day more than the last date
            last.add(Calendar.DATE, 1);
            assertCalendarsEquals("", last, cal, 0);
        }

        assertFalse(count % 7 != 0, "There were " + count + " days in this iterator");
        assertCalendarsEquals("", end, cal, 0);
    }

    /**
     * Convenience method for when working with Date objects
     */
    private static void assertWeekIterator(final Iterator<?> it, final Date start, final Date end) {
        final Calendar calStart = Calendar.getInstance();
        calStart.setTime(start);
        final Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(end);

        assertWeekIterator(it, calStart, calEnd);
    }

    @BeforeAll
    public static void classSetup() {
        final GregorianCalendar cal = new GregorianCalendar(2023, 9, 29, 22, 33, 44);
        cal.set(Calendar.MILLISECOND, 555);
        BASE_DATE = cal.getTime();
    }

    private DateFormat dateParser;
    private DateFormat dateTimeParser;
    private Date dateAmPm1;
    private Date dateAmPm2;
    private Date dateAmPm3;
    private Date dateAmPm4;
    private Date date0;
    private Date date1;
    private Date date2;
    private Date date3;
    private Date date4;
    private Date date5;
    private Date date6;
    private Date date7;
    private Date date8;
    private Calendar calAmPm1;
    private Calendar calAmPm2;
    private Calendar calAmPm3;
    private Calendar calAmPm4;
    private Calendar cal1;
    private Calendar cal2;
    private Calendar cal3;
    private Calendar cal4;
    private Calendar cal5;
    private Calendar cal6;

    private Calendar cal7;

    private Calendar cal8;

    private TimeZone zone;

    @AfterEach
    public void afterEachResetTimeZone() {
        TimeZone.setDefault(DEFAULT_ZONE);
    }

    private void assertDate(final Date date, final int year, final int month, final int day, final int hour, final int min, final int sec, final int mil) {
        final GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        assertEquals(year, cal.get(Calendar.YEAR));
        assertEquals(month, cal.get(Calendar.MONTH));
        assertEquals(day, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(hour, cal.get(Calendar.HOUR_OF_DAY));
        assertEquals(min, cal.get(Calendar.MINUTE));
        assertEquals(sec, cal.get(Calendar.SECOND));
        assertEquals(mil, cal.get(Calendar.MILLISECOND));
    }

    @BeforeEach
    public void setUp() throws Exception {
        dateParser = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        dateTimeParser = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);

        dateAmPm1 = dateTimeParser.parse("February 3, 2002 01:10:00.000");
        dateAmPm2 = dateTimeParser.parse("February 3, 2002 11:10:00.000");
        dateAmPm3 = dateTimeParser.parse("February 3, 2002 13:10:00.000");
        dateAmPm4 = dateTimeParser.parse("February 3, 2002 19:10:00.000");
        date0 = dateTimeParser.parse("February 3, 2002 12:34:56.789");
        date1 = dateTimeParser.parse("February 12, 2002 12:34:56.789");
        date2 = dateTimeParser.parse("November 18, 2001 1:23:11.321");
        DEFAULT_ZONE = TimeZone.getDefault();
        zone = TimeZone.getTimeZone("MET");
        try {
            TimeZone.setDefault(zone);
            dateTimeParser.setTimeZone(zone);
            date3 = dateTimeParser.parse("March 30, 2003 05:30:45.000");
            date4 = dateTimeParser.parse("March 30, 2003 01:10:00.000");
            date5 = dateTimeParser.parse("March 30, 2003 01:40:00.000");
            date6 = dateTimeParser.parse("March 30, 2003 02:10:00.000");
            date7 = dateTimeParser.parse("March 30, 2003 02:40:00.000");
            date8 = dateTimeParser.parse("October 26, 2003 05:30:45.000");
        } finally {
            dateTimeParser.setTimeZone(DEFAULT_ZONE);
            TimeZone.setDefault(DEFAULT_ZONE);
        }
        calAmPm1 = Calendar.getInstance();
        calAmPm1.setTime(dateAmPm1);
        calAmPm2 = Calendar.getInstance();
        calAmPm2.setTime(dateAmPm2);
        calAmPm3 = Calendar.getInstance();
        calAmPm3.setTime(dateAmPm3);
        calAmPm4 = Calendar.getInstance();
        calAmPm4.setTime(dateAmPm4);
        cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        try {
            TimeZone.setDefault(zone);
            cal3 = Calendar.getInstance();
            cal3.setTime(date3);
            cal4 = Calendar.getInstance();
            cal4.setTime(date4);
            cal5 = Calendar.getInstance();
            cal5.setTime(date5);
            cal6 = Calendar.getInstance();
            cal6.setTime(date6);
            cal7 = Calendar.getInstance();
            cal7.setTime(date7);
            cal8 = Calendar.getInstance();
            cal8.setTime(date8);
        } finally {
            TimeZone.setDefault(DEFAULT_ZONE);
        }
    }

    @Test
    public void testAddPositiveYearsToValidDate(){
        Date result = DateUtils.addYears(BASE_DATE, 1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2024, 9, 29, 22, 33, 44, 555);
    }

    @Test
    public void testAddNegativeYearsToValidDate(){
        Date result = DateUtils.addYears(BASE_DATE, -1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2022, 9, 29, 22, 33, 44, 555);
    }

    @Test
    public void testAddYearsToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.addYears(null, 0));
    }

    @Test
    public void testAddPositiveMonthsToValidDate(){
        Date result = DateUtils.addMonths(BASE_DATE, 1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 10, 29, 22, 33, 44, 555);
    }

    @Test
    public void testAddNegativeMonthsToValidDate(){
        Date result = DateUtils.addMonths(BASE_DATE, -1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 8, 29, 22, 33, 44, 555);
    }

    @Test
    public void testAddMonthsToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.addMonths(null, 0));
    }

    @Test
    public void testAddPositiveWeeksToValidDate(){
        Date result = DateUtils.addWeeks(BASE_DATE, 1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 10, 5, 22, 33, 44, 555);
    }

    @Test
    public void testAddNegativeWeeksToValidDate(){
        Date result = DateUtils.addWeeks(BASE_DATE, -1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 22, 22, 33, 44, 555);
    }

    @Test
    public void testAddWeeksToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.addWeeks(null, 0));
    }

    @Test
    public void testAddPositiveDaysToValidDate(){
        Date result = DateUtils.addDays(BASE_DATE, 1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 30, 22, 33, 44, 555);
    }

    @Test
    public void testAddNegativeDaysToValidDate(){
        Date result = DateUtils.addDays(BASE_DATE, -1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 28, 22, 33, 44, 555);
    }

    @Test
    public void testAddDaysToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.addDays(null, 0));
    }

    @Test
    public void testAddPositiveHoursToValidDate(){
        Date result = DateUtils.addHours(BASE_DATE, 1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 23, 33, 44, 555);
    }

    @Test
    public void testAddNegativeHoursToValidDate(){
        Date result = DateUtils.addHours(BASE_DATE, -1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 21, 33, 44, 555);
    }

    @Test
    public void testAddHoursToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.addHours(null, 0));
    }

    @Test
    public void testAddPositiveMinutesToValidDate(){
        Date result = DateUtils.addMinutes(BASE_DATE, 1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 34, 44, 555);
    }

    @Test
    public void testAddNegativeMinutesToValidDate(){
        Date result = DateUtils.addMinutes(BASE_DATE, -1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 32, 44, 555);
    }

    @Test
    public void testAddMinutesToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.addMinutes(null, 0));
    }

    @Test
    public void testAddPositiveSecondsToValidDate(){
        Date result = DateUtils.addSeconds(BASE_DATE, 1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 33, 45, 555);
    }

    @Test
    public void testAddNegativeSecondsToValidDate(){
        Date result = DateUtils.addSeconds(BASE_DATE, -1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 33, 43, 555);
    }

    @Test
    public void testAddSecondsToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.addSeconds(null, 0));
    }

    @Test
    public void testAddPositiveMillisecondsToValidDate(){
        Date result = DateUtils.addMilliseconds(BASE_DATE, 1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 33, 44, 556);
    }

    @Test
    public void testAddNegativeMillisecondsToValidDate(){
        Date result = DateUtils.addMilliseconds(BASE_DATE, -1);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 33, 44, 554);
    }

    @Test
    public void testAddMillisecondsToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.addMilliseconds(null, 0));
    }

    @Test
    public void testSetYearToPositiveAmount() {
        Date result = DateUtils.setYears(BASE_DATE, 2001);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2001, 9, 29, 22, 33, 44, 555);
    }

    @Test
    public void testSetYearToNegativeAmountThrowsIllegalArgumentException() throws Exception {
        final String outsideOfRangeAssertionMessage = "DateUtils.setYears did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setYears(BASE_DATE, -1),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetYearsToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.setYears(null, 0));
    }

    @Test
    public void testSetMonthToValidAmount() {
        Date result = DateUtils.setMonths(BASE_DATE, 2);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 2, 29, 22, 33, 44, 555);
    }

    @Test
    public void testSetMonthToFebForNonLeapYearAtDay29thThrowsIllegalArgumentException() throws Exception{
        final String outsideOfRangeAssertionMessage = "DateUtils.setMonths did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setMonths(BASE_DATE, 1),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetMonthTo30DayMonthForValidDateAtDay31stThrowsIllegalArgumentException() throws Exception{
        final GregorianCalendar cal = new GregorianCalendar(2023, 2, 31, 22, 33, 44);
        cal.set(Calendar.MILLISECOND, 555);
        Date dateInMar = cal.getTime();
        final String outsideOfRangeAssertionMessage = "DateUtils.setMonths did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setMonths(dateInMar, 3),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetMonthToAmountBelowValidRangeThrowsIllegalArgumentException() throws Exception {
        final String outsideOfRangeAssertionMessage = "DateUtils.setMonths did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setMonths(BASE_DATE, -1),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetMonthToAmountAboveValidRangeThrowsIllegalArgumentException() throws Exception {
        final String outsideOfRangeAssertionMessage = "DateUtils.setMonths did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setMonths(BASE_DATE, 12),
                outsideOfRangeAssertionMessage);
    }
    
    
    @Test
    public void testSetMonthsToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.setMonths(null, 0));
    }

    @Test
    public void testSetDayToValidAmount() {
        Date result = DateUtils.setDays(BASE_DATE, 30);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 30, 22, 33, 44, 555);
    }


    @Test
    public void testSetDayToAmountBelowValidRangeThrowsIllegalArgumentException() throws Exception {
        final String outsideOfRangeAssertionMessage = "DateUtils.setDays did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setDays(BASE_DATE, 0),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetDayToAmountAbove28thForDateInFebOfNonLeapYearThrowsIllegalArgumentException() throws Exception {
        final GregorianCalendar cal = new GregorianCalendar(2023, 1, 28, 22, 33, 44);
        cal.set(Calendar.MILLISECOND, 555);
        Date dateInFeb = cal.getTime();

        final String outsideOfRangeAssertionMessage = "DateUtils.setDays did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setDays(dateInFeb, 29),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetDayToAmountAbove29thForDateInFebOfLeapYearThrowsIllegalArgumentException() throws Exception {
        final GregorianCalendar cal = new GregorianCalendar(2000, 1, 28, 22, 33, 44);
        cal.set(Calendar.MILLISECOND, 555);
        Date dateInFeb = cal.getTime();

        final String outsideOfRangeAssertionMessage = "DateUtils.setDays did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setDays(dateInFeb, 30),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetDayToAmountAboveValidRangeForDateIn30DayMonthThrowsIllegalArgumentException() throws Exception {
        final GregorianCalendar cal = new GregorianCalendar(2023, 3, 28, 22, 33, 44);
        cal.set(Calendar.MILLISECOND, 555);
        Date dateInApr = cal.getTime();

        final String outsideOfRangeAssertionMessage = "DateUtils.setDays did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setDays(dateInApr, 31),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetDayToAmountAboveValidRangeForDateIn31DayMonthThrowsIllegalArgumentException() throws Exception {

        final String outsideOfRangeAssertionMessage = "DateUtils.setDays did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setDays(BASE_DATE, 32),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetDaysToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.setDays(null, 0));
    }
    
    @Test
    public void testSetHourToValidAmount() {
        Date result = DateUtils.setHours(BASE_DATE, 0);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 0, 33, 44, 555);
    }


    @Test
    public void testSetHourToAmountBelowValidRangeThrowsIllegalArgumentException() throws Exception {
        final String outsideOfRangeAssertionMessage = "DateUtils.setHours did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setHours(BASE_DATE, -1),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetHourToAmountAboveValidRangeThrowsIllegalArgumentException() throws Exception {

        final String outsideOfRangeAssertionMessage = "DateUtils.setHours did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setHours(BASE_DATE, 24),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetHoursToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.setHours(null, 0));
    }

    @Test
    public void testSetMinuteToValidAmount() {
        Date result = DateUtils.setMinutes(BASE_DATE, 0);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 0, 44, 555);
    }


    @Test
    public void testSetMinuteToAmountBelowValidRangeThrowsIllegalArgumentException() throws Exception {
        final String outsideOfRangeAssertionMessage = "DateUtils.setMinutes did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setMinutes(BASE_DATE, -1),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetMinuteToAmountAboveValidRangeThrowsIllegalArgumentException() throws Exception {

        final String outsideOfRangeAssertionMessage = "DateUtils.setMinutes did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setMinutes(BASE_DATE, 60),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetMinutesToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.setMinutes(null, 0));
    }

    @Test
    public void testSetSecondToValidAmount() {
        Date result = DateUtils.setSeconds(BASE_DATE, 0);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 33, 0, 555);
    }


    @Test
    public void testSetSecondToAmountBelowValidRangeThrowsIllegalArgumentException() throws Exception {
        final String outsideOfRangeAssertionMessage = "DateUtils.setSeconds did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setSeconds(BASE_DATE, -1),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetSecondToAmountAboveValidRangeThrowsIllegalArgumentException() throws Exception {

        final String outsideOfRangeAssertionMessage = "DateUtils.setSeconds did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setSeconds(BASE_DATE, 60),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetSecondsToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.setSeconds(null, 0));
    }

    @Test
    public void testSetMillisecondToValidAmount() {
        Date result = DateUtils.setMilliseconds(BASE_DATE, 0);
        assertNotSame(BASE_DATE, result);
        assertDate(result, 2023, 9, 29, 22, 33, 44, 0);
    }


    @Test
    public void testSetMillisecondToAmountBelowValidRangeThrowsIllegalArgumentException() throws Exception {
        final String outsideOfRangeAssertionMessage = "DateUtils.setMilliseconds did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setMilliseconds(BASE_DATE, -1),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetMillisecondToAmountAboveValidRangeThrowsIllegalArgumentException() throws Exception {

        final String outsideOfRangeAssertionMessage = "DateUtils.setMilliseconds did not throw an expected IllegalArgumentException.";
        assertThrows(
                IllegalArgumentException.class,
                () -> DateUtils.setMilliseconds(BASE_DATE, 1000),
                outsideOfRangeAssertionMessage);
    }

    @Test
    public void testSetMillisecondsToNullDateThrowsNullPointerException() throws Exception {
        assertThrows(NullPointerException.class, () -> DateUtils.setMilliseconds(null, 0));
    }
}