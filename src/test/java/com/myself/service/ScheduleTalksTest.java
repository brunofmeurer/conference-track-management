package com.myself.service;

import com.myself.Exception.ErrorCutTimeException;
import com.myself.Exception.InvalidFileException;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ValueRange;

public class ScheduleTalksTest {

    private ScheduleTalks schedule = new ScheduleTalks();

    @Test
    public void init() throws IOException, InvalidFileException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL teste = classLoader.getResource("input.txt");
        schedule.init(teste.toString().replace("file:/", ""));
        System.out.println(teste.toString().replace("file:/", ""));
    }

    @Test
    public void getCut() throws ErrorCutTimeException {
        String value = schedule.cutTime("A World Without HackerNews 30min");
        if (!value.equals("30min")) {
            throw new ErrorCutTimeException();
        }
        value = schedule.cutTime("Rails for Python Developers lightning");
        if (!value.equals("lightning")) {
            throw new ErrorCutTimeException();
        }
    }

    @Test
    public void getTime() {
        schedule.getTime("60min");
        schedule.getTime("120min");
        schedule.getTime("lightning");
    }
}
