package com.example.stud.musicapp.searchAlbum;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stud.musicapp.R;
import com.example.stud.musicapp.api.ApiService;
import com.example.stud.musicapp.api.SearchAlbum;
import com.example.stud.musicapp.api.SearchAlbums;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class searchAlbum extends AppCompatActivity {

    EditText etQuery;
    RecyclerView rvList;

    SharedPreferences sharedPreferences;
    List<SearchAlbum> albums = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_album);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getPreferences(MODE_PRIVATE);

        etQuery = findViewById(R.id.etQuery);
        rvList = findViewById(R.id.rvList);

        SearchAlbumAdapter searchAlbumAdapter = new SearchAlbumAdapter(albums);
        rvList.setAdapter(searchAlbumAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(linearLayoutManager);

        String artist = sharedPreferences.getString("query", null);
        etQuery.setText(artist);

       


        Button bSearch = findViewById(R.id.bSearch);
        bSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = etQuery.getText().toString();
                rememberQuery(query);
                searchAlbums(query);

            }
        });
    }
    private void rememberQuery(String query){
        SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("query", query);
                editor.apply();

    }

    private void searchAlbums(String query) {
        getSupportActionBar().setSubtitle(query);

        if (query == null || query.isEmpty()) {
            Toast.makeText(this, "Pusta fraza", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<SearchAlbums> searchAlbumsCall = ApiService.getService().searchAlbums(query);
        searchAlbumsCall.enqueue(new Callback<SearchAlbums>() {
            @Override
            public void onResponse(@NonNull Call<SearchAlbums> call, @NonNull Response<SearchAlbums> response) {
                SearchAlbums searchAlbums = response.body();

                if (searchAlbums == null || searchAlbums.album == null || searchAlbums.album.isEmpty()) {
                    Toast.makeText(searchAlbum.this, "Brak wyników", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(searchAlbum.this, "Znaleziono " + searchAlbums.album.size() + " wyników", Toast.LENGTH_SHORT).show();

                private void updateList(SearchAlbums searchAlbums) {
                    albums.clear();
                    albums.addAll(searchAlbums.album);

                    rvList.getAdapter().notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchAlbums> call, Throwable t) {
                Toast.makeText(searchAlbum.this, "Błąd pobierania danych: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }
}
