package com.project.fabrickinterview.domain.transaction.model;

import lombok.Value;

@Value
public class Transaction {
    String transactionId;
    String operationId;
    String accountingDate;
    String valueDate;
    String typeEnumeration;
    String typeValue;
    Integer amount;
    String currency;
    String description;
}
