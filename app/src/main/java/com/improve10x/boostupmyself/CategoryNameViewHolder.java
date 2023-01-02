package com.improve10x.boostupmyself;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.boostupmyself.databinding.ActivityCategoryNameBinding;
import com.improve10x.boostupmyself.databinding.CategoryNameItemBinding;

public class CategoryNameViewHolder extends RecyclerView.ViewHolder {

    CategoryNameItemBinding binding;

    public CategoryNameViewHolder(CategoryNameItemBinding categoryNameItemBinding) {
        super(categoryNameItemBinding.getRoot());
        this.binding = categoryNameItemBinding;
    }
}
