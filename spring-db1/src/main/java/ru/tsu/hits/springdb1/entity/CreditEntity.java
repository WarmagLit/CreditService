package ru.tsu.hits.springdb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Определение сущности
@Table(name = "credit") // Определение таблицы в БД
@Data // Аннотация, включающая в себя геттеры, сеттеры и т.д.
@NoArgsConstructor // Конструктор без аргументов
@AllArgsConstructor // Конструктор с аргументами для заполнение всех полей, которые есть в классе

public class CreditEntity {
    @Id
    @Column(name = "id")
    private String creditId;

    @Column
    private String clientId;

    @Column
    private String creditAccountNumber;

    @Column
    private Double interestRate;

    @Column
    private Double loanAmount;

    @Column
    private Integer loanMonthPeriod;

    @Column
    private String loanRateId;
}
