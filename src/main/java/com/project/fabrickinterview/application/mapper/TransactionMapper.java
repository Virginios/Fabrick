package com.project.fabrickinterview.application.mapper;

import com.project.fabrickinterview.domain.transaction.dto.TransactionDto;
import com.project.fabrickinterview.domain.transaction.dto.TypeDto;
import com.project.fabrickinterview.domain.transaction.model.Transaction;
import com.project.fabrickinterview.infrastructure.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Mapper(componentModel = "spring")
@Component
public interface TransactionMapper {

    @Mappings({
            @Mapping(target = "typeEnumeration", expression = "java(setTypeEnumeration(dto.getType()))"),
            @Mapping(target = "typeValue", expression = "java(setTypeValue(dto.getType()))")
    })
    Transaction dtoToDomain(TransactionDto dto);

    @Mappings({})
    TransactionEntity domainToEntity(Transaction transaction);

    default String setTypeEnumeration(TypeDto type) {
        return Optional.ofNullable(type)
                .map(TypeDto::getEnumeration)
                .orElse(null);
    }

    default String setTypeValue(TypeDto type) {
        return Optional.ofNullable(type)
                .map(TypeDto::getValue)
                .orElse(null);
    }
}
