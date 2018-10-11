package com.bala.openweathermap.ui.map.presenter;

import com.bala.openweathermap.api.APIClient;
import com.bala.openweathermap.api.IAPIWeather;
import com.bala.openweathermap.ui.base.BasePresenter;
import com.bala.openweathermap.ui.map.contractor.ICMap;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PMap<V extends ICMap.IVMap> extends BasePresenter<V> implements ICMap.IPMap<V> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    public void onDetach() {
        super.onDetach();
        if (!compositeDisposable.isDisposed()) compositeDisposable.dispose();
    }

    @Override
    public void launchGetWeatherAPI(Map<String, String> params) {
        IAPIWeather iapiWeather = APIClient.getClient(IAPIWeather.class);
        compositeDisposable.add(
                iapiWeather.getWeather(params)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                response -> {
                                    if (getMvpView() != null)
                                        getMvpView().onWeatherAPISuccess(response);
                                    System.out.println(" response ______"+response.toString());
                                },
                                error -> {
                                    if (getMvpView() != null) getMvpView().onWeatherAPIFailed();
                                    error.printStackTrace();
                                }
                        )
        );

    }
}
