package com.example.yandexweather.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Inflater {

    private final LayoutInflater layoutInflater;

    private Inflater(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    public static Inflater create(Context context) {
        return new Inflater(context);
    }

    public static View inflate(Context context, int layout) {
        return inflate(context, null, layout);
    }

    public static View inflate(Context context, ViewGroup root, int layout) {
        return LayoutInflater.from(context).inflate(layout, root);
    }

    public View inflate(int layout) {
        return inflate(layout, null);
    }

    public View inflate(int layout, ViewGroup root) {
        return layoutInflater.inflate(layout, root);
    }
}