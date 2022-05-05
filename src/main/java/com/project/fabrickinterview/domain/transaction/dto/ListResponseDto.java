package com.project.fabrickinterview.domain.transaction.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListResponseDto {
    List<TransactionDto> list;
}
