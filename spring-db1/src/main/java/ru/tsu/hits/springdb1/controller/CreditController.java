package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import ru.tsu.hits.springdb1.dto.CreditByLoanRateDto;
import ru.tsu.hits.springdb1.dto.CustomCreditDto;
import ru.tsu.hits.springdb1.entity.CreditEntity;
import ru.tsu.hits.springdb1.service.CreditService;
import ru.tsu.hits.springdb1.service.webservice.CreateAccountWebService;

import java.util.List;

@RestController // аннотация контроллера
@RequestMapping("/credit") // роутинг
@RequiredArgsConstructor // конструктор для final полей класса, генерируемый с помощью Lombok
public class CreditController {

    private final CreditService creditService; // Сервис для работы с сущностями

    @GetMapping
    // public Iterable<CreditEntity> getAllCredits() {
    public String getAllCredits() {
        /*
        WebClient client = WebClient.create();

        WebClient.ResponseSpec responseSpec = client.get()
                .uri("http://localhost:8080/accounts/open-credit-account/123")
                .retrieve();

        return responseSpec.bodyToMono(String.class).block();*/
        return new CreateAccountWebService().createCreditAccount();
        // return creditService.getAllCredits();
    }

    @PostMapping("/by-loan-rate")
    public CreditEntity createCreditByLoanRate(@RequestBody CreditByLoanRateDto creditByLoanRateDto) {
        return creditService.addCreditByLoanRate(creditByLoanRateDto);
    }

    @PostMapping
    public CreditEntity createCustomCredit(@RequestBody CustomCreditDto customCreditDto) {
        return creditService.addCustomCredit(customCreditDto);
    }

    @PostMapping("/account")
    public CreditEntity createCustomCreditAndAccount(@RequestBody CustomCreditDto customCreditDto) {
        return creditService.addCustomCredit(customCreditDto);
    }
}
