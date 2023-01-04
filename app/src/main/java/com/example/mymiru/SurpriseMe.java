package com.example.mymiru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SurpriseMe extends AppCompatActivity {
    Button button_surprise;
    ImageView logo;
    TextView title;
    TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surprise_me);
        title = findViewById(R.id.surprise_me_text_1);
        subtitle = findViewById(R.id.surprise_me_text_2);
        logo = findViewById(R.id.surprise_me_image);
        button_surprise = findViewById(R.id.button_surprise_me);

        button_surprise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyApp)getApplication()).networkingService.getSurpriseTitles();

            }
        });

    }

//    @Override
//    public void onItemClick(int pos) {
//        Intent intent=new Intent(SurpriseMe.this,AnimeDetails.class);
//        ((MyApp)getApplication()).pos=pos;
//        startActivity(intent);
//    }

//                ((MyApp)getApplication()).networkingService.getS();

}