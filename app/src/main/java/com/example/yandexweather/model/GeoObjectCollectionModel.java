package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoObjectCollectionModel {
    @SerializedName("featureMember")
    private List<FeatureMemberModel> featureMember;

    public List<FeatureMemberModel> getFeatureMember() {
        return featureMember;
    }
}
