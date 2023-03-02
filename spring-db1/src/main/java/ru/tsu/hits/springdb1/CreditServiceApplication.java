package ru.tsu.hits.springdb1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.tsu.hits.springdb1.service.paymentlooper.PaymentLooper;

@SpringBootApplication
public class CreditServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CreditServiceApplication.class, args);
	}

}
