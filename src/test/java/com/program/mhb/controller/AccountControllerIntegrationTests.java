package com.program.mhb.controller;

import com.program.mhb.domain.Account;
import com.program.mhb.domain.Currency;
import com.program.mhb.domain.Status;
import com.program.mhb.dto.AccountViewDto;
import com.program.mhb.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@WebMvcTest(AccountController.class)
public class AccountControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService service;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {

        AccountViewDto account1 = new AccountViewDto(1, "ROINGBEURxxxxxxxxxxx11", Currency.EUR);
        AccountViewDto account2 = new AccountViewDto(1, "ROINGBEURxxxxxxxxxxx22", Currency.EUR);

        List<AccountViewDto> allAccounts = Arrays.asList(account1, account2);

        this.mockMvc.perform(get("/accounts")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(contains("HelloMock")));
    }
}
