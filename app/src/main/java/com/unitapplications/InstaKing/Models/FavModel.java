package com.unitapplications.InstaKing.Models;

public class FavModel {
    int id;
    String fav_caption;

    public FavModel(int id, String fav_caption) {
        this.id = id;
        this.fav_caption = fav_caption;
    }

    public FavModel(String fav_caption) {
        this.fav_caption = fav_caption;
    }

    public FavModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFav_caption() {
        return fav_caption;
    }

    public void setFav_caption(String fav_caption) {
        this.fav_caption = fav_caption;
    }
}