package com.bala.openweathermap.ui.map.contractor;

import com.bala.openweathermap.api.response.ResWeather;
import com.bala.openweathermap.ui.base.IBasePresenter;
import com.bala.openweathermap.ui.base.IBaseView;

import java.util.Map;

import retrofit2.http.QueryMap;

public interface ICMap {
    interface IVMap extends IBaseView {
        void onWeatherAPISuccess(ResWeather response);

        void onWeatherAPIFailed();
    }

    interface IPMap<V extends IVMap> extends IBasePresenter<V> {
        void launchGetWeatherAPI(Map<String,String> params);
    }
}
