package com.project.fabrickinterview.domain;

import com.project.fabrickinterview.IntegrationTest;
import com.project.fabrickinterview.domain.transaction.dto.ListResponseDto;
import com.project.fabrickinterview.domain.transaction.dto.TransactionDto;
import com.project.fabrickinterview.domain.transaction.dto.TransactionResponse;
import com.project.fabrickinterview.domain.transaction.dto.TypeDto;
import com.project.fabrickinterview.infrastructure.mapper.TransactionMapper;
import com.project.fabrickinterview.infrastructure.provider.FabrickProvider;
import com.project.fabrickinterview.infrastructure.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class FabrickServiceIT extends IntegrationTest {

    @MockBean
    FabrickProvider provider;

    @Autowired
    TransactionRepository repository;

    @Autowired
    FabrickService fabrickService;

    @Test
    public void shouldCorrectlySaveTransactionInDb() {
        TransactionResponse response = new TransactionResponse();
        ListResponseDto dtos = new ListResponseDto();
        dtos.setList(List.of(getTransactionDto("1"), getTransactionDto("2"), getTransactionDto("3")));
        response.setPayload(dtos);

        when(fabrickService.getAllTransactionsBetweenDate(any(), any(), any())).thenReturn(Optional.of(response));
        fabrickService.saveTransactions(65L, "", "");

        Assert.assertEquals(3, repository.findAll().size());
    }

    @Test
    public void shouldNotSaveAnyTransactionInDb() {
        when(fabrickService.getAllTransactionsBetweenDate(any(), any(), any())).thenReturn(Optional.empty());
        fabrickService.saveTransactions(65L, "", "");

        Assert.assertEquals(0, repository.findAll().size());
    }


    public TransactionDto getTransactionDto(String id) {
        TransactionDto dto = new TransactionDto();
        TypeDto typeDto = new TypeDto();
        typeDto.setEnumeration("enum");
        typeDto.setValue("val");
        dto.setTransactionId(id);
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
