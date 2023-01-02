package com.improve10x.boostupmyself.savedvideos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.boostupmyself.databinding.ActivitySavedVideoItemsBinding;
import com.improve10x.boostupmyself.homescreen.HomeScreenActivity;
import com.improve10x.boostupmyself.homescreen.Video;

import java.util.ArrayList;
import java.util.List;

public class SavedVideoItemsActivity extends AppCompatActivity {

    private ArrayList<SavedVideos> savedVideoItems = new ArrayList<>();

    private ActivitySavedVideoItemsBinding binding;

    private SavedVideoItemsAdapter savedVideoItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding = ActivitySavedVideoItemsBinding.inflate(getLayoutInflater());
        getSupportActionBar().setTitle("Saved Videos");
        setupData();
        fetchVideos();
        setupSavedVideosRv();
        setupSavedVideoItemsAdapter();
    }

    private void setupData() {
        savedVideoItems = new ArrayList<>();
        SavedVideos savedVideos = new SavedVideos();
        savedVideos.imageUrl = "https://i.ytimg.com/vi/H8NxLkm231Q/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCgCO06SZGeXOeSV2KxZhdUoMyPaw";
        savedVideos.channelLogoUrl = "https://yt3.ggpht.com/ytc/AMLnZu-tZwMfO7URtC5z9fDqNn3MoRk1mGbMesjehoLaaP0=s68-c-k-c0x00ffffff-no-rj";
        savedVideos.title = "speeches";
        savedVideos.channelName = "charan videos";
        savedVideos.uploadedTime = "12 months ago";
    }

    private void fetchVideos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("videos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            List<SavedVideos> savedVideos = task.getResult().toObjects(SavedVideos.class);
                            savedVideoItemsAdapter.setData(savedVideoItems);
                            Toast.makeText(SavedVideoItemsActivity.this, "Video Size : " + savedVideos.size(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SavedVideoItemsActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setupSavedVideoItemsAdapter() {
        savedVideoItemsAdapter = new SavedVideoItemsAdapter();
        savedVideoItemsAdapter.setData(savedVideoItems);
        binding.savedVideosRv.setAdapter(savedVideoItemsAdapter);
    }

    private void setupSavedVideosRv() {
        binding.savedVideosRv.setLayoutManager(new LinearLayoutManager(this));
    }
}