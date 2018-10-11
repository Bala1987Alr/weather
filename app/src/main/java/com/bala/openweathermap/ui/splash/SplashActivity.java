package com.bala.openweathermap.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bala.openweathermap.R;
import com.bala.openweathermap.ui.map.MapActivity;
import com.bala.openweathermap.ui.base.BaseActivity;
import com.bala.openweathermap.ui.splash.contractor.ICSplash;
import com.bala.openweathermap.ui.splash.presenter.PSplash;


public class SplashActivity extends BaseActivity implements ICSplash.IVSplash {
    private ICSplash.IPSplash<ICSplash.IVSplash> ipSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ipSplash = new PSplash<>();
        ipSplash.onAttach(this);
        ipSplash.onNavigateToNextViewWithDelay();
    }

    @Override
    public void onNavigateToHome() {
        MapActivity.startActivity(SplashActivity.this);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ipSplash != null)
            ipSplash.onDetach();
    }
}
