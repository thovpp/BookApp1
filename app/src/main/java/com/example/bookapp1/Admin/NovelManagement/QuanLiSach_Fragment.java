package com.example.bookapp1.Admin.NovelManagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_QuanliSach;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Novel;
import com.example.bookapp1.R;
import java.util.ArrayList;
import java.util.List;

public class QuanLiSach_Fragment extends Fragment {
    private RecyclerView rV_quanlisach;
    private QuanLiSach_Adapter adapterDanhSachTruyen;
    private List<BaseItem> itemListTruyen;
    private List<BaseItem> originalItemListTruyen; // Keep an unfiltered list
    private EditText searchEditText;
    private Button btn_addNovel;
    private static final int REQUEST_CODE_ADD_NOVEL = 1;

    public static QuanLiSach_Fragment newInstance(String content) {
        QuanLiSach_Fragment fragment = new QuanLiSach_Fragment();
        Bundle args = new Bundle();
        args.putString("novel_content", content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_li_sach, container, false);

        // Initialize RecyclerView and search EditText
        rV_quanlisach = view.findViewById(R.id.rv_quanlisach);
        rV_quanlisach.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        searchEditText = view.findViewById(R.id.searchEditText);

        // Load novel list and set up search
        loadNovelList();
        setupSearch();

        // Add Novel button functionality
        btn_addNovel = view.findViewById(R.id.btn_themsach);
        btn_addNovel.setOnClickListener(v -> {
            Intent intent = new Intent(requireActivity(), Novel_Add_Edit_Activity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_NOVEL);
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh the novel list every time the fragment becomes visible
        refreshNovelList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NOVEL && resultCode == Activity.RESULT_OK) {
            refreshNovelList();
        }
    }

    private void loadNovelList() {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(getContext());
        List<Novel> novels = dbHelper.getAllNovels();

        // Convert Novel objects to RvItem_QuanliSach items
        itemListTruyen = new ArrayList<>();
        originalItemListTruyen = new ArrayList<>(); // Store a copy of all items for filtering
        for (Novel novel : novels) {
            RvItem_QuanliSach item = new RvItem_QuanliSach(
                    novel.getId(),
                    novel.getTitle(),
                    novel.getCategory(),
                    novel.getThumbnailUri(),
                    novel.getTotalChapters()
            );
            itemListTruyen.add(item);
            originalItemListTruyen.add(item); // Add to unfiltered list
        }

        adapterDanhSachTruyen = new QuanLiSach_Adapter(itemListTruyen);
        rV_quanlisach.setAdapter(adapterDanhSachTruyen);
    }

    private void setupSearch() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString()); // Filter list as text changes
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filter(String query) {
        itemListTruyen.clear();
        if (query.isEmpty()) {
            itemListTruyen.addAll(originalItemListTruyen); // Show all items if query is empty
        } else {
            for (BaseItem item : originalItemListTruyen) {
                if (item instanceof RvItem_QuanliSach) {
                    RvItem_QuanliSach novelItem = (RvItem_QuanliSach) item;
                    if (novelItem.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        itemListTruyen.add(novelItem);
                    }
                }
            }
        }
        adapterDanhSachTruyen.notifyDataSetChanged(); // Refresh the adapter with filtered list
    }

    private void refreshNovelList() {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(getContext());
        List<Novel> novels = dbHelper.getAllNovels();

        itemListTruyen.clear();
        originalItemListTruyen.clear(); // Clear the backup list as well
        for (Novel novel : novels) {
            RvItem_QuanliSach item = new RvItem_QuanliSach(
                    novel.getId(),
                    novel.getTitle(),
                    novel.getCategory(),
                    novel.getThumbnailUri(),
                    novel.getTotalChapters()
            );
            itemListTruyen.add(item);
            originalItemListTruyen.add(item); // Re-add to the backup list
        }

        adapterDanhSachTruyen.notifyDataSetChanged();
    }
}
