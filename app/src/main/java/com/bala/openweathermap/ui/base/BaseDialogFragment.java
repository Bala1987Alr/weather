package com.bala.openweathermap.ui.base;

import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment implements IBaseView {

    @Override
    public BaseActivity getBaseActivty() {
        return (BaseActivity) getActivity();
    }

    @Override
    public boolean isNetworkConnected() {
        return getActivity() != null && ((BaseActivity) getActivity()).isNetworkConnected();
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

    @Override
    public void hideKeyboard() {
        if (getActivity() != null)
            ((BaseActivity) getActivity()).hideKeyboard();
    }

}
