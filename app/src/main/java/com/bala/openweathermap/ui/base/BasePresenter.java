package com.bala.openweathermap.ui.base;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    private V mvpView;

    @Override
    public boolean isViewAttached() {
        return mvpView != null;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }

    @Override
    public V getMvpView() {
        return mvpView;
    }
}
