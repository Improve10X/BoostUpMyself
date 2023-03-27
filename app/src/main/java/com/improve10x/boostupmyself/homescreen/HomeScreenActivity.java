package com.improve10x.boostupmyself.homescreen;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.boostupmyself.Constants;
import com.improve10x.boostupmyself.LoginActivity;
import com.improve10x.boostupmyself.PlayVideoActivity;
import com.improve10x.boostupmyself.SavedVideosActivity;
import com.improve10x.boostupmyself.base.BaseActivity;
import com.improve10x.boostupmyself.categories.CategoriesActivity;
import com.improve10x.boostupmyself.R;
import com.improve10x.boostupmyself.databinding.ActivityHomeScreenBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends BaseActivity {

    private ArrayList<Video> videoItems = new ArrayList<>();
    private ActivityHomeScreenBinding binding;
    private VideoItemsAdapter videoItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Home");
        getVideos();
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
        } else if (item.getItemId() == R.id.log_out) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent intent = new Intent(HomeScreenActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void getVideos() {
        showProgressBar();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("videos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        hideProgressBar();
                        if (task.isSuccessful()) {
                            List<Video> videos = task.getResult().toObjects(Video.class);
                            getSavedVideos(videos);
                            showToast("Video Size : " + videos.size());
                        } else {
                            showToast("Failed to get data");
                        }
                    }
                });
    }

    private void setSavedVideo(Video video) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("/users/" + user.getUid() + "/savedVideos")
                .document(video.id)
                .set(video)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        showToast("Successfully added video");
                        getVideos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showToast("Failed to add Video");
                    }
                });
    }

    private void getSavedVideos(List<Video> videos) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("/users/" + user.getUid() + "/savedVideos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Video> savedVideos = task.getResult().toObjects(Video.class);
                            List<Video> updatedVideos = updateVideosWithBookmark(videos, savedVideos);
                            videoItemsAdapter.setData(updatedVideos);
                        }
                    }
                });
    }

    private List<Video> updateVideosWithBookmark(List<Video> videos, List<Video> savedVideos) {
        for (int videosIndex = 0; videosIndex < videos.size(); videosIndex++) {
            for (int savedVideosIndex = 0; savedVideosIndex < savedVideos.size(); savedVideosIndex++) {
                if (videos.get(videosIndex).id.equals(savedVideos.get(savedVideosIndex).id)) {
                    videos.get(videosIndex).bookmark = true;
                }
            }
        }
        return videos;
    }

    private void setupAdapter() {
        videoItemsAdapter = new VideoItemsAdapter();
        videoItemsAdapter.setData(videoItems);
        videoItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Video video) {
                showToast("Clicked");
                Intent intent = new Intent(HomeScreenActivity.this, PlayVideoActivity.class);
                intent.putExtra(Constants.HOME_SCREEN, video);
                startActivity(intent);
            }

            @Override
            public void onItemSave(Video video) {
                setSavedVideo(video);
            }

            @Override
            public void onItemUnSave(Video video) {
                getVideos();
            }
        });
    }

    private void setupVideoItemRv() {
        binding.videoItemRv.setLayoutManager(new LinearLayoutManager(this));
        binding.videoItemRv.setAdapter(videoItemsAdapter);
    }

    private void showProgressBar() {
        binding.progress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progress.setVisibility(View.GONE);
    }
}