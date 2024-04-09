package br.com.gurodriguesdev.paymentsmicroservice.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

public record Wallet(
    @Id Long id,
    String fullname,
    Long cpf,
    String email,
    String password,
    int type,
    BigDecimal balance) {
}
