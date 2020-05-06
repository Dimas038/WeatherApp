package com.example.yandexweather.ui.recyclerview.adapter;

import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.yandexweather.R;
import com.example.yandexweather.model.HourModel;
import com.example.yandexweather.ui.recyclerview.base.BaseRecyclerViewAdapter;
import com.example.yandexweather.ui.recyclerview.base.BaseRecyclerViewSinglePageAdapter;
import com.example.yandexweather.ui.recyclerview.base.BaseViewHolder;

import butterknife.BindView;

public class HoursModelsAdapter extends BaseRecyclerViewSinglePageAdapter<HourModel> {

    public HoursModelsAdapter(RecyclerView recyclerView) {
        super(recyclerView);
        recyclerView.addItemDecoration(new ItemOffsetDecoration());
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
        return new HoursViewHolder(this);
    }

    @Override
    protected void onBindContentItemViewHolder(RecyclerView.ViewHolder contentViewHolder, int position) throws BaseViewHolder.AdapterLostException {
        super.onBindContentItemViewHolder(contentViewHolder, position);
        HoursViewHolder vh = (HoursViewHolder) contentViewHolder;
        vh.onBind(position, getModels().get(position));
    }

    protected class HoursViewHolder extends BaseViewHolder<HourModel, BaseRecyclerViewAdapter> {

        @BindView(R.id.textViewHour)
        TextView textViewHour;
        @BindView(R.id.textViewTemperature)
        TextView textViewTemperature;
        @BindView(R.id.weatherIcon)
        ImageView weatherIcon;
        private RequestBuilder<PictureDrawable> requestBuilder;

        public HoursViewHolder(BaseRecyclerViewAdapter adapter) {
            super(adapter, R.layout.item_hour);
            requestBuilder =
                    Glide.with(HoursModelsAdapter.this.getContext())
                            .as(PictureDrawable.class);
        }

        @Override
        public void onBind(int position, HourModel model) throws AdapterLostException {
            super.onBind(position, model);
            textViewHour.setText(String.valueOf(model.getHour()));
            requestBuilder.load(model.getIcon()).into(weatherIcon);
            textViewTemperature.setText(model.getTemperature());
        }
    }

    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

        public ItemOffsetDecoration() {
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            long position = parent.getChildAdapterPosition(view);
            if (position == 0) {
                outRect.left = (int) parent.getContext().getResources().getDimension(R.dimen.divider_width_side);
                outRect.right = (int) parent.getContext().getResources().getDimension(R.dimen.divider_width);
            } else if (position == getContentItemCount() - 1) {
                outRect.left = (int) parent.getContext().getResources().getDimension(R.dimen.divider_width);
                outRect.right = (int) parent.getContext().getResources().getDimension(R.dimen.divider_width_side);
            } else {
                outRect.right = (int) parent.getContext().getResources().getDimension(R.dimen.divider_width);
                outRect.left = (int) parent.getContext().getResources().getDimension(R.dimen.divider_width);
            }
        }
    }
}
