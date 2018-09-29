package com.myself.Exception;

/**
 * @author Bruno Meurer
 * @version 1.0
 */
public class InvalidFileException extends Exception {
    /**
     * Help to pass by param
     */
    public static final String INVALID_EXTENSION = "The extension is different than '.txt'";

    /**
     * To process file
     * @param message
     */
    public InvalidFileException(String message) {
        super("Invalid file: " + message);
    }
}
