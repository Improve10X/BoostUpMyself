package com.example.boostupmyself.homescreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.boostupmyself.CategoriesActivity;
import com.example.boostupmyself.R;
import com.example.boostupmyself.SavedVideosActivity;
import com.example.boostupmyself.databinding.ActivityHomeScreenBinding;
import com.example.boostupmyself.homescreen.Video;
import com.example.boostupmyself.homescreen.VideoItemsAdapter;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity {

    private ArrayList<Video> videoItems;
    private ActivityHomeScreenBinding binding;
    private VideoItemsAdapter videoItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Home");
        setupData();
        setupAdapter();
        setupVideoItemRv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.categories) {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.saved_videos) {
            Intent intent = new Intent(this, SavedVideosActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void setupData() {
        videoItems = new ArrayList<>();
        Video english = new Video("Samantha english speech", "https://i.ytimg.com/vi/s5BMcaQsjbM/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCiKGYtTAt37RRIBnJaJQjJOLiT5Q", "3 months ago", "Surya", "https://yt3.ggpht.com/3ErdBd0bg2Qw5rKdqDK-7vPAf0tirRuodlGGZuhZePQcjEu8i5KniCN-EUCBtQkSOy14M26O=s68-c-k-c0x00ffffff-no-rj");
        videoItems.add(english);
    }

    private void setupAdapter() {
        videoItemsAdapter = new VideoItemsAdapter();
        videoItemsAdapter.setData(videoItems);
    }

    private void setupVideoItemRv() {
        binding.videoItemRv.setLayoutManager(new LinearLayoutManager(this));
        binding.videoItemRv.setAdapter(videoItemsAdapter);
    }
}