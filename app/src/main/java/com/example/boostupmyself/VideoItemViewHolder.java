package com.example.boostupmyself;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boostupmyself.databinding.HomeScreenItemBinding;

public class VideoItemViewHolder extends RecyclerView.ViewHolder {

    HomeScreenItemBinding binding;

    public VideoItemViewHolder(HomeScreenItemBinding homeScreenItemBinding) {
        super(homeScreenItemBinding.getRoot());
        binding = homeScreenItemBinding;
    }
}
