package com.example.bookapp1;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class StartLogoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening); // Create a layout for the splash screen

        // Set a delay for the splash screen
        new Handler().postDelayed(() -> {
            // Start the main activity after the delay
            Intent intent = new Intent(StartLogoActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish the splash activity so the user can't go back to it
        }, 2000); // 2000 milliseconds (2 seconds)
    }
}

