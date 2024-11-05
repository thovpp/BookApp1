package com.example.bookapp1.Admin.NovelManagement;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.Chapter.ChapterActivity;
import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_Chapter;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.R;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DanhSachChuongActivity extends AppCompatActivity {

    private RecyclerView rV_chuong;
    private ChapterAdapter adapterChuong;
    private List<RvItem_Chapter> itemListChapters = new ArrayList<>();
    private ImageButton btnArrange, ivBack;
    private TextView tvNovelTitle;
    private boolean isSortedByOldest = true;
    private DatabaseHelper dbHelper;
    private static final int REQUEST_CODE_ADD_CHAPTER = 1;
    private int novelId;  // Declare novelId as a class-level variable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dschuong);

        // Initialize DatabaseHelper
        dbHelper = DatabaseHelper.getInstance(this);

        // Retrieve novel ID and title
         novelId = getIntent().getIntExtra("novelId", -1);
        String novelTitle = getIntent().getStringExtra("novelTitle");

        // Set the novel title in a TextView
        tvNovelTitle = findViewById(R.id.tv_title);
        tvNovelTitle.setText(novelTitle);

        // Initialize RecyclerView
        rV_chuong = findViewById(R.id.rv_dschuong);
        rV_chuong.setLayoutManager(new LinearLayoutManager(this));

        // Initialize Back and Sort Buttons
        ivBack = findViewById(R.id.btn_dschuong_back);
        btnArrange = findViewById(R.id.ib_chuong_arrange);

        // Back button action
        ivBack.setOnClickListener(v -> finish());

        // Sort button action
        btnArrange.setOnClickListener(v -> toggleSort());

        // Load chapters and set adapter
        loadChapters(novelId);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_CHAPTER && resultCode == RESULT_OK) {
            loadChapters(novelId); // Refresh chapters list after a new one is added
        }
    }

    private void loadChapters(int novelId) {
        itemListChapters = dbHelper.getChaptersForNovel(novelId);

        // Set up adapter with chapter and delete listeners
        adapterChuong = new ChapterAdapter(itemListChapters, chapter -> {
            Intent intent = new Intent(DanhSachChuongActivity.this, ChapterActivity.class);
            intent.putExtra("CHAPTER_ID", chapter.getID());
            startActivity(intent);
        }, chapter -> {
            // Remove the chapter from the database and refresh the list
            dbHelper.deleteChapterById(chapter.getID(), novelId);
            itemListChapters.remove(chapter);
            adapterChuong.notifyDataSetChanged();
        });
        rV_chuong.setAdapter(adapterChuong);
    }



    private void toggleSort() {
        if (isSortedByOldest) {
            itemListChapters.sort((c1, c2) -> c2.getTime().compareTo(c1.getTime())); // Sort by newest
        } else {
            itemListChapters.sort((c1, c2) -> c1.getTime().compareTo(c2.getTime())); // Sort by oldest
        }
        isSortedByOldest = !isSortedByOldest; // Toggle the sort order
        adapterChuong.notifyDataSetChanged();
    }

    public void deleteChapterById(int chapterId) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        db.delete("Chapter", "chapter_id = ?", new String[]{String.valueOf(chapterId)});
        db.close();
    }
}

