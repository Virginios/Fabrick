package com.project.fabrickinterview.domain.moneytransfer.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AmountDto {
    Double debtorAmount;
    String debtorCurrency;
    Double creditorAmount;
    String creditorCurrency;
    String creditorCurrencyDate;
    Double exchangeRate;
}
