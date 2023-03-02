package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomCreditDto {

    private String clientId;

    private String creditAccountNumber;

    private Double interestRate;

    private Double loanAmount;

    private Integer loanMonthPeriod;
}
