package com.improve10x.boostupmyself.categories;

import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.CategoriesItemBinding;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    CategoriesItemBinding binding;

    public CategoryViewHolder(CategoriesItemBinding categoriesItemBinding) {
        super(categoriesItemBinding.getRoot());
        binding = categoriesItemBinding;
    }
}
