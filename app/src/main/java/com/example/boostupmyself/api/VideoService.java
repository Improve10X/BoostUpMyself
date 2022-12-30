package com.example.boostupmyself.api;

import com.example.boostupmyself.categories.Category;
import com.example.boostupmyself.homescreen.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VideoService {

    @GET("BoostUpMyself")
    Call<List<Video>> fetchVideos();
}
