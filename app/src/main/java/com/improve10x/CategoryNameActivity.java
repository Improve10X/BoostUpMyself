package com.improve10x;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.improve10x.boostupmyself.R;
import com.improve10x.boostupmyself.categories.Category;
import com.improve10x.boostupmyself.databinding.ActivityCategoryNameBinding;

import java.util.ArrayList;

public class CategoryNameActivity extends AppCompatActivity {

    public ArrayList<CategoryName> categoryNames;
    private ActivityCategoryNameBinding binding;
    private CategoryNamesAdapter categoryNamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("CategoryName");
        setupData();
        setupCategoryNameRv();
    }

    private void setupData() {
        categoryNames = new ArrayList<>();
        CategoryName categoryName = new CategoryName("Samantha English Videos", "https://i.ytimg.com/vi/s5BMcaQsjbM/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCiKGYtTAt37RRIBnJaJQjJOLiT5Q", "1 day ago", "English Speeches", "https://yt3.ggpht.com/3ErdBd0bg2Qw5rKdqDK-7vPAf0tirRuodlGGZuhZePQcjEu8i5KniCN-EUCBtQkSOy14M26O=s68-c-k-c0x00ffffff-no-rj");
        categoryNames.add(categoryName);
    }

    private void setupCategoryNamesAdapter() {
        categoryNamesAdapter = new CategoryNamesAdapter();
        categoryNamesAdapter.setData(categoryNames);
    }

    private void setupCategoryNameRv() {
        binding.categoryNameRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoryNameRv.setAdapter(categoryNamesAdapter);
    }
}