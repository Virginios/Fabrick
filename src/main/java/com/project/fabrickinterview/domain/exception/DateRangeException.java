package com.project.fabrickinterview.domain.exception;

public class DateRangeException extends RuntimeException {

    public DateRangeException() {
        super("La data di partenza è successiva a quella di arrivo");
    }
}
