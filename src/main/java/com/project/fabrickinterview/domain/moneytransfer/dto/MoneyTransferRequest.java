package com.project.fabrickinterview.domain.moneytransfer.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoneyTransferRequest {

    @NotNull
    CreditorDto creditor;

    @NotBlank
    String executionDate;

    String uri;

    @NotBlank
    String description;

    @Positive
    Double amount;

    @NotNull
    String currency;

    Boolean isUrgent;

    Boolean isInstant;

    String feeType;

    String feeAccountId;

    TaxReliefDto TaxReliefObject;
}
