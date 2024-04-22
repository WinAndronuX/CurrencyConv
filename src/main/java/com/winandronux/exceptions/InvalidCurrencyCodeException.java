package com.winandronux.exceptions;

public class InvalidCurrencyCodeException extends RuntimeException {
    public InvalidCurrencyCodeException(String message) {
        super(message + " is not a valid currency code");
    }
}
