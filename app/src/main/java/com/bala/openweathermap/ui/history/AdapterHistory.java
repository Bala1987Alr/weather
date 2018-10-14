package com.bala.openweathermap.ui.history;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bala.openweathermap.R;
import com.bala.openweathermap.api.response.ResWeather;
import com.bala.openweathermap.databinding.ItemWeatherHistoryBinding;
import com.bala.openweathermap.ui.base.BaseActivity;
import com.bala.openweathermap.ui.detail.AdapterWeathers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.realm.RealmResults;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.ViewHolder> {
    private BaseActivity activity;
    private RealmResults<ResWeather> weathers;
    DateFormat dateFoirmat = new SimpleDateFormat("dd/MM/yy HH:mm");

    public AdapterHistory(BaseActivity activity, RealmResults<ResWeather> weathers) {
        this.activity = activity;
        this.weathers = weathers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemWeatherHistoryBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.item_weather_history, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.onBind(weathers.get(i));
    }

    @Override
    public int getItemCount() {
        return weathers != null ? weathers.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemWeatherHistoryBinding binding;
        private AdapterWeathers adapterWeathers;

        public ViewHolder(@NonNull ItemWeatherHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.rvweathers.setLayoutManager(new LinearLayoutManager(activity));
        }

        void onBind(ResWeather weather) {
            binding.tvName.setText(weather.getName().isEmpty() ? " - " : weather.getName());
            binding.tvDate.setText(dateFoirmat.format(weather.getCreatedOn()));
            binding.tvCoordinate.setText(activity.getString(R.string.history_label_cordinate
                    , weather.getCoord().getLat() + ""
                    , weather.getCoord().getLon() + ""));

            if (adapterWeathers == null) {
                adapterWeathers = new AdapterWeathers(activity, weather.getWeather());
                binding.rvweathers.setAdapter(adapterWeathers);
            }
            adapterWeathers.setWeather(weather.getWeather());

        }
    }
}
