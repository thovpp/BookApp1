package com.example.bookapp1.Novel_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.bookapp1.R;

public class chapterSettingThongtin_Fragment  extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_chapter_setting_thongtin, container, false);
        return view;
    }
}