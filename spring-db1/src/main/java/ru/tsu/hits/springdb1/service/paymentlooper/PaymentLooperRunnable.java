package ru.tsu.hits.springdb1.service.paymentlooper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tsu.hits.springdb1.entity.PaymentScheduleEntity;
import ru.tsu.hits.springdb1.repository.PaymentScheduleRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentLooperRunnable implements Runnable {

    Calendar cal = Calendar.getInstance();

    private static final int timeBetweenPaymentChecks = 3000;

    private final PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public void run() {
        while (true) {
            System.out.println("Checking Payments at: "+  Calendar.getInstance().getTime());
            checkPaymentSchedulesForPayments();
            try {
                Thread.sleep(timeBetweenPaymentChecks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkPaymentSchedulesForPayments() {
        LocalDate localDate = LocalDate.now();

        var optionalPayments = paymentScheduleRepository.findByPaymentday(localDate);

        if (optionalPayments.isPresent()) {
            var payments = optionalPayments.get();

            for (PaymentScheduleEntity payment: payments) {
                // TODO("Call through gRPC")
                if (!payment.getIsPayed()) {
                    boolean isPayed = true;
                    payment.setIsPayed(isPayed);
                    paymentScheduleRepository.save(payment);
                    System.out.println("Paying " + payment.getPaymentSum() + " for " + payment.getPaymentday().toString());
                }
            }
        }
    }
}