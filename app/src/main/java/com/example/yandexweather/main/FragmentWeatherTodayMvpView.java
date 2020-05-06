package com.example.yandexweather.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.yandexweather.retrofit.response.WeatherResponse;

public interface FragmentWeatherTodayMvpView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void onComplete(WeatherResponse weatherResponse);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onError();
}
