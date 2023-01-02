package com.improve10x.boostupmyself.savedvideos;

import com.google.gson.annotations.SerializedName;

public class SavedVideos {
    @SerializedName("_id")
    public String id;
    public String title;
    public String imageUrl;
    public String uploadedTime;
    public String channelName;
    public String channelLogoUrl;
}


