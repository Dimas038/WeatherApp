package com.example.yandexweather.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class GeopositionResponse<T> {
    @SerializedName("error")
    private ErrorResponse error;
    @SerializedName("response")
    private T response = null;

    public ErrorResponse getError() {
        return error;
    }

    public T getResponse() {
        return response;
    }
}