package com.example.bookapp1.Novel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_Comment;
import com.example.bookapp1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class binhLuan_Fragment extends Fragment {
    private Button btnMoi, btnCu;
    private RecyclerView rvRating;
    private MyAdapter ratingAdapter;
    private List<BaseItem> commentItemList;
    private TextView numberOfComment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_binhluan, container, false);

        // Gán nút
        btnMoi = view.findViewById(R.id.btn_moi);
        btnCu = view.findViewById(R.id.btn_cu);
        numberOfComment = view.findViewById(R.id.tv_numberOfComment);
        // Gán RecyclerView
        rvRating = view.findViewById(R.id.rv_binhluan);
        rvRating.setLayoutManager(new LinearLayoutManager(getContext()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        // Khởi tạo danh sách item cho RecyclerView
        commentItemList = new ArrayList<>();
        try {
            // Tạo danh sách 20 bình luận mẫu với tên người dùng
            commentItemList.add(new RvItem_Comment("Lục Tiểu Phụng", "Một câu chuyện tuyệt vời về hành trình phiêu lưu.", "Chương 1", R.drawable.background_novel_1, sdf.parse("01/01/2024")));
            commentItemList.add(new RvItem_Comment("Nguyễn Tàu", "Tôi rất thích cách mà nhân vật phát triển.", "Chương 2", R.drawable.background_novel_2, sdf.parse("15/01/2024")));
            commentItemList.add(new RvItem_Comment("Tôn Ngộ Không", "Câu chuyện này thật sự hấp dẫn!", "Chương 3", R.drawable.ic_launcher_background, sdf.parse("20/01/2024")));
            commentItemList.add(new RvItem_Comment("Triệu Phú", "Một cái kết đầy bất ngờ.", "Chương 4", R.drawable.background_novel_1, sdf.parse("25/01/2024")));
            commentItemList.add(new RvItem_Comment("Vương Thiên", "Cốt truyện rất lôi cuốn.", "Chương 5", R.drawable.background_novel_2, sdf.parse("01/02/2024")));
            commentItemList.add(new RvItem_Comment("Đinh Nho", "Tôi không thể ngừng đọc!", "Chương 6", R.drawable.ic_launcher_background, sdf.parse("05/02/2024")));
            commentItemList.add(new RvItem_Comment("Trần Quốc", "Cảm ơn tác giả vì một tác phẩm tuyệt vời!", "Chương 7", R.drawable.background_novel_1, sdf.parse("10/02/2024")));
            commentItemList.add(new RvItem_Comment("Phạm Cảnh", "Nhân vật chính rất thú vị.", "Chương 8", R.drawable.background_novel_2, sdf.parse("15/02/2024")));
            commentItemList.add(new RvItem_Comment("Lê Thành", "Bìa sách thật đẹp!", "Chương 9", R.drawable.ic_launcher_background, sdf.parse("20/02/2024")));
            commentItemList.add(new RvItem_Comment("Ngô Minh", "Mong chờ những chương tiếp theo.", "Chương 10", R.drawable.background_novel_1, sdf.parse("25/02/2024")));
            commentItemList.add(new RvItem_Comment("Đào Tĩnh", "Nội dung rất phong phú.", "Chương 11", R.drawable.background_novel_2, sdf.parse("01/03/2024")));
            commentItemList.add(new RvItem_Comment("Phan Thiên", "Chương này thật cảm động.", "Chương 12", R.drawable.ic_launcher_background, sdf.parse("05/03/2024")));
            commentItemList.add(new RvItem_Comment("Bạch Nhạn", "Cuộc chiến thật khốc liệt!", "Chương 13", R.drawable.background_novel_1, sdf.parse("10/03/2024")));
            commentItemList.add(new RvItem_Comment("Nguyễn Văn", "Tôi đã khóc khi đọc chương này.", "Chương 14", R.drawable.background_novel_2, sdf.parse("15/03/2024")));
            commentItemList.add(new RvItem_Comment("Hồ Quang", "Tác giả thật sáng tạo!", "Chương 15", R.drawable.ic_launcher_background, sdf.parse("20/03/2024")));
            commentItemList.add(new RvItem_Comment("Trần Đức", "Một câu chuyện không thể bỏ lỡ.", "Chương 16", R.drawable.background_novel_1, sdf.parse("25/03/2024")));
            commentItemList.add(new RvItem_Comment("Lê Minh", "Tôi rất thích nhân vật phụ.", "Chương 17", R.drawable.background_novel_2, sdf.parse("30/03/2024")));
            commentItemList.add(new RvItem_Comment("Nguyễn Thái", "Cảm ơn vì những giây phút tuyệt vời.", "Chương 18", R.drawable.ic_launcher_background, sdf.parse("05/04/2024")));
            commentItemList.add(new RvItem_Comment("Dương Huy", "Chương này khiến tôi cảm thấy hồi hộp.", "Chương 19", R.drawable.background_novel_1, sdf.parse("10/04/2024")));
            commentItemList.add(new RvItem_Comment("Trương Tử", "Mong chờ chương tiếp theo!", "Chương 20", R.drawable.background_novel_2, sdf.parse("15/04/2024")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Thiết lập adapter cho RecyclerView
        ratingAdapter = new MyAdapter(commentItemList);
        rvRating.setAdapter(ratingAdapter);

        // Logic cho nút
        btnMoi.setOnClickListener(v -> {
            resetBackgroundButton();
            btnMoi.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray_light));
            sortByRecent();
        });

        btnCu.setOnClickListener(v -> {
            resetBackgroundButton();
            btnCu.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray_light));
            sortByOldest();
        });
        sortByRecent();

        numberOfComment.setText("("+commentItemList.size()+")");
        return view;
    }

    private void resetBackgroundButton() {
        btnMoi.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray_deep));
        btnCu.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray_deep));
    }

    private void sortByRecent() {
        commentItemList.sort((item1, item2) -> {
            Date date1 = ((RvItem_Comment) item1).getTime();
            Date date2 = ((RvItem_Comment) item2).getTime();
            return date2.compareTo(date1); // Sắp xếp giảm dần
        });
        ratingAdapter.notifyDataSetChanged();
    }

    private void sortByOldest() {
        commentItemList.sort((item1, item2) -> {
            Date date1 = ((RvItem_Comment) item1).getTime();
            Date date2 = ((RvItem_Comment) item2).getTime();
            return date1.compareTo(date2); // Sắp xếp tăng dần
        });
        ratingAdapter.notifyDataSetChanged();
    }


}