package com.example.bookapp1;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookapp1.Novel_Fragment.chapterSetting_Fragment;

public class ChapterActivity extends AppCompatActivity {

    private Button btnChapterSelector;
    private PopupWindow popupWindow;
    private String[][] chapters;
    private int currentChapterIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctruyen);

        btnChapterSelector = findViewById(R.id.btn_chapterSelector);
        // Set click listener for the button to show the chapter list
        btnChapterSelector.setOnClickListener(this::showChapterList);

        ImageButton ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> finish());
        Button btnPrevious = findViewById(R.id.btn_previousChapter);
        Button btnNext = findViewById(R.id.btn_nextChapter);
        btnPrevious.setOnClickListener(v -> loadPreviousChapter());
        btnNext.setOnClickListener(v -> loadNextChapter());
        ImageButton btnSetting = findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(v -> showSettingsDialog());
        // Nạp dữ liệu vô
        chapters = initializeChapters();
        // Nhận ID chương từ Intent để check hiện chương nào
        String chapterId =getIntent().getStringExtra("CHAPTER_ID");

        // Sử dụng chapterId để nạp nội dung tương ứng
        if (chapterId != null) {
            loadChapterContent(chapterId);
        } else {
            Toast.makeText(this, "No chapter selected", Toast.LENGTH_SHORT).show();
        }
    }
    //Nạp dữ liệu
    private String[][] initializeChapters() {
        return new String[][] {
                {"1", "Chương 1: Huyết Vân Kiếm - Huyền Thoại Bắt Đầu", "" +
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."+
                        "Huyết Vân Kiếm khởi đầu một cuộc hành trình vĩ đại, nơi mà những bí mật cổ xưa được khám phá."
                },
                {"2", "Chương 2: Thiên Long Bát Bộ - Những Cuộc Chiến Kỳ Diệu", "Cuộc chiến giữa các thế lực Thiên Long mang đến những kỳ diệu không thể tưởng tượng nổi."},
                {"3", "Chương 3: Bích Huyết Đao - Báu Vật Từ Thế Giới Ngầm", "Bích Huyết Đao chứa đựng sức mạnh vô biên, bí ẩn từ thế giới ngầm chưa từng được tiết lộ."},
                {"4", "Chương 4: Ngọc Hoàng Thái Cực - Cuộc Hành Trình Mạo Hiểm", "Cuộc hành trình tìm kiếm Ngọc Hoàng Thái Cực bắt đầu, dẫn lối đến những thử thách cam go."},
                {"5", "Chương 5: Độc Cô Cầu Bại - Sự Trỗi Dậy Của Một Anh Hùng", "Độc Cô Cầu Bại, một anh hùng trỗi dậy từ bóng tối, quyết tâm chiến thắng mọi kẻ thù."},
                {"6", "Chương 6: Cửu Âm Chân Kinh - Bí Ẩn Từ Vạn Thế", "Cửu Âm Chân Kinh tiết lộ những bí ẩn từ vạn thế, là chìa khóa mở ra sức mạnh tiềm ẩn."},
                {"7", "Chương 7: Nhất Kiếm Đoạt Hồn - Sát Thủ Trong Đêm", "Nhất Kiếm Đoạt Hồn, sát thủ xuất hiện trong đêm tối, sẵn sàng cho những trận chiến ác liệt."},
                {"8", "Chương 8: Thiên Nhẫn Đạo - Đối Đầu Với Kẻ Thù", "Thiên Nhẫn Đạo đứng vững trước kẻ thù, bảo vệ những điều tốt đẹp trong thế giới hỗn loạn."},
                {"9", "Chương 9: Bạch Kinh Nhi - Tình Yêu Và Định Mệnh", "Bạch Kinh Nhi đối diện với định mệnh, tình yêu và hy vọng hòa quyện trong một hành trình."},
                {"10", "Chương 10: Đại Tạo Hóa - Thế Giới Chưa Từng Khám Phá", "Đại Tạo Hóa khám phá thế giới chưa từng biết, nơi mà những điều kỳ diệu đang chờ đón."},
                {"11", "Chương 11: Mặc Vân Tiên - Hành Trình Vượt Qua Biển Cả", "Mặc Vân Tiên vượt biển cả, đối mặt với sóng gió và khám phá những vùng đất mới."},
                {"12", "Chương 12: Huyền Vũ Khai Thiên - Khởi Đầu Một Kỷ Nguyên", "Huyền Vũ khai thiên, khởi đầu một kỷ nguyên mới với nhiều cơ hội và thử thách lớn."},
                {"13", "Chương 13: Hồ Ly Mị Ảnh - Những Mảnh Ghép Của Tâm Hồn", "Hồ Ly Mị Ảnh, những mảnh ghép tâm hồn kết nối với nhau, tạo nên một câu chuyện kỳ diệu."},
                {"14", "Chương 14: Tuyệt Thế Thần Thoại - Cuộc Chiến Cuối Cùng", "Tuyệt Thế Thần Thoại mang đến cuộc chiến cuối cùng giữa các thế lực hùng mạnh."},
                {"15", "Chương 15: Phong Vân Vô Kỵ - Kẻ Bất Khả Chiến Bại", "Phong Vân Vô Kỵ nổi lên như một kẻ bất khả chiến bại, khiến mọi đối thủ khiếp sợ."},
                {"16", "Chương 16: Kim Long Thần Điện - Nơi Quy Nguyên Lịch Sử", "Kim Long Thần Điện lưu giữ quy nguyên lịch sử, nơi những bí mật được bảo vệ cẩn thận."},
                {"17", "Chương 17: Lãnh Huyết Kiếm - Con Đường Tìm Kiếm Quyền Lực", "Lãnh Huyết Kiếm dẫn dắt con đường tìm kiếm quyền lực, nơi mà mọi thứ đều có giá."},
                {"18", "Chương 18: Ngọc Nữ Huyền Băng - Bí Ẩn Từ Một Tình Yêu", "Ngọc Nữ Huyền Băng mang đến những bí ẩn từ một tình yêu bất diệt, khiến mọi người say mê."},
                {"19", "Chương 19: Quỷ Cốc Tử - Kẻ Thù Từ Bóng Tối", "Quỷ Cốc Tử, kẻ thù từ bóng tối, mang theo những âm mưu tàn độc và đầy hiểm nguy."},
                {"20", "Chương 20: Huyền Ảo Kiếm - Những Giấc Mơ Tận Cùng", "Huyền Ảo Kiếm xuất hiện trong những giấc mơ, đưa nhân vật vào cuộc hành trình kỳ thú."},
                {"21", "Chương 21: Thần Y Huyền Cơ - Kẻ Chữa Bệnh Kỳ Diệu", "Thần Y Huyền Cơ, kẻ chữa bệnh kỳ diệu, mang lại hy vọng cho những người đang khổ đau."},
                {"22", "Chương 22: Liệt Hỏa Kiếm - Ngọn Lửa Quyết Định Số Phận", "Liệt Hỏa Kiếm, ngọn lửa quyết định số phận, tạo nên những trận chiến không thể quên."},
                {"23", "Chương 23: Nguyệt Tổ Vân Mộng - Khúc Hát Của Mộng Mơ", "Nguyệt Tổ hát khúc hát của mộng mơ, đưa tâm hồn đến những chân trời mới."},
                {"24", "Chương 24: Tà Thần Tà Ma - Âm Mưu Từ Bóng Tối", "Tà Thần Tà Ma xuất hiện với âm mưu từ bóng tối, khiến mọi người phải cảnh giác."},
                {"25", "Chương 25: Bạch Vân Cốc - Nơi Tình Bạn Được Thử Thách", "Bạch Vân Cốc trở thành nơi thử thách tình bạn, nơi mà lòng trung thành được kiểm chứng."},
                {"26", "Chương 26: Cửu Âm Bạch Thảo - Bí Ẩn Từ Cuộc Chiến", "Cửu Âm Bạch Thảo, những bí ẩn từ cuộc chiến, tạo nên câu chuyện đầy kịch tính."},
                {"27", "Chương 27: Lưu Ly Vân - Giấc Mơ Về Một Tình Yêu", "Lưu Ly Vân mơ về một tình yêu thuần khiết, vượt qua mọi rào cản của thời gian."},
                {"28", "Chương 28: Ngọc Đế Hồi Xuân - Thời Gian Đã Thay Đổi", "Ngọc Đế Hồi Xuân trở về thời gian đã thay đổi, dẫn lối cho những cuộc phiêu lưu mới."},
                {"29", "Chương 29: Xích Bích Cửu Thiên - Cuộc Chiến Đỉnh Cao", "Xích Bích Cửu Thiên mang đến cuộc chiến đỉnh cao, nơi mà những anh hùng tỏa sáng."},
                {"30", "Chương 30: Cô Nương Đang Chờ - Chờ Đợi Một Tình Yêu", "Cô Nương đang chờ đợi một tình yêu, tâm hồn ngập tràn hy vọng và mơ mộng."},
                {"31", "Chương 31: Hắc Ám Đế Quốc - Những Bí Ẩn Chưa Được Khám Phá", "Hắc Ám Đế Quốc chứa đựng nhiều bí ẩn chưa được khám phá, gợi mở những câu chuyện ly kỳ."},
                {"32", "Chương 32: Chân Long Thượng Giới - Hành Trình Khám Phá Bầu Trời", "Chân Long Thượng Giới mở ra hành trình khám phá bầu trời, nơi có những điều kỳ diệu."},
                {"33", "Chương 33: Bích Nguyệt Đường - Những Con Đường Đen Tối", "Bích Nguyệt Đường dẫn lối đến những con đường đen tối, nơi mà nguy hiểm luôn rình rập."},
                {"34", "Chương 34: Kiếm Thế Mê - Giấc Mơ Về Sự Bất Tử", "Kiếm Thế Mê mang đến giấc mơ về sự bất tử, nơi mà mọi điều đều có thể xảy ra."},
                {"35", "Chương 35: Ngọc Hoàng Bảo Tích - Kho Tàng Vô Giá", "Ngọc Hoàng Bảo Tích chứa đựng kho tàng vô giá, bảo vệ bí mật từ xa xưa."},
                {"36", "Chương 36: Tuyết Sơn Thần Đao - Cuộc Chiến Dưới Băng", "Tuyết Sơn Thần Đao dẫn dắt cuộc chiến dưới băng, nơi mà sức mạnh và lòng can đảm gặp nhau."},
                {"37", "Chương 37: Đoạn Kiếm - Sát Thủ Truyền Thuyết", "Đoạn Kiếm, sát thủ truyền thuyết, với tài năng vượt trội và sự bí ẩn không ai biết."},
                {"38", "Chương 38: Huyết Hồn Thiên - Những Cuộc Tìm Kiếm Kỳ Diệu", "Huyết Hồn Thiên thực hiện những cuộc tìm kiếm kỳ diệu, dẫn lối cho những cuộc phiêu lưu."},
                {"39", "Chương 39: Lục Địa Huyền Bí - Bí Ẩn Của Những Vùng Đất", "Lục Địa Huyền Bí chứa đựng nhiều bí ẩn của những vùng đất chưa từng được khám phá."},
                {"40", "Chương 40: Nhật Nguyệt Thần Đồ - Vạn Năm Huyền Bí", "Nhật Nguyệt Thần Đồ mang đến những huyền bí vạn năm, mở ra cánh cửa đến quá khứ."},
                {"41", "Chương 41: Huyền Thoại Kiếm - Câu Chuyện Về Những Anh Hùng", "Huyền Thoại Kiếm kể lại câu chuyện về những anh hùng, những người dám đứng lên vì chính nghĩa."},
                {"42", "Chương 42: Tam Sinh Tam Thế - Tình Yêu Vượt Thời Gian", "Tam Sinh Tam Thế, tình yêu vượt thời gian, kết nối những số phận lại với nhau."},
                {"43", "Chương 43: Đại Địa Thần - Kẻ Bảo Vệ Thế Giới", "Đại Địa Thần, kẻ bảo vệ thế giới, đứng vững trước mọi nguy hiểm để bảo vệ hòa bình."},
                {"44", "Chương 44: Thần Đao Huyền Ảo - Những Câu Chuyện Huyền Bí", "Thần Đao Huyền Ảo mang đến những câu chuyện huyền bí, mở ra những bí ẩn chưa được biết."},
                {"45", "Chương 45: Huyền Thoại Huyền Bí - Câu Chuyện Chưa Kể", "Huyền Thoại Huyền Bí còn nhiều câu chuyện chưa được kể, chờ đợi những người khám phá."},
                {"46", "Chương 46: Cầu Tài Đón Lộc - Những Giấc Mơ Thành Hiện Thực", "Cầu Tài Đón Lộc giúp những giấc mơ thành hiện thực, tạo nên cuộc sống tươi đẹp hơn."},
                {"47", "Chương 47: Phong Bạo - Cuộc Chiến Bão Táp", "Phong Bạo nổi lên giữa cuộc chiến bão táp, quyết tâm chiến thắng mọi khó khăn."},
                {"48", "Chương 48: Thiên Địa Nhất Thần - Một Huyền Thoại Mới", "Thiên Địa Nhất Thần tạo nên một huyền thoại mới, khắc ghi dấu ấn trong lòng mọi người."},
                {"49", "Chương 49: Mộng Ảo - Những Cuộc Hành Trình Đầy Mộng Mơ", "Mộng Ảo dẫn dắt những cuộc hành trình đầy mộng mơ, nơi mà mọi điều đều có thể xảy ra."},
                {"50", "Chương 50: Đoạn Kết - Hành Trình Kết Thúc", "Đoạn Kết mang lại cái nhìn tổng quan về hành trình đã qua, nơi mà mọi kỷ niệm sẽ sống mãi."}
        };
    }

    private void showChapterList(@NonNull View anchor) {
        //Nạp cíi scroll view vô
        View popupView = LayoutInflater.from(this).inflate(R.layout.dstruyen_list_chapters, null);

        //Set up cho cái scroll view kích thước, thông số,...
        popupWindow = new PopupWindow(
                popupView,
                800,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        popupWindow.setHeight(1500);
        popupWindow.setFocusable(true);
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        //Vị trí cía list xuất hiện trang bản đồ
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        popupWindow.showAtLocation(anchor, Gravity.CENTER_HORIZONTAL, 0, location[0]-180);

        // Khai báo cái
        LinearLayout layout = popupView.findViewById(R.id.popup_layout);
        layout.removeAllViews();

        //Set up cho từng chapter button
        for (int i = 0; i < chapters.length; i++) {
            Button chapterButton = new Button(this);
            chapterButton.setText(chapters[i][1]);
            chapterButton.setTag(chapters[i][0]);

            // Đang ở chương nào thì chapter button đó sáng lên
            if (i == currentChapterIndex) {
                chapterButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light)); // Yellow background
                chapterButton.setTextColor(getResources().getColor(android.R.color.black)); // Black text
            } else {
                chapterButton.setBackgroundColor(getResources().getColor(R.color.list_chapter)); // Default background
                chapterButton.setTextColor(getResources().getColor(R.color.text_chapter)); // Default text color
            }

            //Set up chỉ số giao diện cho từng chapterButton
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            chapterButton.setLayoutParams(params);
            chapterButton.setGravity(Gravity.LEFT);
            chapterButton.setAllCaps(false);

            //Set up chức năng click cho từng chapterButton
            chapterButton.setOnClickListener(v -> {
                String chapterId = (String) v.getTag();
                String chapterName = ((Button) v).getText().toString();
                popupWindow.dismiss();
                btnChapterSelector.setText(chapterName);
                loadChapterContent(chapterId);
            });

            layout.addView(chapterButton); // Add button to the layout
        }
        //Cho nó tự di chuyển tới chapter đang ở
        if (currentChapterIndex >= 0) {
            Button currentButton = (Button) layout.getChildAt(currentChapterIndex);
            if (currentButton != null) {
                currentButton.post(() -> {
                    ScrollView scrollView = popupView.findViewById(R.id.scrollView);
                    scrollView.scrollTo(0, currentButton.getTop());
                });
            }
        }
    }

    private void loadChapterContent(String chapterId) {
        for (int i = 0; i < chapters.length; i++) {
            if (chapters[i][0].equals(chapterId)) {
                // Debug message
                Log.d("ChapterActivity", "Loading chapter: " + chapterId);
                currentChapterIndex = i;

                TextView textViewContent = findViewById(R.id.tv_story);
                textViewContent.setText(chapters[i][2]);

                TextView textViewTitle = findViewById(R.id.tv_title);
                textViewTitle.setText(chapters[i][1]);
                btnChapterSelector.setText(chapters[i][1]);
                return;
            }
        }
        Toast.makeText(this, "Chương không tồn tại", Toast.LENGTH_SHORT).show();
    }

    // Trở về tập trước đó
    private void loadPreviousChapter() {
        if (currentChapterIndex > 0) {
            currentChapterIndex--;
            loadChapterContent(chapters[currentChapterIndex][0]);
        } else {
            Toast.makeText(this, "This is the first chapter.", Toast.LENGTH_SHORT).show();
        }
    }

    // Xem tập tiếp theo
    private void loadNextChapter() {
        if (currentChapterIndex < chapters.length - 1) {
            currentChapterIndex++;
            loadChapterContent(chapters[currentChapterIndex][0]);
        } else {
            Toast.makeText(this, "This is the last chapter.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showSettingsDialog() {
        chapterSetting_Fragment dialogFragment = new chapterSetting_Fragment();
        dialogFragment.show(getSupportFragmentManager(), "chapterSetting_Fragment");
    }


}
