package com.example.yandexweather.retrofit.exception;

public class ForbiddenException extends Exception {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
