//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.yandexweather.ui.recyclerview.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.example.yandexweather.utils.Inflater;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseViewHolder<BO, CA extends BaseRecyclerViewAdapter> extends ViewHolder implements BaseViewHolderInterface<BO> {
    private final WeakReference<CA> baseRecyclerViewAdapter;
    private final boolean useButterKnifeBinder;
    private Unbinder unbinder;
    private BO bindObject;

    public BaseViewHolder(CA baseRecyclerViewAdapter, @LayoutRes int layoutResId) {
        super(Inflater.inflate(baseRecyclerViewAdapter.getContext(), layoutResId));
        this.baseRecyclerViewAdapter = new WeakReference(baseRecyclerViewAdapter);
        this.useButterKnifeBinder = true;
        bindButterKnife();
    }

    public BaseViewHolder(CA baseRecyclerViewAdapter, View itemView) {
        super(itemView);
        this.baseRecyclerViewAdapter = new WeakReference(baseRecyclerViewAdapter);
        this.useButterKnifeBinder = true;
        bindButterKnife();
    }

    public BaseViewHolder(CA baseRecyclerViewAdapter, View itemView, boolean useButterKnifeBinder) {
        super(itemView);
        this.baseRecyclerViewAdapter = new WeakReference(baseRecyclerViewAdapter);
        this.useButterKnifeBinder = useButterKnifeBinder;
        if (useButterKnifeBinder)
            bindButterKnife();
    }

    public BaseViewHolder(CA baseRecyclerViewAdapter, @LayoutRes int layoutResId, boolean useButterKnifeBinder) {
        super(Inflater.inflate(baseRecyclerViewAdapter.getContext(), layoutResId));
        this.baseRecyclerViewAdapter = new WeakReference(baseRecyclerViewAdapter);
        this.useButterKnifeBinder = useButterKnifeBinder;
        if (useButterKnifeBinder)
            bindButterKnife();
    }

    public BaseViewHolder(CA baseRecyclerViewAdapter, ViewGroup rootView, @LayoutRes int layoutResId, boolean useButterKnifeBinder) {
        super(Inflater.inflate(baseRecyclerViewAdapter.getContext(), rootView, layoutResId));
        this.baseRecyclerViewAdapter = new WeakReference(baseRecyclerViewAdapter);
        this.useButterKnifeBinder = useButterKnifeBinder;
        if (useButterKnifeBinder)
            bindButterKnife();
    }

    public void onBind(int position, BO object) throws AdapterLostException {
        this.bindObject = object;
        if (useButterKnifeBinder && unbinder == null) bindButterKnife();
    }

    public void onViewRecycled() {
        if (this.unbinder != null) {
            this.unbinder.unbind();
            this.unbinder = null;
        }
        this.bindObject = null;
    }

    public Context getContext() throws AdapterLostException {
        return this.getAdapter().getContext();
    }

    public int getContentItemPosition() throws AdapterLostException {
        return this.getAdapter().getContentItemPosition(this);
    }

    public int getFooterItemPosition() throws AdapterLostException {
        return this.getAdapter().getFooterItemPosition(this);
    }

    public RecyclerView getRecyclerView() throws AdapterLostException {
        return this.getAdapter().getRecyclerView();
    }

    public BO getBindObject() {
        return this.bindObject;
    }

    public void setBindObject(BO bindObject) {
        this.bindObject = bindObject;
    }

    public CA getAdapter() throws AdapterLostException {
        if (baseRecyclerViewAdapter == null || baseRecyclerViewAdapter.get() == null)
            throw new AdapterLostException();
        return baseRecyclerViewAdapter.get();
    }

    protected String getString(int stringResId) throws AdapterLostException {
        return this.getContext().getString(stringResId);
    }

    protected float getDimension(int dimenResId) throws AdapterLostException {
        return this.getContext().getResources().getDimension(dimenResId);
    }

    protected int getColor(int colorResId) throws AdapterLostException {
        return ContextCompat.getColor(this.getContext(), colorResId);
    }

    private void bindButterKnife() {
        if (unbinder == null)
            unbinder = ButterKnife.bind(this, this.itemView);
    }

    public static class AdapterLostException extends Throwable {
    }

}