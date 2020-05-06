package com.example.yandexweather.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.Glide;
import com.example.yandexweather.App;
import com.example.yandexweather.R;
import com.example.yandexweather.Screens;
import com.example.yandexweather.main.FragmentCoordinatesMvpPresenter;
import com.example.yandexweather.main.FragmentCoordinatesMvpView;
import com.example.yandexweather.model.CoordinatesModel;
import com.example.yandexweather.retrofit.response.GeoObjectCollectionGetResponse;
import com.example.yandexweather.retrofit.response.GeopositionResponse;
import com.example.yandexweather.ui.base.fragment.BaseFragment;
import com.example.yandexweather.ui.common.BackButtonListener;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import ru.terrakok.cicerone.Router;

public class FragmentCoordinates extends BaseFragment implements FragmentCoordinatesMvpView, BackButtonListener {

    @Inject
    Router router;
    @InjectPresenter
    FragmentCoordinatesMvpPresenter presenter;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.background)
    ImageView background;

    public static FragmentCoordinates getNewInstance() {
        FragmentCoordinates fragment = new FragmentCoordinates();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @ProvidePresenter
    public FragmentCoordinatesMvpPresenter createPresenter() {
        return new FragmentCoordinatesMvpPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.INSTANCE.getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewInflated(View view, @Nullable Bundle savedInstanceState) {
        Glide.with(this).load(R.drawable.night).into(background);
    }

    @Override
    public int onInflateLayout() {
        return R.layout.fragment_coordinates;
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

    @OnClick(R.id.clickableView)
    void clickableView() {
        if (editText.getText() != null && editText.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), R.string.type_city, Toast.LENGTH_LONG).show();
        } else
            presenter.load(editText.getText().toString(), getContext());
    }

    @Override
    public void onComplete(GeopositionResponse<GeoObjectCollectionGetResponse> geopositionResponse) {
        if (geopositionResponse.getResponse().getGeoObjectCollection().getFeatureMember().size() == 0) {
            Toast.makeText(getActivity(), R.string.city_was_typed_incorrectly, Toast.LENGTH_LONG).show();
        } else {
            String coordinates = geopositionResponse.getResponse().getGeoObjectCollection().getFeatureMember().get(0).getGeoObject().getPoint().getPos();
            String latLon[] = coordinates.split(" ");
            double lat = Double.parseDouble(latLon[1]);
            double lon = Double.parseDouble(latLon[0]);

            CoordinatesModel coordinatesModel = new CoordinatesModel(lat, lon, editText.getText().toString());
            router.navigateTo(Screens.FRAGMENT_DAY, new FragmentDay.InstantiateObject(coordinatesModel));
        }
    }

    @Override
    public void onError() {
        router.exitWithMessage("ERROR");
    }
}
