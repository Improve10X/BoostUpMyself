package com.improve10x.boostupmyself.videos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.boostupmyself.databinding.ActivityVideosBinding;

import java.util.ArrayList;
import java.util.List;

public class VideosActivity extends AppCompatActivity {

    public ArrayList<Video> videos;
    private ActivityVideosBinding binding;
    private VideosAdapter videosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Videos");
        //setupData();
        fetchVideos();
        setupCategoryNamesAdapter();
        setupCategoryNameRv();
    }

    private void fetchVideos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("videos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            List<Video> videos = task.getResult().toObjects(Video.class);
                            videosAdapter.setData(videos);
                            Toast.makeText(VideosActivity.this, "Video Size : " + videos.size(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(VideosActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

  /*  private void setupData() {
        categoryNames = new ArrayList<>();
        CategoryName categoryName = new CategoryName("Samantha English Videos", "https://i.ytimg.com/vi/s5BMcaQsjbM/hq720.jpg?sqp=-oaymwEcCNAFEJQDSFXyq4qpAw4IARUAAIhCGAFwAcABBg==&rs=AOn4CLCiKGYtTAt37RRIBnJaJQjJOLiT5Q", "1 day ago", "English Speeches", "https://yt3.ggpht.com/3ErdBd0bg2Qw5rKdqDK-7vPAf0tirRuodlGGZuhZePQcjEu8i5KniCN-EUCBtQkSOy14M26O=s68-c-k-c0x00ffffff-no-rj");
        categoryNames.add(categoryName);
    }
*/
    private void setupCategoryNamesAdapter() {
        videosAdapter = new VideosAdapter();
        videosAdapter.setData(videos);
    }

    private void setupCategoryNameRv() {
        binding.categoryNameRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoryNameRv.setAdapter(videosAdapter);
    }
}