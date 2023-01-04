package com.example.mymiru;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
@Entity


public class Anime implements Parcelable {
    @PrimaryKey(autoGenerate = true)


    private String _id;
    private String title;
    private ArrayList<String> alternativeTitles;
    private int ranking;
    private ArrayList<String> genres;
    private int episodes;
    private Boolean hasEpisode;
    private Boolean hasRanking;
    private String image;
    private String link;
    private String status;
    private String synopsis;
    private String thumb;
    private String type;
    //might get rid
    private String animeid;

    public Anime(String _id, String title, ArrayList<String> alternativeTitles,
                 int ranking, ArrayList<String> genres, int episodes,
                 Boolean hasEpisode, Boolean hasRanking, String image,
                 String link, String status, String synopsis,
                 String thumb, String type, String animeid) {
        this._id = _id;
        this.title = title;
        this.alternativeTitles = alternativeTitles;
        this.ranking = ranking;
        this.genres = genres;
        this.episodes = episodes;
        this.hasEpisode = hasEpisode;
        this.hasRanking = hasRanking;
        this.image = image;
        this.link = link;
        this.status = status;
        this.synopsis = synopsis;
        this.thumb = thumb;
        this.type = type;
        this.animeid = animeid;
    }

    public Anime(String _id, String title, ArrayList<String> alternativeTitles,
                 int ranking, ArrayList<String> genres, int episodes,
                 Boolean hasEpisode, Boolean hasRanking, String image,
                 String link, String status, String synopsis,
                 String thumb, String type) {
        this._id = _id;
        this.title = title;
        this.alternativeTitles = alternativeTitles;
        this.ranking = ranking;
        this.genres = genres;
        this.episodes = episodes;
        this.hasEpisode = hasEpisode;
        this.hasRanking = hasRanking;
        this.image = image;
        this.link = link;
        this.status = status;
        this.synopsis = synopsis;
        this.thumb = thumb;
        this.type = type;

    }

    protected Anime(Parcel in) {
        _id = in.readString();
        animeid = in.readString();
        title = in.readString();
        alternativeTitles = in.createStringArrayList();
        ranking = in.readInt();
        genres = in.createStringArrayList();
        episodes = in.readInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            hasRanking = in.readBoolean();
            hasEpisode = in.readBoolean();
        }
        image = in.readString();
        link = in.readString();
        status = in.readString();
        synopsis = in.readString();
        thumb = in.readString();
        type = in.readString();
    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel in) {

            return new Anime(in);
        }

        @Override
        public Anime[] newArray(int size) {

            return new Anime[size];
        }
    };

    public Anime(String title, String image, String synopsis) {
        this.title = title;
        this.image = image;
        this.synopsis = synopsis;

    }

    public Anime(String title, String img, String synopsis, String animeid ) {
        this.title = title;
        this.image = img;
        this.synopsis = synopsis;
        this.animeid = animeid;
    }

    public String getAnimeid() {
        return animeid;
    }

    public void setAnimeid(String animeid) {
        this.animeid = animeid;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(ArrayList<String> alternativeTitles) {
        this.alternativeTitles = alternativeTitles;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public Boolean getHasEpisode() {
        return hasEpisode;
    }

    public void setHasEpisode(Boolean hasEpisode) {
        this.hasEpisode = hasEpisode;
    }

    public Boolean getHasRanking() {
        return hasRanking;
    }

    public void setHasRanking(Boolean hasRanking) {
        this.hasRanking = hasRanking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_id);
        parcel.writeString(animeid);
        parcel.writeString(title);
        parcel.writeStringList(alternativeTitles);
        parcel.writeInt(ranking);
        parcel.writeStringList(genres);
        parcel.writeInt(episodes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            parcel.writeBoolean(hasEpisode);
            parcel.writeBoolean(hasEpisode);
        }
        parcel.writeString(image);
        parcel.writeString(link);
        parcel.writeString(status);
        parcel.writeString(synopsis);
        parcel.writeString(thumb);
        parcel.writeString(type);
    }
}
