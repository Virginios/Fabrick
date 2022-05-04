package com.project.fabrickinterview.domain.moneytransfer.model;

import com.project.fabrickinterview.domain.moneytransfer.dto.AddressDto;
import lombok.Value;

@Value
public class Creditor {
    String name;
    Account account;
    AddressDto address;
}
