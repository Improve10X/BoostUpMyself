package com.improve10x.boostupmyself;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.improve10x.boostupmyself.databinding.ActivityPlayVideoBinding;
import com.improve10x.boostupmyself.homescreen.Video;
import com.squareup.picasso.Picasso;

public class PlayVideoActivity extends AppCompatActivity {

    private ActivityPlayVideoBinding binding;
    private Video video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityPlayVideoBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Play video");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        intent.hasExtra(Constants.HOME_SCREEN);
        video = (Video) intent.getSerializableExtra(Constants.HOME_SCREEN);
        showData();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showData() {
        Picasso.get().load(video.imageUrl).into(binding.videoImg);
        Picasso.get().load(video.channelLogoUrl).into(binding.channelLogoImg);
        binding.videoTitleTxt.setText(video.title);
        binding.channelNameTxt.setText(video.channelName);
        binding.uploadedTimeTxt.setText(video.uploadedTime);
    }
}