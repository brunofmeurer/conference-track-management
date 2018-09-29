package com.myself.service;

import com.myself.Entity.Talk;
import com.myself.Entity.Track;
import com.myself.Exception.InvalidFileException;
import com.myself.factory.TalkFactory;
import com.myself.util.ConstantsUtil;
import com.myself.util.DateUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Schedule talks. Here the magic happen
 * @author Bruno Meurer
 * @version 1.0
 */
public class ScheduleTalks {

    private List<Talk> importTalks;
    private List<Track> tracks;

    public ScheduleTalks() {
        importTalks = new ArrayList<>();
        tracks = new ArrayList<>();
    }

    /**
     * Init process file
     * @param arg
     * @throws IOException
     * @throws InvalidFileException
     */
    public void init(String arg) throws IOException, InvalidFileException {
        Path path = Paths.get(arg);
        Stream<String> lines = Files.lines(path);
        validFile(arg);
        readFile(lines);
        scheduleTalks();
        printResult();
    }

    /**
     * Validation file
     * @param arg
     * @throws InvalidFileException
     */
    private void validFile(String arg) throws InvalidFileException {
        if (!arg.endsWith(ConstantsUtil.TXT)) {
            throw new InvalidFileException(InvalidFileException.INVALID_EXTENSION);
        }
    }

    /**
     * Read file lines
     * @param lines
     */
    private void readFile(Stream<String> lines) {
        lines.forEach(line -> {
            Talk talk = new Talk(line);
            talk.setMinutes(getTime(cutTime(line)));
            // if minutes of talk equal than 0 it is not valid and is ignored
            if (talk.getMinutes() != 0) {
                importTalks.add(talk);
            }
        });
        // to diferent results
        Collections.shuffle(importTalks);
    }

    /**
     * Start schedule talks
     */
    private void scheduleTalks() {
        Calendar timeAM = DateUtil.INSTANCE.getInstance();
        timeAM.set(Calendar.HOUR_OF_DAY, ConstantsUtil.START_TRACK_AM);
        Calendar timePM = DateUtil.INSTANCE.getInstance();
        timePM.set(Calendar.HOUR_OF_DAY, ConstantsUtil.START_TRACK_PM);

        for (int i=0;i<ConstantsUtil.CONFERENCE_DAYS;i++) {
            Track track = new Track();
            tracks.add(track);
            scheduleTalksByParamMorning(timeAM, track);
            scheduleTalksByParamAfternoon(timePM, track);
        }
    }

    /**
     * print result of scheduled
     */
    private void printResult() {
        tracks.forEach(track -> System.out.println(track));
    }

    /**
     * Schedule talks by param
     * @param time
     * @param track
     * @param timeLimit
     * @return Calendar
     */
    private Calendar scheduleTalksByParam(Calendar time, Track track, int timeLimit) {
        Calendar cal = DateUtil.INSTANCE.newInstance(time);
        for (Talk talk : importTalks) {
            if (!isAlreadyUsed(talk.getName())) {
                talk.setTime(cal);
                track.addTalk(talk);
                cal.add(Calendar.MINUTE, talk.getMinutes());
            }

            int timeRemain = timeLimit - cal.get(Calendar.HOUR_OF_DAY);
            if (timeRemain < 1) {
                break;
            }
        }
        return cal;
    }

    /**
     * Schedule talks by param at morning
     * @param time
     * @param track
     */
    private void scheduleTalksByParamMorning(Calendar time, Track track) {
        scheduleTalksByParam(time, track, ConstantsUtil.LAST_HOUR_AM);
        track.addTalk(TalkFactory.INSTANCE.lunch());
    }

    /**
     * Schedule talks by param at afternoon
     * @param time
     * @param track
     */
    private void scheduleTalksByParamAfternoon(Calendar time, Track track) {
        Calendar cal = scheduleTalksByParam(time, track, ConstantsUtil.LAST_HOUR_PM);
        track.addTalk(TalkFactory.INSTANCE.network(cal));
    }

    /**
     * returns if talk already was scheduled
     * @param name
     * @return boolean
     */
    private boolean isAlreadyUsed(String name) {
        for (Track track : tracks) {
            for (Talk talk : track.getTalks()) {
                if (talk.getName().equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * extract string time to end of line
     * @param line
     * @return String
     */
    public String cutTime(String line) {
        return line.substring(line.lastIndexOf(" ") + 1);
    }

    /**
     * extract int time to end of line
     * @param stringTime
     * @return int
     */
    public int getTime(String stringTime) {
        if (stringTime.endsWith(ConstantsUtil.MINUTE)) {
            stringTime = stringTime.replace("min", "");
            return Integer.parseInt(stringTime);
        }
        if (stringTime.endsWith(ConstantsUtil.LIGHTNING)) {
            return 5;
        }
        return 0;
    }

    /**
     * Returns scheduled tracks
     * @return List<Track>
     */
    public List<Track> getTracks() {
        return tracks;
    }
}
