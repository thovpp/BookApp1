package com.example.bookapp1.Novel_Fragment;

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

import com.example.bookapp1.Dtos.BaseItem;
import com.example.bookapp1.Dtos.RvItem_Rating;
import com.example.bookapp1.MyAdapter;
import com.example.bookapp1.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class danhGia_Fragment extends Fragment {
    private Button btnMoi, btnCu, btnThich;
    private RecyclerView rvRating;
    private MyAdapter ratingAdapter;
    private List<BaseItem> ratingItemList;
    private TextView numberOfRating;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhgia, container, false);

        // Gán nút
        btnMoi = view.findViewById(R.id.btn_moi);
        btnCu = view.findViewById(R.id.btn_cu);
        btnThich = view.findViewById(R.id.btn_thich);
        numberOfRating = view.findViewById(R.id.tv_numberOfRating);
        // Gán RecyclerView
        rvRating = view.findViewById(R.id.rv_danhgia);
        rvRating.setLayoutManager(new LinearLayoutManager(getContext()));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        // Khởi tạo danh sách item cho RecyclerView
        ratingItemList = new ArrayList<>();
        try {
            // Tạo danh sách 20 item đánh giá với tên người dùng là nhân vật trong truyện
            ratingItemList.add(new RvItem_Rating("Lục Tiểu Phụng", "Hành trình phiêu lưu đầy kịch tính", R.drawable.background_novel_1, sdf.parse("01/01/2024"), 4.5f));
            ratingItemList.add(new RvItem_Rating("Nguyễn Tàu", "Một hành trình tìm kiếm chân lý và sức mạnh", R.drawable.background_novel_2, sdf.parse("15/01/2024"), 4.0f));
            ratingItemList.add(new RvItem_Rating("Tôn Ngộ Không", "Câu chuyện về sức mạnh và sự khôn ngoan", R.drawable.ic_launcher_background, sdf.parse("20/01/2024"), 4.8f));
            ratingItemList.add(new RvItem_Rating("Triệu Phú", "Cuộc chiến giành quyền lực trong giang hồ", R.drawable.background_novel_1, sdf.parse("25/01/2024"), 5.0f));
            ratingItemList.add(new RvItem_Rating("Vương Thiên", "Những bí mật trong thế giới tu tiên", R.drawable.background_novel_2, sdf.parse("01/02/2024"), 4.2f));
            ratingItemList.add(new RvItem_Rating("Đinh Nho", "Khám phá sức mạnh của tu luyện", R.drawable.ic_launcher_background, sdf.parse("05/02/2024"), 4.6f));
            ratingItemList.add(new RvItem_Rating("Trần Quốc", "Cuộc chiến giữa thiện và ác", R.drawable.background_novel_1, sdf.parse("10/02/2024"), 4.9f));
            ratingItemList.add(new RvItem_Rating("Phạm Cảnh", "Hành trình trở thành truyền nhân của võ lâm", R.drawable.background_novel_2, sdf.parse("15/02/2024"), 4.3f));
            ratingItemList.add(new RvItem_Rating("Lê Thành", "Tìm kiếm danh vọng trong giang hồ", R.drawable.ic_launcher_background, sdf.parse("20/02/2024"), 4.7f));
            ratingItemList.add(new RvItem_Rating("Ngô Minh", "Câu chuyện về tình bạn và lòng dũng cảm", R.drawable.background_novel_1, sdf.parse("25/02/2024"), 5.0f));
            ratingItemList.add(new RvItem_Rating("Đào Tĩnh", "Khám phá những bí mật trong tu tiên", R.drawable.background_novel_2, sdf.parse("01/03/2024"), 4.1f));
            ratingItemList.add(new RvItem_Rating("Phan Thiên", "Hành trình chinh phục vũ trụ tu tiên", R.drawable.ic_launcher_background, sdf.parse("05/03/2024"), 4.4f));
            ratingItemList.add(new RvItem_Rating("Bạch Nhạn", "Cuộc chiến giữa các thế lực trong tu tiên", R.drawable.background_novel_1, sdf.parse("10/03/2024"), 4.8f));
            ratingItemList.add(new RvItem_Rating("Nguyễn Văn", "Truyền thuyết về người anh hùng trong giang hồ", R.drawable.background_novel_2, sdf.parse("15/03/2024"), 4.0f));
            ratingItemList.add(new RvItem_Rating("Hồ Quang", "Hành trình khám phá sức mạnh cổ xưa", R.drawable.ic_launcher_background, sdf.parse("20/03/2024"), 4.6f));
            ratingItemList.add(new RvItem_Rating("Trần Đức", "Cuộc chiến giữa các bậc thầy tu luyện", R.drawable.background_novel_1, sdf.parse("25/03/2024"), 4.3f));
            ratingItemList.add(new RvItem_Rating("Lê Minh", "Truyền thuyết về những cuộc chiến không thể quên", R.drawable.background_novel_2, sdf.parse("30/03/2024"), 5.0f));
            ratingItemList.add(new RvItem_Rating("Nguyễn Thái", "Cuộc chiến để tìm lại danh dự", R.drawable.ic_launcher_background, sdf.parse("05/04/2024"), 4.2f));
            ratingItemList.add(new RvItem_Rating("Dương Huy", "Hành trình trở thành người đứng đầu", R.drawable.background_novel_1, sdf.parse("10/04/2024"), 4.7f));
            ratingItemList.add(new RvItem_Rating("Trương Tử", "Câu chuyện về những mối quan hệ trong giang hồ", R.drawable.background_novel_2, sdf.parse("15/04/2024"), 4.9f));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
       // Thiết lập adapter cho RecyclerView
        ratingAdapter = new MyAdapter(ratingItemList);
        rvRating.setAdapter(ratingAdapter);
        sortByRecent();
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

        btnThich.setOnClickListener(v -> {
            resetBackgroundButton();
            btnThich.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray_light));
            sortByRating();
        });
        numberOfRating.setText("("+ratingItemList.size()+")");
        return view;
    }

    private void resetBackgroundButton() {
        btnMoi.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray_deep));
        btnCu.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray_deep));
        btnThich.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray_deep));
    }

    private void sortByRecent() {
        ratingItemList.sort((item1, item2) -> {
            Date date1 = ((RvItem_Rating) item1).getTime();
            Date date2 = ((RvItem_Rating) item2).getTime();
            return date2.compareTo(date1); // Sắp xếp giảm dần
        });
        ratingAdapter.notifyDataSetChanged();
    }

    private void sortByOldest() {
        ratingItemList.sort((item1, item2) -> {
            Date date1 = ((RvItem_Rating) item1).getTime();
            Date date2 = ((RvItem_Rating) item2).getTime();
            return date1.compareTo(date2); // Sắp xếp tăng dần
        });
        ratingAdapter.notifyDataSetChanged();
    }

    private void sortByRating() {
        ratingItemList.sort((item1, item2) -> {
            float rating1 = ((RvItem_Rating) item1).getRating();
            float rating2 = ((RvItem_Rating) item2).getRating();
            return Float.compare(rating2, rating1); // Sắp xếp giảm dần
        });
        ratingAdapter.notifyDataSetChanged();
    }

}
