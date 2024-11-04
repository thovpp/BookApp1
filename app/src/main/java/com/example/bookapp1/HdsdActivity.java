package com.example.bookapp1;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class HdsdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hdsd);

        ImageButton ivBack = findViewById(R.id.btn_back);
        ivBack.setOnClickListener(v -> finish());
    }
}