package com.example.stud.musicapp.api;

/**
 * Created by W57020 on 2018-04-19.
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiClinet {

    @GET("trending.php")
    Call<TrendingList> getTrendingList(@Query("country") String country, @Query("type") String type, @Query("format") String format);

}
