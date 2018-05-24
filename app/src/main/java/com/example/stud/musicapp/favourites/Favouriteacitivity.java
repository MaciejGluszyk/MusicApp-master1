package com.example.stud.musicapp.favourites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.stud.musicapp.R;

public class Favouriteacitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favouriteacitivity);

        Realm realm = Realm.getDefaultIntance();

        RealmResults<Favorite> favorites = realm
                .where(Favorite.class)
                .sort(fieldName:"date", Sort.DESCENDING)
                .findAll();

        if (favorites.size() > 0) {
            Toast.makeText(this, "Pobrano ulubione", Toast.LENGTH_SHORT).show();

            for (Favorite favorite : favorites) {
                Log.d("FAV", favorite.getArtist() + " - " + favorite.getTrack());
            }
        } else {
            Toast.makeText(this, "Brak ulubionych", Toast.LENGTH_SHORT).show();
        }

    }
}
