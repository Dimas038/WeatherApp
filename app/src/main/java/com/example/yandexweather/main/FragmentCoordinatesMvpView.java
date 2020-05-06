package com.example.yandexweather.main;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.yandexweather.retrofit.response.GeoObjectCollectionGetResponse;
import com.example.yandexweather.retrofit.response.GeopositionResponse;

public interface FragmentCoordinatesMvpView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void onComplete(GeopositionResponse<GeoObjectCollectionGetResponse> geopositionResponse);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void onError();
}
