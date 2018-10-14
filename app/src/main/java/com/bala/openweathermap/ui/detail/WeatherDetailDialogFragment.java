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
import com.bala.openweathermap.databinding.FragmentWeatherDetailDialogBindingImpl;
import com.bala.openweathermap.ui.base.BaseDialogFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherDetailDialogFragment extends BaseDialogFragment {
    private FragmentWeatherDetailDialogBindingImpl binding;

    public WeatherDetailDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather_detail_dialog, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvweathers.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rvweathers.setAdapter(new AdapterWeathers(getBaseActivty()));

    }
}
