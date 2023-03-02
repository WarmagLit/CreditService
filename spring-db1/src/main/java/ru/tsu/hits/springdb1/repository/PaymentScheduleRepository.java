package ru.tsu.hits.springdb1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.LoanRateEntity;
import ru.tsu.hits.springdb1.entity.PaymentScheduleEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PaymentScheduleRepository extends CrudRepository<PaymentScheduleEntity, String> {
    Optional<List<PaymentScheduleEntity>> findByCreditId(String creditId);
    void deleteByCreditId(String creditId);
    Optional<List<PaymentScheduleEntity>> findByPaymentday(LocalDate day);
}