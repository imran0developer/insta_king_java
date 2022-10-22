package com.unitapplications.captiongram.Models;

public class CategoryModel {
    String catergory_name;
    int category_pic;

    public CategoryModel(String catergory_name, int category_pic) {
        this.catergory_name = catergory_name;
        this.category_pic = category_pic;
    }
    public CategoryModel(String catergory_name) {
        this.catergory_name = catergory_name;
    }
    public CategoryModel() {
    }

    public String getCatergory_name() {
        return catergory_name;
    }

    public void setCatergory_name(String catergory_name) {
        this.catergory_name = catergory_name;
    }

    public int getCategory_pic() {
        return category_pic;
    }

    public void setCategory_pic(int category_pic) {
        this.category_pic = category_pic;
    }
}