package com.example.mymiru;

import java.util.ArrayList;

public class AllGenres {

    String genreId;
    private ArrayList<GenreItem> genreItemList;

    public AllGenres(String genreId, ArrayList<GenreItem> genreItemList) {
        this.genreId = genreId;

        this.genreItemList = genreItemList;
    }

    public ArrayList<GenreItem> getGenreItemList() {
        return genreItemList;
    }

    public void setGenreItemList(ArrayList<GenreItem> genreItemList) {
        this.genreItemList = genreItemList;
    }



    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }
}
