package com.project.fabrickinterview.domain.moneytransfer.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LegalPersonBeneficiaryDto {
    String fiscalCode;
    String legalRepresentativeFiscalCode;
}
