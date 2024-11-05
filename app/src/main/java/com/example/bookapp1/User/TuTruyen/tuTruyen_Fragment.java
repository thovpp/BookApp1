package com.example.bookapp1.User.TuTruyen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_History;
import com.example.bookapp1.Novel.NovelActivity;
import com.example.bookapp1.Admin.NovelManagement.Novel_Add_Edit_Activity;
import com.example.bookapp1.R;
import com.example.bookapp1.User.MainAdapter;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Novel;

import java.util.ArrayList;
import java.util.List;

public class tuTruyen_Fragment extends Fragment {
    private RecyclerView rV_lichsu;
    private MainAdapter adapterLichsu;
    private List<BaseItem> itemListLichsu;
    private List<BaseItem> originalItemListLichsu; // Unfiltered backup of novels
    private DatabaseHelper dbHelper;
    private EditText searchEditText;
    private Button btn_addNovel;

    private static final int REQUEST_CODE_ADD_NOVEL = 1;
    private static final int REQUEST_CODE_EDIT_NOVEL = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tutruyen, container, false);

        // Initialize RecyclerView and DatabaseHelper
        rV_lichsu = view.findViewById(R.id.rv_history);
        searchEditText = view.findViewById(R.id.searchEditText); // Search input
        dbHelper = DatabaseHelper.getInstance(getContext());

        // Set up RecyclerView
        rV_lichsu.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        itemListLichsu = new ArrayList<>();
        originalItemListLichsu = new ArrayList<>(); // Backup list for filtering

        // Adapter setup
        adapterLichsu = new MainAdapter(itemListLichsu, item -> {
            if (item instanceof RvItem_History) {
                RvItem_History chapter = (RvItem_History) item;
                Intent intent = new Intent(getActivity(), NovelActivity.class);
                intent.putExtra("NOVEL_ID", chapter.getID());
                startActivity(intent);
            }
        }, this::editNovel, this::deleteNovel);

        rV_lichsu.setAdapter(adapterLichsu);

        // Load novels and set up search functionality
        loadExistingNovels();
        setupSearchFunctionality();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NOVEL && resultCode == Activity.RESULT_OK) {
            refreshNovelList();
        }
    }

    private void setupSearchFunctionality() {
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterNovels(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void loadExistingNovels() {
        List<Novel> novelList = dbHelper.getAllNovels();
        Log.d("tuTruyen_Fragment", "Number of novels fetched: " + novelList.size());

        itemListLichsu.clear();
        originalItemListLichsu.clear();
        for (Novel novel : novelList) {
            RvItem_History item = new RvItem_History(novel.getId(), novel.getTitle(), novel.getAuthor(), 0, novel.getTotalChapters(), novel.getCategory(), novel.getThumbnailUri());
            itemListLichsu.add(item);
            originalItemListLichsu.add(item); // Add to unfiltered list
        }
        adapterLichsu.notifyDataSetChanged();
    }

    private void refreshNovelList() {
        itemListLichsu.clear();
        originalItemListLichsu.clear();
        List<Novel> novelList = dbHelper.getAllNovels();

        for (Novel novel : novelList) {
            RvItem_History item = new RvItem_History(novel.getId(), novel.getTitle(), novel.getAuthor(), 0, novel.getTotalChapters(), novel.getCategory(), novel.getThumbnailUri());
            itemListLichsu.add(item);
            originalItemListLichsu.add(item);
        }

        adapterLichsu.notifyDataSetChanged();
    }

    private void filterNovels(String query) {
        itemListLichsu.clear();
        if (query.isEmpty()) {
            itemListLichsu.addAll(originalItemListLichsu); // Show all items if query is empty
        } else {
            for (BaseItem item : originalItemListLichsu) {
                if (item instanceof RvItem_History) {
                    RvItem_History novelItem = (RvItem_History) item;
                    if (novelItem.getTitle().toLowerCase().contains(query.toLowerCase())) {
                        itemListLichsu.add(novelItem);
                    }
                }
            }
        }
        adapterLichsu.notifyDataSetChanged();
    }

    private void deleteNovel(int id) {
        if (dbHelper.deleteNovel(id)) {
            Toast.makeText(getContext(), "Novel deleted successfully", Toast.LENGTH_SHORT).show();
            refreshNovelList();
        } else {
            Toast.makeText(getContext(), "Failed to delete novel", Toast.LENGTH_SHORT).show();
        }
    }

    private void editNovel(int id) {
        Intent intent = new Intent(requireActivity(), Novel_Add_Edit_Activity.class);
        intent.putExtra("NOVEL_ID", id);
        startActivityForResult(intent, REQUEST_CODE_EDIT_NOVEL);
    }
}
