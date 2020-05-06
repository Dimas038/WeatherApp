package com.example.yandexweather.ui.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.yandexweather.App;
import com.example.yandexweather.R;
import com.example.yandexweather.Screens;
import com.example.yandexweather.ui.base.activity.BaseActivity;
import com.example.yandexweather.ui.common.BackButtonListener;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.SupportFragmentNavigator;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Command;
import ru.terrakok.cicerone.commands.Replace;

public class MainActivity extends BaseActivity {

    private final Navigator navigator = new SupportFragmentNavigator(getSupportFragmentManager(), R.id.fragmentContainer) {
        @Override
        protected Fragment createFragment(String screenKey, Object data) {
            switch (screenKey) {
                case Screens.FRAGMENT_COORDINATES:
                    return FragmentCoordinates.getNewInstance();
                case Screens.FRAGMENT_DAY:
                    return FragmentDay.getNewInstance((FragmentDay.InstantiateObject) data);
                case Screens.FRAGMENT_WEATHER_TODAY:
                    return FragmentWeatherToday.getNewInstance((FragmentWeatherToday.InstantiateObject) data);
                case Screens.FRAGMENT_WEATHER_WEEK:
                    return FragmentWeatherWeek.getNewInstance((FragmentWeatherWeek.InstantiateObject) data);
            }
            return null;
        }

        @Override
        protected void showSystemMessage(String message) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void exit() {
            finish();
        }

        @Override
        public void applyCommands(Command[] commands) {
            try {
                super.applyCommands(commands);
                getSupportFragmentManager().executePendingTransactions();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Inject
    NavigatorHolder navigatorHolder;
    @Inject
    Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.INSTANCE.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initButterKnife();
        if (savedInstanceState == null) {
            navigator.applyCommands(new Command[]{new BackTo(null), new Replace(Screens.FRAGMENT_COORDINATES, null)});
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (fragment != null
                && fragment instanceof BackButtonListener
                && ((BackButtonListener) fragment).onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResumeFragments() {
        if (navigatorHolder != null) {
            super.onResumeFragments();
            navigatorHolder.setNavigator(navigator);
        }
    }

    @Override
    protected void onPause() {
        if (navigatorHolder != null) navigatorHolder.removeNavigator();
        super.onPause();
    }
}
