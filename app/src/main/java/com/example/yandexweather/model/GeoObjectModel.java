package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

public class GeoObjectModel {
    @SerializedName("Point")
    private PointModel point;

    public PointModel getPoint() {
        return point;
    }
}
