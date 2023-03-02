package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditByLoanRateDto {

    private String clientId;

    private String creditAccountNumber;

    private Double loanAmount;

    private String loanRateId;
}
