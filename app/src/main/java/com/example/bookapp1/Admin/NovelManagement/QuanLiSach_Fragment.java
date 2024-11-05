package com.example.bookapp1.Admin.NovelManagement;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_QuanliSach;
import com.example.bookapp1.R;

import java.util.ArrayList;
import java.util.List;

public class QuanLiSach_Fragment extends Fragment {
    private RecyclerView rV_quanlisach;
    private QuanLiSach_Adapter adapterDanhSachTruyen;
    private List<BaseItem> itemListTruyen;
    Button btn_addNovel;
    private static final int REQUEST_CODE_ADD_NOVEL = 1; // Request code for adding novels
    private static final int REQUEST_CODE_EDIT_NOVEL = 2; // Request code for editing novels
    // Static method to create new instance with content
    public static QuanLiSach_Fragment newInstance(String content) {
        QuanLiSach_Fragment fragment = new QuanLiSach_Fragment();
        Bundle args = new Bundle();
        args.putString("novel_content", content); // Pass the content
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_li_sach, container, false);

        // Initialize RecyclerView
        rV_quanlisach = view.findViewById(R.id.rv_quanlisach);
        LinearLayoutManager layoutManagerQuanLiSach = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rV_quanlisach.setLayoutManager(layoutManagerQuanLiSach);

        // Sample data for RecyclerView
        itemListTruyen = new ArrayList<>();
        itemListTruyen.add(new RvItem_QuanliSach("Tại Đại Kết Cục", "Tu tiên", R.drawable.background_novel_1, 200));
        itemListTruyen.add(new RvItem_QuanliSach("1000 Con thỏ", "Kinh Dị", R.drawable.background_novel_2, 100));
        itemListTruyen.add(new RvItem_QuanliSach("Vua Chúa chỉ có thế", "Hài hước", R.drawable.background_novel_1, 20));
        itemListTruyen.add(new RvItem_QuanliSach("Hololive tập 1", "Ảo", R.drawable.ic_launcher_background, 1));
        // Set up adapter
        adapterDanhSachTruyen = new QuanLiSach_Adapter(itemListTruyen);
        rV_quanlisach.setAdapter(adapterDanhSachTruyen);


        btn_addNovel = view.findViewById(R.id.btn_themsach);
        btn_addNovel.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), Novel_Add_Edit_Activity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_NOVEL); // Start activity for result
        });
        return view;
    }
}