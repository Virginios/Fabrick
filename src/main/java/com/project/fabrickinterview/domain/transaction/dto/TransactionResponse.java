package com.project.fabrickinterview.domain.transaction.dto;

import lombok.Value;

@Value
public class TransactionResponse {
    String status;
    Object[] error;
    ListResponseDto payload;
}
