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

public class Novel_Edit_Activity extends AppCompatActivity {
    private EditText titleEditText, authorEditText, categoryEditText, totalChaptersEditText, summaryEditText, contentEditText;
    private Button editNovelButton, chooseImageButton;
    ImageButton backButton;
    private ImageView thumbnailImageView;
    private DatabaseHelper dbHelper;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_edit);

        dbHelper = DatabaseHelper.getInstance(this);

        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        totalChaptersEditText = findViewById(R.id.totalChaptersEditText);
        summaryEditText = findViewById(R.id.summaryEditText);
        contentEditText = findViewById(R.id.contentEditText); // New field
        thumbnailImageView = findViewById(R.id.thumbnailImageView);
        editNovelButton = findViewById(R.id.btn_edit);
        chooseImageButton = findViewById(R.id.chooseImageButton);
        backButton = findViewById(R.id.ib_back);

        backButton.setOnClickListener(v -> finish());
        chooseImageButton.setOnClickListener(v -> openImageChooser());
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

}