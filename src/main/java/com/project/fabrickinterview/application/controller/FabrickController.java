package com.project.fabrickinterview.application.controller;

import com.project.fabrickinterview.application.validator.DateValidator;
import com.project.fabrickinterview.domain.FabrickService;
import com.project.fabrickinterview.domain.transaction.dto.TransactionResponse;
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
    @GetMapping(value = "/transaction/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> getAllTransactions(@RequestParam String fromAccountingDate, @RequestParam String toAccountingDate) {
        return Optional.of(dateValidator.isValidRange(fromAccountingDate, toAccountingDate))
                .filter(bool -> bool)
                .map(b -> ResponseEntity.of(service.getAllTransactionsBetweenDate(accountId, fromAccountingDate, toAccountingDate)))
                .orElseThrow(DateRangeException::new);
    }

    @Operation(summary = "Get list of cash account")
    @ApiResponse(responseCode = "200", description = "List of all cash account has been returned")
    @GetMapping(value = "/transaction/saveAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveAllTransactions(@RequestParam String fromAccountingDate, @RequestParam String toAccountingDate) {
        Optional.of(dateValidator.isValidRange(fromAccountingDate, toAccountingDate))
                .filter(bool -> bool)
                .ifPresentOrElse(b -> service.saveTransactions(accountId, fromAccountingDate, toAccountingDate), DateRangeException::new);
        return ResponseEntity.ok().build();
    }

}
