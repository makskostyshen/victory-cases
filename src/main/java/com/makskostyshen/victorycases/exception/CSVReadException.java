package com.makskostyshen.victorycases.exception;

public class CSVReadException extends RuntimeException {
    public CSVReadException(final Exception e) {
        super(e);
    }
}
