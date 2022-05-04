package com.project.fabrickinterview.domain.moneytransfer.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorDto {
    String code;
    String description;
}
