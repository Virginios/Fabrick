package com.project.fabrickinterview.domain;

import com.project.fabrickinterview.application.mapper.TransactionMapper;
import com.project.fabrickinterview.domain.transaction.dto.ListResponseDto;
import com.project.fabrickinterview.domain.transaction.dto.TransactionResponse;
import com.project.fabrickinterview.domain.cashaccount.model.CashAccountResponse;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferRequest;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferResponse;
import com.project.fabrickinterview.domain.transaction.model.Transaction;
import com.project.fabrickinterview.infrastructure.entity.TransactionEntity;
import com.project.fabrickinterview.infrastructure.provider.FabrickProvider;
import com.project.fabrickinterview.infrastructure.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FabrickService {

    private final FabrickProvider fabrickProvider;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public Optional<CashAccountResponse> getCashAccount(Long accountId) {
        return fabrickProvider.getCashAccount(accountId);
    }

    public Optional<MoneyTransferResponse> saveMoneyTransfer(Long accountId, MoneyTransferRequest moneyTransferRequest) {
        return fabrickProvider.saveMoneyTransfer(accountId, moneyTransferRequest);
    }

    public Optional<TransactionResponse> getAllTransactionsBetweenDate(Long accountId, String fromAccountingDate, String toAccountingDate) {
        return fabrickProvider.getAllTransaction(accountId, fromAccountingDate, toAccountingDate);
    }

    public void saveTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) {
        getAllTransactionsBetweenDate(accountId, fromAccountingDate, toAccountingDate)
                .map(TransactionResponse::getPayload)
                .map(ListResponseDto::getList)
                .ifPresent(list -> saveAll(list
                        .stream()
                        .map(transactionMapper::dtoToDomain)
                        .collect(Collectors.toList())));
    }

    private void saveAll(List<Transaction> transactionList) {
        List<TransactionEntity> transactionEntities = transactionList.stream().map(transactionMapper::domainToEntity).collect(Collectors.toList());
        transactionRepository.saveAll(transactionEntities);
    }
}
