package com.project.fabrickinterview.domain.moneytransfer.model;

import lombok.Value;

@Value
public class TaxRelief {
    String taxReliefId;
    Boolean isCondoUpgrade;
    String creditorFiscalCode;
    String beneficiaryType;
    NaturalPersonBeneficiary naturalPersonBeneficiary;
    LegalPersonBeneficiary legalPersonBeneficiary;
}
