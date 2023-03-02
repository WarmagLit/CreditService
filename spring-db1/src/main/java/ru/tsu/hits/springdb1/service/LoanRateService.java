package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tsu.hits.springdb1.dto.LoanRateDto;
import ru.tsu.hits.springdb1.entity.CreditEntity;
import ru.tsu.hits.springdb1.entity.LoanRateEntity;
import ru.tsu.hits.springdb1.repository.CreditRepository;
import ru.tsu.hits.springdb1.repository.LoanRateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanRateService {

    private final LoanRateRepository loanRateRepository;

    public List<LoanRateEntity> getLoanRates() {
        List<LoanRateEntity> result = new ArrayList<LoanRateEntity>();
        var entities = loanRateRepository.findAll();
        entities.forEach(result::add);
        return result;
    }

    public LoanRateEntity addLoanRate(LoanRateDto loanRateDto) {
        var entity = new LoanRateEntity(
                UUID.randomUUID().toString(),
                loanRateDto.getName(),
                loanRateDto.getInterestRate(),
                loanRateDto.getLoanMonthPeriod()
        );

        var savedEntity = loanRateRepository.save(entity);
        return savedEntity;
    }
}
