package com.example.stud.musicapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by W57020 on 2018-04-19.
 */

public class ApiService {

    private static ApiClinet service;

    public static ApiClinet get_Service() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.theaudiodb.com/api/v1/json/1/")

                 .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiClinet.class);


    }


    public static ApiClinet getService() {
        return service;
    }
}
