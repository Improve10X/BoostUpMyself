package com.example.boostupmyself;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        getSupportActionBar().setTitle("Categories");
    }
}