package com.unitapplications.captiongram.Models;

public class InstaModel {
    private String caption;


    public InstaModel(String caption) {
        this.caption = caption;
    }

    public InstaModel() { }


    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}