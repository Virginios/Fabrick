package com.project.fabrickinterview.infrastructure.mapper;

import com.project.fabrickinterview.IntegrationTest;
import com.project.fabrickinterview.domain.transaction.dto.TransactionDto;
import com.project.fabrickinterview.domain.transaction.dto.TypeDto;
import com.project.fabrickinterview.domain.transaction.model.Transaction;
import com.project.fabrickinterview.infrastructure.entity.TransactionEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TransactionMapperIT extends IntegrationTest {

    @Autowired
    TransactionMapper mapper;

    @Test
    public void shouldMapADtoInADomainObject() {
        TransactionDto dto = getTransactionDto();
        Transaction transaction = mapper.dtoToDomain(dto);

        assertEquals(dto.getTransactionId(), transaction.getTransactionId());
        assertEquals(dto.getOperationId(), transaction.getOperationId());
        assertEquals(dto.getAccountingDate(), transaction.getAccountingDate());
        assertEquals(dto.getValueDate(), transaction.getValueDate());
        assertEquals(dto.getType().getEnumeration(), transaction.getTypeEnumeration());
        assertEquals(dto.getType().getValue(), transaction.getTypeValue());
        assertEquals(dto.getAmount(), transaction.getAmount());
        assertEquals(dto.getCurrency(), transaction.getCurrency());
        assertEquals(dto.getDescription(), transaction.getDescription());
    }

    @Test
    public void shouldMapADtoInADomainObjectWithoutTypeFields() {
        TransactionDto dto = getTransactionDto();
        dto.getType().setValue(null);
        dto.getType().setEnumeration(null);
        Transaction transaction = mapper.dtoToDomain(dto);


        TransactionDto dto1 = getTransactionDto();
        dto1.setType(null);
        Transaction transaction1 = mapper.dtoToDomain(dto1);

        assertNull(transaction.getTypeEnumeration());
        assertNull(transaction.getTypeValue());
        assertNull(transaction1.getTypeValue());
        assertNull(transaction1.getTypeValue());
    }

    @Test
    public void shouldMapADomainObjectToEntity() {
        TransactionDto dto = getTransactionDto();
        Transaction transaction = mapper.dtoToDomain(dto);
        TransactionEntity transactionEntity = mapper.domainToEntity(transaction);

        assertEquals(transaction.getTransactionId(), transactionEntity.getTransactionId());
        assertEquals(transaction.getOperationId(), transactionEntity.getOperationId());
        assertEquals(transaction.getAccountingDate(), transactionEntity.getAccountingDate());
        assertEquals(transaction.getValueDate(), transactionEntity.getValueDate());
        assertEquals(transaction.getTypeEnumeration(), transactionEntity.getTypeEnumeration());
        assertEquals(transaction.getTypeValue(), transactionEntity.getTypeValue());
        assertEquals(transaction.getAmount(), transactionEntity.getAmount());
        assertEquals(transaction.getCurrency(), transactionEntity.getCurrency());
        assertEquals(transaction.getDescription(), transactionEntity.getDescription());
    }

    public TransactionDto getTransactionDto() {
        TransactionDto dto = new TransactionDto();
        TypeDto typeDto = new TypeDto();
        typeDto.setEnumeration("enum");
        typeDto.setValue("val");
        dto.setTransactionId("1");
        dto.setOperationId("2");
        dto.setAccountingDate("aDate");
        dto.setValueDate("anotherDate");
        dto.setType(typeDto);
        dto.setAmount(12);
        dto.setCurrency("EUR");
        dto.setDescription("desc");
        return dto;
    }
}