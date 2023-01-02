package com.improve10x.boostupmyself.videos;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.CategoryNameItemBinding;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    CategoryNameItemBinding binding;

    public VideoViewHolder(CategoryNameItemBinding categoryNameItemBinding) {
        super(categoryNameItemBinding.getRoot());
        this.binding = categoryNameItemBinding;
    }
}
