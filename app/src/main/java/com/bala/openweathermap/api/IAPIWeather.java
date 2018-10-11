package com.bala.openweathermap.api;


import com.bala.openweathermap.api.response.ResWeather;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

public interface IAPIWeather {
    @GET("weather")
    public Single<ResWeather> getWeather(@QueryMap Map<String, String> queryParams);
}
