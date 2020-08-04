package com.example.yandexweather.retrofit;

import com.example.yandexweather.retrofit.response.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface WeatherApiClient {

    String BASE_HOST = "api.weather.yandex.ru";
    String BASE_URL = "https://" + BASE_HOST + "/v1/";
    String LAT = "lat";
    String LON = "lon";
    String LANG = "lang";
    String LIMIT = "limit";
    String HOURS = "hours";

    @GET("forecast")
    @Headers("X-Yandex-API-Key: ae0b8b77-d7d7-40c0-a3fc-c3c598d26082")
    Call<WeatherResponse> weatherGet(@Query(LAT) double lat, @Query(LON) double lon,
                                     @Query(LANG) String lang, @Query(LIMIT) int limit,
                                     @Query(HOURS) boolean hours);
}
