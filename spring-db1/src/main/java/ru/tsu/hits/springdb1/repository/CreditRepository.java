package ru.tsu.hits.springdb1.repository;

import org.springframework.data.repository.CrudRepository;
import ru.tsu.hits.springdb1.entity.CreditEntity;

public interface CreditRepository extends CrudRepository<CreditEntity, String> {

}