package com.improve10x.boostupmyself.savedvideos;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.SavedVideosItemBinding;

public class SavedVideoItemViewHolder extends RecyclerView.ViewHolder {

    SavedVideosItemBinding binding;

    public SavedVideoItemViewHolder(SavedVideosItemBinding savedVideosItemBinding) {
        super(savedVideosItemBinding.getRoot());
        binding = savedVideosItemBinding;
    }
}
