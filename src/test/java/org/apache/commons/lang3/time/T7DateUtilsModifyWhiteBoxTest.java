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
    public void canCeilTheCalendarBySemiMonth(){
        calendar1.set(2001, 11, 1, 1, 43, 41 );
        calendar1.set(Calendar.MILLISECOND, 560);
        DateUtils.ceiling(calendar1, DateUtils.SEMI_MONTH);
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
        calendar1.set(2002, Calendar.FEBRUARY, 3, 13, 10, 0);
        calendar1.set(Calendar.MILLISECOND, 0);
        Date date = DateUtils.ceiling(calendar1.getTime(), Calendar.AM_PM);

        Calendar c2 = Calendar.getInstance();
        c2.set(2002, Calendar.FEBRUARY, 4, 0, 0, 0);
        c2.set(Calendar.MILLISECOND, 0);
        assertEquals(c2.getTime(), date);
    }

}
