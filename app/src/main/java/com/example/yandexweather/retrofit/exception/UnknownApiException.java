package com.example.yandexweather.retrofit.exception;

public class UnknownApiException extends Exception {

    public UnknownApiException() {
        super();
    }

    public UnknownApiException(String message) {
        super(message);
    }
}
