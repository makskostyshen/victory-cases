package com.makskostyshen.victorycases.exception;

public class CSVWriteException extends RuntimeException {
    public CSVWriteException(final Exception e) {
        super(e);
    }
}
