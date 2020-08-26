package com.vk59.coronastat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.vk59.coronastat.API.APIConfig;
import com.vk59.coronastat.API.APIService;
import com.vk59.coronastat.API.APIServiceConstructor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textData = findViewById(R.id.textData);

        APIService service = APIServiceConstructor.CreateService(APIService.class);
        Call<ResponseData> call = service.getDataCountry(APIConfig.COUNTRY);
        call.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                if (response.body() != null) {
                    textData.setText(response.body().getCountryData().get(0).toString());
                }
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {

            }
        });
    }
}