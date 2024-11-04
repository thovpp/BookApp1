package com.example.bookapp1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.Dtos.BaseItem;
import com.example.bookapp1.Dtos.RvItem_History;
import com.example.bookapp1.Main_Fragment.MainAdapter;
import com.example.bookapp1.Main_Fragment.khamPha_Fragment;
import com.example.bookapp1.Main_Fragment.taiKhoan_Fragment;
import com.example.bookapp1.Main_Fragment.tuTruyen_Fragment;
import com.example.bookapp1.Main_Fragment.xepHang_Fragment;
import com.example.bookapp1.database.DatabaseHelper;
import com.example.bookapp1.models.Novel;

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