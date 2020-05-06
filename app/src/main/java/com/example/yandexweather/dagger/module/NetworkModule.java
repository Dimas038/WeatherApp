package com.example.yandexweather.dagger.module;

import com.example.yandexweather.BuildConfig;
import com.example.yandexweather.retrofit.GeocoderApiClient;
import com.example.yandexweather.retrofit.WeatherApiClient;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    OkHttpClient okHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addNetworkInterceptor(logging).build();
    }

    @Provides
    @Singleton
    WeatherApiClient weatherApiClient(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(WeatherApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(WeatherApiClient.class);
    }

    @Provides
    @Singleton
    GeocoderApiClient geocoderApiClient(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(GeocoderApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(GeocoderApiClient.class);
    }
}
