package com.bala.openweathermap.ui.base;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements IBaseView{
    @Override
    public BaseActivity getBaseActivty() {
        return null;
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
