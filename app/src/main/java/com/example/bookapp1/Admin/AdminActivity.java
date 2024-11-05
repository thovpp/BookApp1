package com.example.bookapp1.Admin;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bookapp1.Admin.NovelManagement.QuanLiSach_Fragment;
import com.example.bookapp1.Admin.UserManagement.QuanLiReaderFragment;
import com.example.bookapp1.R;

public class AdminActivity extends AppCompatActivity {
    private LinearLayout btnQlSach, btnQlNguoidung, btnTaiKhoan;
    private LinearLayout currentSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_thanhdieuhuong);

        btnQlSach = findViewById(R.id.btn_quanliSach);
        btnQlNguoidung = findViewById(R.id.btn_quanliNguoiDung);
        btnTaiKhoan = findViewById(R.id.btn_taiKhoan);


        // Show default fragment
        setOnChoose(btnQlSach, new QuanLiSach_Fragment());
        // Set up button click listeners
        btnQlSach.setOnClickListener(view -> {
            setOnChoose(btnQlSach, new QuanLiSach_Fragment());
        });
        btnQlNguoidung.setOnClickListener(view -> {
            setOnChoose(btnQlNguoidung, new QuanLiReaderFragment());
        });
        btnTaiKhoan.setOnClickListener(view -> {
            setOnChoose(btnTaiKhoan, new AdminTaiKhoan_Fragment());
        });
    }

    private void setOnChoose(LinearLayout button, Fragment fragment) {
        // Reset the currently selected button's state
        if (currentSelectedButton != null) {
            resetButtonState(currentSelectedButton);
        }

        // Highlight the selected button
        currentSelectedButton = button;
        changeButtonState(button, true);

        // Replace the fragment
        replaceFragment(fragment);
    }

    private void resetButtonState(LinearLayout button) {
        changeButtonState(button, false);
    }

    private void changeButtonState(LinearLayout button, boolean isSelected) {
        ImageView icon;
        TextView text;
        // Example logic for changing colors based on button ID
        int iconColor = isSelected ? R.color.yellow : R.color.white;
        int textColor = isSelected ? R.color.yellow : R.color.white;

        if (button.getId() == R.id.btn_quanliSach) {
            icon = button.findViewById(R.id.iv_qlsach); // Specific ID for icon
            text = button.findViewById(R.id.tv_qlsach); // Specific ID for text
        } else if (button.getId() == R.id.btn_quanliNguoiDung) {
            icon = button.findViewById(R.id.iv_quanlireader); // Specific ID for icon
            text = button.findViewById(R.id.tv_quanlireader); // Specific ID for text
        } else if (button.getId() == R.id.btn_taiKhoan) {
            icon = button.findViewById(R.id.iv_Taikhoan); // Specific ID for icon
            text = button.findViewById(R.id.tv_Taikhoan); // Specific ID for text
        }
        else{
            icon = button.findViewById(R.id.iv_qlsach);
            text = button.findViewById(R.id.tv_qlsach);
        }

        icon.setImageTintList(ColorStateList.valueOf(getResources().getColor(iconColor)));
        text.setTextColor(getResources().getColor(textColor));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
