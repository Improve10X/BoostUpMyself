package com.example.boostupmyself.categories;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.boostupmyself.databinding.CategoriesItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    public List<Category> categories;

    void setData(List<Category> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoriesItemBinding binding = CategoriesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(binding);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        Picasso.get().load(category.categoryImg).into(holder.categoriesItemBinding.categoryImg);
        holder.categoriesItemBinding.categoryTitle.setText(category.categoryTitle);
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
