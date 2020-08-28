package com.vk59.coronastat.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.vk59.coronastat.R;
import com.vk59.coronastat.model.CountryData;
import com.vk59.coronastat.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements IMainView {

    @InjectPresenter
    public MainPresenter presenter;

    private SwipeRefreshLayout layoutRefresh;
    private TextView textStatistics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textStatistics = findViewById(R.id.textStatistics);
        layoutRefresh = findViewById(R.id.layoutRefresh);

        layoutRefresh.setOnRefreshListener(onUpdateListener);
    }


    @Override
    public void loading() {
        layoutRefresh.setRefreshing(true);
    }

    @Override
    public void success(CountryData data) {
        textStatistics.setText(data.toString());
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