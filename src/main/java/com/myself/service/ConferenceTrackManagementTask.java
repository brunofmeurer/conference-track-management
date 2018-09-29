package com.myself.service;

import com.myself.Entity.Talk;
import com.myself.Entity.Track;
import com.myself.Exception.InvalidFileException;
import com.myself.util.ConstantsUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public enum ConferenceTrackManagementTask {
    INSTANCE;

    private List<Talk> importTalks;
    private List<Track> tracks;

    ConferenceTrackManagementTask() {
        importTalks = new ArrayList<>();
        tracks = new ArrayList<>();
    }
    public void init(String arg) throws IOException, InvalidFileException {
        Path path = Paths.get(arg);
        Stream<String> lines = Files.lines(path);
        validFile(arg);
        readFile(lines);
        mountTracks();
    }

    private void validFile(String arg) throws InvalidFileException {
        if (!arg.endsWith(ConstantsUtil.TXT)) {
            throw new InvalidFileException(InvalidFileException.INVALID_EXTENSION);
        }
    }

    private void readFile(Stream<String> lines) {
        lines.forEach(line -> {
            Talk talk = new Talk(line);
            talk.setMinutes(getTime(cutTime(line)));
            importTalks.add(talk);
        });
    }

    private void mountTracks () {
        importTalks.sort((o1, o2) -> o2.getMinutes() - o1.getMinutes());
        importTalks.forEach(talk -> System.out.println(talk));
    }

    private String cutTime(String line) {
        return line.substring(line.lastIndexOf(" ") + 1);
    }

    private int getTime(String stringTime) {
        if (stringTime.endsWith(ConstantsUtil.MINUTE)) {
            stringTime = stringTime.replace("min", "");
            return Integer.parseInt(stringTime);
        }

        if (stringTime.endsWith(ConstantsUtil.LIGHTNING)) {
            return 5;
        }
        return 0;
    }
}
