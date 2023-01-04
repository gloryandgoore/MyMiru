package com.example.mymiru;

public class GenreItem {
    Integer id;
    String animeName;
    String imageUrl;
    String synopsis;

    public GenreItem(Integer id, String animeName, String imageUrl, String synopsis) {
        this.id = id;
        this.animeName = animeName;
        this.imageUrl = imageUrl;
        this.synopsis = synopsis;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
