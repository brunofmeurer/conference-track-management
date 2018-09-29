package com.myself.Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bruno Meurer
 * @version 1.0
 */
public class Track {
    private String name;
    private List<Talk> talks;

    public Track () {
        this.name = "";
        this.talks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    public void addTalk (Talk talk) {
        this.talks.add(talk);
    }

    @Override
    public String toString() {
        String returnStr = "Track: ".concat(this.name).concat("\n");
        for (Talk talk : this.talks) {
            returnStr = returnStr
                    .concat(talk.toString())
                    .concat("\n");
        }
        return returnStr;
    }
}
