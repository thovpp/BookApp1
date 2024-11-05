package com.example.bookapp1.Admin.NovelManagement;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Add_Chapter_Activity extends AppCompatActivity {
    // Add a novel ID and mode flag
    private int novelId = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_chapter);

        // Retrieve novel details passed from the adapter
        String novelTitle = getIntent().getStringExtra("novelTitle");
        String novelAuthor = getIntent().getStringExtra("novelAuthor");
        String novelImg = getIntent().getStringExtra("image");
        int chapterCount = getIntent().getIntExtra("chapterCount", 0); // Default to 0 if not found

        // Find views and set data
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvAuthor = findViewById(R.id.tv_author);
        TextView tvTotalChapter = findViewById(R.id.tv_tongsochuong);
        TextView currentChapterText = findViewById(R.id.currentChapterText);
        ImageView imageView = findViewById(R.id.iv_quanlisach_thumb);

        // Set the novel details to the views
        tvTitle.setText(novelTitle);
        tvAuthor.setText(novelAuthor);
        tvTotalChapter.setText(String.valueOf(chapterCount));
        currentChapterText.setText("Chương: " + (chapterCount + 1));

        Button addChapterButton = findViewById(R.id.addChapterButton);
        addChapterButton.setOnClickListener(v -> {
            String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
            String content = ((EditText) findViewById(R.id.contentEditText)).getText().toString();
            int length = content.length(); // Calculate length based on content length
            String status = "published"; // Or any other logic to determine status
            int isVip = 0; // Example value, set based on conditions
            int coinRequired = 0; // Example value
            String createdDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            String updatedDate = createdDate;
            int novelId = getIntent().getIntExtra("novelId", -1); // Get novelId from Intent extras
            Log.d("Add_Chapter_Activity", "Retrieved novelId: " + novelId);

            DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
            boolean isAdded = dbHelper.addChapter(title, length, content, status, isVip, coinRequired, createdDate, updatedDate, novelId);

            if (isAdded) {
                Toast.makeText(this, "Chapter added successfully", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK); // Notify that the operation succeeded
                finish(); // Close activity
            } else {
                Toast.makeText(this, "Failed to add chapter", Toast.LENGTH_SHORT).show();
            }
        });
    }
}