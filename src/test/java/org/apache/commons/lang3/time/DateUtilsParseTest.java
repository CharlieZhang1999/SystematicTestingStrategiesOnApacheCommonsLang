package org.apache.commons.lang3.time;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DateUtilsParseTest {


    private static final TimeZone DEFAULT_ZONE = TimeZone.getTimeZone("GMT");

    private static Calendar cal;

    @BeforeEach
    public void setUp()  {
        cal = Calendar.getInstance();
        cal.set(2008, Calendar.APRIL, 9, 23, 55, 38);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(DEFAULT_ZONE);
    }

    @Test
    public void canParseCorrectStrWithSingleValidPattern_2_1() throws ParseException {
        Date date1 = DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz");
        Date date2 = DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz");

        assertEquals(cal.getTime(), date1);
        assertEquals(cal.getTime(), date2);
    }

    @Test
    public void canParseCorrectStrWithMultiValidPatterns_2_2() throws ParseException {
        Date date1 = DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd");
        Date date2 = DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd");

        assertEquals(cal.getTime(), date1);
        assertEquals(cal.getTime(), date2);
    }

    @Test
    public void cannotParseNullStrWithSingleValidPattern_2_3() {
        assertThrows(NullPointerException.class, () -> DateUtils.parseDate(null,
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
        assertThrows(NullPointerException.class, () -> DateUtils.parseDateStrictly(null,
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseEmptyStrWithSingleValidPattern_2_4() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseNullStrWithMultiValidPatterns_2_5() {
        assertThrows(NullPointerException.class, () -> DateUtils.parseDate(null,
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
        assertThrows(NullPointerException.class, () -> DateUtils.parseDateStrictly(null,
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseEmptyStrWithMultiValidPatterns_2_6() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseValidStrWithNullPattern_2_7() {
        assertThrows(NullPointerException.class, () -> DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT", null));
        assertThrows(NullPointerException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT", null));
    }

    @Test
    public void cannotParseValidStrWithEmptyPattern_2_8() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT", ""));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT", ""));
    }

    @Test
    public void cannotParseInvalidWeekdayStrWithSingleValidPattern_2_9() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Abc, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Abc, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void cannotParseInvalidWeekdayStrWithMultiValidPatterns_2_10() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Abc, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Abc, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseMismatchWeekdayStrWithSingleValidPattern_2_11() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Thr, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Thr, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void cannotParseMismatchWeekdayStrWithMultiValidPatterns_2_12() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Thr, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Thr, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseInvalidMonthStrWithSingleValidPattern_2_13() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Wed, 09 Abc 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Abc 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void cannotParseInvalidMonthStrWithMultiValidPatterns_2_14() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Wed, 09 Abc 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Abc 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseInvalidZoneStrWithSingleValidPattern_2_15() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 ABC",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 ABC",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void cannotParseInvalidZoneStrWithMultiValidPatterns_2_16() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 ABC",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 ABC",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseInvalidStrWithSingleValidPattern_2_17() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Not valid string",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Not valid string",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void cannotParseInvalidStrWithMultiValidPatterns_2_18() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Not valid string",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Not valid string",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void canParseValidStrWithPartialValidPatterns_2_19() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "Not a valid pattern");

        assertEquals(cal.getTime(), date);

        assertThrows(IllegalArgumentException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT",
                "Not a valid pattern", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseValidStrWithSingleInvalidPattern_2_20() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT",
                "Not a valid pattern"));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT",
                "Not a valid pattern"));
    }

    @Test
    public void cannotParseValidStrWithMultiInvalidPatterns_2_21() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT",
                "Not a valid pattern", "Another invalid pattern"));
        assertThrows(IllegalArgumentException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT",
                "Not a valid pattern", "Another invalid pattern"));
    }

    @Test
    public void cannotParseValidStrWithSingleValidButMismatchPattern_2_22() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT",
                "yyyy'-'MM'-'dd"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT",
                "yyyy'-'MM'-'dd"));
    }

    @Test
    public void cannotParseValidStrWithMultiValidButMismatchPatterns_2_23() {
        assertThrows(ParseException.class, () -> DateUtils.parseDate("Wed, 09 Apr 2008 23:55:38 GMT",
                "yyyy HH:mm", "yyyy'-'MM'-'dd"));
        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:38 GMT",
                "yyyy HH:mm", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void canParseLenientMonthStrWithSingleValidPattern_2_24() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 13/09 2008 23:55:38 GMT",
                "EEE, MM/dd yyyy HH:mm:ss zzz");

        Calendar cld = Calendar.getInstance();
        cld.set(2009, Calendar.JANUARY, 9, 23, 55, 38);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 13/09 2008 23:55:38 GMT",
                "EEE, MM/dd yyyy HH:mm:ss zzz"));
    }

    @Test
    public void canParseLenientMonthStrWithMultiValidPatterns_2_25() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 00/09 2008 23:55:38 GMT",
                "EEE, MM/dd yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd");

        Calendar cld = Calendar.getInstance();
        cld.set(2007, Calendar.DECEMBER, 9, 23, 55, 38);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 00/09 2008 23:55:38 GMT",
                "EEE, MM/dd yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void canParseLenientDayStrWithSingleValidPattern_2_26() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 32 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz");

        Calendar cld = Calendar.getInstance();
        cld.set(2008, Calendar.MAY, 2, 23, 55, 38);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 32 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void canParseLenientDayStrWithMultiValidPatterns_2_27() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 00 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd");

        Calendar cld = Calendar.getInstance();
        cld.set(2008, Calendar.MARCH, 31, 23, 55, 38);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 00 Apr 2008 23:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void canParseLenientHourStrWithSingleValidPattern_2_28() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 09 Apr 2008 24:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz");

        Calendar cld = Calendar.getInstance();
        cld.set(2008, Calendar.APRIL, 10, 0, 55, 38);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 24:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void canParseLenientHourStrWithMultiValidPatterns_2_29() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 09 Apr 2008 25:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd");

        Calendar cld = Calendar.getInstance();
        cld.set(2008, Calendar.APRIL, 10, 1, 55, 38);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 25:55:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void canParseLenientMinuteStrWithSingleValidPattern_2_30() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 09 Apr 2008 23:60:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz");

        Calendar cld = Calendar.getInstance();
        cld.set(2008, Calendar.APRIL, 10, 0, 0, 38);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:60:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void canParseLenientHourStrWithMultiValidPatterns_2_31() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 09 Apr 2008 23:70:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd");

        Calendar cld = Calendar.getInstance();
        cld.set(2008, Calendar.APRIL, 10, 0, 10, 38);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:70:38 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

    @Test
    public void canParseLenientSecondStrWithSingleValidPattern_2_32() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 09 Apr 2008 23:55:60 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz");

        Calendar cld = Calendar.getInstance();
        cld.set(2008, Calendar.APRIL, 9, 23, 56, 0);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:60 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz"));
    }

    @Test
    public void canParseLenientSecondStrWithMultiValidPatterns_2_33() throws ParseException {
        Date date = DateUtils.parseDate("Wed, 09 Apr 2008 23:55:99 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd");

        Calendar cld = Calendar.getInstance();
        cld.set(2008, Calendar.APRIL, 9, 23, 56, 39);
        cld.set(Calendar.MILLISECOND, 0);
        cld.setTimeZone(DEFAULT_ZONE);

        assertEquals(cld.getTime(), date);

        assertThrows(ParseException.class, () -> DateUtils.parseDateStrictly("Wed, 09 Apr 2008 23:55:99 GMT",
                "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy'-'MM'-'dd"));
    }

}
