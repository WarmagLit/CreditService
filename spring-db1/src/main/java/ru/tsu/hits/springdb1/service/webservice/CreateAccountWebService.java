package ru.tsu.hits.springdb1.service.webservice;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tsu.hits.springdb1.dto.AccountDto;
import ru.tsu.hits.springdb1.dto.AccountType;
import ru.tsu.hits.springdb1.dto.UserDto;

import java.math.BigDecimal;

public class CreateAccountWebService {
/*
    public String getAccountById() {
        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec = client.get()
                .uri("http://localhost:8080/accounts")
                .retrieve();
    }*/

    public String createCreditAccount() {
        WebClient client = WebClient.create();

        AccountDto accountDto = new AccountDto(
                1234L,
                new UserDto( 123L),
                "rub",
                new BigDecimal("0.0"),
                false,
                AccountType.CREDIT
        );

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("id", accountDto.getId().toString());
        formData.add("user", "user: { \"id\": \"123\"}");
        formData.add("currencyCode", accountDto.getCurrencyCode().toString());
        formData.add("balance", accountDto.getBalance().toString());
        formData.add("isClosed", "false");
        formData.add("accountType", accountDto.getAccountType().toString());

        WebClient.ResponseSpec responseSpec = client.post()
                .uri("http://localhost:8080/accounts")
                .body(BodyInserters.fromFormData(formData))
                .retrieve();

        return responseSpec.bodyToMono(String.class).block();
    }
}
