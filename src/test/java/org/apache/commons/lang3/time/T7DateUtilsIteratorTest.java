package org.apache.commons.lang3.time;

import static org.apache.commons.lang3.time.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.*;

import org.apache.commons.lang3.AbstractLangTest;
import org.junit.jupiter.api.Test;

public class T7DateUtilsIteratorTest extends AbstractLangTest {
    private static Date aDate;
    private static Calendar aCalendar;
    private static Calendar finalDate;
    private static Calendar finalCalendar;
    private static Iterator<Calendar> dateIterator;
    private static Iterator<Calendar> calendarIterator;

    private void createIterator(int rangeStyle) {
        calendarIterator = DateUtils.iterator(aCalendar, rangeStyle);
        dateIterator = DateUtils.iterator(aDate, rangeStyle);
    }

    private void beforeTest(int rangeStyle) throws Exception {
        aCalendar = Calendar.getInstance();
        aDate = aCalendar.getTime();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(() -> createIterator(rangeStyle));
        try {
            future.get(1, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            throw new Exception("Potential Infinite Loop Found");
        } finally {
            executor.shutdownNow();
        }
    }

    @Test
    public void testIteratorIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> DateUtils.iterator(Calendar.getInstance(), -9999));
    }

    @Test
    public void testIteratorNullPointerException() {
        assertThrows(NullPointerException.class, () -> DateUtils.iterator((Date) null, DateUtils.RANGE_MONTH_SUNDAY));
        assertThrows(NullPointerException.class, () -> DateUtils.iterator((Calendar) null, DateUtils.RANGE_MONTH_SUNDAY));
        assertThrows(NullPointerException.class, () -> DateUtils.iterator((Object) null, DateUtils.RANGE_MONTH_SUNDAY));
    }

    @Test
    public void testIteratorClassCastException() {
        assertThrows(ClassCastException.class, () -> DateUtils.iterator("", DateUtils.RANGE_MONTH_SUNDAY));
    }

    @Test
    public void testIteratorUnsupportedOperationException() throws Exception {
        beforeTest(RANGE_MONTH_SUNDAY);
        assertThrows(UnsupportedOperationException.class, () -> calendarIterator.remove());
    }

    @Test
    public void testIteratorRANGE_MONTH_SUNDAY() throws Exception {
        beforeTest(RANGE_MONTH_SUNDAY);
        assertTrue(dateIterator.hasNext());
        assertTrue(calendarIterator.hasNext());
        finalDate = dateIterator.next();
        finalCalendar = calendarIterator.next();
        assertEquals(finalCalendar.get(Calendar.DATE), finalDate.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testIteratorRANGE_MONTH_MONDAY() throws Exception {
        beforeTest(RANGE_MONTH_MONDAY);
        assertTrue(dateIterator.hasNext());
        assertTrue(calendarIterator.hasNext());
        finalDate = dateIterator.next();
        finalCalendar = calendarIterator.next();
        assertEquals(finalCalendar.get(Calendar.DATE), finalDate.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testIteratorRANGE_WEEK_SUNDAY() throws Exception {
        beforeTest(RANGE_WEEK_SUNDAY);
        assertTrue(dateIterator.hasNext());
        assertTrue(calendarIterator.hasNext());
        finalDate = dateIterator.next();
        finalCalendar = calendarIterator.next();
        assertEquals(finalCalendar.get(Calendar.DATE), finalDate.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testIteratorRANGE_WEEK_MONDAY() throws Exception {
        beforeTest(RANGE_WEEK_MONDAY);
        assertTrue(dateIterator.hasNext());
        assertTrue(calendarIterator.hasNext());
        finalDate = dateIterator.next();
        finalCalendar = calendarIterator.next();
        assertEquals(finalCalendar.get(Calendar.DATE), finalDate.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testIteratorRANGE_WEEK_RELATIVE() throws Exception {
        beforeTest(RANGE_WEEK_RELATIVE);
        assertTrue(dateIterator.hasNext());
        assertTrue(calendarIterator.hasNext());
        finalDate = dateIterator.next();
        finalCalendar = calendarIterator.next();
        assertEquals(finalCalendar.get(Calendar.DATE), finalDate.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testIteratorRANGE_WEEK_CENTER() throws Exception {
        beforeTest(RANGE_WEEK_CENTER);
        assertTrue(dateIterator.hasNext());
        assertTrue(calendarIterator.hasNext());
        finalDate = dateIterator.next();
        finalCalendar = calendarIterator.next();
        assertEquals(finalCalendar.get(Calendar.DATE), finalDate.get(Calendar.DAY_OF_MONTH));
    }
}
