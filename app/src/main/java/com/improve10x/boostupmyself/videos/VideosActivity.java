package com.improve10x.boostupmyself.videos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.boostupmyself.Constants;
import com.improve10x.boostupmyself.PlayVideoActivity;
import com.improve10x.boostupmyself.categories.Category;
import com.improve10x.boostupmyself.databinding.ActivityVideosBinding;
import com.improve10x.boostupmyself.homescreen.HomeScreenActivity;
import com.improve10x.boostupmyself.homescreen.OnItemActionListener;
import com.improve10x.boostupmyself.homescreen.Video;
import com.improve10x.boostupmyself.homescreen.VideoItemsAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideosActivity extends AppCompatActivity {

    public ArrayList<Video> videos = new ArrayList<>();
    private ActivityVideosBinding binding;
    private VideoItemsAdapter videoItemsAdapter;
    private Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Videos");
        Intent intent = getIntent();
        intent.hasExtra(Constants.CATEGORY_ID);
        category = (Category) intent.getSerializableExtra(Constants.CATEGORY_ID);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getVideos();
        setupCategoryNamesAdapter();
        setupCategoryNameRv();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void getVideos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        db.collection("videos")
                .whereEqualTo("categoryId", category.categoryId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            List<Video> videos = task.getResult().toObjects(Video.class);
                            videoItemsAdapter.setData(videos);
                            Toast.makeText(VideosActivity.this, "Video Size : " + videos.size(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(VideosActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void setupCategoryNamesAdapter() {
        videoItemsAdapter = new VideoItemsAdapter();
        videoItemsAdapter.setData(videos);
        videoItemsAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Video video) {
                Toast.makeText(VideosActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(VideosActivity.this, PlayVideoActivity.class);
                    intent.putExtra(Constants.HOME_SCREEN, video);
                    startActivity(intent);
                }
        });
    }

    private void setupCategoryNameRv() {
        binding.videosRv.setLayoutManager(new LinearLayoutManager(this));
        binding.videosRv.setAdapter(videoItemsAdapter);
    }
}