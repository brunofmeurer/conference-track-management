package com.myself;

import com.myself.Exception.InvalidFileException;
import com.myself.service.ConferenceTrackManagementTask;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFileException {
        args = new String[]{"C:\\Users\\bruno\\pessoais\\conference-track-management\\input-file\\input.txt"};

        for (String arg : args) {
            ConferenceTrackManagementTask.INSTANCE.init(arg);
        }
    }
}
