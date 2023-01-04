package com.example.mymiru;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

public class DBManager {

    public interface DataBaseListener{
        void insertingAnimeCompleted();
        void gettingAnimeCompleted(WishAnimes[] list);
        void gettingWishAnimesTitleCompleted(WishAnimes[] list);
        void deleteWishAnimesCompleted();
        void deleteWishAnimesWithAnimeIDCompleted();
        void deleteAllWishAnimesCompleted();

    }

    public DataBaseListener listener;

    AnimeDatabase animeDB;
    Handler dbHandler = new Handler(Looper.getMainLooper());


    public AnimeDatabase getAnimeDB(Context context){
        if (animeDB == null)
            animeDB = Room.databaseBuilder(context,AnimeDatabase.class,"anime_db").build();

        return animeDB;
    }

    void insertNewWishAnimeAsync(WishAnimes animes){

        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                animeDB.getDao().insertNewWishAnime(animes);
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.insertingAnimeCompleted();
                    }
                });
            }
        });
    }

    void getAllWishAnimes(){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                WishAnimes[] list = animeDB.getDao().getAllWishAnimes();
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.gettingAnimeCompleted(list);
                    }
                });
            }
        });
    }

    public void getTitleWishAnimes(){
        MyApp.executorService.execute(new Runnable() {
            WishAnimes[] list;
            @Override
            public void run() {
                try{
                    list = animeDB.getDao().getTitleWishAnimes();
                } catch (Exception e){

                }
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.gettingWishAnimesTitleCompleted(list);
                    }
                });
            }
        });
    }

    public void deleteWishAnimes(WishAnimes animes){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    animeDB.getDao().deleteWishAnimes(animes);
                } catch (Exception e){

                }
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.deleteWishAnimesCompleted();
                    }
                });
            }
        });
    }

    public void deleteWishAnimesWithAnimeID(String animes){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    animeDB.getDao().deleteWishAnimes(animes);
                } catch (Exception e){

                }
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.deleteWishAnimesWithAnimeIDCompleted();
                    }
                });
            }
        });
    }

    public void deleteAllWishAnimes(){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    animeDB.getDao().resetWishAnimes();
                } catch (Exception e){

                }
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.deleteAllWishAnimesCompleted();
                    }
                });
            }
        });
    }
}

