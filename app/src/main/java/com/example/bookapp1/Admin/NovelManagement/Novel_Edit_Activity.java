package com.example.bookapp1.Admin.NovelManagement;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Novel_Edit_Activity extends AppCompatActivity {

    private EditText titleEditText, authorEditText, categoryEditText, totalChaptersEditText, summaryEditText, contentEditText;
    private Button chooseImageButton, btnEdit;
    private ImageButton backButton;
    private ImageView thumbnailImageView;
    private DatabaseHelper dbHelper;
    private static final int PICK_IMAGE_REQUEST = 1;
    private String savedImagePath;
    private int novelId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novel_edit);

        dbHelper = DatabaseHelper.getInstance(this);

        // Initialize views
        titleEditText = findViewById(R.id.titleEditText);
        authorEditText = findViewById(R.id.authorEditText);
        categoryEditText = findViewById(R.id.categoryEditText);
        totalChaptersEditText = findViewById(R.id.totalChaptersEditText);
        summaryEditText = findViewById(R.id.summaryEditText);
        contentEditText = findViewById(R.id.contentEditText);
        thumbnailImageView = findViewById(R.id.thumbnailImageView);
        chooseImageButton = findViewById(R.id.chooseImageButton);
        btnEdit = findViewById(R.id.btn_edit);
        backButton = findViewById(R.id.ib_back);

        backButton.setOnClickListener(v -> finish());
        chooseImageButton.setOnClickListener(v -> openImageChooser());
        btnEdit.setOnClickListener(v -> updateNovelInDatabase());

        // Retrieve novel ID and load details
        novelId = getIntent().getIntExtra("novelId", -1);
        if (novelId != -1) {
            loadNovelDetails(novelId);
        } else {
            Toast.makeText(this, "Novel not found.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void loadNovelDetails(int novelId) {
        Novel novel = dbHelper.getNovelById(novelId);
        if (novel != null) {
            titleEditText.setText(novel.getTitle());
            authorEditText.setText(novel.getAuthor());
            categoryEditText.setText(novel.getCategory());
            totalChaptersEditText.setText(String.valueOf(novel.getTotalChapters()));
            summaryEditText.setText(novel.getSummary());
            contentEditText.setText(novel.getContent());

            savedImagePath = novel.getThumbnailUri();
            Bitmap bitmap = BitmapFactory.decodeFile(savedImagePath);
            if (bitmap != null) {
                thumbnailImageView.setImageBitmap(bitmap);
            }
        }
    }

    private void openImageChooser() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                try {
                    savedImagePath = saveImageLocally(imageUri);
                    Bitmap bitmap = BitmapFactory.decodeFile(savedImagePath);
                    thumbnailImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String saveImageLocally(Uri imageUri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(imageUri);
        if (inputStream == null) throw new IOException("Unable to open input stream");

        File imageFile = new File(getFilesDir(), "novel_" + System.currentTimeMillis() + ".jpg");
        FileOutputStream outputStream = new FileOutputStream(imageFile);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.close();
        inputStream.close();

        return imageFile.getAbsolutePath();
    }

    private void updateNovelInDatabase() {
        String title = titleEditText.getText().toString().trim();
        String author = authorEditText.getText().toString().trim();
        String category = categoryEditText.getText().toString().trim();
        String totalChaptersStr = totalChaptersEditText.getText().toString().trim();
        String summary = summaryEditText.getText().toString().trim();
        String content = contentEditText.getText().toString().trim();

        if (title.isEmpty() || author.isEmpty() || category.isEmpty() || totalChaptersStr.isEmpty() || savedImagePath == null || content.isEmpty()) {
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

        Novel novel = new Novel(novelId, title, author, category, totalChapters, savedImagePath, summary, content, novelId);

        boolean isUpdated = dbHelper.updateNovel(novel);
        if (isUpdated) {
            Toast.makeText(this, "Novel updated successfully", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "Failed to update novel", Toast.LENGTH_SHORT).show();
        }
    }
}
