package com.project.fabrickinterview.domain.cashaccount.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CashAccountDto {

    String accountId;
    String iban;
    String abiCode;
    String cabCode;
    String countryCode;
    String internationalCin;
    String nationalCin;
    String account;
    String alias;
    String productName;
    String holderName;
    String activatedDate;
    String currency;
}
