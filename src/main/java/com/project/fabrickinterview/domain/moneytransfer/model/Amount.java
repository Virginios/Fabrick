package com.project.fabrickinterview.domain.moneytransfer.model;

import lombok.Value;

@Value
public class Amount {
    Double debtorAmount;
    String debtorCurrency;
    Double creditorAmount;
    String creditorCurrency;
    String creditorCurrencyDate;
    Double exchangeRate;
}
