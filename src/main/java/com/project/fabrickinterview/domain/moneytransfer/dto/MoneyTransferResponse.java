package com.project.fabrickinterview.domain.moneytransfer.dto;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MoneyTransferResponse {
    String moneyTransferId;
    String status;
    String direction;
    CreditorDto creditor;
    DebtorDto debtor;
    String cro;
    String uri;
    String trn;
    String description;
    String createdDatetime;
    String accountedDatetime;
    String debtorValueDate;
    String creditorValueDate;
    AmountDto amount;
    Boolean isUrgent;
    Boolean isInstant;
    String feeType;
    String feeAccountId;
    ArrayList<Object> fees = new ArrayList<>();
    Boolean hasTaxRelief;
}
