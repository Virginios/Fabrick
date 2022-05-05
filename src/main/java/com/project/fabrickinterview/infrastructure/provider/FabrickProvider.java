package com.project.fabrickinterview.infrastructure.provider;

import com.project.fabrickinterview.domain.transaction.dto.TransactionResponse;
import com.project.fabrickinterview.domain.cashaccount.model.CashAccountResponse;
import com.project.fabrickinterview.domain.exception.MoneyTransferException;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferRequest;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FabrickProvider {

    private static final String BASE_PATH = "/api/gbs/banking/v4.0/accounts/";

    @Value("${fabrick.baseUrl}")
    private String baseUrl;

    @Value("${fabrick.authSchema}")
    private String authSchema;

    @Value("${fabrick.apiKey}")
    private String apiKey;


    private final WebClient.Builder webClientBuilder;


    public Optional<CashAccountResponse> getCashAccount(Long accountId) {
        return getWebClient()
                .get()
                .uri(BASE_PATH + accountId)
                .retrieve()
                .bodyToMono(CashAccountResponse.class)
                .doOnSubscribe(s -> log.debug("Calling Fabrick api to retrieve cash account for account id {}", accountId))
                .doOnSuccess(res -> log.debug("Fabrick api response <- ok, cash account found {}", res))
                .doOnError(throwable -> log.debug("Error while calling Fabrick api for account id {} {}", accountId, throwable.getMessage()))
                .blockOptional();
    }

    public Optional<TransactionResponse> getAllTransaction(Long accountId, String from, String to) {
        return getWebClient()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(BASE_PATH + accountId + "/transactions")
                        .queryParam("fromAccountingDate", from)
                        .queryParam("toAccountingDate", to)
                        .build())
                .retrieve()
                .bodyToMono(TransactionResponse.class)
                .doOnSubscribe(s -> log.debug("Calling Fabrick api to retrieve all cash accounts for account id {}", accountId))
                .doOnSuccess(res -> log.debug("Fabrick api response <- ok, cash account found {}", res))
                .doOnError(throwable -> log.debug("Error while calling Fabrick api for account id {}", accountId))
                .blockOptional();
    }

    public Optional<MoneyTransferResponse> saveMoneyTransfer(Long accountId, MoneyTransferRequest moneyTransferRequest) {
        return getWebClient()
                .post()
                .uri(BASE_PATH + accountId + "/payments/money-transfers")
                .bodyValue(moneyTransferRequest)
                .retrieve()
                .bodyToMono(MoneyTransferResponse.class)
                .doOnSubscribe(s -> log.debug("Calling Fabrick api to save money transfer {} for account id {}", moneyTransferRequest, accountId))
                .doOnSuccess(res -> log.debug("Fabrick api response <- ok, money transfer with id {} saved", res.getMoneyTransferId()))
                .onErrorMap(Throwable.class, throwable -> new MoneyTransferException())
                .blockOptional();
    }

    protected WebClient getWebClient() {
        return webClientBuilder
                .baseUrl(baseUrl)
                .defaultHeader("Auth-Schema", authSchema)
                .defaultHeader("Api-Key", apiKey)
                .defaultHeader("X-Time-Zone", "Europe/Rome") // TimeZone.getDeafult().getID() gives me Berlin i don't know why
                .build();
    }
}
