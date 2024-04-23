package br.com.gurodriguesdev.paymentsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@EnableJdbcAuditing
@SpringBootApplication
public class PaymentsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsMicroserviceApplication.class, args);
	}

}