package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

public class HourModel {

    @SerializedName("hour")
    private int hour;
    @SerializedName("temp")
    private int temp;
    @SerializedName("icon")
    private String icon;

    public int getHour() {
        return hour;
    }

    public int getTemp() {
        return temp;
    }

    public String getIcon() {
        return "https://yastatic.net/weather/i/icons/blueye/color/svg/" + icon + ".svg";
    }

    public String getTemperature() {
        return temp + "\u00B0" + "C";
    }
}
