package com.myself.util;

import com.myself.Exception.DateUtilException;
import org.junit.Test;

import java.util.Calendar;

public class DataUtilTest {
    @Test
    public void getInstance() throws DateUtilException {
        Calendar cal = DateUtil.INSTANCE.getInstance();
        if(cal.get(Calendar.HOUR_OF_DAY) != 0) {
            throw new DateUtilException(DateUtilException.DATA_ZERO);
        }
    }

    @Test
    public void getMidday() throws DateUtilException {
        Calendar cal = DateUtil.INSTANCE.getMidday();
        if(cal.get(Calendar.HOUR_OF_DAY) != 12) {
            throw new DateUtilException(DateUtilException.DATA_MIDDAY);
        }
    }
}
