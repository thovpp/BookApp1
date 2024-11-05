package com.example.bookapp1.Novel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.CategoryButtonData;
import com.example.bookapp1.DTOs.RvItem_Novel_3Detail;
import com.example.bookapp1.DTOs.RvItem_Novel_5Detail;
import com.example.bookapp1.R;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

public class gioithieu_Fragment extends Fragment {
    private RecyclerView rV_cungtacgia,rV_cungtheloai;
    private MyAdapter adapterCungtacgia,adapterCungtheloai;
    private List<BaseItem> itemListCungtheloai, itemListCungtacgia;
    private List<CategoryButtonData> buttonDataList;

    // Static method to create new instance with content
    public static gioithieu_Fragment newInstance(String content) {
        gioithieu_Fragment fragment = new gioithieu_Fragment();
        Bundle args = new Bundle();
        args.putString("novel_content", content); // Pass the content
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gioithieu, container, false);

        // Initialize RecyclerView
        rV_cungtacgia = view.findViewById(R.id.rv_cungTacGia);
        rV_cungtheloai = view.findViewById(R.id.rv_cungTheLoai);
        FlexboxLayout flexboxLayout = view.findViewById(R.id.flexbox_category);
        Button templateButton = view.findViewById(R.id.btn_category);
        TextView contentTextView =  view.findViewById(R.id.content_novel);



        LinearLayoutManager layoutManagerCungtheLoai = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerCungtacgia = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rV_cungtheloai.setLayoutManager(layoutManagerCungtheLoai);
        rV_cungtacgia.setLayoutManager(layoutManagerCungtacgia);

        // Sample data for RecyclerView
        itemListCungtheloai = new ArrayList<>();
        itemListCungtheloai.add(new RvItem_Novel_3Detail("Tại Đại Kết Cục", "Tu tiên", R.drawable.background_novel_1));
        itemListCungtheloai.add(new RvItem_Novel_3Detail("Truyện Kiếm Hiệp", "Huyền ảo", R.drawable.background_novel_2));
        itemListCungtheloai.add(new RvItem_Novel_3Detail("Hành Trình Vô Tận", "Phiêu lưu", R.drawable.background_novel_1));
        itemListCungtheloai.add(new RvItem_Novel_3Detail("Vũ Trụ Bất Tận", "Khám phá", R.drawable.background_novel_2));

        itemListCungtacgia = new ArrayList<>();
        itemListCungtacgia.add(new RvItem_Novel_5Detail("Title 1", "Description 1", 5.0f, "Fiction", R.drawable.background_novel_1));
        itemListCungtacgia.add(new RvItem_Novel_5Detail("Title 2", "Description 2", 4.0f, "Fantasy", R.drawable.background_novel_2));
        itemListCungtacgia.add(new RvItem_Novel_5Detail("Title 3", "Description 3", 3.0f, "Adventure", R.drawable.background_novel_2));
        itemListCungtacgia.add(new RvItem_Novel_5Detail("Title 4", "Description 4", 2.2f, "Mystery", R.drawable.ic_launcher_background));
        itemListCungtacgia.add(new RvItem_Novel_5Detail("Title 5", "Description 5", 1.7f, "Romance", R.drawable.background_novel_1));

        if (getArguments() != null) {
            String content = getArguments().getString("novel_content");
            contentTextView.setText(content); // Set the content to TextView
        }

        // Set up adapter
        adapterCungtacgia = new MyAdapter(itemListCungtacgia);
        adapterCungtheloai = new MyAdapter(itemListCungtheloai);
        rV_cungtacgia.setAdapter(adapterCungtacgia);
        rV_cungtheloai.setAdapter(adapterCungtheloai);

        createButtons(flexboxLayout, templateButton);
        return view;
    }


    // Method to create and add buttons to the layout
    private void createButtons(FlexboxLayout flexboxLayout, Button templateButton) {

        buttonDataList = new ArrayList<>();
        buttonDataList.add(new CategoryButtonData(1, "Tu tien"));
        buttonDataList.add(new CategoryButtonData(2, "Tinh cam"));
        buttonDataList.add(new CategoryButtonData(3, "Hanh dong"));
        buttonDataList.add(new CategoryButtonData(4, "Isekai"));
        buttonDataList.add(new CategoryButtonData(5, "Doi song thuong nhat"));

        for (CategoryButtonData data : buttonDataList) {
            Button button = new Button(getContext());
            button.setLayoutParams(templateButton.getLayoutParams());
            button.setBackground(templateButton.getBackground());
            button.setPadding(10, 0, 10, 0);
            button.setTextColor(templateButton. getTextColors());
            button.setText(data.getText());
            button.setId(data.getId());

            button.setOnClickListener(v -> {
                String buttonText = ((Button) v).getText().toString();
                int buttonId = v.getId();
                Toast.makeText(getContext(), "Clicked: " + buttonText + " (ID: " + buttonId + ")", Toast.LENGTH_SHORT).show();
            });
            flexboxLayout.addView(button);
        }
    }
}
