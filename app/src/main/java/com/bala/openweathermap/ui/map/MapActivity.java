package com.bala.openweathermap.ui.map;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.bala.openweathermap.BuildConfig;
import com.bala.openweathermap.R;
import com.bala.openweathermap.api.APIConstants;
import com.bala.openweathermap.api.response.ResWeather;
import com.bala.openweathermap.databinding.ActivityMapBinding;
import com.bala.openweathermap.ui.base.BaseActivity;
import com.bala.openweathermap.ui.map.contractor.ICMap;
import com.bala.openweathermap.ui.map.presenter.PMap;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;


public class MapActivity extends BaseActivity implements OnMapReadyCallback, ICMap.IVMap {
    private GoogleMap googleMap;
    private ICMap.IPMap<ICMap.IVMap> ipMap;
    private ActivityMapBinding binding;

    private ResWeather resWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ipMap = new PMap<>();
        ipMap.onAttach(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapWeather);
        mapFragment.getMapAsync(this);
        binding.ivSearch.setOnClickListener(v -> {

        });
        hideKeyboard();


        binding.etSearchLocation.setOnEditorActionListener((v, actionId, event) -> {
            if  ((actionId == EditorInfo.IME_ACTION_SEARCH)) {
                Map<String, String> params = new HashMap<>();
                params.put("q", binding.etSearchLocation.getText().toString());
                params.put(APIConstants.APPID, BuildConfig.APPID);
                ipMap.launchGetWeatherAPI(params);
                hideKeyboard();
                return true;
            }
            return false;
        });

    }



    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MapActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng initialLoc = googleMap.getCameraPosition().target;
        LatLng coordinate = new LatLng(initialLoc.latitude, initialLoc.longitude); //Store these lat lng values somewhere. These should be constant.
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinate, 5);
        googleMap.animateCamera(location);

    }

    @Override
    public void onWeatherAPISuccess(ResWeather response) {
        this.resWeather = resWeather;
        googleMap.clear();
        LatLng coordinate = new LatLng(response.getCoord().getLat(), response.getCoord().getLon());
        googleMap.addMarker(new MarkerOptions().position(coordinate)
                .title(""));


        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinate, 15);
        googleMap.animateCamera(location);
    }

    @Override
    public void onWeatherAPIFailed() {
        Toast.makeText(this, getString(R.string.api_weather_failed), Toast.LENGTH_SHORT).show();
    }
}
