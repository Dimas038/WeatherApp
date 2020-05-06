package com.example.yandexweather.retrofit.response;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("error_msg")
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
