package com.myself.Exception;

public class InvalidFileException extends Exception {
    /**
     * Help to pass by param
     */
    public static final String INVALID_EXTENSION = "The extension is different than '.txt'";
    public InvalidFileException(String message) {
        super("Invalid file: " + message);
    }
}
