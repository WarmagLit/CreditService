package ru.tsu.hits.springdb1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.CreditEntity;
import ru.tsu.hits.springdb1.entity.LoanRateEntity;

public interface LoanRateRepository extends CrudRepository<LoanRateEntity, String> {

}