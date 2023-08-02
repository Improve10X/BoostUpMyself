package com.improve10x.boostupmyself;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.boostupmyself.databinding.ActivityPlayVideoBinding;
import com.improve10x.boostupmyself.homescreen.Video;
import com.improve10x.boostupmyself.videos.VideosActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayVideoActivity extends YouTubeBaseActivity {

    private ActivityPlayVideoBinding binding;
    private Video video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        intent.hasExtra(Constants.HOME_SCREEN);
        video = (Video) intent.getSerializableExtra(Constants.HOME_SCREEN);
        showData();
        setupWebView(video.youtubeVideoId);
        // setupYouTubePlayerView();
    }

    private void setupWebView(String id) {
        String videos = "<iframe id='player' type='text/html' width='100%' height='100%'\n" +
                "  src='http://www.youtube.com/embed/"+id+"?enablejsapi=1&origin=http://boostupmyself.improve10x.com'\n" +
                "  frameborder='0'allowfullscreen='allowfullscreen'></iframe>";
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.setWebChromeClient(new WebChromeClient());
        binding.webview.loadData(videos, "text/html", "utf-8");

    }

    /*private void setupYouTubePlayerView() {
        binding.youtubePlayerView.initialize(Constants.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(video.youtubeVideoId);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(PlayVideoActivity.this, "Fail to load video", Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    private void showData() {
        Picasso.get().load(video.channelLogoUrl).into(binding.channelLogoImg);
        binding.videoTitleTxt.setText(video.title);
        binding.channelNameTxt.setText(video.channelName);
        binding.uploadedTimeTxt.setText(video.uploadedTime);
    }
}