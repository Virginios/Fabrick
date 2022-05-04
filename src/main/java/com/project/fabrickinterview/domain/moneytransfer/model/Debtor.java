package com.project.fabrickinterview.domain.moneytransfer.model;

import lombok.Value;

@Value
public class Debtor {
    String name;
    Account account;
}
