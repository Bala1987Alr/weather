package com.bala.openweathermap.api;

import com.practice.openweathermap.api.response.ResWeather;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface IAPIWeather {
    @GET("weather")
    public Single<ResWeather> getWeather(@QueryMap HashMap<String, String> queryParams);
}
