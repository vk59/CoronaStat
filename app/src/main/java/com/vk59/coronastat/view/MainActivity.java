package com.vk59.coronastat.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.vk59.coronastat.API.APIConfig;
import com.vk59.coronastat.API.APIService;
import com.vk59.coronastat.API.APIServiceConstructor;
import com.vk59.coronastat.R;
import com.vk59.coronastat.model.CountryData;
import com.vk59.coronastat.model.ResponseData;
import com.vk59.coronastat.presenter.MainPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends MvpAppCompatActivity implements IMainView {

    @InjectPresenter
    public MainPresenter presenter;

    private TextView textData;
    private SwipeRefreshLayout layoutRefresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textData = findViewById(R.id.textData);
        layoutRefresh = findViewById(R.id.layoutRefresh);
        layoutRefresh.setOnRefreshListener(onUpdateListener);
    }

    @Override
    public void loading() {
        layoutRefresh.setRefreshing(true);
    }

    @Override
    public void success(CountryData data) {
        textData.setText(data.toString());
        layoutRefresh.setRefreshing(false);
    }

    @Override
    public void failure() {
        Snackbar snackbar = Snackbar.make(layoutRefresh, R.string.message_failure,
                Snackbar.LENGTH_LONG).setAction(R.string.failure_answer,
                v -> presenter.loadData());
        snackbar.show();
        layoutRefresh.setRefreshing(false);
    }

    private SwipeRefreshLayout.OnRefreshListener onUpdateListener =
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    presenter.loadData();
                }
            };
}