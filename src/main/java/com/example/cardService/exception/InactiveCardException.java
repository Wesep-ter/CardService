package com.example.cardService.exception;

public class InactiveCardException extends RuntimeException {
    public InactiveCardException(String message) {
        super(message);
    }
}
