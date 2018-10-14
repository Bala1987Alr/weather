package com.bala.openweathermap.ui.detail;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bala.openweathermap.R;
import com.bala.openweathermap.ui.base.BaseActivity;

import com.bala.openweathermap.databinding.ItemWeatherBinding;

public class AdapterWeathers extends RecyclerView.Adapter<AdapterWeathers.ViewHolder> {
    BaseActivity activity;

    public AdapterWeathers(BaseActivity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemWeatherBinding binding=DataBindingUtil.inflate(LayoutInflater.from(activity),R.layout.item_weather,viewGroup,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ItemWeatherBinding binding) {
            super(binding.getRoot());
        }
    }
}
