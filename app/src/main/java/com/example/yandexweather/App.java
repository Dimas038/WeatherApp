package com.example.yandexweather;

import android.app.Application;

import com.example.yandexweather.dagger.AppComponent;
import com.example.yandexweather.dagger.DaggerAppComponent;
import com.example.yandexweather.dagger.module.ApplicationModule;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;

public class App extends Application {

    public static App INSTANCE;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(getString(R.string.default_font_path))
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
    }

    public AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .applicationModule(new ApplicationModule(this)).build();
        }
        return appComponent;
    }
}
