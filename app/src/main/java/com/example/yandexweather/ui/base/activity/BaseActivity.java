package com.example.yandexweather.ui.base.activity;

import android.content.Context;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public abstract class BaseActivity extends MvpAppCompatActivity {

    private Unbinder unbinder;

    public void initButterKnife() {
        unbinder = ButterKnife.bind(this);
    }

    private void releaseButterKnife() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    protected void onDestroy() {
        releaseButterKnife();
        super.onDestroy();
    }

    //    Calligraphy
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
