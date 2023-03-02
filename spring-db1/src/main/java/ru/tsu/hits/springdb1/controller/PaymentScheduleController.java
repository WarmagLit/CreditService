package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tsu.hits.springdb1.entity.LoanRateEntity;
import ru.tsu.hits.springdb1.entity.PaymentScheduleEntity;
import ru.tsu.hits.springdb1.service.LoanRateService;
import ru.tsu.hits.springdb1.service.PaymentScheduleService;

import java.util.List;

@RestController // аннотация контроллера
@RequestMapping("/schedule") // роутинг
@RequiredArgsConstructor
public class PaymentScheduleController {

    private final PaymentScheduleService paymentScheduleService; // Сервис для работы с сущностями


    @GetMapping
    public List<PaymentScheduleEntity> getScheduleOfCredit(@RequestParam("creditId") String creditId) {
        return paymentScheduleService.getSchedule(creditId);
    }

}
