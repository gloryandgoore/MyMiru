package com.example.mymiru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity implements  NetworkingService.NetworkingListener, AnimeDetailsRecyclerView.ItemClickListener{

    BannerAnimePagerAdapter bannerAnimePagerAdapter;
//    TabLayout indicatorTab, screenTab;
    ViewPager bannerAnimeViewPager;
    List<HeaderAnimes> headerAnimesList;
    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainRecycler;
    List<AllGenres> allGenresList;
    String genre;
    FloatingActionButton wishButton;
    FloatingActionButton surpriseButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        indicatorTab = findViewById(R.id.tab_indicator);
//        screenTab = findViewById(R.id.tabLayoutTop);
        wishButton = findViewById(R.id.go_to_wish_button);
        surpriseButton = findViewById(R.id.go_to_surprise_button);
        Intent intent = getIntent();
        genre = intent.getStringExtra("genre");

        headerAnimesList = new ArrayList<>();
        headerAnimesList.add(new HeaderAnimes("1", "Naruto", "https://m.media-amazon.com/images/S/pv-target-images/45fa87b65b1f9f0e66a23111f778ba198710f3520fc8f9ecde24885b3eca5218._UR1920,1080_UX400_UY225_.jpg", ""));
        headerAnimesList.add(new HeaderAnimes("2", "One Piece", "https://m.media-amazon.com/images/S/pv-target-images/c02450f675aef50667e49705d74483e68dea0deb74333ed644b794edd45214eb._UR1920,1080_UX400_UY225_.jpg", "" ));


        headerAnimesList.add(new HeaderAnimes("4", "Borut0", "https://m.media-amazon.com/images/S/pv-target-images/6c7288aa32a8cbea64f6cb2a2a76fa6d298325f0287a963b8da23e80195c2e94._UR1920,1080_UX400_UY225_.jpg", "" ));
        headerAnimesList.add(new HeaderAnimes("5", "Yu-Gi-Oh!", "https://m.media-amazon.com/images/S/pv-target-images/d04bd0567ecd836e6191052a12b9e1c7690281299519e37b0e7528b6d2c5e2cc._UR1920,1080_UX400_UY225_.jpg", "" ));

        headerAnimesList.add(new HeaderAnimes("6", "Assassination Classroom", "https://m.media-amazon.com/images/S/pv-target-images/56e79d339d2cb847170bf05b87b27d25250c259791ee2d6ec7e3f36f0d511208._UR1920,1080_UX400_UY225_.jpg", "" ));
        headerAnimesList.add(new HeaderAnimes("7", "Donten", "https://m.media-amazon.com/images/S/pv-target-images/e651af720faa1fbfe0d814d0cb46f9654abd9527dec3726e8ec2631010ec350f._UR1920,1080_UX400_UY225_.jpg", "" ));

        headerAnimesList.add(new HeaderAnimes("8", "Naruto", "https://cdn.myanimelist.net/images/anime/1493/116732.jpg", "" ));
        headerAnimesList.add(new HeaderAnimes("9", "One Piece", "https://cdn.myanimelist.net/images/anime/1939/97699.jpg", "" ));

        setBannerAnimePagerAdapter(headerAnimesList);

//        screenTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()){
//                    case 1:
//                        setBannerAnimePagerAdapter(headerAnimesList);
//                        return;
//
//                    case 2:
//                        setBannerAnimePagerAdapter(headerAnimesList);
//                        return;
//
//                    case 3:
//                        setBannerAnimePagerAdapter(headerAnimesList);
//                        return;
//
//                    default:
//                        setBannerAnimePagerAdapter(headerAnimesList);
//
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        ArrayList<GenreItem> actionCategoryList = new ArrayList<>();

        actionCategoryList.add(new GenreItem(1, "Naruto", "https://m.media-amazon.com/images/S/pv-target-images/45fa87b65b1f9f0e66a23111f778ba198710f3520fc8f9ecde24885b3eca5218._UR1920,1080_UX400_UY225_.jpg", ""));
        actionCategoryList.add(new GenreItem(2, "Hunter x Hunter", "https://m.media-amazon.com/images/S/pv-target-images/53606cd2f688af59b61567ddd204b2050de137eec93e4312b693eccd0e2b8ac6._UR1920,1080_UX400_UY225_.jpg", ""));
        actionCategoryList.add(new GenreItem(3, "Assassination Classroom", "https://m.media-amazon.com/images/S/pv-target-images/56e79d339d2cb847170bf05b87b27d25250c259791ee2d6ec7e3f36f0d511208._UR1920,1080_UX400_UY225_.jpg", ""));

        ArrayList<GenreItem> awardCategoryList = new ArrayList<>();

        awardCategoryList.add(new GenreItem(1, "Yu-Gi-Oh!", "https://m.media-amazon.com/images/S/pv-target-images/d04bd0567ecd836e6191052a12b9e1c7690281299519e37b0e7528b6d2c5e2cc._UR1920,1080_UX400_UY225_.jpg", "" ));
        awardCategoryList.add(new GenreItem(2, "Borut0", "https://m.media-amazon.com/images/S/pv-target-images/6c7288aa32a8cbea64f6cb2a2a76fa6d298325f0287a963b8da23e80195c2e94._UR1920,1080_UX400_UY225_.jpg", "" ));
        awardCategoryList.add(new GenreItem(3, "One Piece", "https://cdn.myanimelist.net/images/anime/1939/97699.jpg", ""));

        ArrayList<GenreItem> dramaCategoryList = new ArrayList<>();
        dramaCategoryList.add(new GenreItem(1, "Assassination Classroom", "https://m.media-amazon.com/images/S/pv-target-images/56e79d339d2cb847170bf05b87b27d25250c259791ee2d6ec7e3f36f0d511208._UR1920,1080_UX400_UY225_.jpg", "" ));
        dramaCategoryList.add(new GenreItem(2, "Donten", "https://m.media-amazon.com/images/S/pv-target-images/e651af720faa1fbfe0d814d0cb46f9654abd9527dec3726e8ec2631010ec350f._UR1920,1080_UX400_UY225_.jpg", "" ));
        dramaCategoryList.add(new GenreItem(3, "Ronja", "https://m.media-amazon.com/images/S/pv-target-images/3ed4c9a275e17f3b4f1c4c410bc62c3a4196ce6528ac86869e0ef5ecb4053c43._UR1920,1080_UX400_UY225_.jpg", "" ));



        allGenresList = new ArrayList<>();
        allGenresList.add(new AllGenres( "Award Winning", awardCategoryList));
        allGenresList.add(new AllGenres( "Action", actionCategoryList));
        allGenresList.add(new AllGenres( "Drama", dramaCategoryList));

        setMainRecycler(allGenresList);

        wishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, WishList.class));

            }
        });

        surpriseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SurpriseMe.class));

            }
        });

    }

    private void setBannerAnimePagerAdapter(List<HeaderAnimes> headerAnimesList){
        bannerAnimePagerAdapter = new BannerAnimePagerAdapter(this, headerAnimesList);
        bannerAnimeViewPager = findViewById(R.id.banner_viewPager);
        bannerAnimeViewPager.setAdapter(bannerAnimePagerAdapter);
//        indicatorTab.setupWithViewPager(bannerAnimeViewPager);

        Timer slideTimer = new Timer();
        slideTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 6000);
//        indicatorTab.setupWithViewPager(bannerAnimeViewPager, true);
    }

    @Override
    public void gettingJsonIsCompleted(String json) {
    }


    class AutoSlider extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (bannerAnimeViewPager.getCurrentItem() < headerAnimesList.size() - 1) {
                        bannerAnimeViewPager.setCurrentItem(bannerAnimeViewPager.getCurrentItem() + 1);
                    } else {
                        bannerAnimeViewPager.setCurrentItem(0);
                    }
                }
            });
        }

    }

    public void setMainRecycler(List<AllGenres> allGenresList){
        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allGenresList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }

    public void setGenreRecycler(List<AllGenres> allGenresList){
        mainRecycler = findViewById(R.id.item_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(this, allGenresList);
        mainRecycler.setAdapter(mainRecyclerAdapter);
    }


    @Override
    public void onItemClick(int pos) {
        Intent intent=new Intent(MainActivity.this,AnimeDetails.class);
        ((MyApp)getApplication()).pos=pos;
        startActivity(intent);
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
                    ((MyApp)getApplication()).query=query;
                    Intent anime = new Intent(MainActivity.this,AnimeSearchRecyclerView.class);
                    anime.putExtra("query",query);
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



}