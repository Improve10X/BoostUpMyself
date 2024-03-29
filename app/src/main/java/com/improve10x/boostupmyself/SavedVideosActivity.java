package com.improve10x.boostupmyself;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.boostupmyself.base.BaseActivity;
import com.improve10x.boostupmyself.databinding.ActivitySavedVideosBinding;
import com.improve10x.boostupmyself.homescreen.HomeScreenActivity;
import com.improve10x.boostupmyself.homescreen.OnItemActionListener;
import com.improve10x.boostupmyself.homescreen.Video;
import com.improve10x.boostupmyself.homescreen.VideoItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class SavedVideosActivity extends BaseActivity {

    private ArrayList<Video> savedVideos = new ArrayList<>();
    private VideoItemsAdapter videoItemsAdapter;
    private ActivitySavedVideosBinding binding;
    private Video video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySavedVideosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Saved Videos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        intent.hasExtra(Constants.HOME_SCREEN);
        video = (Video) intent.getSerializableExtra(Constants.HOME_SCREEN);
        getVideos();
        setupVideoItemsAdapter();
        setupSavedVideosRv();
//        unSaveVideo(video.id);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void getVideos() {
        showProgressBar();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("/users/" + user.getUid() + "/savedVideos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        hideProgressBar();
                        if (task.isSuccessful()) {
                            List<Video> videos = task.getResult().toObjects(Video.class);
                            for (int videosIndex = 0; videosIndex < videos.size(); videosIndex++) {
                                videos.get(videosIndex).bookmark = true;
                            }
                            videoItemsAdapter.setData(videos);
                            showToast("Video Size : " + videos.size());
                        } else {
                            showToast("Failed to get data");
                        }
                    }
                });
    }

    private void unSaveVideo(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("/users/" + user.getUid() + "/savedVideos")
                .document(id)
                .delete()
                .addOnSuccessListener(unused -> {
                    showToast("Successfully unsave the video");
                    getVideos();
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showToast("Failed to unsave video");
                    }
                });
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        showToast("Failed to unsave the video");
//                    }
//                });
    }

    private void setupVideoItemsAdapter() {
        videoItemsAdapter = new VideoItemsAdapter();
        videoItemsAdapter.setData(savedVideos);
        videoItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Video video) {
                showToast("Failed to get data");
                Intent intent = new Intent(SavedVideosActivity.this, PlayVideoActivity.class);
                intent.putExtra(Constants.HOME_SCREEN, video);
                startActivity(intent);
            }

            @Override
            public void onItemSave(Video video) {
            }

            @Override
            public void onItemUnSave(Video video) {
                unSaveVideo(video.id);
            }
        });
    }

    private void setupSavedVideosRv() {
        binding.savedVideosRv.setLayoutManager(new LinearLayoutManager(this));
        binding.savedVideosRv.setAdapter(videoItemsAdapter);
    }

    private void showProgressBar() {
        binding.progress.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        binding.progress.setVisibility(View.GONE);
    }
}