package com.improve10x.boostupmyself.videos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.VideoItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    public List<Video> videos;

    void setData(List<Video> categoryNames) {
        this.videos = categoryNames;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VideoItemBinding binding = VideoItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(binding);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
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
