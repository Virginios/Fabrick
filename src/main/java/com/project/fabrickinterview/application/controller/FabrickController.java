package com.project.fabrickinterview.application.controller;

import com.project.fabrickinterview.application.validator.DateValidator;
import com.project.fabrickinterview.domain.cashaccount.FabrickService;
import com.project.fabrickinterview.domain.cashaccount.model.CashAccountListResponse;
import com.project.fabrickinterview.domain.cashaccount.model.CashAccountResponse;
import com.project.fabrickinterview.domain.exception.DateFormatException;
import com.project.fabrickinterview.domain.exception.DateRangeException;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferRequest;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@ControllerAdvice
@RequestMapping("/fabrick")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FabrickController {

    public static final Long accountId = 14537780L;

    private final FabrickService service;
    private final DateValidator dateValidator;

    @Operation(summary = "Get a cash account for the given account id")
    @ApiResponse(responseCode = "200", description = "The cash account has been found")
    @GetMapping(value = "/cashaccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CashAccountResponse> getCashAccountByAccountId() {
        return ResponseEntity.of(service.getCashAccount(accountId));
    }

    @Operation(summary = "Creates a new money transfer")
    @ApiResponse(responseCode = "500", description = "The money transfer has not been created because of an error")
    @ApiResponse(responseCode = "400", description = "Some fields in the request were not accepted")
    @PostMapping(value = "/money-transfer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MoneyTransferResponse> createMoneyTransfer(@RequestBody @Valid MoneyTransferRequest moneyTransferRequest) {
        return Optional.of(moneyTransferRequest)
                .map(MoneyTransferRequest::getExecutionDate)
                .map(dateValidator::isValid)
                .filter(bool -> bool)
                .map(b -> ResponseEntity.of(service.saveMoneyTransfer(accountId, moneyTransferRequest)))
                .orElseThrow(DateFormatException::new);
    }

    @Operation(summary = "Get list of cash account")
    @ApiResponse(responseCode = "200", description = "List of all cash account has been returned")
    @GetMapping(value = "/cashaccount/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CashAccountListResponse> getAllTransactions(@RequestParam String fromAccountingDate, @RequestParam String toAccountingDate) {
        return Optional.of(dateValidator.isValidRange(fromAccountingDate, toAccountingDate))
                .filter(bool -> bool)
                .map(b -> ResponseEntity.of(service.getAllTransactions(accountId, fromAccountingDate, toAccountingDate)))
                .orElseThrow(DateRangeException::new);
    }

}
