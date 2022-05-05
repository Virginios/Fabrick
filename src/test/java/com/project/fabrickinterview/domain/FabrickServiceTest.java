package com.project.fabrickinterview.domain;

import com.project.fabrickinterview.domain.cashaccount.model.CashAccountResponse;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferRequest;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferResponse;
import com.project.fabrickinterview.domain.transaction.dto.ListResponseDto;
import com.project.fabrickinterview.domain.transaction.dto.TransactionDto;
import com.project.fabrickinterview.domain.transaction.dto.TransactionResponse;
import com.project.fabrickinterview.infrastructure.mapper.TransactionMapper;
import com.project.fabrickinterview.infrastructure.provider.FabrickProvider;
import com.project.fabrickinterview.infrastructure.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FabrickServiceTest {

    @Mock
    private FabrickProvider fabrickProvider;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private TransactionMapper transactionMapper;

    private FabrickService sut;

    @Before
    public void setup() {
        sut = new FabrickService(fabrickProvider, transactionRepository, transactionMapper);
    }

    @Test
    public void getCashAccountShouldReturnAnOptionalResponse() {
        CashAccountResponse mock = mock(CashAccountResponse.class);
        when(fabrickProvider.getCashAccount(any(Long.class))).thenReturn(Optional.of(mock));
        Optional<CashAccountResponse> cashAccount = sut.getCashAccount(7654L);

        assertNotNull(cashAccount);
        assertEquals(mock, cashAccount.get());
        verify(fabrickProvider, times(1)).getCashAccount(7654L);
    }

    @Test
    public void saveMoneyTransferShouldReturnAnOptionalResponse() {
        MoneyTransferResponse mock = mock(MoneyTransferResponse.class);
        when(fabrickProvider.saveMoneyTransfer(any(Long.class), any(MoneyTransferRequest.class))).thenReturn(Optional.of(mock));
        Optional<MoneyTransferResponse> moneyTransferResponse = sut.saveMoneyTransfer(7654L, new MoneyTransferRequest());

        assertNotNull(moneyTransferResponse);
        assertEquals(mock, moneyTransferResponse.get());
        verify(fabrickProvider, times(1)).saveMoneyTransfer(any(), any());
    }

    @Test
    public void getAllTransactionBeetweenDateShouldReturnAnOptionalResponse() {
        TransactionResponse mock = mock(TransactionResponse.class);
        when(fabrickProvider.getAllTransaction(any(Long.class), anyString(), anyString())).thenReturn(Optional.of(mock));
        Optional<TransactionResponse> transactionResponse = sut.getAllTransactionsBetweenDate(7654L, "", "");

        assertNotNull(transactionResponse);
        assertEquals(mock, transactionResponse.get());
        verify(fabrickProvider, times(1)).getAllTransaction(any(), anyString(), anyString());
    }

    @Test
    public void saveTransactionShouldSaveElements() {
        TransactionResponse response = new TransactionResponse();
        ListResponseDto listResponseDto = new ListResponseDto();
        listResponseDto.setList(List.of(mock(TransactionDto.class), mock(TransactionDto.class)));
        response.setPayload(listResponseDto);
        when(fabrickProvider.getAllTransaction(any(Long.class), anyString(), anyString())).thenReturn(Optional.of(response));

        sut.saveTransactions(7654L, "", "");

        verify(fabrickProvider, times(1)).getAllTransaction(any(), anyString(), anyString());
        verify(transactionMapper, times(2)).dtoToDomain(any());
        verify(transactionRepository, times(1)).saveAll(any());
    }

    @Test
    public void saveTransactionShouldNotSaveElements() {
        when(fabrickProvider.getAllTransaction(any(Long.class), anyString(), anyString())).thenReturn(Optional.empty());

        sut.saveTransactions(7654L, "", "");

        verify(fabrickProvider, times(1)).getAllTransaction(any(), anyString(), anyString());
        verifyNoInteractions(transactionMapper);
        verifyNoInteractions(transactionRepository);
    }

}