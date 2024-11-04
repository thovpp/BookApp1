package com.example.bookapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bookapp1.database.DatabaseHelper;
import com.example.bookapp1.models.Reader;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText, rePasswordEditText, phoneEditText; // Added phoneEditText
    private Button registerButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize DatabaseHelper
        dbHelper = DatabaseHelper.getInstance(this);

        // Link UI elements
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rePasswordEditText = findViewById(R.id.rePasswordEditText);
        phoneEditText = findViewById(R.id.phoneEditText); // Link the new phoneEditText
        registerButton = findViewById(R.id.registerButton);

        // Set up button click listener
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewReader();
            }
        });
    }

    private void registerNewReader() {
        // Get input values
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String rePassword = rePasswordEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim(); // Get the phone number

        // Validate input
        if (email.isEmpty() || password.isEmpty() || rePassword.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(rePassword)) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create new reader object
        Reader newReader = new Reader(0, "defaultUsername", password, email, phone, "Active", "Reader");

        // Register reader in the database
        long result = dbHelper.registerReader(newReader);

        if (result != -1) {
            // Registration successful
            Log.d("Register", "Registration successful for email: " + email);
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

            // Optionally, redirect to login or main activity
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } else {
            // Registration failed
            Log.d("Register", "Registration failed for email: " + email);
            Toast.makeText(this, "Registration failed. Try again.", Toast.LENGTH_SHORT).show();
        }
    }
}