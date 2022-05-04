package com.project.fabrickinterview.domain.exception;

public class DateFormatException extends RuntimeException {

    public DateFormatException() {
        super("Formato data non valido");
    }
}
