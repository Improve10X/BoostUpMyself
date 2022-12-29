package com.example.boostupmyself.homescreen;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boostupmyself.databinding.HomeScreenItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VideoItemsAdapter extends RecyclerView.Adapter<VideoItemViewHolder> {

    private ArrayList<Video> videos;

    void setData(ArrayList<Video> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeScreenItemBinding binding = HomeScreenItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        VideoItemViewHolder videoItemViewHolder = new VideoItemViewHolder(binding);
        return videoItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoItemViewHolder holder, int position) {
        Video video = videos.get(position);
        Picasso.get().load(video.imageUrl).into(holder.binding.videoImg);
        Picasso.get().load(video.channelLogoUrl).into(holder.binding.channelLogoImg);
        holder.binding.videoTitleTxt.setText(video.title);
        holder.binding.channelNameTxt.setText(video.channelName);
        holder.binding.uploadedTimeTxt.setText(video.uploadedTime);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
