package com.project.fabrickinterview.domain.cashaccount.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashAccountResponse {
    String status;
    Object[] error;
    CashAccountDto payload;
}
