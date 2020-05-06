package com.example.yandexweather.ui.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yandexweather.R;
import com.example.yandexweather.model.DayModel;
import com.example.yandexweather.ui.recyclerview.base.BaseRecyclerViewAdapter;
import com.example.yandexweather.ui.recyclerview.base.BaseRecyclerViewSinglePageAdapter;
import com.example.yandexweather.ui.recyclerview.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DayModelsAdapter extends BaseRecyclerViewSinglePageAdapter<DayModel> {

    public static final int TYPE_TODAY = 1;
    public static final int TYPE_WEEK = 2;

    private OnClickListener listener;

    public DayModelsAdapter(RecyclerView recyclerView, OnClickListener listener) {
        super(recyclerView);
        this.listener = listener;
        List<DayModel> models = new ArrayList<>();
        models.add(new DayModel(TYPE_TODAY, getContext().getString(R.string.forecast_for_today)));
        models.add(new DayModel(TYPE_WEEK, getContext().getString(R.string.forecast_for_week)));
        setModels(models);
    }

    @Override
    protected int getHeaderItemCount() {
        return 0;
    }

    @Override
    protected int getFooterItemCount() {
        return 0;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateContentItemViewHolder(ViewGroup parent, int contentViewType) {
        return new DayViewHolder(this);
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) throws BaseViewHolder.AdapterLostException {
        super.onBindContentItemViewHolder(contentViewHolder, position);
        DayViewHolder vh = (DayViewHolder) contentViewHolder;
        vh.onBind(position, getModels().get(position));
    }


    public interface OnClickListener {
        void onClick(DayModel dayModel);
    }

    protected class DayViewHolder extends BaseViewHolder<DayModel, BaseRecyclerViewAdapter> {

        @BindView(R.id.textViewDay)
        TextView textViewDay;

        public DayViewHolder(BaseRecyclerViewAdapter adapter) {
            super(adapter, R.layout.item_day);
        }

        @Override
        public void onBind(int position, DayModel model) throws AdapterLostException {
            super.onBind(position, model);
            textViewDay.setText(model.getTitle());
        }

        @OnClick(R.id.clickableView)
        void clickableView() {
            if (listener != null) listener.onClick(getBindObject());
        }
    }
}
