package com.example.boostupmyself.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.boostupmyself.databinding.ActivityCategoriesBinding;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity {

    private ArrayList<Category> categories;
    ActivityCategoriesBinding binding;
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Categories");
        setupData();
        setupCategoriesAdapter();
        setupCategoriesRv();
    }

    private void setupData() {
        categories = new ArrayList<>();
        Category category = new Category("https://i.ytimg.com/vi/s5BMcaQsjbM/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCiKGYtTAt37RRIBnJaJQjJOLiT5Q", "Samantha English Speech");
        categories.add(category);
    }

    private void setupCategoriesAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setData(categories);
    }

    private void setupCategoriesRv() {
        binding.categoriesRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoriesRv.setAdapter(categoriesAdapter);
    }
}