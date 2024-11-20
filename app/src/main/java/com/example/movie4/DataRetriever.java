package com.example.movie4;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRetriever {
    private static final String BASE_URL = "http://localhost:8080/";
    public ApiService apiService;

    public DataRetriever() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

}

