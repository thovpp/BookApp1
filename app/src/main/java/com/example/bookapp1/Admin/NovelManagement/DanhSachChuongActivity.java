package com.example.bookapp1.Admin.NovelManagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.Chapter.ChapterActivity;
import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_Chapter;
import com.example.bookapp1.Novel.MyAdapter;
import com.example.bookapp1.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DanhSachChuongActivity extends AppCompatActivity {

    private RecyclerView rV_chuong;
    private ImageButton btn_arrange;
    private MyAdapter adapterChuong;
    private List<BaseItem> itemListChapters = new ArrayList<>();
    private boolean isSortedByOldest = true;
    ImageButton ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dschuong);

            // Initialize RecyclerView
            rV_chuong = findViewById(R.id.rv_dschuong);
            LinearLayoutManager layoutManagerChuong = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rV_chuong.setLayoutManager(layoutManagerChuong);

            // Populate chapter items
            setupChapterItems();
            // Button to toggle sorting
            btn_arrange = findViewById(R.id.ib_chuong_arrange);
            btn_arrange.setOnClickListener(v -> {
                toggleSort();
                adapterChuong.notifyDataSetChanged();
            });
            ivBack = findViewById(R.id.btn_dschuong_back);
        ivBack.setOnClickListener(v -> finish());
    }

        private void setupChapterItems() {
            String[][] chapterData = {
                    {"1", "Chương 1: Huyết Vân Kiếm - Huyền Thoại Bắt Đầu", "01-01-2000"},
                    {"2", "Chương 2: Thiên Long Bát Bộ - Những Cuộc Chiến Kỳ Diệu", "02-01-2000"},
                    {"3", "Chương 3: Bích Huyết Đao - Báu Vật Từ Thế Giới Ngầm", "03-01-2000"},
                    {"4", "Chương 4: Ngọc Hoàng Thái Cực - Cuộc Hành Trình Mạo Hiểm", "04-01-2000"},
                    {"5", "Chương 5: Độc Cô Cầu Bại - Sự Trỗi Dậy Của Một Anh Hùng", "05-01-2000"},
                    {"6", "Chương 6: Cửu Âm Chân Kinh - Bí Ẩn Từ Vạn Thế", "06-01-2000"},
                    {"7", "Chương 7: Nhất Kiếm Đoạt Hồn - Sát Thủ Trong Đêm", "07-01-2000"},
                    {"8", "Chương 8: Thiên Nhẫn Đạo - Đối Đầu Với Kẻ Thù", "08-01-2000"},
                    {"9", "Chương 9: Bạch Kinh Nhi - Tình Yêu Và Định Mệnh", "09-01-2000"},
                    {"10", "Chương 10: Đại Tạo Hóa - Thế Giới Chưa Từng Khám Phá", "10-01-2000"},
                    {"11", "Chương 11: Mặc Vân Tiên - Hành Trình Vượt Qua Biển Cả", "11-01-2000"},
                    {"12", "Chương 12: Huyền Vũ Khai Thiên - Khởi Đầu Một Kỷ Nguyên", "12-01-2000"},
                    {"13", "Chương 13: Hồ Ly Mị Ảnh - Những Mảnh Ghép Của Tâm Hồn", "13-01-2000"},
                    {"14", "Chương 14: Tuyệt Thế Thần Thoại - Cuộc Chiến Cuối Cùng", "14-01-2000"},
                    {"15", "Chương 15: Phong Vân Vô Kỵ - Kẻ Bất Khả Chiến Bại", "15-01-2000"},
                    {"16", "Chương 16: Kim Long Thần Điện - Nơi Quy Nguyên Lịch Sử", "16-01-2000"},
                    {"17", "Chương 17: Lãnh Huyết Kiếm - Con Đường Tìm Kiếm Quyền Lực", "17-01-2000"},
                    {"18", "Chương 18: Ngọc Nữ Huyền Băng - Bí Ẩn Từ Một Tình Yêu", "18-01-2000"},
                    {"19", "Chương 19: Quỷ Cốc Tử - Kẻ Thù Từ Bóng Tối", "19-01-2000"},
                    {"20", "Chương 20: Huyền Ảo Kiếm - Những Giấc Mơ Tận Cùng", "20-01-2000"},
                    {"21", "Chương 21: Thần Y Huyền Cơ - Kẻ Chữa Bệnh Kỳ Diệu", "21-01-2000"},
                    {"22", "Chương 22: Liệt Hỏa Kiếm - Ngọn Lửa Quyết Định Số Phận", "22-01-2000"},
                    {"23", "Chương 23: Nguyệt Tổ Vân Mộng - Khúc Hát Của Mộng Mơ", "23-01-2000"},
                    {"24", "Chương 24: Tà Thần Tà Ma - Âm Mưu Từ Bóng Tối", "24-01-2000"},
                    {"25", "Chương 25: Bạch Vân Cốc - Nơi Tình Bạn Được Thử Thách", "25-01-2000"},
                    {"26", "Chương 26: Cửu Âm Bạch Thảo - Bí Ẩn Từ Cuộc Chiến", "26-01-2000"},
                    {"27", "Chương 27: Lưu Ly Vân - Giấc Mơ Về Một Tình Yêu", "27-01-2000"},
                    {"28", "Chương 28: Ngọc Đế Hồi Xuân - Thời Gian Đã Thay Đổi", "28-01-2000"},
                    {"29", "Chương 29: Xích Bích Cửu Thiên - Cuộc Chiến Đỉnh Cao", "29-01-2000"},
                    {"30", "Chương 30: Cô Nương Đang Chờ - Chờ Đợi Một Tình Yêu", "30-01-2000"},
                    {"31", "Chương 31: Hắc Ám Đế Quốc - Những Bí Ẩn Chưa Được Khám Phá", "31-01-2000"},
                    {"32", "Chương 32: Chân Long Thượng Giới - Hành Trình Khám Phá Bầu Trời", "01-02-2000"},
                    {"33", "Chương 33: Bích Nguyệt Đường - Những Con Đường Đen Tối", "02-02-2000"},
                    {"34", "Chương 34: Kiếm Thế Mê - Giấc Mơ Về Sự Bất Tử", "03-02-2000"},
                    {"35", "Chương 35: Ngọc Hoàng Bảo Tích - Kho Tàng Vô Giá", "04-02-2000"},
                    {"36", "Chương 36: Tuyết Sơn Thần Đao - Cuộc Chiến Dưới Băng", "05-02-2000"},
                    {"37", "Chương 37: Đoạn Kiếm - Sát Thủ Truyền Thuyết", "06-02-2000"},
                    {"38", "Chương 38: Huyết Hồn Thiên - Những Cuộc Tìm Kiếm Kỳ Diệu", "07-02-2000"},
                    {"39", "Chương 39: Lục Địa Huyền Bí - Bí Ẩn Của Những Vùng Đất", "08-02-2000"},
                    {"40", "Chương 40: Nhật Nguyệt Thần Đồ - Vạn Năm Huyền Bí", "09-02-2000"},
                    {"41", "Chương 41: Huyền Thoại Kiếm - Câu Chuyện Về Những Anh Hùng", "10-02-2000"},
                    {"42", "Chương 42: Tam Sinh Tam Thế - Tình Yêu Vượt Thời Gian", "11-02-2000"},
                    {"43", "Chương 43: Đại Địa Thần - Kẻ Bảo Vệ Thế Giới", "12-02-2000"},
                    {"44", "Chương 44: Thần Đao Huyền Ảo - Những Câu Chuyện Huyền Bí", "13-02-2000"},
                    {"45", "Chương 45: Huyền Thoại Huyền Bí - Câu Chuyện Chưa Kể", "14-02-2000"},
                    {"46", "Chương 46: Cầu Tài Đón Lộc - Những Giấc Mơ Thành Hiện Thực", "15-02-2000"},
                    {"47", "Chương 47: Phong Bạo - Cuộc Chiến Bão Táp", "16-02-2000"},
                    {"48", "Chương 48: Thiên Địa Nhất Thần - Một Huyền Thoại Mới", "17-02-2000"},
                    {"49", "Chương 49: Mộng Ảo - Những Cuộc Hành Trình Đầy Mộng Mơ", "18-02-2000"},
                    {"50", "Chương 50: Đoạn Kết - Hành Trình Kết Thúc", "19-02-2000"}
            };


            // Create RvItem_Chapter objects from data
            for (int i = 0; i < chapterData.length; i++) {
                int id =Integer.parseInt(chapterData[i][0]); // ID
                String title = chapterData[i][1]; // Title
                Date time = parseDate(chapterData[i][2]); // Convert string to Date
                int orderNumber = i + 1; // Order number from 1 to 50

                // Create a new item and add to the list
                itemListChapters.add(new RvItem_Chapter(id, title, time,orderNumber));
            }

            // Set click listener for each item
            adapterChuong = new MyAdapter(itemListChapters, new MyAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseItem item) {
                    if (item instanceof RvItem_Chapter) {
                        RvItem_Chapter chapter = (RvItem_Chapter) item;

                        // Khởi chạy ChapterActivity và truyền ID chương
                        Intent intent = new Intent(DanhSachChuongActivity.this, ChapterActivity.class);
                        intent.putExtra("CHAPTER_ID", ""+chapter.getID());
                        startActivity(intent);
                    }
                }

            });
            rV_chuong.setAdapter(adapterChuong);

        }

        private static Date parseDate(String dateString) {
            try {
                return new SimpleDateFormat("dd-MM-yyyy").parse(dateString);
            } catch (Exception e) {
                System.out.println("Invalid date.");
                return new Date();
            }
        }

        private void toggleSort() {
            if (isSortedByOldest) {
                sortByNewest();
            } else {
                sortByOldest();
            }
            isSortedByOldest = !isSortedByOldest;
        }

        private void sortByOldest() {
            itemListChapters.sort(Comparator.comparing(o -> ((RvItem_Chapter) o).getTime())); // Sort by oldest date
        }

        private void sortByNewest() {
            itemListChapters.sort((o1, o2) -> ((RvItem_Chapter) o2).getTime().compareTo(((RvItem_Chapter) o1).getTime())); // Sort by newest date
        }
    }
