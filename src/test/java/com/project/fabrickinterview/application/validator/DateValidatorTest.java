package com.project.fabrickinterview.application.validator;

import com.project.fabrickinterview.domain.exception.DateFormatException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DateValidatorTest {

    private DateValidator sut;

    @Before
    public void setup() {
        sut = new DateValidator();
    }

    @Test
    public void isValidShouldReturnTrue() {
        String date = "2019-12-01";
        assertTrue(sut.isValid(date));
    }

    @Test
    public void isValidShouldReturnFalseWhenDayIsIncorrect() {
        String date = "2019-12-99";
        assertFalse(sut.isValid(date));
    }

    @Test
    public void isValidShouldReturnFalseWhenMonthIsIncorrect() {
        String date = "2019-58-01";
        assertFalse(sut.isValid(date));
    }

    @Test
    public void isValidShouldReturnFalseWhenDateIsNull() {
        assertFalse(sut.isValid(null));
    }

    @Test
    public void isValidShouldReturnFalseWhenDateIsMalformed() {
        assertFalse(sut.isValid("2019-12.01"));
        assertFalse(sut.isValid("2019/12/01"));
        assertFalse(sut.isValid("01/12/2019"));
    }

    @Test
    public void isValidRangeShouldReturnTrue() {
        assertTrue(sut.isValidRange("2019-11-01", "2019-12-01"));
    }

    @Test
    public void isValidRangeShouldReturnFalseWhenFromIsAfterTo() {
        assertFalse(sut.isValidRange("2019-12-01", "2019-11-01"));
    }

    @Test
    public void isValidRangeShouldThrowsDateFormatException() {
        Exception exception = assertThrows(DateFormatException.class, () ->
            sut.isValidRange("2019-12.01", "2019-11-01"));
        Exception exception1 = assertThrows(DateFormatException.class, () ->
                sut.isValidRange(null, "2019-11-01"));
        assertEquals("Formato data non valido", exception.getMessage());
        assertEquals("Formato data non valido", exception1.getMessage());
    }

}