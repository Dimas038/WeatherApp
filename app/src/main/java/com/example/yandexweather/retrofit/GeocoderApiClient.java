package com.example.yandexweather.retrofit;

import com.example.yandexweather.retrofit.response.GeoObjectCollectionGetResponse;
import com.example.yandexweather.retrofit.response.GeopositionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocoderApiClient {

    String BASE_HOST = "geocode-maps.yandex.ru";
    String BASE_URL = "https://" + BASE_HOST + "/1.x/";
    String API_KEY = "apikey";
    String GEOCODE = "geocode";
    String FORMAT = "format";

    @GET(" ")
    Call<GeopositionResponse<GeoObjectCollectionGetResponse>> addressGet(@Query(API_KEY) String apikey,
                                                                         @Query(GEOCODE) String geocode,
                                                                         @Query(FORMAT) String format);
}
