package com.example.bookapp1.Admin.NovelManagement;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Novel;
import com.example.bookapp1.R;

public class Novel_Add_Edit_Activity extends AppCompatActivity {
    private EditText titleEditText, authorEditText, categoryEditText, totalChaptersEditText, summaryEditText, contentEditText;
    private Button addNovelButton, chooseImageButton;
    ImageButton backButton;
    private ImageView thumbnailImageView;
    private DatabaseHelper dbHelper;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_add);

        dbHelper = DatabaseHelper.getInstance(this);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        totalChaptersEditText = findViewById(R.id.totalChaptersEditText);
        summaryEditText = findViewById(R.id.summaryEditText);
        contentEditText = findViewById(R.id.contentEditText); // New field
        thumbnailImageView = findViewById(R.id.thumbnailImageView);
        addNovelButton = findViewById(R.id.addNovelButton);
        chooseImageButton = findViewById(R.id.chooseImageButton);
        backButton = findViewById(R.id.ib_back);

        backButton.setOnClickListener(v -> finish());
        chooseImageButton.setOnClickListener(v -> openImageChooser());

        addNovelButton.setOnClickListener(v -> addNovelToDatabase());
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            if (imageUri != null) {
                thumbnailImageView.setImageURI(imageUri);
            } else {
                Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addNovelToDatabase() {
        String title = titleEditText.getText().toString().trim();
        String author = authorEditText.getText().toString().trim();
        String category = categoryEditText.getText().toString().trim();
        String totalChaptersStr = totalChaptersEditText.getText().toString().trim();
        String summary = summaryEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim(); // Get content

        if (title.isEmpty() || author.isEmpty() || category.isEmpty() || totalChaptersStr.isEmpty() || imageUri == null || content.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int totalChapters;
        try {
            totalChapters = Integer.parseInt(totalChaptersStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid number of chapters", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Novel instance with the content
        Novel novel = new Novel(0, title, author, category, totalChapters, imageUri.toString(), summary, content, 1);

        boolean isAdded = dbHelper.addNovel(novel);
        Log.d("Novel_Add_Edit_Activity", "Image URI: " + imageUri);
        if (isAdded) {
            Toast.makeText(this, "Novel added successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Failed to add novel", Toast.LENGTH_SHORT).show();
        }
    }
}