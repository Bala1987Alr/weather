package com.bala.openweathermap.api;


import com.bala.openweathermap.BuildConfig;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static <T> T getClient(Class<T> c) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(getOkhttpClient())
                .build().create(c);
    }



    private static OkHttpClient getOkhttpClient(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
      /*  if(BuildConfig.DEBUG)
        builder.addNetworkInterceptor()*/
       return builder.build();
    }
}
