package com.improve10x.boostupmyself.homescreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Video implements Serializable {
    @SerializedName("_id")
    public String id;
    public String title;
    public String imageUrl;
    public String uploadedTime;
    public String channelName;
    public String channelLogoUrl;
    public String youtubeVideoId;
    public Boolean bookmark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUploadedTime() {
        return uploadedTime;
    }

    public void setUploadedTime(String uploadedTime) {
        this.uploadedTime = uploadedTime;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelLogoUrl() {
        return channelLogoUrl;
    }

    public void setChannelLogoUrl(String channelLogoUrl) {
        this.channelLogoUrl = channelLogoUrl;
    }

    public String getYoutubeVideoId() {
        return youtubeVideoId;
    }

    public void setYoutubeVideoId(String youtubeVideoId) {
        this.youtubeVideoId = youtubeVideoId;
    }

    public Boolean getBookmark() {
        return bookmark;
    }

    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }

    public Video() {
    }

    public Video(String title, String imageUrl, String uploadedTime, String channelName, String channelLogoUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.uploadedTime = uploadedTime;
        this.channelName = channelName;
        this.channelLogoUrl = channelLogoUrl;
    }
}

