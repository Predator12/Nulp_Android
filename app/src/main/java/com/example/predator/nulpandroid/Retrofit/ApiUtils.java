package com.example.predator.nulpandroid.Retrofit;

/**
 * Created by Predator on 08.11.2018.
 */
public class ApiUtils {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/";

    public static Service getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(Service.class);
    }
}