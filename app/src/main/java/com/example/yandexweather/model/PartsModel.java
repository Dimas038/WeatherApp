package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

public class PartsModel {

    @SerializedName("night")
    private DayPartModel night;
    @SerializedName("morning")
    private DayPartModel morning;
    @SerializedName("day")
    private DayPartModel day;
    @SerializedName("evening")
    private DayPartModel evening;

    public DayPartModel getNight() {
        return night;
    }

    public DayPartModel getMorning() {
        return morning;
    }

    public DayPartModel getDay() {
        return day;
    }

    public DayPartModel getEvening() {
        return evening;
    }
}