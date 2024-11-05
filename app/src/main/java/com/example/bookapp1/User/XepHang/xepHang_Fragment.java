
package com.example.bookapp1.User.XepHang;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.Nullable; // Import for Nullable

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_History;
import com.example.bookapp1.User.KhamPha.filter_Fragment;
import com.example.bookapp1.Novel.NovelActivity;
import com.example.bookapp1.Novel_Add_Edit_Activity;
import com.example.bookapp1.R;
import com.example.bookapp1.User.MainAdapter;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Novel;

import java.util.ArrayList;
import java.util.List;

public class xepHang_Fragment extends Fragment {
    private RecyclerView rV_danhSachTruyen;
    private MainAdapter adapterXephang;
    private List<BaseItem> itemListDanhSachLoc;
    private DatabaseHelper dbHelper;
    ImageButton btnSetting;
    private static final int REQUEST_CODE_ADD_NOVEL = 1; // Request code for adding novels
    private static final int REQUEST_CODE_EDIT_NOVEL = 2; // Request code for editing novels
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xephang, container, false);

        // Initialize RecyclerView and DatabaseHelper
        rV_danhSachTruyen = view.findViewById(R.id.rv_history);
        dbHelper = DatabaseHelper.getInstance(getContext());

        // Initialize itemListLichsu
        itemListDanhSachLoc = new ArrayList<>();

        // Set up RecyclerView
        LinearLayoutManager layoutManagerLichsu = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rV_danhSachTruyen.setLayoutManager(layoutManagerLichsu);
        adapterXephang = new MainAdapter(itemListDanhSachLoc, new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseItem item) {
                if (item instanceof RvItem_History) {
                    RvItem_History chapter = (RvItem_History) item;
                    Intent intent = new Intent(getActivity(), NovelActivity.class);
                    intent.putExtra("NOVEL_ID", chapter.getID());
                    startActivity(intent);
                }
            }
        }, this::editNovel, this::deleteNovel); // Pass the editNovel and deleteNovel methods as listeners
        rV_danhSachTruyen.setAdapter(adapterXephang);

        // Load existing novels
        loadExistingNovels();


        btnSetting = view.findViewById(R.id.btn_filter);
        btnSetting.setOnClickListener(v -> showSettingsDialog());


        // Add button for adding new novels
        return view;
    }



    private void loadExistingNovels() {
        List<Novel> novelList = dbHelper.getAllNovels();
        Log.d("tuTruyen_Fragment", "Number of novels fetched: " + novelList.size());

        itemListDanhSachLoc.clear(); // Clear the existing list first
        for (Novel novel : novelList) {
            itemListDanhSachLoc.add(new RvItem_History(novel.getId(), novel.getTitle(), novel.getAuthor(), 0, novel.getTotalChapters(), novel.getCategory(), novel.getThumbnailUri()));
        }

        // Notify the adapter of the data change
        adapterXephang.notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_NOVEL && resultCode == RESULT_OK) {
            refreshNovelList(); // Refresh the list to show newly added novel
        }
    }

    private void refreshNovelList() {
        // Clear the existing list
        itemListDanhSachLoc.clear();

        // Fetch all novels from the database
        List<Novel> novelList = dbHelper.getAllNovels();

        // Prepare data for the RecyclerView
        for (Novel novel : novelList) {
            itemListDanhSachLoc.add(new RvItem_History(novel.getId(), novel.getTitle(), novel.getAuthor(), 0, novel.getTotalChapters(), novel.getCategory(), novel.getThumbnailUri()));
        }

        // Notify the adapter of the data change
        adapterXephang.notifyDataSetChanged();
    }

    private void deleteNovel(int id) {
        boolean isDeleted = dbHelper.deleteNovel(id); // Call your database helper method
        if (isDeleted) {
            Toast.makeText(getContext(), "Novel deleted successfully", Toast.LENGTH_SHORT).show();
            refreshNovelList(); // Refresh the list to reflect changes
        } else {
            Toast.makeText(getContext(), "Failed to delete novel", Toast.LENGTH_SHORT).show();
        }
    }

    private void editNovel(int id) {
        Intent intent = new Intent(requireActivity(), Novel_Add_Edit_Activity.class);
        intent.putExtra("NOVEL_ID", id); // Pass the ID of the novel to edit
        startActivityForResult(intent, REQUEST_CODE_EDIT_NOVEL); // Start activity for result
    }

    private void showSettingsDialog() {
        filter_Fragment dialogFragment = new filter_Fragment();
        dialogFragment.show(getParentFragmentManager(), "chapterSetting_Fragment");

    }
}

