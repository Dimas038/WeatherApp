package com.example.yandexweather.retrofit.interactor;

import com.example.yandexweather.retrofit.GeocoderApiClient;
import com.example.yandexweather.retrofit.exception.ForbiddenException;
import com.example.yandexweather.retrofit.exception.UnknownApiException;
import com.example.yandexweather.retrofit.response.GeoObjectCollectionGetResponse;
import com.example.yandexweather.retrofit.response.GeopositionResponse;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class GeocoderApiInteractor {

    private final GeocoderApiClient geocoderApiClient;

    @Inject
    GeocoderApiInteractor(GeocoderApiClient geocoderApiClient) {
        this.geocoderApiClient = geocoderApiClient;
    }

    public GeopositionResponse addressGet(String apikey, String geocode, String format) throws IOException, UnknownApiException, ForbiddenException {
        Response<GeopositionResponse<GeoObjectCollectionGetResponse>> response = geocoderApiClient.addressGet(apikey, geocode, format).execute();
        if (response == null) throw new UnknownApiException();
        if (response.code() == 403) throw new ForbiddenException();
        if (response.code() != 200) throw new UnknownApiException();
        if (response.errorBody() != null) throw new UnknownApiException();
        if (response.body() == null) throw new UnknownApiException();
        GeopositionResponse<GeoObjectCollectionGetResponse> geopositionResponse = response.body();
        return geopositionResponse;
    }
}
