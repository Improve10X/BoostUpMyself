package com.improve10x.boostupmyself.categories;

import java.io.Serializable;

public class Category implements Serializable {
    public String id;
    public String categoryImg;
    public String categoryTitle;
    public String categoryId;

    public Category() {
    }

    public Category(String categoryImg, String categoryTitle) {
        this.categoryImg = categoryImg;
        this.categoryTitle = categoryTitle;
    }
}
