package com.myself.util;

import java.util.Calendar;

/**
 * Util date tricks
 * @author Bruno Meurer
 * @version 1.0
 */
public enum DateUtil {
    INSTANCE;

    /**
     * get instance of calendar with zero time
     * @return Calendar
     */
    public Calendar getInstance() {
        Calendar cal = Calendar.getInstance();
        setInitialValues(cal, 0);
        return cal;
    }

    /**
     * new instance by param
     * @param cal
     * @return Calendar
     */
    public Calendar newInstance(Calendar cal) {
        Calendar value = Calendar.getInstance();
        value.setTime(cal.getTime());
        return value;
    }

    /**
     * get midday time
     * @return Calendar
     */
    public Calendar getMidday () {
        Calendar cal = Calendar.getInstance();
        setInitialValues(cal, 12);
        return cal;
    }

    /**
     * set initial values zero time
     * @param cal
     */
    private void setInitialValues(Calendar cal, int hour) {
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.HOUR_OF_DAY, hour);
    }
}
