package com.example.bookapp1.User.TaiKhoan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bookapp1.R;

public class taiKhoan_Fragment extends Fragment {

    TextView txt_nameProfile, txt_email, txt_phone;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan, container, false);

        txt_nameProfile = view.findViewById(R.id.txt_nameProfile);
        txt_email = view.findViewById(R.id.txt_email); // Assuming you have a TextView for email
        txt_phone = view.findViewById(R.id.txt_phone); // Assuming you have a TextView for phone

        // Retrieve username or email from SharedPreferences
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String username = preferences.getString("username", "Guest"); // Default to "Guest" if no value
        String email = preferences.getString("email", ""); // Retrieve email
        String phone = preferences.getString("phone", ""); // Retrieve phone

        // Display the username, email, and phone in the TextViews
        txt_nameProfile.setText(username);
        txt_email.setText(email);
        txt_phone.setText(phone);

        return view;
    }
}
