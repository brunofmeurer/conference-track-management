package com.myself.Entity;

import com.myself.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Bruno Meurer
 * @version 1.0
 */
public class Talk {
    private String name;
    private int minutes;
    private Calendar time;
    private SimpleDateFormat format;

    public Talk (String name, Calendar time, int minutes) {
        this.name = name;
        this.minutes = minutes;
        this.time = time;
        this.format = new SimpleDateFormat("hh:mma");
    }

    public Talk (String name) {
        this.name = name;
        this.minutes = 0;
        this.time = Calendar.getInstance();
        this.format = new SimpleDateFormat("hh:mma");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = DateUtil.INSTANCE.newInstance(time);
    }

    @Override
    public String toString() {
        return this.format
                .format(time.getTime())
                .concat(" ")
                .concat(this.name);
    }
}
