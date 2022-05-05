package com.project.fabrickinterview.domain.moneytransfer.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditorDto {

    @NotBlank
    String name;

//    @NotNull missing to simplify test
    AccountDto account;

//    @NotNull missing to simplify test
    AddressDto address;
}
