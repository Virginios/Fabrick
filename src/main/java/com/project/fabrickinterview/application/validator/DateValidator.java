package com.project.fabrickinterview.application.validator;

import com.project.fabrickinterview.domain.exception.DateFormatException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Component
public class DateValidator {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public boolean isValid(String field) {
        boolean isValid = false;
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(field);
            isValid = true;
        } catch (ParseException | NullPointerException ignored) {
        }
        return Objects.nonNull(field) && isValid;
    }

    public boolean isValidRange(String from, String to) {
        try {
            simpleDateFormat.setLenient(false);
            return simpleDateFormat.parse(from).before(simpleDateFormat.parse(to));
        } catch(ParseException | NullPointerException e) {
            throw new DateFormatException();
        }
    }
}
