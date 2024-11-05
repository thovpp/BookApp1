package com.example.bookapp1.User.KhamPha;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.R;

import java.util.ArrayList;
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
        rv_Banner = view.findViewById(R.id.rv_main_banner);
        rv_Moidang = view.findViewById(R.id.rv_main_moidang);
        rv_Capnhat = view.findViewById(R.id.rv_main_capnhat);

        LinearLayoutManager layoutManagerBanner = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerMoidang = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerCapnhat = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        rv_Banner.setLayoutManager(layoutManagerBanner);
        rv_Moidang.setLayoutManager(layoutManagerMoidang);
        rv_Capnhat.setLayoutManager(layoutManagerCapnhat);

        // Sample data for RecyclerView_Banner
        itemListBn = new ArrayList<>();
        itemListBn.add(new RvItem_Banner(R.drawable.vitasprakle));
        itemListBn.add(new RvItem_Banner(R.drawable.bright_anime_manga_eyes_with_stars));

        // Sample data for RecyclerView_Capnhat
        itemListCapnhat = new ArrayList<>();
        itemListCapnhat.add(new RvItem_Capnhat("Tất cả tại hoàng hậu làm đó!", "Hài hước| Xuyên không", "251", R.drawable.hoang_hau_vo_duc));
        itemListCapnhat.add(new RvItem_Capnhat("Truyện Kiếm Hiệp", "Huyền ảo", "150", R.drawable.kiemhiep));
        itemListCapnhat.add(new RvItem_Capnhat("Hành Trình Vô Tận", "Phiêu lưu", "347", R.drawable.bulubala));
        itemListCapnhat.add(new RvItem_Capnhat("Vũ Trụ Bất Tận", "Khám phá", "18", R.drawable.images));
        itemListCapnhat.add(new RvItem_Capnhat("Phu nhân và Hầu Tước", "Đời thường", "500", R.drawable.hautuoc));
        itemListCapnhat.add(new RvItem_Capnhat("Dưới ánh trăng", "BL| Cổ trang", "745", R.drawable.ehehe));
        itemListCapnhat.add(new RvItem_Capnhat("Học sinh thứ 10", "Huyền ảo| Action", "321", R.drawable.rere));

        // Sample data for RecyclerView_Moidang
        itemListMoidang = new ArrayList<>();
        itemListMoidang.add(new RvItem_Moidang(R.drawable.bulubala));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.hoang_hau_vo_duc));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.kiemhiep));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.rere));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.nhinnguoidangsau));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.nutongtai));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.ehehe));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.hautuoc));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.images));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.emon));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.rereer));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.thinhan));
        itemListMoidang.add(new RvItem_Moidang(R.drawable.tinhcum));

        // Set up adapter
        adapterBanner = new bmAdapter(itemListBn);
        adapterMoidang = new bmAdapter(itemListMoidang);
        adapterCapnhat = new bmAdapter(itemListCapnhat);
        rv_Banner.setAdapter(adapterBanner);
        rv_Moidang.setAdapter(adapterMoidang);
        rv_Capnhat.setAdapter(adapterCapnhat);

        // Start auto-scrolling for both RecyclerViews
        startAutoScroll();
        return view; // Fixed missing semicolon
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
