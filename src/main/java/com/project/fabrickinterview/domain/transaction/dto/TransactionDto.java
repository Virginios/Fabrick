package com.project.fabrickinterview.domain.transaction.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
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
