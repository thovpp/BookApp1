package com.example.bookapp1.KhamPha_Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.bookapp1.Novel_Fragment.chapterSettingCaidat_Fragment;
import com.example.bookapp1.Novel_Fragment.chapterSettingThongtin_Fragment;
import com.example.bookapp1.R;

public class filter_Fragment extends DialogFragment {
    private Button btnThongtin;
    private Button btnCaidat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_xephang_filter, container, false);
/*

        btnThongtin = rootView.findViewById(R.id.btn_thongtin);
        btnCaidat = rootView.findViewById(R.id.btn_caidat);

        btnThongtin.setOnClickListener(v -> {
            updateButtonColors(btnThongtin, btnCaidat);
            replaceFragment(new chapterSettingThongtin_Fragment());
        });

        btnCaidat.setOnClickListener(v -> {
            updateButtonColors(btnCaidat, btnThongtin);
            replaceFragment(new chapterSettingCaidat_Fragment());
        });

        // Initialize with the first fragment
        updateButtonColors(btnThongtin, btnCaidat);
        replaceFragment(new chapterSettingThongtin_Fragment());
*/

        return view;
    }

   /* @Override
    public void onStart() {
        super.onStart();
        // Set the dialog height to wrap content
        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            layoutParams.gravity = Gravity.BOTTOM;
            getDialog().getWindow().setAttributes(layoutParams);
        }
    }

    private void replaceFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.layout_caidat, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void updateButtonColors(Button selectedButton, Button otherButton) {
        // Change color for the selected button
        selectedButton.setTextColor(getResources().getColor(R. color. yellow));

        // Reset color for the other button
        otherButton.setTextColor(getResources().getColor(R. color. white));
    }*/
}
