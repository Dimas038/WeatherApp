package com.example.yandexweather.main;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.yandexweather.App;

@InjectViewState
public class FragmentDayMvpPresenter extends MvpPresenter<FragmentDayMvpView> {

    public FragmentDayMvpPresenter() {
        App.INSTANCE.getAppComponent().inject(this);
    }
}
