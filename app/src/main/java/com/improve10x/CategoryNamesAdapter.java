package com.improve10x;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.ActivityCategoryNameBinding;
import com.improve10x.boostupmyself.databinding.CategoryNameItemBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryNamesAdapter extends RecyclerView.Adapter<CategoryNameViewHolder> {

    public List<CategoryName> categoryNames;

    void setData(List<CategoryName> categoryNames) {
        this.categoryNames = categoryNames;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryNameItemBinding binding = CategoryNameItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        CategoryNameViewHolder categoryNameViewHolder = new CategoryNameViewHolder(binding);
        return categoryNameViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryNameViewHolder holder, int position) {
        CategoryName categoryName = categoryNames.get(position);
        Picasso.get().load(categoryName.imageUrl).into(holder.binding.videoImg);
        holder.binding.videoTitleTxt.setText(categoryName.title);
        holder.binding.channelNameTxt.setText(categoryName.channelName);
        Picasso.get().load(categoryName.channelLogoUrl).into(holder.binding.channelLogoImg);
        holder.binding.uploadedTimeTxt.setText(categoryName.uploadedTime);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
