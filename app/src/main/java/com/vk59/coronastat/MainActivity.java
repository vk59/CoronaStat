package com.vk59.coronastat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.vk59.coronastat.API.APIConfig;
import com.vk59.coronastat.API.APIService;
import com.vk59.coronastat.API.APIServiceConstructor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textData;
    private SwipeRefreshLayout layoutRefresh;
    private APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textData = findViewById(R.id.textData);
        layoutRefresh = findViewById(R.id.layoutRefresh);
        layoutRefresh.setOnRefreshListener(onUpdateListener);

        loadData();
    }

    private void loadData() {
        layoutRefresh.setRefreshing(true);
        apiService = APIServiceConstructor.CreateService(APIService.class);
        Call<ResponseData> call = apiService.getDataCountry(APIConfig.COUNTRY);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body() != null) {
                    textData.setText(response.body().getCountryData().get(0).toString());
                }
                layoutRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Snackbar snackbar = Snackbar.make(layoutRefresh, R.string.message_failure,
                        Snackbar.LENGTH_LONG).setAction(R.string.failure_answer,
                        v -> loadData());
                snackbar.show();
                layoutRefresh.setRefreshing(false);
            }
        });
    }

    private SwipeRefreshLayout.OnRefreshListener onUpdateListener =
            new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadData();
        }
    };
}