package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.LoanRateDto;
import ru.tsu.hits.springdb1.entity.CreditEntity;
import ru.tsu.hits.springdb1.entity.LoanRateEntity;
import ru.tsu.hits.springdb1.service.CreditService;
import ru.tsu.hits.springdb1.service.LoanRateService;

import java.util.List;

@RestController // аннотация контроллера
@RequestMapping("/loan-rate") // роутинг
@RequiredArgsConstructor
public class LoanRateController {

    private final LoanRateService loanRateService; // Сервис для работы с сущностями

    @GetMapping
    public List<LoanRateEntity> getLoanRates() {
        return loanRateService.getLoanRates();
    }

    @PostMapping
    public LoanRateEntity addLoanRate(@RequestBody LoanRateDto loanRateDto) {
        return loanRateService.addLoanRate(loanRateDto);
    }
}
