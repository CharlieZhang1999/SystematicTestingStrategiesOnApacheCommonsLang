package org.apache.commons.lang3.time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class T7DateUtilsModifyWhiteBoxTest {
    private static Calendar calendar1;

    @BeforeEach
    public void setUp() {
        calendar1 = Calendar.getInstance();
    }

    @Test
    public void canCeilTheCalendarWithDateEquals1BySemiMonth(){
        calendar1.set(2001, Calendar.NOVEMBER, 1, 1, 43, 41 );
        calendar1.set(Calendar.MILLISECOND, 560);
        Date date1 = calendar1.getTime();
        Date date = DateUtils.ceiling(calendar1.getTime(), DateUtils.SEMI_MONTH);

        Calendar c2 = Calendar.getInstance();
        c2.set(2001, Calendar.NOVEMBER, 16, 0, 0, 0 );
        c2.set(Calendar.MILLISECOND, 0);
        assertEquals(c2.getTime(), date);
    }

    @Test
    public void canRoundDownTheCalendarWithDateEquals1BySemiMonth(){
        calendar1.set(2001, Calendar.NOVEMBER, 1, 1, 43, 41 );
        calendar1.set(Calendar.MILLISECOND, 560);
        Date date1 = calendar1.getTime();
        Date date = DateUtils.round(calendar1.getTime(), DateUtils.SEMI_MONTH);

        Calendar c2 = Calendar.getInstance();
        c2.set(2001, Calendar.NOVEMBER, 1, 0, 0, 0 );
        c2.set(Calendar.MILLISECOND, 0);
        assertEquals(c2.getTime(), date);
    }

    @Test
    public void canTruncateTheCalendarWithDateEquals1BySemiMonth(){
        calendar1.set(2001, Calendar.NOVEMBER, 1, 1, 43, 41 );
        calendar1.set(Calendar.MILLISECOND, 560);
        Date date1 = calendar1.getTime();
        Date date = DateUtils.truncate(calendar1.getTime(), DateUtils.SEMI_MONTH);

        Calendar c2 = Calendar.getInstance();
        c2.set(2001, Calendar.NOVEMBER, 1, 0, 0, 0 );
        c2.set(Calendar.MILLISECOND, 0);
        assertEquals(c2.getTime(), date);
    }
    @Test
    public void canRound11AMTo12PMByAMPM() {
        calendar1.set(2002, Calendar.FEBRUARY, 3, 11, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Date date = DateUtils.round(calendar1.getTime(), Calendar.AM_PM);

        Calendar c2 = Calendar.getInstance();
        c2.set(2002, Calendar.FEBRUARY, 3, 12, 0, 0);
        c2.set(Calendar.MILLISECOND, 0);
        assertEquals(c2.getTime(), date);
    }

    @Test
    public void canRound23PMTo00AMByAMPM() {
        calendar1.set(2002, Calendar.FEBRUARY, 3, 23, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Date date = DateUtils.round(calendar1.getTime(), Calendar.AM_PM);

        Calendar c2 = Calendar.getInstance();
        c2.set(2002, Calendar.FEBRUARY, 4, 0, 0, 0);
        c2.set(Calendar.MILLISECOND, 0);
        assertEquals(c2.getTime(), date);
    }

    @Test
    public void canCeil1AMTo12PMByAMPM() {
        calendar1.set(2002, Calendar.FEBRUARY, 3, 1, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Date date = DateUtils.ceiling(calendar1.getTime(), Calendar.AM_PM);

        Calendar c2 = Calendar.getInstance();
        c2.set(2002, Calendar.FEBRUARY, 3, 12, 0, 0);
        c2.set(Calendar.MILLISECOND, 0);
        assertEquals(c2.getTime(), date);
    }

    @Test
    public void canCei1PMTo12AMByAMPM() {
        calendar1.set(2002, Calendar.FEBRUARY, 3, 12, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Date date = DateUtils.ceiling(calendar1.getTime(), Calendar.AM_PM);

        Calendar c2 = Calendar.getInstance();
        c2.set(2002, Calendar.FEBRUARY, 4, 0, 0, 0);
        c2.set(Calendar.MILLISECOND, 0);
        assertEquals(c2.getTime(), date);
    }

    @Test
    public void roundWithOver280MillionYears() throws ArithmeticException {
        calendar1.set(300000000, Calendar.FEBRUARY, 3, 13, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        assertThrows(ArithmeticException.class, () -> DateUtils.round(calendar1, Calendar.MINUTE));
    }

    @Test
    public void truncateWithOver280MillionYears() throws ArithmeticException {
        calendar1.set(300000000, Calendar.FEBRUARY, 3, 13, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        assertThrows(ArithmeticException.class, () -> DateUtils.truncate(calendar1, Calendar.MINUTE));
    }

    @Test
    public void ceilingWithOver280MillionYears() throws ArithmeticException {
        calendar1.set(300000000, Calendar.FEBRUARY, 3, 13, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        assertThrows(ArithmeticException.class, () -> DateUtils.ceiling(calendar1, Calendar.MINUTE));
    }

    @Test
    public void roundWithoutTruncatingMilliseconds() {
        calendar1.set(2008, Calendar.FEBRUARY, 3, 13, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 750);
        Calendar result = DateUtils.round(calendar1, Calendar.SECOND);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2008, Calendar.FEBRUARY, 3, 13, 10, 1);
        calendar2.set(Calendar.MILLISECOND, 0);
        assertEquals(calendar2, result);
    }

    @Test
    public void ceilingWithoutTruncatingMilliseconds() {
        calendar1.set(2008, Calendar.FEBRUARY, 3, 13, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 750);
        Calendar result = DateUtils.ceiling(calendar1, Calendar.SECOND);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2008, Calendar.FEBRUARY, 3, 13, 10, 1);
        calendar2.set(Calendar.MILLISECOND, 0);
        assertEquals(calendar2, result);
    }

}
