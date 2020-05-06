package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

public class FeatureMemberModel {
    @SerializedName("GeoObject")
    private GeoObjectModel geoObject;

    public GeoObjectModel getGeoObject() {
        return geoObject;
    }
}
