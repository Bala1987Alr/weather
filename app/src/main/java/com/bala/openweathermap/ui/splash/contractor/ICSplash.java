package com.bala.openweathermap.ui.splash.contractor;

import com.bala.openweathermap.ui.base.IBasePresenter;
import com.bala.openweathermap.ui.base.IBaseView;

public interface ICSplash {
    interface IVSplash extends IBaseView {
        void onNavigateToHome();
    }

    interface IPSplash<V extends ICSplash.IVSplash> extends IBasePresenter<V> {
        void onNavigateToNextViewWithDelay();
    }
}
