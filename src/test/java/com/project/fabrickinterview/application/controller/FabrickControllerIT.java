package com.project.fabrickinterview.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.project.fabrickinterview.IntegrationTest;
import com.project.fabrickinterview.domain.moneytransfer.dto.CreditorDto;
import com.project.fabrickinterview.domain.moneytransfer.dto.MoneyTransferRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FabrickControllerIT extends IntegrationTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void shouldCallGetCashAccountById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/fabrick/cashaccount").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCallMoneyTransferAndRetrieve400() throws Exception {

        MoneyTransferRequest moneyTransferRequest = new MoneyTransferRequest();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(moneyTransferRequest);

        mvc.perform(MockMvcRequestBuilders.post("/fabrick/money-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCallMoneyTransferAndRetrieve500() throws Exception {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(moneyTransferRequest());

        mvc.perform(MockMvcRequestBuilders.post("/fabrick/money-transfer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().is(500))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCallGetAllTransactionAndRetrieve400DueDateFormat() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/fabrick/transaction/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("fromAccountingDate", "2019-11-01")
                        .queryParam("toAccountingDate", "2019-08-01"))
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCallGetAllTransactionAndRetrieve200() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/fabrick/transaction/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("fromAccountingDate", "2019-08-01")
                        .queryParam("toAccountingDate", "2019-11-01"))
                .andExpect(status().is(200))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCallSaveAllTransactionAndRetrieve200() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/fabrick/transaction/saveAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("fromAccountingDate", "2019-08-01")
                        .queryParam("toAccountingDate", "2019-11-01"))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldCallSaveAllTransactionAndRetrieve400DueDateFormat() throws Exception {

        mvc.perform(MockMvcRequestBuilders.get("/fabrick/transaction/saveAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("fromAccountingDate", "2019-11-01")
                        .queryParam("toAccountingDate", "2019-08-01"))
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    public MoneyTransferRequest moneyTransferRequest() {
        MoneyTransferRequest request = new MoneyTransferRequest();
        CreditorDto creditorDto = new CreditorDto();
        creditorDto.setName("");
        request.setCreditor(creditorDto);
        request.setExecutionDate("2019-02-02");
        request.setDescription("desc");
        request.setCurrency("curr");
        request.setAmount(2.0);
        return request;
    }
}