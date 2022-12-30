package com.improve10x.boostupmyself.homescreen;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.HomeScreenItemBinding;

public class VideoItemViewHolder extends RecyclerView.ViewHolder {

    HomeScreenItemBinding binding;

    public VideoItemViewHolder(HomeScreenItemBinding homeScreenItemBinding) {
        super(homeScreenItemBinding.getRoot());
        binding = homeScreenItemBinding;
    }
}
