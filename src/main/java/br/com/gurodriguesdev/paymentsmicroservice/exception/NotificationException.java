package br.com.gurodriguesdev.paymentsmicroservice.exception;

public class NotificationException extends RuntimeException{
    public NotificationException(String message) {
        super(message);
    }
}
