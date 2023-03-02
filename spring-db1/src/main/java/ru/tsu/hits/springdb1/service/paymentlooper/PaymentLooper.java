package ru.tsu.hits.springdb1.service.paymentlooper;

import ru.tsu.hits.springdb1.repository.PaymentScheduleRepository;

import java.util.Calendar;

public class PaymentLooper {

    PaymentScheduleRepository repository;

    public PaymentLooper(PaymentScheduleRepository repository) {
        this.repository = repository;
    }
    Thread loopThread;

    public void initializeLoop() {
        loopThread = new Thread(new PaymentLooperRunnable(repository));
        loopThread.start();
    }
}
