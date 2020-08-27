package com.vk59.coronastat.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.vk59.coronastat.R;
import com.vk59.coronastat.databinding.ActivityMainBinding;
import com.vk59.coronastat.presenter.MainPresenter;

public class MainActivity extends MvpAppCompatActivity implements IMainView {

    @InjectPresenter
    public MainPresenter presenter;

    private SwipeRefreshLayout layoutRefresh;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =
                DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setPresenter(presenter);
    }


    @Override
    public void failure() {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), R.string.message_failure,
                Snackbar.LENGTH_LONG).setAction(R.string.failure_answer,
                v -> presenter.loadData());
        snackbar.show();
    }

    private SwipeRefreshLayout.OnRefreshListener onUpdateListener =
            new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    presenter.loadData();
                }
            };
}