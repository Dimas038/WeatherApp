package com.example.yandexweather.ui.main;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.example.yandexweather.App;
import com.example.yandexweather.R;
import com.example.yandexweather.Screens;
import com.example.yandexweather.main.FragmentDayMvpPresenter;
import com.example.yandexweather.main.FragmentDayMvpView;
import com.example.yandexweather.model.CoordinatesModel;
import com.example.yandexweather.model.DayModel;
import com.example.yandexweather.ui.base.fragment.BaseFragment;
import com.example.yandexweather.ui.common.BackButtonListener;
import com.example.yandexweather.ui.recyclerview.adapter.DayModelsAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import ru.terrakok.cicerone.Router;

import static com.example.yandexweather.ui.recyclerview.adapter.DayModelsAdapter.TYPE_TODAY;
import static com.example.yandexweather.ui.recyclerview.adapter.DayModelsAdapter.TYPE_WEEK;

public class FragmentDay extends BaseFragment implements FragmentDayMvpView, BackButtonListener {

    @Inject
    Router router;

    @InjectPresenter
    FragmentDayMvpPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.background)
    ImageView background;

    private DayModelsAdapter adapter;

    public FragmentDay() {
        super(true);
    }

    public static FragmentDay getNewInstance(InstantiateObject instantiateObject) {
        FragmentDay fragment = new FragmentDay();
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANTIATE_OBJECT, instantiateObject);
        fragment.setArguments(bundle);
        return fragment;
    }

    @ProvidePresenter
    public FragmentDayMvpPresenter createPresenter() {
        return new FragmentDayMvpPresenter();
    }

    private CoordinatesModel getCoordinatesModel() {
        return getInstantiateObject().getCoordinatesModel();
    }

    private InstantiateObject getInstantiateObject() {
        return getArguments().getParcelable(INSTANTIATE_OBJECT);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    public void onViewInflated(View view, @Nullable Bundle savedInstanceState) {
        Glide.with(this).load(R.drawable.night).into(background);
        initRecyclerView();
    }

    @Override
    public int onInflateLayout() {
        return R.layout.fragment_day;
    }

    private void initRecyclerView() {
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new DayModelsAdapter(recyclerView, new DayModelsAdapter.OnClickListener() {
            @Override
            public void onClick(DayModel dayModel) {
                switch (dayModel.getId()) {
                    case TYPE_TODAY:
                        router.navigateTo(Screens.FRAGMENT_WEATHER_TODAY, new FragmentWeatherToday.InstantiateObject(getCoordinatesModel(), dayModel));
                        break;
                    case TYPE_WEEK:
                        router.navigateTo(Screens.FRAGMENT_WEATHER_WEEK, new FragmentWeatherWeek.InstantiateObject(getCoordinatesModel(), dayModel));
                }
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.release();
            adapter = null;
        }
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
        private CoordinatesModel coordinatesModel;

        public InstantiateObject(CoordinatesModel coordinatesModel) {
            this.coordinatesModel = coordinatesModel;
        }

        protected InstantiateObject(Parcel in) {
            coordinatesModel = in.readParcelable(CoordinatesModel.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(coordinatesModel, flags);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public CoordinatesModel getCoordinatesModel() {
            return coordinatesModel;
        }
    }
}
