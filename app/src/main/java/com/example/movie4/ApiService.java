package com.example.movie4;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movies/{id}")
    Call<Movie> getData(@Path("id") int id);
}

