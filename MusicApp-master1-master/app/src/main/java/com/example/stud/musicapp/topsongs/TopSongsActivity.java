package com.example.stud.musicapp.topsongs;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.stud.musicapp.R;
import com.example.stud.musicapp.api.ApiService;
import com.example.stud.musicapp.api.TrendingList;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static sun.text.bidi.BidiBase.R;

public class TopSongsActivity extends AppCompatActivity {

    RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_songs);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvList = findViewById(R.id.rvList);
        TopSongsAdapter topSongsAdapter = new TopSongsAdapter(trendingSingles);
        rvList.setAdapter(topSongsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rvList.setLayoutManager(linearLayoutManager);


        Call<TrendingList> trendingListCall = ApiService.getService().getTrendingList("us", "itunes", "singles");
        trendingListCall.enqueue(new Callback<TrendingList>() {
            @Override
            public void onResponse(@NonNull Call<TrendingList> call, @NonNull Response<TrendingList> response) {
                TrendingList trendingList = response.body();

                if (trendingList != null ) {
                    TopSongsActivity. this . trendingSingles .clear();
                    TopSongsActivity. this . trendingSingles .addAll(trendingList. trending );
                    TopSongsActivity. this . rvList .getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TrendingList> call, Throwable t) {
                Toast.makeText(TopSongsActivity.this, "Blad pobierania danych: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }



        });}

    public boolean onSupportNavigateUp() {



        onBackPressed();
        return true;
    }}




