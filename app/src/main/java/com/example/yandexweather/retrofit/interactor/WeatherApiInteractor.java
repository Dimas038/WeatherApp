package com.example.yandexweather.retrofit.interactor;

import com.example.yandexweather.retrofit.WeatherApiClient;
import com.example.yandexweather.retrofit.exception.ForbiddenException;
import com.example.yandexweather.retrofit.exception.UnknownApiException;
import com.example.yandexweather.retrofit.response.WeatherResponse;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class WeatherApiInteractor {

    private final WeatherApiClient weatherApiClient;

    @Inject
    WeatherApiInteractor(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    public WeatherResponse weatherGet(double lat, double lon) throws IOException, UnknownApiException, ForbiddenException {
        Response<WeatherResponse> response = weatherApiClient.weatherGet(lat, lon, "ru_Ru", 7, true).execute();
        if (response == null) throw new UnknownApiException();
        if (response.code() == 403) throw new ForbiddenException();
        if (response.code() != 200) throw new UnknownApiException();
        if (response.errorBody() != null) throw new UnknownApiException();
        if (response.body() == null) throw new UnknownApiException();
        WeatherResponse weatherResponse = response.body();
        return weatherResponse;
    }
}
