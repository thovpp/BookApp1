package com.example.bookapp1.User.TaiKhoan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bookapp1.Admin.AdminActivity;
import com.example.bookapp1.LoginActivity;
import com.example.bookapp1.R;

public class taiKhoan_notLogin_Fragment extends Fragment {
    FrameLayout btnLogin,btnLogout, btnHDSD, btn_profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taikhoan_notlogin, container, false);

        FrameLayout btn_admin = view.findViewById(R.id.btn_movetoAdminforTest);
        btn_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }
        });

        btnLogout = view.findViewById(R.id.btn_logout);

        btnLogin = view.findViewById(R.id.btn_dangnhap);
        btnHDSD = view.findViewById(R.id.btn_hdsd);
        btn_profile = view.findViewById(R.id.btn_profile);

        // Check login status
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // User is logged in, hide the login button
            btnLogin.setVisibility(View.GONE);
        } else {
            // User is not logged in, show the login button
            btnLogout.setVisibility(View.GONE);
            btn_profile.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogout();
            }
        });

        btnHDSD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HdsdActivity.class);
                startActivity(intent);
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new taiKhoan_Fragment());
            }
        });

        return view;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager(); // Use parent fragment manager
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment) // Ensure this ID is valid for your layout
                .addToBackStack(null) // Optional: add to back stack
                .commit();
    }

    private void performLogout() {
        SharedPreferences preferences = requireActivity().getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("isLoggedIn", false); // Reset login status on logout
        editor.apply();

        // Redirect to LoginActivity or handle UI updates
        Intent intent = new Intent(requireActivity(), LoginActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }
}