package com.bala.openweathermap.ui.splash.presenter;

import com.bala.openweathermap.ui.base.BasePresenter;
import com.bala.openweathermap.ui.splash.contractor.ICSplash;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.disposables.CompositeDisposable;

public class PSplash<V extends ICSplash.IVSplash> extends BasePresenter<V> implements ICSplash.IPSplash<V> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onNavigateToNextViewWithDelay() {
        compositeDisposable.add(Completable.timer(3, TimeUnit.SECONDS).subscribe(() -> {
            if (getMvpView() != null) getMvpView().onNavigateToHome();
        }));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (!compositeDisposable.isDisposed()) compositeDisposable.dispose();
    }
}
