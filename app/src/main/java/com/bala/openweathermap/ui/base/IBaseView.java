package com.bala.openweathermap.ui.base;

public interface IBaseView {
    BaseActivity getBaseActivty();

    boolean isNetworkConnected();

    void initUI();

    void showLoading();

    void hideLoading();

    void hideKeyboard();
}
