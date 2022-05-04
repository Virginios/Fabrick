package com.project.fabrickinterview.domain.cashaccount;

import com.project.fabrickinterview.domain.cashaccount.model.CashAccountListResponse;
import com.project.fabrickinterview.domain.cashaccount.model.CashAccountResponse;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferRequest;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferResponse;
import com.project.fabrickinterview.infrastructure.provider.FabrickProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FabrickService {

    private final FabrickProvider fabrickProvider;

    public Optional<CashAccountResponse> getCashAccount(Long accountId) {
        return fabrickProvider.getCashAccount(accountId);
    }

    public Optional<MoneyTransferResponse> saveMoneyTransfer(Long accountId, MoneyTransferRequest moneyTransferRequest) {
        return fabrickProvider.saveMoneyTransfer(accountId, moneyTransferRequest);
    }

    public Optional<CashAccountListResponse> getAllTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) {
        return fabrickProvider.getAllCashAccounts(accountId, fromAccountingDate, toAccountingDate);
    }
}
