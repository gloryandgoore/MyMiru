package com.example.mymiru;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {
    public void getAnimeTitles(String stringValue, Context context){
        try {
            JSONObject jsonObject = new JSONObject(stringValue);
            JSONArray totalAnimes = jsonObject.getJSONArray("data");

            for(int i=0; i<10; i++)
            {
                JSONObject animeInfo = totalAnimes.getJSONObject(i);
                String _id =  animeInfo.getString("_id");
            String animeName =  animeInfo.getString("title");
            String imageUrl = animeInfo.getString("image");
            String synopsis =  animeInfo.getString("synopsis");
                JSONArray animeAlternative = animeInfo.getJSONArray("alternativeTitles");
                ArrayList  alternativeTitles = new ArrayList<>();
                if(animeAlternative.length() !=0){
                    for (int j = 0; j < animeAlternative.length(); j++) {
                        alternativeTitles.add(animeAlternative.optString(j));
                    }
                }
                JSONArray animeGenres = animeInfo.getJSONArray("genres");
            ArrayList<String> genres = new ArrayList<>();
            if(animeGenres.length() != 0){
                for (int j = 0; j < animeGenres.length(); j++) {
                    genres.add(animeGenres.optString(j));
                }
            }
            int ranking = animeInfo.getInt("ranking");
            int episodes = animeInfo.getInt("episodes");
            Boolean hasEpisode = animeInfo.getBoolean("hasEpisode");
            Boolean hasRanking = animeInfo.getBoolean("hasRanking");
            String link = animeInfo.getString("link");
            String status = animeInfo.getString("status");
            String thumb = animeInfo.getString("thumb");
            String type = animeInfo.getString("type");
                ((MyApp)context.getApplicationContext()).searchAnimes.setAnimeList(animeName,thumb, synopsis, _id);
                ((MyApp)context.getApplicationContext()).searchAnimes.detailsAnimeList(_id, animeName,alternativeTitles,
                        ranking, genres, episodes, hasEpisode, hasRanking, imageUrl,link,status, synopsis,thumb, type);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void getAnimeGenres(String stringValue, Context context){
        try {
            JSONArray jsonarray = new JSONArray(stringValue);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                String genreId = jsonobject.getString("_id");
                System.out.println(genreId);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
