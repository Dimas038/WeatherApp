package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

public class FactModel {

    public static final String CLEAR = "clear";
    public static final String PARTLY_CLOUDY = "partly-cloudy";
    public static final String CLOUDY = "cloudy";
    public static final String OVERCAST = "overcast";
    public static final String PARTLY_CLOUDY_AND_LIGHT_RAIN = "partly-cloudy-and-light-rain";
    public static final String PARTLY_CLOUDY_AND_RAIN = "partly-cloudy-and-rain";
    public static final String OVERCAST_AND_RAIN = "overcast-and-rain";
    public static final String OVERCAST_THUNDERSTORMS_WITH_RAIN = "overcast-thunderstorms-with-rain";
    public static final String CLOUDY_AND_LIGHT_RAIN = "cloudy-and-light-rain";
    public static final String OVERCAST_AND_LIGHT_RAIN = "overcast-and-light-rain";
    public static final String CLOUDY_AND_RAIN = "cloudy-and-rain";
    public static final String OVERCAST_AND_WET_SNOW = "overcast-and-wet-snow";
    public static final String PARTLY_CLOUDY_AND_LIGHT_SNOW = "partly-cloudy-and-light-snow";
    public static final String PARTLY_CLOUDY_AND_SNOW = "partly-cloudy-and-snow";
    public static final String OVERCAST_AND_SNOW = "overcast-and-snow";
    public static final String CLOUDY_AND_LIGHT_SNOW = "cloudy-and-light-snow";
    public static final String OVERCAST_AND_LIGHT_SNOW = "overcast-and-light-snow";
    public static final String CLOUDY_AND_SNOW = "cloudy-and-snow";

    public static final String DAY = "d";
    public static final String NIGHT = "n";

    @SerializedName("temp")
    private int temp;
    @SerializedName("icon")
    private String icon;
    @SerializedName("condition")
    private String condition;
    @SerializedName("daytime")
    private String daytime;

    public String getDaytime() {
        return daytime;
    }

    public int getTemp() {
        return temp;
    }

    public String getIcon() {
        return "https://yastatic.net/weather/i/icons/blueye/color/svg/" + icon + ".svg";
    }

    public String getTemperature() {
        return temp + "\u00B0";
    }

    public String getCondition() {
        return condition;
    }

    public String getConditionLocalized() {
        if (condition != null)
            switch (condition) {
                case CLEAR:
                    return "ясно";
                case PARTLY_CLOUDY:
                    return "малооблачно";
                case CLOUDY:
                    return "облачно с прояснениями";
                case OVERCAST:
                    return "пасмурно";
                case PARTLY_CLOUDY_AND_LIGHT_RAIN:
                    return "небольшой дождь";
                case PARTLY_CLOUDY_AND_RAIN:
                    return "дождь";
                case OVERCAST_AND_RAIN:
                    return "сильный дождь";
                case OVERCAST_THUNDERSTORMS_WITH_RAIN:
                    return "сильный дождь, гроза";
                case CLOUDY_AND_LIGHT_RAIN:
                    return "небольшой дождь";
                case OVERCAST_AND_LIGHT_RAIN:
                    return "небольшой дождь";
                case CLOUDY_AND_RAIN:
                    return "дождь";
                case OVERCAST_AND_WET_SNOW:
                    return "дождь со снегом";
                case PARTLY_CLOUDY_AND_LIGHT_SNOW:
                    return "небольшой снег";
                case PARTLY_CLOUDY_AND_SNOW:
                    return "снег";
                case OVERCAST_AND_SNOW:
                    return "снегопад";
                case CLOUDY_AND_LIGHT_SNOW:
                    return "небольшой снег";
                case OVERCAST_AND_LIGHT_SNOW:
                    return "небольшой снег";
                case CLOUDY_AND_SNOW:
                    return "снег";
                default:
                    return condition;
            }
        return null;
    }
}
