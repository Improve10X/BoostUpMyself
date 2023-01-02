package com.improve10x.boostupmyself;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.boostupmyself.databinding.ActivityCategoryNameBinding;
import com.improve10x.boostupmyself.homescreen.HomeScreenActivity;
import com.improve10x.boostupmyself.homescreen.Video;

import java.util.ArrayList;
import java.util.List;

public class CategoryNameActivity extends AppCompatActivity {

    public ArrayList<CategoryName> categoryNames;
    private ActivityCategoryNameBinding binding;
    private CategoryNamesAdapter categoryNamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoryNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("CategoryName");
        //setupData();
        fetchVideos();
        setupCategoryNamesAdapter();
        setupCategoryNameRv();
    }

    private void fetchVideos() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            List<CategoryName> categoryNames = task.getResult().toObjects(CategoryName.class);
                            categoryNamesAdapter.setData(categoryNames);
                            Toast.makeText(CategoryNameActivity.this, "Video Size : " + categoryNames.size(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(CategoryNameActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
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
        categoryNamesAdapter = new CategoryNamesAdapter();
        categoryNamesAdapter.setData(categoryNames);
    }

    private void setupCategoryNameRv() {
        binding.categoryNameRv.setLayoutManager(new LinearLayoutManager(this));
        binding.categoryNameRv.setAdapter(categoryNamesAdapter);
    }
}