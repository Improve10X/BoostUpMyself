package com.improve10x.boostupmyself.savedvideos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.SavedVideosItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SavedVideoItemsAdapter extends RecyclerView.Adapter<SavedVideoItemViewHolder> {

    private ArrayList<SavedVideos> saveVideoItem;

    void setData(ArrayList<SavedVideos> saveVideoItem) {
        this.saveVideoItem = saveVideoItem;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SavedVideoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SavedVideosItemBinding binding = SavedVideosItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        SavedVideoItemViewHolder savedVideoItemViewHolder = new SavedVideoItemViewHolder(binding);
        return savedVideoItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavedVideoItemViewHolder holder, int position) {
        SavedVideos savedVideos = saveVideoItem.get(position);
        Picasso.get().load(savedVideos.imageUrl).into(holder.binding.videoImg);
        Picasso.get().load(savedVideos.channelLogoUrl).into(holder.binding.channelLogoImg);
        holder.binding.videoTitleTxt.setText(savedVideos.title);
        holder.binding.channelNameTxt.setText(savedVideos.channelName);
        holder.binding.uploadedTimeTxt.setText(savedVideos.uploadedTime);
    }

    @Override
    public int getItemCount() {
        return saveVideoItem.size();
    }
}
