package com.improve10x.boostupmyself.homescreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.HomeScreenItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoItemsAdapter extends RecyclerView.Adapter<VideoItemViewHolder> {

    private List<Video> videos;

    public OnItemActionListener onItemActionListener;

    public void setData(List<Video> videos) {
        this.videos = videos;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
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
        if (video.imageUrl != null && video.imageUrl.isEmpty() == false) {
            Picasso.get().load(video.imageUrl).into(holder.binding.videoImg);
        }
        if (video.channelLogoUrl != null && video.channelLogoUrl.isEmpty() == false) {
            Picasso.get().load(video.channelLogoUrl).into(holder.binding.channelLogoImg);
        }
        holder.binding.videoTitleTxt.setText(video.title);
        holder.binding.channelNameTxt.setText(video.channelName);
        holder.binding.uploadedTimeTxt.setText(video.uploadedTime);
        holder.itemView.setOnClickListener(view -> {
            onItemActionListener.onItemClicked(video);
        });
        if (video.bookmark != null && video.bookmark == true) {
            holder.binding.saveImgBtn.setVisibility(View.VISIBLE);
            holder.binding.unsaveImgBtn.setVisibility(View.GONE);
        } else {
            holder.binding.unsaveImgBtn.setVisibility(View.VISIBLE);
            holder.binding.saveImgBtn.setVisibility(View.GONE);
        }
        holder.binding.unsaveImgBtn.setOnClickListener(view -> {
            onItemActionListener.onItemSave(video);


        });
        holder.binding.saveImgBtn.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
}
