package com.vk59.coronastat.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vk59.coronastat.API.APIConfig;
import com.vk59.coronastat.API.APIService;
import com.vk59.coronastat.API.APIServiceConstructor;
import com.vk59.coronastat.model.ResponseData;
import com.vk59.coronastat.view.IMainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class MainPresenter extends MvpPresenter<IMainView> {
    private APIService apiService;

    public MainPresenter() {
        apiService = APIServiceConstructor.CreateService(APIService.class);
        loadData();
    }

    public void loadData() {
        getViewState().loading();
        Call<ResponseData> call = apiService.getDataCountry(APIConfig.COUNTRY);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body() != null) {
                    getViewState().success(response.body().getCountryData().get(0));
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                getViewState().failure();
            }
        });
    }
}
