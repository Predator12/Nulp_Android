package com.example.predator.nulpandroid.Retrofit;

import com.example.predator.nulpandroid.Models.Result;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Predator on 08.11.2018.
 */
public interface Service {
    @GET("character/?page=1")
    Call<Result> getCharacters();
}
