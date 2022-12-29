package com.example.boostupmyself;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.boostupmyself.databinding.ActivityHomeScreenBinding;

import java.util.ArrayList;

public class HomeScreenActivity extends AppCompatActivity {

    private ArrayList<Video> videoItemList;
    private ActivityHomeScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Home");
    }
}