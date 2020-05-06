package com.example.yandexweather.ui.main;

import android.graphics.Color;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.yandexweather.App;
import com.example.yandexweather.R;
import com.example.yandexweather.main.FragmentWeatherTodayMvpPresenter;
import com.example.yandexweather.main.FragmentWeatherTodayMvpView;
import com.example.yandexweather.model.CoordinatesModel;
import com.example.yandexweather.model.DayModel;
import com.example.yandexweather.model.ForecastModel;
import com.example.yandexweather.retrofit.response.WeatherResponse;
import com.example.yandexweather.ui.base.fragment.BaseFragment;
import com.example.yandexweather.ui.common.BackButtonListener;
import com.example.yandexweather.ui.recyclerview.adapter.DayModelsAdapter;
import com.example.yandexweather.ui.recyclerview.adapter.HoursModelsAdapter;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import ru.terrakok.cicerone.Router;

public class FragmentWeatherToday extends BaseFragment implements FragmentWeatherTodayMvpView, BackButtonListener, AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_SHOW_IMAGE = 1;
    @Inject
    Router router;
    @InjectPresenter
    FragmentWeatherTodayMvpPresenter presenter;
//    @BindView(R.id.content)
//    View content;
    @BindView(R.id.background)
    ImageView background;
    @BindView(R.id.textViewLocation)
    TextView textViewLocation;
    @BindView(R.id.weatherIcon)
    ImageView weatherIcon;
    @BindView(R.id.textViewCurrentTemp)
    TextView textViewCurrentTemp;
//    @BindView(R.id.progressWheel)
//    ProgressWheel progressWheel;
    @BindView(R.id.textViewDate)
    TextView textViewDate;
    @BindView(R.id.textViewSunrise)
    TextView textViewSunrise;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.linLayout)
    LinearLayout linLayout;
    @BindView(R.id.upperDivider)
    View upperDivider;
    @BindView(R.id.lowerDivider)
    View lowerDivider;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;

    private RequestBuilder<PictureDrawable> requestBuilder;

    public FragmentWeatherToday() {
        super(true);
    }

    public static FragmentWeatherToday getNewInstance(InstantiateObject instantiateObject) {
        FragmentWeatherToday fragment = new FragmentWeatherToday();
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANTIATE_OBJECT, instantiateObject);
        fragment.setArguments(bundle);
        return fragment;
    }

    @ProvidePresenter
    public FragmentWeatherTodayMvpPresenter createPresenter() {
        return new FragmentWeatherTodayMvpPresenter(getCoordinatesModel(), getDayModel());
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
//        progressWheel.setBarColor(Color.WHITE);
        upperDivider.setVisibility(View.GONE);
        lowerDivider.setVisibility(View.GONE);
        appbar.addOnOffsetChangedListener(this);
    }

    @Override
    public int onInflateLayout() {
        return R.layout.fragment_weather_today;
    }

    @Override
    public void onComplete(WeatherResponse weatherResponse) {

        textViewCurrentTemp.setText(weatherResponse.getFact().getTemperature());

        loadIcon(weatherResponse);
        loadBackground(weatherResponse);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        HoursModelsAdapter adapter = new HoursModelsAdapter(recyclerView);
        adapter.setModels(weatherResponse.getForecasts().get(0).getHours());
        recyclerView.setAdapter(adapter);

//        content.setVisibility(View.VISIBLE);
//        progressWheel.setVisibility(View.GONE);
        upperDivider.setVisibility(View.VISIBLE);
        lowerDivider.setVisibility(View.VISIBLE);

        List<ForecastModel> forecastModels = weatherResponse.getForecasts();

        if (getDayModel().getId() == DayModelsAdapter.TYPE_TODAY) {
            for (int i = 0; i < 6; i++)
                forecastModels.remove(1);
        }

        textViewDate.setText(weatherResponse.getForecasts().get(0).getDateReverse() + getString(R.string.today));
        textViewSunrise.setText(getString(R.string.sunrise_is_in) + weatherResponse.getForecasts().get(0).getSunrise());

        for (int i = 0; i < forecastModels.size(); i++) {

            LayoutInflater ltInflater = getLayoutInflater();
            View item = ltInflater.inflate(R.layout.item_weather_today, linLayout, false);

            TextView textViewNight = item.findViewById(R.id.textViewNightDefinition);
            TextView textViewMorning = item.findViewById(R.id.textViewMorningDefinition);
            TextView textViewDay = item.findViewById(R.id.textViewDayDefinition);
            TextView textViewEvening = item.findViewById(R.id.textViewEveningDefinition);
            if (forecastModels.get(i).getParts() != null && forecastModels.get(i).getParts().getNight() != null)
                textViewNight.setText(forecastModels.get(i).getParts().getNight().getDayPartForecast() + "\n");
            else textViewNight.setText("");

            if (forecastModels.get(i).getParts() != null && forecastModels.get(i).getParts().getMorning() != null)
                textViewMorning.setText(forecastModels.get(i).getParts().getMorning().getDayPartForecast() + "\n");
            else textViewMorning.setText("");

            if (forecastModels.get(i).getParts() != null && forecastModels.get(i).getParts().getDay() != null)
                textViewDay.setText(forecastModels.get(i).getParts().getDay().getDayPartForecast() + "\n");
            else textViewDay.setText("");

            if (forecastModels.get(i).getParts() != null && forecastModels.get(i).getParts().getEvening() != null)
                textViewEvening.setText(forecastModels.get(i).getParts().getEvening().getDayPartForecast());
            else textViewEvening.setText("");

            linLayout.addView(item);
        }
    }

    private void loadBackground(WeatherResponse weatherResponse) {
        Integer backgroundResourceId = weatherResponse.getBackground();
        if (weatherResponse.getBackground() != null)
            Glide.with(this).load(backgroundResourceId).into(background);
    }

    public void loadIcon(WeatherResponse weatherResponse) {
        requestBuilder.load(weatherResponse.getFact().getIcon()).into(weatherIcon);
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

    @Override
    public void onOffsetChanged(AppBarLayout appbar, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appbar.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(i)) * 100 / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;
                ViewCompat.animate(textViewCurrentTemp).alpha(0);
                ViewCompat.animate(textViewDate).alpha(0);
                ViewCompat.animate(textViewSunrise).alpha(0);
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(textViewCurrentTemp).alpha(1);
                ViewCompat.animate(textViewDate).alpha(1);
                ViewCompat.animate(textViewSunrise).alpha(1);
            }
        }
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

        public CoordinatesModel getCoordinatesModel() {
            return coordinatesModel;
        }

        public DayModel getDayModel() {
            return dayModel;
        }
    }
}
