package com.example.mymiru;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Arrays;


public class WishList extends AppCompatActivity implements DBManager.DataBaseListener, AnimeAdapter.ItemListener{
    AnimeAdapter animeAdapter;
    ArrayList<WishAnimes> wishAnimesArrayList = new ArrayList<>(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        this.setTitle("Wish List");

        RecyclerView wishAnimesList = findViewById(R.id.item_wish_recycler);
        animeAdapter = new AnimeAdapter(this,wishAnimesArrayList);

        wishAnimesList.setAdapter(animeAdapter);
        animeAdapter.listener = this;
        wishAnimesList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void insertingAnimeCompleted() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.anime_searchview);
        SearchView searchView = (SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(query.length()>2){
                    ((MyApp)getApplication()).query = query;
                    Intent anime = new Intent(WishList.this,AnimeSearchRecyclerView.class);
                    anime.putExtra("query", query);
                    startActivity(anime);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu) ;

    }



    @Override
    public void gettingAnimeCompleted(WishAnimes[] list) {
        wishAnimesArrayList = new ArrayList(Arrays.asList(list));
        animeAdapter.list = wishAnimesArrayList;
        animeAdapter.notifyDataSetChanged();

    }

    @Override
    public void gettingWishAnimesTitleCompleted(WishAnimes[] list) {

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



    @Override
    public void onResume() {
        super.onResume();
        ((MyApp) getApplication()).dbManager.getAnimeDB(this);
        ((MyApp) getApplication()).dbManager.getAllWishAnimes();
        ((MyApp) getApplication()).dbManager.listener = this;



    }

    @Override
    public void onClicked(int post) {
        Intent i = new Intent(this, AnimeDetails.class);
        startActivity(i);

    }
}