package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tsu.hits.springdb1.dto.CreditByLoanRateDto;
import ru.tsu.hits.springdb1.dto.CreditInfoDto;
import ru.tsu.hits.springdb1.dto.CustomCreditDto;
import ru.tsu.hits.springdb1.entity.CreditEntity;
import ru.tsu.hits.springdb1.entity.PaymentScheduleEntity;
import ru.tsu.hits.springdb1.exception.ApiRequestException;
import ru.tsu.hits.springdb1.repository.CreditRepository;
import ru.tsu.hits.springdb1.repository.LoanRateRepository;
import ru.tsu.hits.springdb1.repository.PaymentScheduleRepository;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;

    private final LoanRateRepository loanRateRepository;

    private final PaymentScheduleRepository paymentScheduleRepository;

    public Iterable<CreditEntity> getAllCredits() {
        var entities = creditRepository.findAll();
        return entities;
    }

    public CreditEntity addCreditAndAccount(CreditInfoDto creditInfoDto) {

        // TODO("Add account through gRPC")
        var accountNumber = "124";

        var dto = new CustomCreditDto(
                creditInfoDto.getClientId(),
                accountNumber,
                creditInfoDto.getInterestRate(),
                creditInfoDto.getLoanAmount(),
                creditInfoDto.getLoanMonthPeriod()
        );

        return addCustomCredit(dto);
    }

    public CreditEntity addCustomCredit(CustomCreditDto customCreditDto) {
        var entity = new CreditEntity(
                UUID.randomUUID().toString(),
                customCreditDto.getClientId(),
                customCreditDto.getCreditAccountNumber(),
                customCreditDto.getInterestRate(),
                customCreditDto.getLoanAmount(),
                customCreditDto.getLoanMonthPeriod(),
                null
        );

        var savedEntity = creditRepository.save(entity);
        calculatePaymentSchedule(savedEntity);
        return savedEntity;
    }

    public CreditEntity addCreditByLoanRate(CreditByLoanRateDto creditByLoanRateDto) {
        System.out.println(creditByLoanRateDto.toString());
        var optionalLoanRate = loanRateRepository.findById(creditByLoanRateDto.getLoanRateId());

        if (optionalLoanRate.isEmpty()) {
            throw new ApiRequestException("No such loan rate");
        } else {
            var loanRate = optionalLoanRate.get();
            var entity = new CreditEntity(
                    UUID.randomUUID().toString(),
                    creditByLoanRateDto.getClientId(),
                    creditByLoanRateDto.getCreditAccountNumber(),
                    loanRate.getInterestRate(),
                    creditByLoanRateDto.getLoanAmount(),
                    loanRate.getLoanMonthPeriod(),
                    loanRate.getLoanRateId()
            );

            var savedEntity = creditRepository.save(entity);
            calculatePaymentSchedule(savedEntity);
            return savedEntity;
        }
    }

    public void changeCreditTerms(CreditEntity creditEntity) {
        paymentScheduleRepository.deleteByCreditId(creditEntity.getCreditId());

        var savedEntity = creditRepository.save(creditEntity);
        calculatePaymentSchedule(savedEntity);
    }

    private void calculatePaymentSchedule(CreditEntity credit) {
        LocalDate localDate = LocalDate.now();

        double creditMonthSum = credit.getLoanAmount() / credit.getLoanMonthPeriod();
        double monthInterestRate = (credit.getInterestRate() / 12) / 100;

        Double paidLoan = 0.0;
        for (int month = 0; month < credit.getLoanMonthPeriod(); month++) {
            // localDate = localDate.plusMonths(1);
            double paymentAmount = creditMonthSum + (credit.getLoanAmount() - paidLoan) * monthInterestRate;
            var schedule = new PaymentScheduleEntity(
                    UUID.randomUUID().toString(),
                    credit.getClientId(),
                    credit.getCreditAccountNumber(),
                    localDate,
                    paymentAmount,
                    creditMonthSum,
                    paymentAmount - creditMonthSum,
                    false
            );
            paidLoan += creditMonthSum;
            paymentScheduleRepository.save(schedule);
        }
    }
}
