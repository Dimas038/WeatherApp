package com.example.yandexweather.model;

import com.google.gson.annotations.SerializedName;

public class DayPartModel {

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

    public static final String NORTH_WEST = "nw";
    public static final String NORTH = "n";
    public static final String NORTH_EAST = "ne";
    public static final String EAST = "e";
    public static final String SOUTH_EAST = "se";
    public static final String SOUTH = "s";
    public static final String SOUTH_WEST = "sw";
    public static final String WEST = "w";
    public static final String CALM = "c";


    public static final String DEGREES = "\u00B0" + "C";
    public static final String DEGREES_CUT = "\u00B0";

    @SerializedName("temp_min")
    private int tempMin;
    @SerializedName("temp_max")
    private int tempMax;
    @SerializedName("temp_avg")
    private int tempAvg;
    @SerializedName("wind_speed")
    private double windSpeed;
    @SerializedName("condition")
    private String condition;
    @SerializedName("icon")
    private String icon;
    @SerializedName("pressure_mm")
    private int pressure;
    @SerializedName("wind_dir")
    private String windDir;
    @SerializedName("prec_mm")
    private double prec;
    @SerializedName("humidity")
    private int humidity;

    public int getTempAvg() {
        return tempAvg;
    }

    public String getTempAverage() {
        return tempAvg + DEGREES_CUT;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public String getCondition() {
        return condition;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public String getPrecipitation() {
        if (prec == 0D) {
            return "0";
        } else return String.valueOf(prec);
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

    public String getWindDirLocalized() {
        if (windDir != null)
            switch (windDir) {
                case NORTH_WEST:
                    return "сз";
                case NORTH:
                    return "с";
                case NORTH_EAST:
                    return "св";
                case EAST:
                    return "в";
                case SOUTH_EAST:
                    return "юв";
                case SOUTH:
                    return "ю";
                case SOUTH_WEST:
                    return "юз";
                case WEST:
                    return "з";
                case CALM:
                    return "";
                default:
                    return windDir;
            }
        return null;
    }

    public String getDayPartForecast() {
        return "Средняя температура: " + getTempAvg() + DEGREES +
                "\nВетер: " + getWindDirLocalized() + " " + getWindSpeed() + " м/с" +
                "\nОсадки: " + getPrecipitation() + " мм" +
                "\nВлажность: " + getHumidity() + "%" +
                "\nДавление: " + getPressure() + " мм рт.ст."
                + "\nУсловия: " + getConditionLocalized();

    }

    public String getIcon() {
        return "https://yastatic.net/weather/i/icons/blueye/color/svg/" + icon + ".svg";
    }
}
