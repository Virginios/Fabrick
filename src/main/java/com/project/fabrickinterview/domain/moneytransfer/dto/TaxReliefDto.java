package com.project.fabrickinterview.domain.moneytransfer.dto;

import com.project.fabrickinterview.domain.moneytransfer.dto.LegalPersonBeneficiaryDto;
import com.project.fabrickinterview.domain.moneytransfer.dto.NaturalPersonBeneficiaryDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaxReliefDto {

    String taxReliefId;

    @NotNull
    Boolean isCondoUpgrade;

    @NotBlank
    String creditorFiscalCode;

    @NotBlank
    String beneficiaryType;

    NaturalPersonBeneficiaryDto naturalPersonBeneficiary;

    LegalPersonBeneficiaryDto legalPersonBeneficiary;
}
