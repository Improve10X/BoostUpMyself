package com.improve10x.boostupmyself.categories;

public class Category {
    public String id;
    public String categoryImg;
    public String categoryTitle;

    public Category() {
    }

    public Category(String categoryImg, String categoryTitle) {
        this.categoryImg = categoryImg;
        this.categoryTitle = categoryTitle;
    }
}
