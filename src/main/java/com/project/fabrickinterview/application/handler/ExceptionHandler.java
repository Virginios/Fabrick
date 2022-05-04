package com.project.fabrickinterview.application.handler;

import com.project.fabrickinterview.domain.exception.DateFormatException;
import com.project.fabrickinterview.domain.exception.DateRangeException;
import com.project.fabrickinterview.domain.exception.MoneyTransferException;
import com.project.fabrickinterview.domain.moneytransfer.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static com.project.fabrickinterview.application.controller.FabrickController.accountId;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(MoneyTransferException e) {
        String description = String.format("Errore tenico  La condizione BP049 non è prevista per il conto id %s", accountId);
        return ResponseEntity.internalServerError().body(ErrorDto
                .builder()
                .code("API000")
                .description(description)
                .build());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(DateFormatException e) {
        return ResponseEntity.badRequest().body(ErrorDto
                .builder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description(e.getMessage())
                .build());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ErrorDto> handleException(DateRangeException e) {
        return ResponseEntity.badRequest().body(ErrorDto
                .builder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description(e.getMessage())
                .build());
    }
}
