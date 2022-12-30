package com.example.boostupmyself.categories;

import androidx.recyclerview.widget.RecyclerView;

import com.example.boostupmyself.databinding.CategoriesItemBinding;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    CategoriesItemBinding categoriesItemBinding;

    public CategoryViewHolder(CategoriesItemBinding categoriesItemBinding) {
        super(categoriesItemBinding.getRoot());
        this.categoriesItemBinding = categoriesItemBinding;
    }
}
