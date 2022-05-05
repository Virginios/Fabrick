package com.project.fabrickinterview.domain.transaction.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Value;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {
    String status;
    Object[] error;
    ListResponseDto payload;
}
