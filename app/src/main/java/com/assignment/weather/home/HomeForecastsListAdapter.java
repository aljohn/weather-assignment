package com.assignment.weather.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.weather.R;
import com.assignment.weather.common.Utils;
import com.assignment.weather.common.pojos.DayForecast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeForecastsListAdapter extends RecyclerView.Adapter<HomeForecastsListAdapter.ViewHolder> {

    private List<DayForecast> dayForecastList;
    private ViewHolder.ItemClickListener itemClickListener;
    private ItemClickListener externalListener;

    public HomeForecastsListAdapter() {
        dayForecastList = new ArrayList<>();
        itemClickListener = position -> {
            if (externalListener != null) {
                externalListener.onClick(dayForecastList.get(position));
            }
        };
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        return new ViewHolder(itemView, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dayForecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return dayForecastList.size();
    }

    public void setOnItemClickListener(ItemClickListener listener) {
        this.externalListener = listener;
    }

    public void setData(List<DayForecast> next10DaysList) {
        dayForecastList.clear();
        dayForecastList.addAll(next10DaysList);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_weather_icon)
        ImageView imgWeatherIcon;
        @BindView(R.id.txt_date)
        TextView txtDate;
        @BindView(R.id.txt_temp)
        TextView txtTemperature;
        @BindView(R.id.txt_forecast)
        TextView txtForecast;

        ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                if (itemClickListener != null){
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onClick(position);
                    }
                }
            });
        }

        void bind(DayForecast dayForecast) {
            txtDate.setText(Utils.formatDate(dayForecast.getDate()));
            txtTemperature.setText(itemView.getContext().getString(R.string.temp_degrees, String.valueOf(dayForecast.getTempAverage())));
            txtForecast.setText(dayForecast.getDescription());
            imgWeatherIcon.setImageResource(Utils.getDrawableResByName(itemView.getResources(),
                    dayForecast.getIcon(), itemView.getContext().getPackageName()));
        }

        interface ItemClickListener {
            void onClick(int position);
        }
    }

    interface ItemClickListener {
        void onClick(DayForecast dayForecast);
    }
}
