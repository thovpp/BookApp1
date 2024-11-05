package com.example.bookapp1.Chapter;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookapp1.R;

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
                {"1", "Chương 1: Huyết Vân Kiếm - Huyền Thoại Bắt Đầu",
                        "Trong vương quốc cổ đại nơi những bí mật bị che giấu bởi dòng thời gian, tồn tại một thanh kiếm mang tên Huyết Vân Kiếm. Huyền thoại kể rằng thanh kiếm này được rèn từ huyết lệ của những vị anh hùng đã ngã xuống trong cuộc chiến chống lại bóng tối hắc ám. "
                                + "Nhân vật chính, một người trẻ tuổi với lòng nhiệt huyết và khát vọng khám phá sự thật, tình cờ phát hiện ra rằng mình là người được định mệnh lựa chọn để cầm lấy Huyết Vân Kiếm và bắt đầu cuộc hành trình vượt qua mọi hiểm nguy. "
                                + "Trên hành trình, anh ta gặp gỡ những con người đặc biệt, từ những chiến binh lang thang đến những pháp sư giàu kinh nghiệm, mỗi người đều mang trong mình câu chuyện riêng và lý do để tiếp tục đấu tranh. "
                                + "Mỗi bước chân của anh là sự đối mặt với những kẻ thù hùng mạnh, những sinh vật thần thoại và những phép thuật cổ xưa. Nhưng điều quan trọng hơn cả, cuộc hành trình này đưa anh đến việc khám phá bản chất thực sự của chính mình, liệu anh có đủ can đảm và bản lĩnh để đối mặt với số phận và trở thành người bảo vệ thực sự của vương quốc hay không?"},

                {"2", "Chương 2: Thiên Long Bát Bộ - Cuộc Chiến Kỳ Diệu",
                        "Sau khi nhận lấy Huyết Vân Kiếm, nhân vật chính thấy mình bị cuốn vào cuộc chiến không lối thoát giữa các thế lực mạnh mẽ nhất trong vương quốc. Cuộc chiến giữa những thế lực của Thiên Long - một nhóm bí ẩn gồm tám vị thần thú với quyền năng vô song, mỗi vị thần thú đại diện cho một yếu tố của thiên nhiên như gió, nước, lửa, đất, ánh sáng, bóng tối, thời gian và không gian. "
                                + "Cuộc đối đầu bắt đầu khi một trong những kẻ thù chính của nhân vật chính, vốn là hậu duệ của một trong tám vị thần thú, quyết tâm sử dụng quyền năng của mình để tái lập lại trật tự thế giới theo ý mình, bằng cách tiêu diệt những ai dám chống đối. "
                                + "Nhân vật chính nhận ra rằng để đối mặt với những đối thủ đầy quyền năng này, anh không chỉ cần sức mạnh của Huyết Vân Kiếm mà còn phải tìm hiểu cách kích hoạt năng lực tiềm ẩn trong chính mình. "
                                + "Chương này đưa nhân vật vào những cuộc đối đầu kịch tính với những vị thần thú hùng mạnh, nơi anh học cách sử dụng và hòa hợp sức mạnh của mình với thiên nhiên, trở thành một phần của vòng quay vĩ đại trong cuộc chiến của vũ trụ."},

                {"3", "Chương 3: Bích Huyết Đao - Báu Vật Từ Thế Giới Ngầm",
                        "Bích Huyết Đao, thanh đao chứa đựng sức mạnh vô biên, được đồn đại là có khả năng hút lấy sinh khí từ người mà nó tiếp xúc. Nhân vật chính bắt gặp một nhóm kẻ thù đang tìm kiếm báu vật này, tin rằng nó sẽ giúp họ trở nên bất khả chiến bại trong cuộc chiến thống trị thế giới. "
                                + "Khi nhân vật chính tiếp cận Bích Huyết Đao, anh nhận ra rằng thanh đao này không chỉ là một vũ khí, mà còn mang trong mình linh hồn của một chiến binh bất tử từ thời cổ đại. "
                                + "Linh hồn ấy đã bị nguyền rủa, mãi mãi bị giam cầm trong thanh đao để trừng phạt tội ác của hắn trong cuộc chiến tranh giành quyền lực. Thanh đao có khả năng mang lại sức mạnh vô biên nhưng cũng có thể phá hủy tâm hồn của bất cứ ai cố ý chiếm đoạt nó. "
                                + "Nhân vật chính không chỉ phải vượt qua những kẻ thù mà còn phải đối mặt với sức mạnh hấp dẫn của Bích Huyết Đao, học cách kiểm soát bản thân để không bị thanh đao chi phối. "
                                + "Cuối cùng, sau một trận chiến ác liệt, anh quyết định phong ấn thanh đao này và giữ nó như một minh chứng cho sự nguy hiểm của tham vọng không kiểm soát."},

                {"4", "Chương 4: Ngọc Hoàng Thái Cực - Cuộc Hành Trình Mạo Hiểm",
                        "Ngọc Hoàng Thái Cực, viên ngọc thần kỳ được truyền tụng là chứa đựng sức mạnh của âm và dương, có khả năng hồi sinh người chết và ban phước cho người sống. Tuy nhiên, chỉ những người xứng đáng mới có thể sở hữu nó. "
                                + "Được dẫn dắt bởi những dấu vết cổ xưa, nhân vật chính và nhóm của mình bắt đầu một hành trình vào sâu trong những khu rừng đầy nguy hiểm và núi non hiểm trở để tìm kiếm viên ngọc này. "
                                + "Trên đường đi, họ gặp phải những sinh vật cổ xưa đang bảo vệ viên ngọc, mỗi sinh vật đều có sức mạnh và trí tuệ đáng sợ. Nhân vật chính nhận ra rằng viên ngọc không phải là một công cụ để giành quyền lực mà là một thử thách của lòng dũng cảm và sự hi sinh. "
                                + "Trong hành trình, anh phải lựa chọn giữa việc sử dụng viên ngọc để cứu một người bạn đang bị thương nặng hoặc bảo vệ sức mạnh của nó để cứu nhiều người khác trong tương lai. "
                                + "Quyết định cuối cùng của anh không chỉ cứu được người bạn mà còn chứng minh rằng anh xứng đáng với sức mạnh của viên ngọc, giúp anh đạt được sự tiến bộ về tâm linh và sức mạnh cá nhân."},

                {"5", "Chương 5: Độc Cô Cầu Bại - Sự Trỗi Dậy Của Một Anh Hùng",
                        "Trên hành trình tìm kiếm câu trả lời cho những bí mật về Huyết Vân Kiếm và định mệnh của mình, nhân vật chính nghe về một huyền thoại mang tên Độc Cô Cầu Bại, một chiến binh cô độc và bất bại từng đối mặt với mọi kẻ thù mà không ai có thể đánh bại. "
                                + "Nhân vật chính quyết định tìm đến Độc Cô Cầu Bại để xin lời chỉ dạy và học hỏi, mong rằng sự khôn ngoan và kỹ năng chiến đấu của huyền thoại này sẽ giúp anh tiến xa hơn trên hành trình của mình. "
                                + "Khi gặp Độc Cô Cầu Bại, anh phát hiện rằng người chiến binh huyền thoại này đã từ bỏ vũ khí và chọn sống cuộc đời thanh tịnh, vì ông nhận ra rằng sự bất bại không phải là tất cả, mà là sự hiểu biết sâu sắc về chính bản thân và ý nghĩa của cuộc sống. "
                                + "Độc Cô Cầu Bại truyền lại cho anh những lời dạy quý giá về lòng kiên nhẫn, sự bình an trong tâm hồn và trách nhiệm của một chiến binh. "
                                + "Nhờ những lời dạy này, nhân vật chính dần hiểu rằng sức mạnh thực sự không chỉ nằm ở khả năng chiến đấu, mà còn ở sự hiểu biết sâu sắc về bản thân và lòng nhân ái, những điều sẽ giúp anh vượt qua mọi thử thách trong tương lai."},

                {"6", "Chương 6: Cửu Âm Chân Kinh - Bí Ẩn Từ Vạn Thế",
                        "Cửu Âm Chân Kinh là một bộ kinh văn huyền bí được cho là chứa đựng tất cả các tri thức về võ công và thuật pháp của những bậc thầy cổ xưa. Được truyền tụng là bảo vật vô giá, nhưng cũng là mục tiêu săn đuổi của những kẻ tham lam muốn sở hữu sức mạnh vô biên. "
                                + "Nhân vật chính, cùng với những đồng đội của mình, bắt đầu hành trình tìm kiếm Cửu Âm Chân Kinh. Họ phải đối mặt với vô số thử thách, bao gồm các phép thuật và cạm bẫy được đặt ra từ hàng thế kỷ trước để bảo vệ kinh văn khỏi rơi vào tay kẻ xấu. "
                                + "Trong quá trình này, nhân vật chính phát hiện ra rằng Cửu Âm Chân Kinh không chỉ là một bộ sách về sức mạnh mà còn là bài học về đạo đức và nhân văn, giúp anh thấu hiểu rằng võ công không chỉ để chiến đấu, mà còn để bảo vệ và mang lại hòa bình. "
                                + "Anh quyết định không chiếm đoạt bộ kinh văn này, mà sẽ bảo vệ nó để tránh cho thế giới bị tàn phá bởi những kẻ tham vọng, đồng thời cũng nhận ra rằng con đường của mình là tìm cách sử dụng sức mạnh để mang lại lợi ích cho mọi người, chứ không chỉ cho bản thân mình."},

                // Add similar expansions for remaining chapters as needed
        };
    }



    private void showChapterList(@NonNull View anchor) {
        // Inflate the view for the popup
        View popupView = LayoutInflater.from(this).inflate(R.layout.dstruyen_list_chapters, null);

        // Set up the PopupWindow
        popupWindow = new PopupWindow(
                popupView,
                800,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true);
        popupWindow.setHeight(1500);
        popupWindow.setFocusable(true);
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        // Get the location of the anchor view and position the popup
        int[] location = new int[2];
        anchor.getLocationOnScreen(location);
        popupWindow.showAtLocation(anchor, Gravity.CENTER_HORIZONTAL, 0, location[0] - 180);

        // Set up the layout for the chapter list
        LinearLayout layout = popupView.findViewById(R.id.popup_layout);
        layout.removeAllViews();

        // Set up each chapter button
        for (int i = 0; i < chapters.length; i++) {
            Button chapterButton = new Button(this);
            chapterButton.setText(chapters[i][1]);
            chapterButton.setTag(chapters[i][0]);

            // Highlight the current chapter button
            if (i == currentChapterIndex) {
                chapterButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light)); // Yellow background
                chapterButton.setTextColor(getResources().getColor(android.R.color.black)); // Black text
            } else {
                chapterButton.setBackgroundColor(getResources().getColor(R.color.list_chapter)); // Default background
                chapterButton.setTextColor(getResources().getColor(R.color.text_chapter)); // Default text color
            }

            // Set up layout parameters for the chapter button
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            // Directly set padding (left, top, right, bottom)
            chapterButton.setPadding(16, 10, 16, 10); // 16px padding on left and right, no padding on top and bottom

            chapterButton.setLayoutParams(params);
            chapterButton.setGravity(Gravity.LEFT);
            chapterButton.setAllCaps(false);

            // Set up click listener for the chapter button
            chapterButton.setOnClickListener(v -> {
                String chapterId = (String) v.getTag();
                String chapterName = ((Button) v).getText().toString();
                popupWindow.dismiss();
                btnChapterSelector.setText(chapterName);
                loadChapterContent(chapterId);
            });

            layout.addView(chapterButton); // Add the button to the layout
        }

        // Scroll to the current chapter if necessary
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
