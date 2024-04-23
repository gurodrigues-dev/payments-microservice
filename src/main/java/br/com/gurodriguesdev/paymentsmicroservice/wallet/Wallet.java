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

    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullname, cpf, email, password, type, balance.subtract(value));
    }

    public Wallet credit(BigDecimal value) {
        return new Wallet(id, fullname, cpf, email, password, type, balance.add(value));
    }

}
