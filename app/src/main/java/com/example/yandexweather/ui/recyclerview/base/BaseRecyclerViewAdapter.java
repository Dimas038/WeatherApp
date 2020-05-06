package com.example.yandexweather.ui.recyclerview.base;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseRecyclerViewAdapter extends HeaderFooterRecyclerViewAdapter<ViewHolder> {

    private RecyclerView recyclerView;
    private Context context;

    public BaseRecyclerViewAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.context = recyclerView.getContext();
    }

    public BaseRecyclerViewAdapter(Context context) {
        this.recyclerView = null;
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }

    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        if (holder instanceof BaseViewHolder) {
            BaseViewHolder vh = (BaseViewHolder) holder;
            vh.onViewRecycled();
        }
    }

    public int getContentItemPosition(ViewHolder holder) {
        return holder.getAdapterPosition() - this.getHeaderItemCount();
    }

    public int getFooterItemPosition(ViewHolder holder) {
        return holder.getAdapterPosition() - this.getHeaderItemCount() - this.getContentItemCount();
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    protected String getString(int stringResId) {
        return getContext().getString(stringResId);
    }

    protected float getDimension(int dimenResId) {
        return getContext().getResources().getDimension(dimenResId);
    }

    protected int getColor(int colorResId) {
        return ContextCompat.getColor(getContext(), colorResId);
    }

    public void release() {
        this.recyclerView = null;
        this.context = null;
    }

    public BaseViewHolder getViewHolder(int position) {
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View child = recyclerView.getChildAt(i);
            if (child != null) {
                ViewHolder viewHolder = recyclerView.getChildViewHolder(child);
                if (viewHolder != null && viewHolder instanceof BaseViewHolder) {
                    BaseViewHolder baseViewHolder = (BaseViewHolder) viewHolder;
                    if (baseViewHolder.getAdapterPosition() == position)
                        return baseViewHolder;
                }
            }
        }
        return null;
    }

    @Override
    protected void onBindContentItemViewHolder(ViewHolder contentViewHolder, int position) throws BaseViewHolder.AdapterLostException {

    }

    @Override
    protected void onBindFooterItemViewHolder(ViewHolder viewHolder, int position) throws BaseViewHolder.AdapterLostException {

    }

    @Override
    protected void onBindHeaderItemViewHolder(ViewHolder viewHolder, int position) throws BaseViewHolder.AdapterLostException {

    }

    @Override
    protected ViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int footerViewType) {
        return null;
    }

    @Override
    protected ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
        return null;
    }

    @Override
    protected ViewHolder onCreateHeaderItemViewHolder(ViewGroup parent, int headerViewType) {
        return null;
    }
}
