package com.myself.Exception;

public class ErrorCutTimeException extends Exception {
    public ErrorCutTimeException () {
        super("Error extract string time: Line is not valid");
    }
}
