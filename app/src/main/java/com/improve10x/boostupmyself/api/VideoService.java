package com.improve10x.boostupmyself.api;

import com.improve10x.boostupmyself.categories.Category;
import com.improve10x.boostupmyself.homescreen.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VideoService {

    @GET("BoostUpMyself")
    Call<List<Video>> fetchVideos();

    @GET("categoryItem")
    Call<List<Category>> fetchCategories();
}
