package com.bala.openweathermap.ui.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bala.openweathermap.R;
import com.bala.openweathermap.api.response.WeatherItem;
import com.bala.openweathermap.ui.base.BaseActivity;

import com.bala.openweathermap.databinding.ItemWeatherBinding;

import io.realm.RealmList;


public class AdapterWeathers extends RecyclerView.Adapter<AdapterWeathers.ViewHolder> {
    BaseActivity activity;
    RealmList<WeatherItem> weather;

    public AdapterWeathers(BaseActivity activity, RealmList<WeatherItem> weather) {
        this.activity = activity;
        this.weather = weather;
    }

    public void setWeather(RealmList<WeatherItem> weather) {
        this.weather = weather;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemWeatherBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_weather, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.onBind(i);
    }

    @Override
    public int getItemCount() {
        return weather.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemWeatherBinding binding;

        public ViewHolder(@NonNull ItemWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(int position) {
            binding.tvTitle.setText(weather.get(position).getMain());
            binding.tvDesc.setText(weather.get(position).getDescription());
        }
    }
}
