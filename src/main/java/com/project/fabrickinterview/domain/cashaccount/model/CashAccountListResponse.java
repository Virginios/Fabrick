package com.project.fabrickinterview.domain.cashaccount.model;

import lombok.Value;

@Value
public class CashAccountListResponse {
    String status;
    Object[] error;
    ListResponse payload;
}
