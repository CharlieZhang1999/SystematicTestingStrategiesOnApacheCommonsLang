package org.apache.commons.lang3.time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class T7DateUtilsModifyTest_Qiuyang {
    private static Calendar calendar_1;

    @BeforeEach
    public void setUp() {
        calendar_1 = Calendar.getInstance();
    }

    @Test
    public void canCeilTheCalendarBySemiMonth(){
        calendar_1.set(2001, 11, 1, 1, 43, 41 );
        calendar_1.set(Calendar.MILLISECOND, 560);
        DateUtils.ceiling(calendar_1, DateUtils.SEMI_MONTH);
    }
}
