package com.example.mymiru;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimeSearchRecyclerView extends AppCompatActivity implements NetworkingService.NetworkingListener,
            AnimeDetailsRecyclerView.ItemClickListener{

        RecyclerView animeList;
        String query;
        AnimeDetailsRecyclerView adapter;
        ArrayList<WishAnimes> animeIdArrayList=new ArrayList<>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);
            ((MyApp)getApplication()).networkingService.listener=this;
            ((MyApp)getApplication()).searchAnimes.getAnimeList().clear();
            ((MyApp)getApplication()).searchAnimes.getDetailsAnimeList().clear();
            Intent intent = getIntent();
            query = intent.getStringExtra("query");

        }
        protected void setAdapterFunc(String newQuery){

            ((MyApp)getApplication()).networkingService.getAllAnimes(newQuery);
            animeList = findViewById(R.id.anime_search_list);
            adapter = new AnimeDetailsRecyclerView(((MyApp)getApplication()).searchAnimes
                    .getAnimeList(), this);
            adapter.listener = this;
            animeList.setAdapter(adapter);
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
            animeList.setLayoutManager(linearLayoutManager);
        }

        @Override
        protected void onResume() {
            super.onResume();
            setAdapterFunc(((MyApp)getApplication()).query);
        }


        @Override
        public void onItemClick(int pos) {
            Intent intent=new Intent(AnimeSearchRecyclerView.this,AnimeDetails.class);
            ((MyApp)getApplication()).pos=pos;
            startActivity(intent);
        }


    @Override
    public void gettingJsonIsCompleted(String jsonString) {
        ((MyApp)getApplication()).jsonService.getAnimeTitles(jsonString, this);
            adapter.animeArrayList=((MyApp)getApplication()).searchAnimes.getAnimeList();
            int size=adapter.animeArrayList.size();
            adapter.notifyDataSetChanged();

    }

    }
