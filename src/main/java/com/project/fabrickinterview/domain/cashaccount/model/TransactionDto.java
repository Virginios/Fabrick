package com.project.fabrickinterview.domain.cashaccount.model;

import lombok.Value;

@Value
public class TransactionDto {
    String transactionId;
    String operationId;
    String accountingDate;
    String valueDate;
    TypeDto type;
    Integer amount;
    String currency;
    String description;
}
