package com.myself.Exception;

public class InvalidTalkException extends Exception {
    public static final String ONLY_MINUTES = "";
    public static final String NOT_LIGHTNING = "";
    public InvalidTalkException (String message) {
        super("Invalid talk: " + message);
    }
}
