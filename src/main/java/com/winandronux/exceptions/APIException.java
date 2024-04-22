package com.winandronux.exceptions;

public class APIException extends RuntimeException {
    public APIException(String message) {
        super("ExchangeRate-API error: " + message);
    }
}
