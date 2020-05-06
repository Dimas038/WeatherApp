package com.example.yandexweather.retrofit.response;

import com.example.yandexweather.R;
import com.example.yandexweather.model.FactModel;
import com.example.yandexweather.model.ForecastModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("forecasts")
    private List<ForecastModel> forecasts;
    @SerializedName("fact")
    private FactModel fact;

    public List<ForecastModel> getForecasts() {
        return forecasts;
    }

    public FactModel getFact() {
        return fact;
    }

    public Integer getBackground() {
        if (getFact().getDaytime() != null) {
            switch (getFact().getDaytime()) {
                case FactModel.DAY:
                    if (getFact().getCondition() != null) {
                        switch (getFact().getCondition()) {
                            case FactModel.CLEAR:
                                return R.drawable.clear;
                            case FactModel.PARTLY_CLOUDY:
                            case FactModel.CLOUDY:
                                return R.drawable.cloudy;
                            case FactModel.OVERCAST:
                                return R.drawable.overcast;
                            case FactModel.PARTLY_CLOUDY_AND_LIGHT_RAIN:
                            case FactModel.CLOUDY_AND_LIGHT_RAIN:
                            case FactModel.OVERCAST_AND_LIGHT_RAIN:
                            case FactModel.PARTLY_CLOUDY_AND_RAIN:
                            case FactModel.CLOUDY_AND_RAIN:
                            case FactModel.OVERCAST_AND_RAIN:
                                return R.drawable.rain;
                            case FactModel.OVERCAST_THUNDERSTORMS_WITH_RAIN:
                                return R.drawable.thunder;
                            case FactModel.OVERCAST_AND_WET_SNOW:
                            case FactModel.PARTLY_CLOUDY_AND_LIGHT_SNOW:
                            case FactModel.CLOUDY_AND_LIGHT_SNOW:
                            case FactModel.OVERCAST_AND_LIGHT_SNOW:
                            case FactModel.PARTLY_CLOUDY_AND_SNOW:
                            case FactModel.CLOUDY_AND_SNOW:
                            case FactModel.OVERCAST_AND_SNOW:
                                return R.drawable.snow;
                            default:
                                return R.drawable.default_background;
                        }
                    }
                case FactModel.NIGHT:
                    return R.drawable.night;
            }
        }
        return null;
    }
}