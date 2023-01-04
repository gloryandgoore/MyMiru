package com.example.mymiru;


import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;

public class AnimeDetails extends AppCompatActivity implements DBManager.DataBaseListener {
    ImageView animeImage;
    TextView animeName;
    TextView animeSynopsis;
    TextView animeGenres;
    Button button_wish;
    int position;
    String animeid;
    private ArrayList<String> animeIDArrayList = new ArrayList<>();
    int value = 0;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime_details);
        position = ((MyApp)getApplication()).pos;
        animeGenres = findViewById(R.id.genres_anime_details);
        animeImage = findViewById(R.id.anime_details_image);
        animeName = findViewById(R.id.anime_details_name);
        animeSynopsis = findViewById(R.id.anime_details_synopsis);
        animeSynopsis.setMovementMethod(new ScrollingMovementMethod());
        button_wish = findViewById(R.id.button_wish_list);
        if( getIntent().hasExtra("anime")){
            Anime c = getIntent().getParcelableExtra("anime");
            this.setTitle(c.getTitle());
            ((MyApp) getApplication()).dbManager.listener = this;
            ((MyApp) getApplication()).networkingService.getDetailsOfSelectedAnime(c);

        }

        ((MyApp)getApplication()).dbManager.listener = this;
        ((MyApp)getApplication()).dbManager.getAnimeDB(this);
        ((MyApp)getApplication()).dbManager.getTitleWishAnimes();

        button_wish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = ((MyApp)getApplication()).pos;
                ((MyApp)getApplication()).dbManager.getTitleWishAnimes();

                    ((MyApp) getApplication()).dbManager.insertNewWishAnimeAsync(new
                            WishAnimes(((MyApp) getApplication()).searchAnimes.getDetailsAnimeList().get(pos).getTitle(),
                            ((MyApp) getApplication()).searchAnimes.getDetailsAnimeList().get(pos).getImage(),
                            ((MyApp) getApplication()).searchAnimes.getDetailsAnimeList().get(pos).get_id(),
                            ((MyApp) getApplication()).query));
                    AlertDialog.Builder builder = new AlertDialog.Builder(AnimeDetails.this);
                    builder.setMessage("Saved to wish list.").show();
                }
        });


    }


    
    public void showAnimeDesc(int pos){

        String titleFromJson =((MyApp)getApplication()).searchAnimes.getDetailsAnimeList().get(pos).getTitle();
        animeid = ((MyApp)getApplication()).searchAnimes.getDetailsAnimeList().get(pos).getAnimeid();
        animeName.setText(titleFromJson);
        animeName.setTextColor(getResources().getColor(R.color.DarkCyan));

        animeSynopsis.setTextColor(getResources().getColor(R.color.black));
        animeSynopsis.setText(((MyApp)getApplication()).searchAnimes.getDetailsAnimeList().get(pos).getSynopsis());


        int genreSize=((MyApp)getApplication()).searchAnimes.getDetailsAnimeList().get(pos).getGenres().size();
        for(int i=0;i<genreSize;i++){
            animeGenres.setTextColor(getResources().getColor(R.color.DarkCyan));
            animeGenres.setText("Genre: "+((MyApp)getApplication()).searchAnimes.getDetailsAnimeList().get(pos).getGenres().get(i));
        }

        Glide.with(this).load(((MyApp)getApplication()).searchAnimes.getDetailsAnimeList().get(pos).
                getImage()).into(animeImage);
        
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        showAnimeDesc(position);
    }

    @Override
    public void insertingAnimeCompleted() {

    }

    @Override
    public void gettingAnimeCompleted(WishAnimes[] list) {
        
    }

    @Override
    public void gettingWishAnimesTitleCompleted(WishAnimes[] list) {
        int updatedValue = 0;
        if(value==1){
            ArrayList<WishAnimes> wishAnimeID = new ArrayList<>(Arrays.asList(list));
            for (int i = 0; i < wishAnimeID.size(); i++) {
                animeIDArrayList.add(wishAnimeID.get(i).getAnimeId().toString());
            }
            updatedValue = 1;
        }
        value = 0;
        if(updatedValue == 1){

        }

    }

    @Override
    public void deleteWishAnimesCompleted() {

    }

    @Override
    public void deleteWishAnimesWithAnimeIDCompleted() {

    }

    @Override
    public void deleteAllWishAnimesCompleted() {

    }

}