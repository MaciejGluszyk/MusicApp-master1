package com.example.stud.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button bTopsongs = findViewById(R.id.bTopSongs);
        bTopsongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "TODO", Toast.LENGTH_SHORT).show();
            }
        });

        Button bFavorites = findViewById(R.id.bTopSongs);
        bFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(packageContext:MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        });

    }
}
