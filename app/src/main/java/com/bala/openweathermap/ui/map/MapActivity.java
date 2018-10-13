package com.bala.openweathermap.ui.map;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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

    private boolean isCameraMovedManually = false;
    private boolean isCameraMovedAutomatically = false;
    private boolean canZoom = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ipMap = new PMap<>();
        ipMap.onAttach(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapWeather);
        mapFragment.getMapAsync(this);
        binding.ivHistory.setOnClickListener(v -> {

        });
        hideKeyboard();


        binding.etSearchLocation.setOnEditorActionListener((v, actionId, event) -> {
            if ((actionId == EditorInfo.IME_ACTION_SEARCH && googleMap != null && !binding.etSearchLocation.getText().toString().isEmpty())) {
                Map<String, String> params = new HashMap<>();
                params.put("q", binding.etSearchLocation.getText().toString());
                params.put(APIConstants.APPID, BuildConfig.APPID);
                ipMap.launchGetWeatherAPI(params);
                hideKeyboard();
                return true;
            }
            return false;
        });

        binding.fabSearch.setOnClickListener(v -> {
            if (googleMap != null) {
                Map<String, String> params = new HashMap<>();
                LatLng latLng = googleMap.getCameraPosition().target;
                params.put("lat", latLng.latitude + "");
                params.put("lon", latLng.longitude + "");
                params.put(APIConstants.APPID, BuildConfig.APPID);
                ipMap.launchGetWeatherAPI(params);
                hideKeyboard();
            }
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
                coordinate, 3);
        googleMap.animateCamera(location);
        isCameraMovedManually = false;
        isCameraMovedAutomatically = true;

        googleMap.setOnCameraIdleListener(() -> {

            if (isCameraMovedManually && !isCameraMovedAutomatically)
                binding.fabSearch.show();
            else
                binding.fabSearch.hide();

            isCameraMovedManually = false;
            isCameraMovedAutomatically = false;
        });

        googleMap.setOnCameraMoveStartedListener(i -> {
            if (!isCameraMovedAutomatically)
                isCameraMovedManually = true;
        });
    }

    @Override
    public void onWeatherAPISuccess(ResWeather response) {
        isCameraMovedManually = false;
        isCameraMovedAutomatically = true;
        this.resWeather = resWeather;
        googleMap.clear();
        LatLng coordinate = new LatLng(response.getCoord().getLat(), response.getCoord().getLon());
        googleMap.addMarker(new MarkerOptions().position(coordinate)
                .title(""));


        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinate, canZoom ? 13 : googleMap.getCameraPosition().zoom);
        canZoom = false;
        googleMap.animateCamera(location);
    }

    @Override
    public void onWeatherAPIFailed() {
        Toast.makeText(this, getString(R.string.api_weather_failed), Toast.LENGTH_SHORT).show();
    }
}
