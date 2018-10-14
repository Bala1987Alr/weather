package com.bala.openweathermap.ui.detail;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bala.openweathermap.R;
import com.bala.openweathermap.api.response.ResWeather;
import com.bala.openweathermap.databinding.FragmentWeatherDetailDialogBinding;
import com.bala.openweathermap.databinding.FragmentWeatherDetailDialogBindingImpl;
import com.bala.openweathermap.ui.base.BaseDialogFragment;

import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherDetailDialogFragment extends BaseDialogFragment {
    private FragmentWeatherDetailDialogBinding binding;
    Realm realm;

    public WeatherDetailDialogFragment() {
        // Required empty public constructor
    }

    public static WeatherDetailDialogFragment newInstance(long id) {
        WeatherDetailDialogFragment fragment = new WeatherDetailDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("id", id);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_detail_dialog, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed())
            realm.close();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvweathers.setLayoutManager(new LinearLayoutManager(getActivity()));
        long id = getArguments().getLong("id");
        realm = Realm.getDefaultInstance();
        ResWeather resWeather = realm.where(ResWeather.class).equalTo("id", id).findFirst();
        if (resWeather != null) {
            binding.rvweathers.setAdapter(new AdapterWeathers(getBaseActivty(), resWeather.getWeather()));
            binding.tvHeader.setText(resWeather.getName());
        }

        binding.ivClose.setOnClickListener(v -> {
            getDialog().dismiss();
        });

    }
}
