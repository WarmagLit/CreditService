package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tsu.hits.springdb1.dto.CustomCreditDto;
import ru.tsu.hits.springdb1.entity.CreditEntity;
import ru.tsu.hits.springdb1.entity.PaymentScheduleEntity;
import ru.tsu.hits.springdb1.exception.ApiRequestException;
import ru.tsu.hits.springdb1.repository.CreditRepository;
import ru.tsu.hits.springdb1.repository.PaymentScheduleRepository;
import ru.tsu.hits.springdb1.service.paymentlooper.PaymentLooper;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    private final CreditRepository creditRepository;

    private final CreditService creditService;

    PaymentLooper looper;

    @PostConstruct
    private void initBlock() {
        looper = new PaymentLooper(paymentScheduleRepository);
        looper.initializeLoop();
    }

    public void earlyRepayment(String creditId, Double amount) {

        var optionalCredit = creditRepository.findById(creditId);

        if (optionalCredit.isPresent()) {
            Double fullDebtSum = optionalCredit.get().getLoanAmount();

            var payments =  getPayments(creditId);
            var payedPayments = getPaidLoans(payments);

            Double paidAmount = 0.0;
            for (PaymentScheduleEntity payment : payedPayments) {
                amount += payment.getPaymentOfMainDebtSum();
            }

            Double newDebtSum = fullDebtSum - paidAmount;

            var recalculatedCredit = new CreditEntity(
                    optionalCredit.get().getCreditId(),
                    optionalCredit.get().getClientId(),
                    optionalCredit.get().getCreditAccountNumber(),
                    optionalCredit.get().getInterestRate(),
                    newDebtSum,
                    payments.size() - payedPayments.size(),
                    optionalCredit.get().getLoanRateId()
            );
            creditService.changeCreditTerms(recalculatedCredit);
        } else {
            throw new ApiRequestException("No such credit");
        }
    }

    private List<PaymentScheduleEntity> getPaidLoans(List<PaymentScheduleEntity> payments) {
        var paidPayments = payments.stream().filter(PaymentScheduleEntity::getIsPayed);
        return paidPayments.collect(Collectors.toList());
    }

    private List<PaymentScheduleEntity> getPayments(String creditId) {
        var optionalSchedule = paymentScheduleRepository.findByCreditId(creditId);
        if (optionalSchedule.isPresent()) {
            return optionalSchedule.get();
        } else {
            throw new ApiRequestException("No such payments");
        }
    }
}
