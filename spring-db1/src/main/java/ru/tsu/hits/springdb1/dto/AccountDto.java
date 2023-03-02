package ru.tsu.hits.springdb1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;

    private UserDto user;

    private String currencyCode;

    private BigDecimal balance;

    private Boolean isClosed;

    private AccountType accountType;

}
