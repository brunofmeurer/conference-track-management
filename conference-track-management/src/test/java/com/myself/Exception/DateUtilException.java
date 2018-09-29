package com.myself.Exception;

public class DateUtilException extends Exception {
    public static final String DATA_ZERO = "Time invalid! is not zero!!";
    public static final String DATA_MIDDAY = "Time invalid! is not 12h pm!!";
    public DateUtilException(String message) {
        super("Error data util: " + message);
    }
}
