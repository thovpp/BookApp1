package com.example.bookapp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bookapp1.Novel_Fragment.binhLuan_Fragment;
import com.example.bookapp1.Novel_Fragment.danhGia_Fragment;
import com.example.bookapp1.Novel_Fragment.dsChuong_Fragment;
import com.example.bookapp1.Novel_Fragment.gioithieu_Fragment;
import com.example.bookapp1.database.DatabaseHelper;
import com.example.bookapp1.models.Novel;

public class NovelActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ImageView novelImageView;
    private TextView titleTextView, authorTextView, categoryTextView, summaryTextView, ratingTextView;
    private int novelId;
    private Button btnGioiThieu, btnDanhGia, btnBinhLuan, btnDSChuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtintruyen);

        // Initialize buttons
        btnGioiThieu = findViewById(R.id.btn_gioithieu);
        btnDanhGia = findViewById(R.id.btn_danhgia);
        btnBinhLuan = findViewById(R.id.btn_binhluan);
        btnDSChuong = findViewById(R.id.btn_dschuong);

        // Get novel ID from the Intent
        int novelId = getIntent().getIntExtra("NOVEL_ID", -1);

        // Initialize the DatabaseHelper and get novel details
        dbHelper = DatabaseHelper.getInstance(this);
        Novel novel = dbHelper.getNovelById(novelId);

        if (novel != null) {
            // Set novel details to views (e.g., ImageView, TextView) as needed
            ImageView thumbnailImageView = findViewById(R.id.imageView2);
            TextView titleTextView = findViewById(R.id.textView12);
            TextView authorTextView = findViewById(R.id.textView000);

            // Set the data
            titleTextView.setText(novel.getTitle());
            authorTextView.setText(novel.getAuthor());

            // Set the default fragment
            loadFragment(gioithieu_Fragment.newInstance(novel.getSummary()));


            // Button click listeners to switch fragments
            btnGioiThieu.setOnClickListener(v -> loadFragment(new gioithieu_Fragment()));
            btnDanhGia.setOnClickListener(v -> loadFragment(new danhGia_Fragment()));
            btnBinhLuan.setOnClickListener(v -> loadFragment(new binhLuan_Fragment()));
            btnDSChuong.setOnClickListener(v -> loadFragment(new dsChuong_Fragment()));
            // Load image in thumbnailImageView
            // Set other details as needed
        } else {
            // If no novel found, show an error and close the activity
            Toast.makeText(this, "Novel details not available", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void displayNovelDetails(int novelId) {
        Novel novel = dbHelper.getNovelById(novelId);
        if (novel != null) {
            titleTextView.setText(novel.getTitle());
            authorTextView.setText(novel.getAuthor());
            categoryTextView.setText(novel.getCategory());
            summaryTextView.setText(novel.getSummary());
            ratingTextView.setText("4.9"); // Placeholder rating

            // Load thumbnail image, use a library like Glide or Picasso if needed
            novelImageView.setImageResource(R.drawable.background_novel_1); // Placeholder image
        } else {
            Toast.makeText(this, "Novel not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFragment(Fragment fragment) {
        // Replace fragment in container
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

}