package com.example.predator.nulpandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.predator.nulpandroid.Models.Result;
import com.example.predator.nulpandroid.Retrofit.ApiUtils;
import com.example.predator.nulpandroid.Retrofit.Service;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Service mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = ApiUtils.getSOService();
        getNews();

    }

    public void getNews() {
        mApiService.getCharacters().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String json = gson.toJson(response.body());

                    Log.i("Api Json", json);
                } else {
                    Log.e("Error", "News don't load");
                    Toast.makeText(getApplicationContext(), "error loading from API",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
                Toast.makeText(getApplicationContext(), "error loading from API",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
