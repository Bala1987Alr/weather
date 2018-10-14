package com.bala.openweathermap.ui.history;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bala.openweathermap.R;
import com.bala.openweathermap.api.response.ResWeather;
import com.bala.openweathermap.databinding.ActivityHistoryBinding;
import com.bala.openweathermap.ui.base.BaseActivity;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class HistoryActivity extends BaseActivity {
    private ActivityHistoryBinding binding;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history);
        setSupportActionBar(binding.tbHistory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        realm = Realm.getDefaultInstance();
        RealmResults<ResWeather> resWeathers = realm.where(ResWeather.class).findAll().sort("createdOn", Sort.DESCENDING);

        AdapterHistory adapterHistory = new AdapterHistory(getBaseActivty(), resWeathers);
        binding.rvHistory.setLayoutManager(new LinearLayoutManager(this));
        binding.rvHistory.setAdapter(adapterHistory);

    }

    public static void open(Activity activity) {
        activity.startActivity(new Intent(activity, HistoryActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed())
            realm.close();
    }
}
