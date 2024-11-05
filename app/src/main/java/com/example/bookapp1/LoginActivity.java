package com.example.bookapp1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookapp1.Admin.AdminActivity;
import com.example.bookapp1.User.MainActivity;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Reader;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize database helper
        dbHelper = DatabaseHelper.getInstance(this);

        // Find views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button loginButton = findViewById(R.id.loginButton);
        Button signUpButton = findViewById(R.id.signUpButton);
        ImageButton ivBack = findViewById(R.id.iv_exit);

        // Back button to exit the activity
        ivBack.setOnClickListener(v -> finish());

        // Set up login button
        loginButton.setOnClickListener(v -> performLogin());

        // Set up sign-up button (optional: navigate to a Sign-Up activity)
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void performLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email và mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        // Attempt to login
        try {
            Reader reader = dbHelper.loginReader(email, password);
            if (reader != null) {
                Log.d("LoginActivity", "Login successful.");
                Log.d("LoginActivity", "User role: " + reader.getRole());
                // Check the user's role
                if ("Admin".equals(reader.getRole())) {
                    Log.d("LoginActivity", "User is an Admin, redirecting to AdminActivity.");
                    Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("LoginActivity", "User is not an Admin, redirecting to MainActivity.");
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                // Show login success message
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                // Save login status and user information in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("isLoggedIn", true); // Mark user as logged in
                editor.putInt("reader_id", reader.getReaderId()); // Save the reader_id
                editor.putString("email", reader.getEmail()); // Save email
                editor.putString("phone", reader.getPhone()); // Save phone
                editor.putString("role", reader.getRole()); // Save role
                editor.apply();

                // Finish LoginActivity after redirecting
                finish();

            } else {
                Log.d("LoginActivity", "Login failed, staying on LoginActivity.");
                Toast.makeText(this, "Email hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                clearInputs(); // Clear the inputs on failed login
            }
        } catch (Exception e) {
            Log.e("LoginActivity", "Error during login", e);
            Toast.makeText(this, "Có lỗi xảy ra, vui lòng thử lại sau.", Toast.LENGTH_SHORT).show();
        }
    }



    private void clearInputs() {
        emailEditText.setText("");
        passwordEditText.setText("");
    }


}