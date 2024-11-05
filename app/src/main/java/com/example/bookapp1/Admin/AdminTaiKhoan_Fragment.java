package com.example.bookapp1.Admin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bookapp1.LoginActivity;
import com.example.bookapp1.R;

public class AdminTaiKhoan_Fragment extends Fragment {

    private FrameLayout btnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_taikhoan_admin, container, false);

        // Initialize logout button
        btnLogout = view.findViewById(R.id.btn_logout);

        // Set click listener for logout button
        btnLogout.setOnClickListener(v -> logout());

        return view;
    }

    private void logout() {
        // Clear login status and user info from SharedPreferences
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", requireActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();  // Remove all saved preferences
        editor.apply();

        // Redirect to LoginActivity
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        startActivity(intent);
        requireActivity().finish();  // Close the current activity to prevent going back
    }
}
