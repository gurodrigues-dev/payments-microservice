package br.com.gurodriguesdev.paymentsmicroservice.exception;

public class UnauthorizedTransactionException extends RuntimeException{
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
