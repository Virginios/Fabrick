package com.project.fabrickinterview.infrastructure.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "transaction")
//@Table(name = "transaction", indexes = { @Index(name = "idx_unique_transaction_id", columnList = "transaction_id")})

public class TransactionEntity {

    @Id
    @Column
    String transactionId;

    @Column
    String operationId;

    @Column
    String accountingDate;

    @Column
    String valueDate;

    @Column
    Integer amount;

    @Column
    String currency;

    @Column
    String description;

    @Column
    String typeEnumeration;

    @Column
    String typeValue;

}
