package com.example.bookapp1.User;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.bookapp1.R;
import com.example.bookapp1.User.KhamPha.khamPha_Fragment;
import com.example.bookapp1.User.TaiKhoan.taiKhoan_notLogin_Fragment;
import com.example.bookapp1.User.TuTruyen.tuTruyen_Fragment;
import com.example.bookapp1.User.XepHang.xepHang_Fragment;

public class MainActivity extends AppCompatActivity {
    private LinearLayout btnTuTruyen, btnKhamPha, btnXepHang, btnTaiKhoan;
    private LinearLayout currentSelectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_thanhdieuhuong);

        btnTuTruyen = findViewById(R.id.btn_tutruyen);
        btnKhamPha = findViewById(R.id.btn_khampha);
        btnXepHang = findViewById(R.id.btn_xephang);
        btnTaiKhoan = findViewById(R.id.btn_taikhoan);


        // Show default fragment
        setOnChoose(btnTuTruyen, new tuTruyen_Fragment());

        // Set up button click listeners
        btnTuTruyen.setOnClickListener(view -> {
            setOnChoose(btnTuTruyen, new tuTruyen_Fragment());
        });
        btnKhamPha.setOnClickListener(view -> {
            setOnChoose(btnKhamPha, new khamPha_Fragment());
        });
        btnXepHang.setOnClickListener(view -> {
            setOnChoose(btnXepHang, new xepHang_Fragment());
        });
        btnTaiKhoan.setOnClickListener(view -> {
            setOnChoose(btnTaiKhoan, new taiKhoan_notLogin_Fragment());
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

        if (button.getId() == R.id.btn_tutruyen) {
            icon = button.findViewById(R.id.icon_tutruyen); // Specific ID for icon
            text = button.findViewById(R.id.text_tutruyen); // Specific ID for text
        } else if (button.getId() == R.id.btn_khampha) {
            icon = button.findViewById(R.id.icon_khampha); // Specific ID for icon
            text = button.findViewById(R.id.text_khampha); // Specific ID for text
        } else if (button.getId() == R.id.btn_xephang) {
            icon = button.findViewById(R.id.icon_xephang); // Specific ID for icon
            text = button.findViewById(R.id.text_xephang); // Specific ID for text
        } else if (button.getId() == R.id.btn_taikhoan) {
            icon = button.findViewById(R.id.icon_taikhoan); // Specific ID for icon
            text = button.findViewById(R.id.text_taikhoan); // Specific ID for text
        }else{
            icon = button.findViewById(R.id.icon_tutruyen);
            text = button.findViewById(R.id.text_tutruyen);
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
