package com.example.boostupmyself;

public class Video {
    public String id;
    public String title;
    public String imageUrl;
    public String uploadedTime;
    public String channelName;
    public String channelLogoUrl;
    public String youtubeVideoId;

    public Video() {
    }

    public Video(String title, String imageUrl, String uploadedTime, String channelName, String channelLogoUrl, String youtubeVideoId) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.uploadedTime = uploadedTime;
        this.channelName = channelName;
        this.channelLogoUrl = channelLogoUrl;
        this.youtubeVideoId = youtubeVideoId;
    }
}

