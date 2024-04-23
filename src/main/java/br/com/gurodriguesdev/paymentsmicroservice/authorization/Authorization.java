package br.com.gurodriguesdev.paymentsmicroservice.authorization;

public record Authorization(
        String message
) {
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}
