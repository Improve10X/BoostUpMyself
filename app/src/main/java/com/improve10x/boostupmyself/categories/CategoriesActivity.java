package com.improve10x.boostupmyself.categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.improve10x.boostupmyself.Constants;
import com.improve10x.boostupmyself.base.BaseActivity;
import com.improve10x.boostupmyself.databinding.ActivityCategoriesBinding;
import com.improve10x.boostupmyself.api.VideoService;
import com.improve10x.boostupmyself.api.VideosApi;
import com.improve10x.boostupmyself.homescreen.HomeScreenActivity;
import com.improve10x.boostupmyself.homescreen.Video;
import com.improve10x.boostupmyself.videos.VideosActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoriesActivity extends BaseActivity {

    private ArrayList<Category> categories = new ArrayList<>();
    private ActivityCategoriesBinding binding;
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().setTitle("Categories");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getCategories();
        setupCategoriesAdapter();
        setupCategoriesRv();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return false;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void getCategories() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Category> categories = task.getResult().toObjects(Category.class);
                            categoriesAdapter.setData(categories);
                            showToast("Category Size : " + categories.size());
                        } else {
                            showToast("Failed to get data");
                        }
                    }
                });
    }

    public void setupCategoriesAdapter() {
        categoriesAdapter = new CategoriesAdapter();
        categoriesAdapter.setData(categories);
        categoriesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onItemClicked(Category category) {
                showToast("Clicked");
                Intent intent = new Intent(CategoriesActivity.this, VideosActivity.class);
                intent.putExtra(Constants.CATEGORY_ID, category);
                startActivity(intent);
            }
        });
        binding.categoriesRv.setAdapter(categoriesAdapter);
    }

    private void setupCategoriesRv() {
        binding.categoriesRv.setLayoutManager(new LinearLayoutManager(this));
    }
}