package com.project.fabrickinterview.domain.cashaccount.model;

import lombok.Value;

@Value
public class CashAccountResponse {
    String status;
    Object[] error;
    CashAccountDto payload;
}
