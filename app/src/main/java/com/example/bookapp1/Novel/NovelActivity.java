package com.example.bookapp1.Novel;

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

import com.bumptech.glide.Glide;
import com.example.bookapp1.R;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Novel;

import java.io.File;

public class NovelActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ImageView novelImageView;
    private TextView titleTextView, authorTextView, categoryTextView, summaryTextView, ratingTextView;
    private int novelId;
    private Button btnGioiThieu, btnDanhGia, btnBinhLuan, btnDSChuong;
    private View underline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtintruyen);
        underline = findViewById(R.id.underline);
        // Initialize buttons
        btnGioiThieu = findViewById(R.id.btn_gioithieu);
        btnDanhGia = findViewById(R.id.btn_danhgia);
        btnBinhLuan = findViewById(R.id.btn_binhluan);
        btnDSChuong = findViewById(R.id.btn_dschuong);
        ImageButton ivBack = findViewById(R.id.btn_novel_back);
        ivBack.setOnClickListener(v -> finish());
        // Get novel ID from the Intent

        // Initialize the DatabaseHelper and get novel details
        dbHelper = DatabaseHelper.getInstance(this);
        setUnderline(btnGioiThieu);
        loadFragment(new gioithieu_Fragment());
        btnGioiThieu.setOnClickListener(view -> {
            setUnderline(btnGioiThieu);
            loadFragment(new gioithieu_Fragment());
        });
        btnDanhGia.setOnClickListener(view -> {
            setUnderline(btnDanhGia);
            loadFragment(new danhGia_Fragment());
        });
        btnBinhLuan.setOnClickListener(view -> {
            setUnderline(btnBinhLuan);
            loadFragment(new binhLuan_Fragment());
        });
        btnDSChuong.setOnClickListener(view -> {
            setUnderline(btnDSChuong);
            loadFragment(new dsChuong_Fragment());
        });

        int novelId = getIntent().getIntExtra("NOVEL_ID", -1);
        Novel novel = dbHelper.getNovelById(novelId);

        if (novel != null) {
            novelImageView = findViewById(R.id.imageView2);
            Log.d("Image URI", "URI for Glide: " + novel.getThumbnailUri());

            File imageFile = new File(novel.getThumbnailUri());
            if (imageFile.exists()) {
                Glide.with(this)
                        .load(imageFile)  // Load the file path
                        .placeholder(R.drawable.background_novel_1)  // Placeholder while loading
                        .error(R.drawable._logo)  // Image if loading fails
                        .into(novelImageView);  // Load image into ImageView
            } else {
                // If file doesn’t exist, set a default image
                novelImageView.setImageResource(R.drawable._logo);
            }

            // Set novel details to views (e.g., ImageView, TextView) as needed
            ImageView thumbnailImageView = findViewById(R.id.imageView2);
            TextView titleTextView = findViewById(R.id.tv_title);
            TextView authorTextView = findViewById(R.id.tv_author);
            TextView genreTextView = findViewById(R.id.tv_genre);
            // Set the data
            titleTextView.setText(novel.getTitle());
            authorTextView.setText(novel.getAuthor());
            genreTextView.setText(novel.getCategory());
            // Set the default fragment
            loadFragment(gioithieu_Fragment.newInstance(novel.getSummary()));
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

    private void setUnderline(Button button) {
        ViewGroup.LayoutParams params = underline.getLayoutParams();
        params.width = button.getWidth();
        underline.setLayoutParams(params);
        underline.setX(button.getX());
    }


}