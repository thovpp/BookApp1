package com.example.bookapp1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.User.MainAdapter;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Novel;

import java.util.List;

public class ViewAllNovelsActivity extends AppCompatActivity {

    private RecyclerView rvAllNovels;
    private GetAllNovelAdapter novelAdapter;
    private DatabaseHelper dbHelper;
    private RecyclerView rV_lichsu;
    private MainAdapter adapterLichsu;
    private List<BaseItem> itemListLichsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_novels);

        // Initialize DatabaseHelper
        dbHelper = DatabaseHelper.getInstance(this);

        // Fetch all novels
        List<Novel> novelList = dbHelper.getAllNovels();

        // Set up RecyclerView with GetAllNovelAdapter
        rvAllNovels = findViewById(R.id.rv_allnovels);
        rvAllNovels.setLayoutManager(new LinearLayoutManager(this));
        novelAdapter = new GetAllNovelAdapter(novelList, this);
        rvAllNovels.setAdapter(novelAdapter);





    }



}