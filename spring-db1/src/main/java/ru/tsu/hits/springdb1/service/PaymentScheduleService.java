package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tsu.hits.springdb1.entity.PaymentScheduleEntity;
import ru.tsu.hits.springdb1.repository.PaymentScheduleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    public List<PaymentScheduleEntity> getSchedule(String creditId) {
        var schedules = paymentScheduleRepository.findByCreditId(creditId);

        if (!schedules.isPresent()) {
            return Collections.emptyList();
        }
        var iter = schedules.get();

        List<PaymentScheduleEntity> result = new ArrayList<PaymentScheduleEntity>();
        iter.forEach(result::add);
        return result;
    }

}
