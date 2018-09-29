package com.myself;

import com.myself.Entity.Talk;
import com.myself.Entity.Track;
import com.myself.Exception.InvalidFileException;
import com.myself.Exception.InvalidTalkException;
import com.myself.util.ConstantsUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFileException, InvalidTalkException {
        args = new String[]{"C:\\Users\\bruno\\pessoais\\conference-track-management\\input-file\\input.txt"};

        for (String arg : args) {
            Path path = Paths.get(arg);
            Stream<String> lines = Files.lines(path);

            if (!arg.endsWith(ConstantsUtil.TXT)) {
                throw new InvalidFileException(InvalidFileException.INVALID_EXTENSION);
            }
            List<Talk> importTalks = new ArrayList<>();
            lines.forEach(line -> {
                // System.out.println(line.substring(line.lastIndexOf(" ") + 1));
                Talk talk = new Talk(line);
                importTalks.add(talk);
            });
            Track track = new Track("1", importTalks);
            System.out.println(track);
        }
    }
}
