package com.example.yandexweather.ui.main;

import android.graphics.Color;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.yandexweather.App;
import com.example.yandexweather.R;
import com.example.yandexweather.main.FragmentWeatherWeekMvpPresenter;
import com.example.yandexweather.main.FragmentWeatherWeekMvpView;
import com.example.yandexweather.model.CoordinatesModel;
import com.example.yandexweather.model.DayModel;
import com.example.yandexweather.model.ForecastModel;
import com.example.yandexweather.retrofit.response.WeatherResponse;
import com.example.yandexweather.ui.base.fragment.BaseFragment;
import com.example.yandexweather.ui.common.BackButtonListener;
import com.example.yandexweather.ui.recyclerview.adapter.DayModelsAdapter;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.terrakok.cicerone.Router;

public class FragmentWeatherWeek extends BaseFragment implements FragmentWeatherWeekMvpView, BackButtonListener {

    @Inject
    Router router;
    @InjectPresenter
    FragmentWeatherWeekMvpPresenter presenter;
    @BindView(R.id.textViewLocation)
    TextView textViewLocation;
    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    @BindView(R.id.linLayout)
    LinearLayout linLayout;
    @BindView(R.id.progressWheel)
    ProgressWheel progressWheel;
    @BindView(R.id.content)
    View content;

    private RequestBuilder<PictureDrawable> requestBuilder;

    public FragmentWeatherWeek() {
        super(true);
    }

    public static FragmentWeatherWeek getNewInstance(InstantiateObject instantiateObject) {
        FragmentWeatherWeek fragment = new FragmentWeatherWeek();
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANTIATE_OBJECT, instantiateObject);
        fragment.setArguments(bundle);
        return fragment;
    }

    @ProvidePresenter
    public FragmentWeatherWeekMvpPresenter createPresenter() {
        return new FragmentWeatherWeekMvpPresenter(getCoordinatesModel(), getDayModel());
    }

    private CoordinatesModel getCoordinatesModel() {
        return getInstantiateObject().getCoordinatesModel();
    }

    private DayModel getDayModel() {
        return getInstantiateObject().getDayModel();
    }

    private InstantiateObject getInstantiateObject() {
        return getArguments().getParcelable(INSTANTIATE_OBJECT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.INSTANCE.getAppComponent().inject(this);
        requestBuilder =
                Glide.with(this)
                        .as(PictureDrawable.class);
    }

    @Override
    public void onViewInflated(View view, @Nullable Bundle savedInstanceState) {
        textViewLocation.setText(getCoordinatesModel().getTitle());
        progressWheel.setBarColor(Color.WHITE);
    }

    @Override
    public int onInflateLayout() {
        return R.layout.fragment_weather_week;
    }

    @Override
    public void onComplete(WeatherResponse weatherResponse) {

        content.setVisibility(View.VISIBLE);
        progressWheel.setVisibility(View.GONE);

        loadBackground();

        List<ForecastModel> forecastModels = weatherResponse.getForecasts();

        if (getDayModel().getId() == DayModelsAdapter.TYPE_TODAY) {
            for (int i = 0; i < 6; i++)
                forecastModels.remove(1);
        }

        for (int i = 0; i < forecastModels.size(); i++) {

            LayoutInflater ltInflater = getLayoutInflater();
            View item = ltInflater.inflate(R.layout.item_weather_week, linLayout, false);

            TextView textViewDayOfWeek = item.findViewById(R.id.textViewDayOfWeek);
            ImageView weatherIcon = item.findViewById(R.id.weatherIcon);
            TextView textViewDayTemp = item.findViewById(R.id.textViewDayTemp);
            TextView textViewNightTemp = item.findViewById(R.id.textViewNightTemp);

            if (forecastModels.get(i).getDayOfWeek() != null)
                textViewDayOfWeek.setText(forecastModels.get(i).getDayOfWeek());
            else textViewDayOfWeek.setText("");

            weatherIcon.setImageDrawable(null);
            if (forecastModels.get(i).getParts() != null && forecastModels.get(i).getParts().getDay() != null && forecastModels.get(i).getParts().getDay().getIcon() != null)
                requestBuilder.load(weatherResponse.getForecasts().get(i).getParts().getDay().getIcon()).into(weatherIcon);

            if (forecastModels.get(i).getParts() != null && forecastModels.get(i).getParts().getDay() != null && forecastModels.get(i).getParts().getDay().getTempAverage() != null)
                textViewDayTemp.setText(forecastModels.get(i).getParts().getDay().getTempAverage());
            else textViewDayTemp.setText("");

            if (forecastModels.get(i).getParts() != null && forecastModels.get(i).getParts().getNight() != null && forecastModels.get(i).getParts().getNight().getTempAverage() != null)
                textViewNightTemp.setText(forecastModels.get(i).getParts().getNight().getTempAverage());
            else textViewNightTemp.setText("");

            linLayout.addView(item);
        }
    }

    private void loadBackground() {
        Glide.with(this).load(R.drawable.night).into(background);
    }

    @Override
    public void onError() {
        router.exitWithMessage("ERROR");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public boolean onBackPressed() {
        if (router != null) router.exit();
        return true;
    }

    public static class InstantiateObject implements Parcelable {

        public static final Creator<InstantiateObject> CREATOR = new Creator<InstantiateObject>() {
            @Override
            public InstantiateObject createFromParcel(Parcel in) {
                return new InstantiateObject(in);
            }

            @Override
            public InstantiateObject[] newArray(int size) {
                return new InstantiateObject[size];
            }
        };
        private final CoordinatesModel coordinatesModel;
        private final DayModel dayModel;

        public InstantiateObject(CoordinatesModel coordinatesModel, DayModel dayModel) {
            this.coordinatesModel = coordinatesModel;
            this.dayModel = dayModel;
        }

        protected InstantiateObject(Parcel in) {
            coordinatesModel = in.readParcelable(CoordinatesModel.class.getClassLoader());
            dayModel = in.readParcelable(DayModel.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(coordinatesModel, flags);
            dest.writeParcelable(dayModel, flags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public DayModel getDayModel() {
            return dayModel;
        }

        public CoordinatesModel getCoordinatesModel() {
            return coordinatesModel;
        }
    }
}
