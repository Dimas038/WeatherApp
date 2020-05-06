package com.example.yandexweather.dagger;

import com.example.yandexweather.dagger.module.ApplicationModule;
import com.example.yandexweather.dagger.module.JsonModule;
import com.example.yandexweather.dagger.module.NavigationModule;
import com.example.yandexweather.dagger.module.NetworkModule;
import com.example.yandexweather.main.FragmentCoordinatesMvpPresenter;
import com.example.yandexweather.main.FragmentDayMvpPresenter;
import com.example.yandexweather.main.FragmentWeatherTodayMvpPresenter;
import com.example.yandexweather.main.FragmentWeatherWeekMvpPresenter;
import com.example.yandexweather.ui.main.FragmentCoordinates;
import com.example.yandexweather.ui.main.FragmentDay;
import com.example.yandexweather.ui.main.FragmentWeatherToday;
import com.example.yandexweather.ui.main.FragmentWeatherWeek;
import com.example.yandexweather.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NavigationModule.class,
        JsonModule.class,
        NetworkModule.class
})
public interface AppComponent {

    void inject(MainActivity activity);

    void inject(FragmentDay fragment);

    void inject(FragmentDayMvpPresenter presenter);

    void inject(FragmentWeatherToday fragment);

    void inject(FragmentWeatherTodayMvpPresenter presenter);

    void inject(FragmentWeatherWeek fragment);

    void inject(FragmentWeatherWeekMvpPresenter presenter);

    void inject(FragmentCoordinates fragment);

    void inject(FragmentCoordinatesMvpPresenter presenter);
}
