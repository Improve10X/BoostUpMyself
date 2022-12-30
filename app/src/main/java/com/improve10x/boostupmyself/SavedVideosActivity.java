package com.improve10x.boostupmyself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SavedVideosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_videos);
        getSupportActionBar().setTitle("Saved Videos");
    }
}