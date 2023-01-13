package com.example.mymiru;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.AsyncTask;

import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Random;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SurpriseMe extends AppCompatActivity {
    Button button_surprise;
    Button button_wish;
    ImageView logo;
    TextView titleTV;
    TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprise_me);
        titleTV = findViewById(R.id.surprise_me_text_1);
        subtitle = findViewById(R.id.surprise_me_text_2);
        logo = findViewById(R.id.surprise_me_image);
        button_surprise = findViewById(R.id.button_surprise_me);
        button_wish = findViewById(R.id.button_add_wish);
        button_wish.setVisibility(View.GONE);


        button_surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetAnimeTask().execute();
                button_wish.setVisibility(View.VISIBLE);
            }
        });

    }

    private class GetAnimeTask extends AsyncTask<Void, Void, JsonObject> {
        //
        @Override
        protected JsonObject doInBackground(Void... voids) {
            Random rand = new Random();
            int ranking = rand.nextInt(1000) + 1;

            JsonObject jsonObject = null;
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://anime-db.p.rapidapi.com/anime/by-ranking/" + ranking)
                        .get()
                        .addHeader("X-RapidAPI-Key", "c6d174dc42msh926955b2f690e00p1e37fdjsn6b6fedb2172d")
                        .addHeader("X-RapidAPI-Host", "anime-db.p.rapidapi.com")
                        .build();

                Response response = client.newCall(request).execute();

                String jsonString = response.body().string();
                jsonObject = new Gson().fromJson(jsonString, JsonObject.class);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonObject;


        }

        @Override
        protected void onPostExecute(JsonObject jsonObject) {
            super.onPostExecute(jsonObject);
            if (jsonObject != null) {
                titleTV.setText(jsonObject.get("title").getAsString());
                titleTV.setTextSize(24);
                subtitle.setText(jsonObject.get("synopsis").getAsString());
                Glide.with(SurpriseMe.this).load(jsonObject.get("image").getAsString()).into(logo);
            } else {
                Toast.makeText(SurpriseMe.this, "An error occurred. Please try again later.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}