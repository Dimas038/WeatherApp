package com.example.yandexweather.ui.recyclerview.base;

public interface BaseViewHolderInterface<T> {
    void onBind(int position, T object) throws BaseViewHolder.AdapterLostException;

    void onViewRecycled();
}
