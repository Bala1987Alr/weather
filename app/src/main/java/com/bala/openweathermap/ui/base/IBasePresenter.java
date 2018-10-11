package com.bala.openweathermap.ui.base;

public interface IBasePresenter<V extends IBaseView> {

    boolean isViewAttached();

    void onAttach(V mvpView);

    void onDetach();

    V getMvpView();
}
