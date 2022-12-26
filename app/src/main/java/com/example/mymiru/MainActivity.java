package com.example.mymiru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okhttp3.Response;
import okhttp3.Callback;




public class MainActivity extends AppCompatActivity {

TextView testTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTV = findViewById(R.id.testTV);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://anime-db.p.rapidapi.com/anime?page=1&size=10&genres=Award%20Winning&sortBy=ranking&sortOrder=asc")
                .get()
                .addHeader("X-RapidAPI-Key", "c6d174dc42msh926955b2f690e00p1e37fdjsn6b6fedb2172d")
                .addHeader("X-RapidAPI-Host", "anime-db.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws
                    IOException {
                if(response.isSuccessful()){
                    String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            testTV.setText(myResponse);


                        }
                    });
                }
            }
        });
    }
}