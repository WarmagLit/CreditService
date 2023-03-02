package ru.tsu.hits.springdb1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity // Определение сущности
@Table(name = "paymentschedule") // Определение таблицы в БД
@Data // Аннотация, включающая в себя геттеры, сеттеры и т.д.
@NoArgsConstructor // Конструктор без аргументов
@AllArgsConstructor // Конструктор с аргументами для заполнение всех полей, которые есть в классе

public class PaymentScheduleEntity {
    @Id
    @Column(name = "id")
    private String paymentId;

    @Column
    private String creditId;

    @Column
    private String loanAccountNumber;

    @Column
    private LocalDate paymentday;

    @Column
    private Double paymentSum;

    @Column
    private Double paymentOfMainDebtSum;

    @Column
    private Double percentSum;

    @Column
    private Boolean isPayed;
}
