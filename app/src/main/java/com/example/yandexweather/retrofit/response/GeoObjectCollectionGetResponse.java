package com.example.yandexweather.retrofit.response;

import com.example.yandexweather.model.GeoObjectCollectionModel;
import com.google.gson.annotations.SerializedName;

public class GeoObjectCollectionGetResponse {
    @SerializedName("GeoObjectCollection")
    private GeoObjectCollectionModel geoObjectCollection;


    public GeoObjectCollectionModel getGeoObjectCollection() {
        return geoObjectCollection;
    }
}
