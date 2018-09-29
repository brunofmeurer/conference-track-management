package com.myself.factory;

import com.myself.Entity.Talk;
import com.myself.util.DateUtil;

import java.util.Calendar;

/**
 * Singleton factory
 * @author Bruno Meurer
 * @version 1.0
 */
public enum TalkFactory {
    INSTANCE;

    /**
     * Factory lunch time
     * @return Talk
     */
    public Talk lunch () {
        return new Talk("Lunch", DateUtil.INSTANCE.getMidday(), 60);
    }

    /**
     * Factory network time
     * @param time
     * @return Talk
     */
    public Talk network (Calendar time) {
        return new Talk("Network Event", time, 0);
    }
}
