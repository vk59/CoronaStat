package com.vk59.coronastat.API;

import com.vk59.coronastat.model.ResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("free-api")
    Call<ResponseData> getDataCountry(@Query("countryTotal") String country);
}
