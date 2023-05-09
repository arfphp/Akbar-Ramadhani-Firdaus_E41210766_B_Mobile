package com.example.jsonmoviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView showJson = (ImageView) findViewById(R.id.json);
        ImageView showJsonAPI = (ImageView) findViewById(R.id.json_api);
        ImageView showMovieDB = (ImageView) findViewById(R.id.movie_db);


        showJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JsonParseActivity.class);
                startActivity(intent);
            }
        });

        showJsonAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JsonAPIActivity.class);
                startActivity(intent);
            }
        });

        showMovieDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, moviedb.class);
                startActivity(intent);
            }
        });
    }
}