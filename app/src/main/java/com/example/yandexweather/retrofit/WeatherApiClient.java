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
    @Headers("X-Yandex-API-Key: bf73ef9e-e5e5-4461-a142-bb3d0cceb820")
    Call<WeatherResponse> weatherGet(@Query(LAT) double lat, @Query(LON) double lon,
                                     @Query(LANG) String lang, @Query(LIMIT) int limit,
                                     @Query(HOURS) boolean hours);
}
