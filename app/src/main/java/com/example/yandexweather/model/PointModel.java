package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

public class PointModel {
    @SerializedName("pos")
    private String pos;

    public String getPos() {
        return pos;
    }
}
