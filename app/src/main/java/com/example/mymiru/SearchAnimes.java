package com.example.mymiru;

import java.util.ArrayList;

public class SearchAnimes {
    private ArrayList<Anime> animeArrayList = new ArrayList<>();
    private ArrayList<Anime> detailsAnimeList = new ArrayList<>();

    public void detailsAnimeList(String _id, String title, ArrayList<String> alternativeTitles,
                                 int ranking, ArrayList<String> genres, int episodes,
                                 Boolean hasEpisode, Boolean hasRanking, String image,
                                 String link, String status, String synopsis,
                                 String thumb, String type) {
        detailsAnimeList.add(new Anime(_id, title,alternativeTitles,
                ranking, genres, episodes, hasEpisode, hasRanking, image,link,status, synopsis,thumb, type));
    }
    public ArrayList<Anime> getDetailsAnimeList(){
        return detailsAnimeList;
    }

    public void setAnimeList(String title, String image, String synopsis, String animeid){
        animeArrayList.add(new Anime(title,image, synopsis, animeid));
    }

    public ArrayList<Anime> getAnimeList(){
        return animeArrayList;
    }

}
