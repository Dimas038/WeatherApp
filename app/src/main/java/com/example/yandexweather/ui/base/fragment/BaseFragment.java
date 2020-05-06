package com.example.yandexweather.ui.base.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends MvpAppCompatFragment {

    public static final String INSTANTIATE_OBJECT = "INSTANTIATE_OBJECT";

    private final boolean useButterKnifeBinder;

    private Unbinder unbinder;
    private View view;

    public BaseFragment(boolean useButterKnifeBinder) {
        this.useButterKnifeBinder = useButterKnifeBinder;
    }

    public BaseFragment() {
        this.useButterKnifeBinder = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(onInflateLayout(), container, false);
        if (useButterKnifeBinder) unbinder = ButterKnife.bind(this, view);
        onViewInflated(view, savedInstanceState);
        return view;
    }

    @Override
    public void onDestroyView() {
        if (useButterKnifeBinder)
            releaseButterKnife();
        super.onDestroyView();
    }

    private void releaseButterKnife() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    public abstract void onViewInflated(View view, @Nullable Bundle savedInstanceState);

    public abstract @LayoutRes
    int onInflateLayout();
}
