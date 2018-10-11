package com.bala.openweathermap.ui.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bala.openweathermap.BuildConfig;
import com.bala.openweathermap.R;
import com.bala.openweathermap.api.APIConstants;
import com.bala.openweathermap.api.response.ResWeather;
import com.bala.openweathermap.ui.base.BaseActivity;
import com.bala.openweathermap.ui.map.contractor.ICMap;
import com.bala.openweathermap.ui.map.presenter.PMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.HashMap;
import java.util.Map;


public class MapActivity extends BaseActivity implements OnMapReadyCallback, ICMap.IVMap {
    private GoogleMap googleMap;
    private ICMap.IPMap<ICMap.IVMap> ipMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ipMap = new PMap<>();
        ipMap.onAttach(this);
        setContentView(R.layout.activity_map);
        Map<String,String> params=new HashMap<>();
        params.put("q","London");
        params.put(APIConstants.APPID,BuildConfig.APPID);
        ipMap.launchGetWeatherAPI(params);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MapActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

    }

    @Override
    public void onWeatherAPISuccess(ResWeather response) {

    }

    @Override
    public void onWeatherAPIFailed() {
        Toast.makeText(this, getString(R.string.api_weather_failed), Toast.LENGTH_SHORT).show();
    }
}
