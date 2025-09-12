package com.rperotta.autolog.exception;

public class OdometerEntryNotFoundException extends RuntimeException {
    public OdometerEntryNotFoundException(String message) {
        super(message);
    }
}
