package com.example.bookapp1.User.KhamPha;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Novel;
import com.example.bookapp1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class khamPha_Fragment extends Fragment {

    private RecyclerView rv_Capnhat, rv_Moidang, rv_Banner;
    private bmAdapter adapterCapnhat, adapterMoidang, adapterBanner;
    private List<BaseItem> itemListCapnhat, itemListMoidang, itemListBn;

    private Handler handler = new Handler();
    private int bannerPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_khampha, container, false);

        // Initialize RecyclerView
        rv_Capnhat = view.findViewById(R.id.rv_main_capnhat);
        rv_Capnhat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_Moidang = view.findViewById(R.id.rv_main_moidang);
        rv_Banner = view.findViewById(R.id.rv_main_banner);

        if (rv_Capnhat == null || rv_Moidang == null || rv_Banner == null) {
            Log.e("khamPha_Fragment", "RecyclerView initialization failed. Check your XML layout IDs.");
            return view;
        }

        // Set LayoutManagers
        rv_Capnhat.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rv_Moidang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rv_Banner.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        // Load and display random novels
        loadLatestNovels();
        loadRandomNovels();
        loadBannerItems();


        if (adapterBanner != null) {
            startAutoScroll();
        } else {
            Log.e("khamPha_Fragment", "adapterBanner is null. Ensure banner items are loaded correctly.");
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Call displayRandomNovel after the view is fully created
        displayRandomNovel();
    }

    private void loadBannerItems() {
        // Create some dummy banner items or fetch them as needed
        itemListBn = new ArrayList<>();
        itemListBn.add(new RvItem_Banner(R.drawable.bright_anime_manga_eyes_with_stars));
        itemListBn.add(new RvItem_Banner(R.drawable.background_novel_1));
        itemListBn.add(new RvItem_Banner(R.drawable.emon));

        // Initialize adapterBanner with itemListBn
        adapterBanner = new bmAdapter(itemListBn);
        rv_Banner.setAdapter(adapterBanner);
    }

    private void displayRandomNovel() {
        Novel randomNovel = DatabaseHelper.getInstance(getContext()).getRandomNovel();

        if (randomNovel != null) {
            // Find views
            TextView txtTheLoai = getView().findViewById(R.id.txt_main_rec_theloai);
            TextView txtTieuDe = getView().findViewById(R.id.txt_main_rec_tieude);
            ImageView ivMainRec = getView().findViewById(R.id.iv_main_rec);

            // Set text details
            txtTheLoai.setText(randomNovel.getCategory()); // Set category as genre
            txtTieuDe.setText(randomNovel.getTitle());     // Set title

            // Load image with Glide
            Glide.with(this)
                    .load(randomNovel.getThumbnailUri()) // Assuming thumbnail is a file path
                    .placeholder(R.drawable.background_novel_1) // Placeholder image
                    .error(R.drawable._logo) // Error image if loading fails
                    .into(ivMainRec);
        }
    }

    private void loadLatestNovels() {
        // Fetch the latest novels (e.g., top 5 latest novels)
        List<Novel> latestNovels = DatabaseHelper.getInstance(getContext()).loadLatestNovels(5);

        // Convert to BaseItem list for the adapter
        itemListMoidang = new ArrayList<>();
        for (Novel novel : latestNovels) {
            itemListMoidang.add(new RvItem_Moidang(
                    novel.getThumbnailUri() // Pass the thumbnail file path directly
            ));
        }

        // Set up adapter
        adapterMoidang = new bmAdapter(itemListMoidang);
        rv_Moidang.setAdapter(adapterMoidang);
    }

    private void loadRandomNovels() {
        // Fetch all novels
        List<Novel> allNovels = DatabaseHelper.getInstance(getContext()).getAllNovels();

        // Shuffle the list for random order
        Collections.shuffle(allNovels);

        // Take a subset if you only want to display a few (e.g., top 5)
        List<Novel> randomNovels = allNovels.size() > 5 ? allNovels.subList(0, 5) : allNovels;

        // Convert to BaseItem list for the adapter
        itemListCapnhat = new ArrayList<>();
        for (Novel novel : randomNovels) {
            itemListCapnhat.add(new RvItem_Capnhat(
                    novel.getTitle(),
                    novel.getCategory(),
                    String.valueOf(novel.getTotalChapters()),
                    novel.getThumbnailUri() // Pass the thumbnail path directly
            ));
        }

        // Set up adapter
        adapterCapnhat = new bmAdapter(itemListCapnhat);
        rv_Capnhat.setAdapter(adapterCapnhat);
    }

    private void startAutoScroll() {
        handler.postDelayed(autoScrollRunnable, 50); // 50 milliseconds delay
    }

    private Runnable autoScrollRunnable = new Runnable() {
        @Override
        public void run() {
            // Scroll rv_Banner
            if (bannerPosition < adapterBanner.getItemCount()) {
                rv_Banner.smoothScrollToPosition(bannerPosition++);
            } else {
                bannerPosition = 0; // Reset position if reached the end
            }

            // Repeat scrolling every 5 seconds
            handler.postDelayed(this, 5000);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(autoScrollRunnable); // Stop scrolling when fragment's view is destroyed
    }
}
