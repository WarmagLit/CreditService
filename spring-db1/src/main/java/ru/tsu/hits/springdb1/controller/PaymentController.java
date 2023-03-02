package ru.tsu.hits.springdb1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.springdb1.dto.CustomCreditDto;
import ru.tsu.hits.springdb1.entity.PaymentScheduleEntity;
import ru.tsu.hits.springdb1.service.PaymentScheduleService;
import ru.tsu.hits.springdb1.service.PaymentsService;

import java.util.List;

@RestController // аннотация контроллера
@RequestMapping("/payment") // роутинг
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentsService paymentsService;

    @PostMapping
    public void earlyRepayment(@RequestParam("creditId") String creditId, @RequestParam("paymentAmount") Double amount) {
        paymentsService.earlyRepayment(creditId, amount);
    }
}
