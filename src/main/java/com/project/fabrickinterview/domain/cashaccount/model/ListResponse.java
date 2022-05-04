package com.project.fabrickinterview.domain.cashaccount.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListResponse {
    List<TransactionDto> list;
}
