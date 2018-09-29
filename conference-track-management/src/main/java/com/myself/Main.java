package com.myself;

import com.myself.Exception.InvalidFileException;
import com.myself.service.ScheduleTalks;

import java.io.IOException;

/**
 * Main block
 * @author Bruno Meurer
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) throws IOException, InvalidFileException {
        args = new String[]{"C:\\Users\\bruno\\OneDrive\\Documentos\\input.txt"};

        for (String arg : args) {
            ScheduleTalks task = new ScheduleTalks();
            task.init(arg);
        }
    }
}
