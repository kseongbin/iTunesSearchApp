package com.example.itunessearchapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {
    @GET("/search")
    Call<RecyclerViewItem> searchItunesApp(@Query("term") String term, @Query("country") String country, @Query("entity") String entity);
}
