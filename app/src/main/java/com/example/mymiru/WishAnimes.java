package com.example.mymiru;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WishAnimes {

    @PrimaryKey(autoGenerate = true)
    int id;
    String title;
    String image;
    String animeId;
    String query;


    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAnimeId() {
        return animeId;
    }

    public void setAnimeId(String animeId) {
        this.animeId = animeId;
    }

    public WishAnimes(String title, String image, String animeId, String query) {
        this.title = title;
        this.image = image;
        this.animeId = animeId;
        this.query = query;
    }
}
