package com.project.fabrickinterview.domain.moneytransfer.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NaturalPersonBeneficiaryDto {
    @NotBlank
    String fiscalCode1;
    String fiscalCode2;
    String fiscalCode3;
    String fiscalCode4;
    String fiscalCode5;
}
