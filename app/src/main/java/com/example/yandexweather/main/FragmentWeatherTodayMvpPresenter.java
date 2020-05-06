package com.example.yandexweather.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.yandexweather.App;
import com.example.yandexweather.model.CoordinatesModel;
import com.example.yandexweather.model.DayModel;
import com.example.yandexweather.retrofit.interactor.WeatherApiInteractor;
import com.example.yandexweather.retrofit.response.WeatherResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class FragmentWeatherTodayMvpPresenter extends MvpPresenter<FragmentWeatherTodayMvpView> {

    private final CoordinatesModel coordinatesModel;
    private final DayModel dayModel;
    @Inject
    WeatherApiInteractor interactor;
    private Disposable disposableAction;

    public FragmentWeatherTodayMvpPresenter(CoordinatesModel coordinatesModel, DayModel dayModel) {
        this.coordinatesModel = coordinatesModel;
        this.dayModel = dayModel;

        App.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        load();
    }

    private void load() {
        disposeAction();
        disposableAction = Single.just(true)
                .subscribeOn(Schedulers.io())
                .map(id -> {
                    WeatherResponse weatherResponse = interactor.weatherGet(coordinatesModel.getLat(), coordinatesModel.getLon());
                    return weatherResponse;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherResponse -> {
                    getViewState().onComplete(weatherResponse);
                }, throwable -> {
                    getViewState().onError();
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposeAction();
    }

    protected void disposeAction() {
        if (disposableAction != null) {
            if (!disposableAction.isDisposed()) {
                disposableAction.dispose();
            }
            disposableAction = null;
        }
    }
}
