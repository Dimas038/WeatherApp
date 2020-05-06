package com.example.yandexweather.ui.recyclerview.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewSinglePageAdapter<Type> extends BaseRecyclerViewAdapter {

    private final List<Type> models = new ArrayList();

    public BaseRecyclerViewSinglePageAdapter(RecyclerView recyclerView) {
        super(recyclerView);
    }

    public BaseRecyclerViewSinglePageAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getContentItemCount() {
        return models.size();
    }

    @Override
    public void release() {
    }

    public List<Type> getModels() {
        return models;
    }

    public void setModels(List<Type> models) {
        setModels(models, true);
    }

    public void setModels(List<Type> models, boolean notify) {
        this.models.clear();
        if (models != null)
            this.models.addAll(models);
        if (notify)
            notifyDataSetChanged();
    }

    @Override
    protected int getHeaderItemCount() {
        return 0;
    }

    @Override
    protected int getFooterItemCount() {
        return 0;
    }
}
