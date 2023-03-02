package ru.tsu.hits.springdb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Определение сущности
@Table(name = "loanrate") // Определение таблицы в БД
@Data // Аннотация, включающая в себя геттеры, сеттеры и т.д.
@NoArgsConstructor // Конструктор без аргументов
@AllArgsConstructor // Конструктор с аргументами для заполнение всех полей, которые есть в классе


public class LoanRateEntity {
    @Id
    @Column(name = "id")
    private String loanRateId;

    @Column
    private String name;

    @Column
    private Double interestRate;

    @Column
    private Integer loanMonthPeriod;
}
