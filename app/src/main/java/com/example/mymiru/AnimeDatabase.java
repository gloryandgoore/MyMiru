package com.example.mymiru;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(version =1, entities = {WishAnimes.class})
public abstract class AnimeDatabase extends RoomDatabase {
    public abstract AnimeDAO getDao();

}
