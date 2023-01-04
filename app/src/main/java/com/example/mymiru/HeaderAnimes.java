package com.example.mymiru;

import java.util.ArrayList;
import java.util.List;

public class HeaderAnimes {

    String _id;
    String animeName;
    String imageUrl;
    String synopsis;



    public HeaderAnimes(String _id, String animeName, String imageUrl, String synopsis) {
        this._id = _id;
        this.animeName = animeName;
        this.imageUrl = imageUrl;
        this.synopsis = synopsis;

    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }


    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
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


}
