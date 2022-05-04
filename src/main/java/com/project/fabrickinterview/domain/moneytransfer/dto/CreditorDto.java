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

    @NotNull
    AccountDto account;

    @NotNull
    AddressDto address;
}
