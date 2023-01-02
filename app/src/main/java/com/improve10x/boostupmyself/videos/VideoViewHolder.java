package com.improve10x.boostupmyself.videos;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.VideoItemBinding;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    VideoItemBinding binding;

    public VideoViewHolder(VideoItemBinding videoItemBinding) {
        super(videoItemBinding.getRoot());
        this.binding = videoItemBinding;
    }
}
