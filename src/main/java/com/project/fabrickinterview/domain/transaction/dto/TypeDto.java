package com.project.fabrickinterview.domain.transaction.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeDto {
    String enumeration;
    String value;
}
