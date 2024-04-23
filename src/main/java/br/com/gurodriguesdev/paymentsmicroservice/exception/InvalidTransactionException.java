package br.com.gurodriguesdev.paymentsmicroservice.exception;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
}
