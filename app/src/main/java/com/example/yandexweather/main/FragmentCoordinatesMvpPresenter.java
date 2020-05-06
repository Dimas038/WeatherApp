package com.example.yandexweather.main;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.yandexweather.App;
import com.example.yandexweather.R;
import com.example.yandexweather.retrofit.interactor.GeocoderApiInteractor;
import com.example.yandexweather.retrofit.response.GeoObjectCollectionGetResponse;
import com.example.yandexweather.retrofit.response.GeopositionResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class FragmentCoordinatesMvpPresenter extends MvpPresenter<FragmentCoordinatesMvpView> {

    @Inject
    GeocoderApiInteractor interactor;
    private Disposable disposableAction;

    public FragmentCoordinatesMvpPresenter() {
        App.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    public void load(String geocode, Context context) {
        disposeAction();
        disposableAction = Single.just(true)
                .subscribeOn(Schedulers.io())
                .map(id -> {
                    GeopositionResponse<GeoObjectCollectionGetResponse> geopositionResponse = interactor.addressGet(context.getString(R.string.yandex_api_key), geocode, context.getString(R.string.format));
                    return geopositionResponse;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(geopositionResponse -> {
                    getViewState().onComplete(geopositionResponse);
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
