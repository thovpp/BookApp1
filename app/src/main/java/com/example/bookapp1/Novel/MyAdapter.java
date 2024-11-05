// MyAdapter.java
package com.example.bookapp1.Novel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_Chapter;
import com.example.bookapp1.DTOs.RvItem_Comment;
import com.example.bookapp1.DTOs.RvItem_Novel_3Detail;
import com.example.bookapp1.DTOs.RvItem_Novel_5Detail;
import com.example.bookapp1.DTOs.RvItem_Rating;
import com.example.bookapp1.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<BaseItem> itemList;
    private OnItemClickListener itemClickListener;

    public MyAdapter(List<BaseItem> itemList, OnItemClickListener itemClickListener) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }
    public MyAdapter(List<BaseItem> itemList) {
        this.itemList = itemList;
    }
    public interface OnItemClickListener {
        void onItemClick(BaseItem item);
    }
    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof RvItem_Novel_3Detail) {
            return 0;
        }
        else if (itemList.get(position) instanceof RvItem_Novel_5Detail) {
            return 1;
        }else if (itemList.get(position) instanceof RvItem_Rating) {
            return 2;
        }else if (itemList.get(position) instanceof RvItem_Comment) {
            return 3;
        }
        else if (itemList.get(position) instanceof RvItem_Chapter) {
            return 4;
        }
        return -1; // Mặc định
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_cungtheloai, parent, false);
                return new CungTheLosiViewHolder(view);
            }
            case 1:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_cungtacgia, parent, false);
                return new CungTacGiaViewHolder(view);
            }
            case 2:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_danhgia, parent, false);
                return new RatingViewHolder(view);
            }
            case 3:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_binhluan, parent, false);
                return new CommentViewHolder(view);
            }
            case 4:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_chaper, parent, false);
                return new ChapterViewHolder(view);
            }
            default:{
                return null;
            }
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaseItem item = itemList.get(position);

        // Set item click listener
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(item);
            }
        });
        if (holder instanceof CungTheLosiViewHolder) {
            RvItem_Novel_3Detail detail_item = (RvItem_Novel_3Detail) itemList.get(position);
            ((CungTheLosiViewHolder) holder).title.setText(detail_item.getTitle());
            ((CungTheLosiViewHolder) holder).category.setText(detail_item.getDescription());
            ((CungTheLosiViewHolder) holder).thumb.setImageResource(detail_item.getImageResId());
        }
        else if (holder instanceof CungTacGiaViewHolder) {
            RvItem_Novel_5Detail detail_item = (RvItem_Novel_5Detail) itemList.get(position);
            ((CungTacGiaViewHolder) holder).title.setText(detail_item.getTitle());
            ((CungTacGiaViewHolder) holder).subtitle.setText(detail_item.getDescription());
            ((CungTacGiaViewHolder) holder).raingPoint.setText(String.valueOf(detail_item.getRating()));
            ((CungTacGiaViewHolder) holder).category.setText(detail_item.getCategory());
            ((CungTacGiaViewHolder) holder).thumb.setImageResource(detail_item.getImageResId());
            ((CungTacGiaViewHolder) holder).ratingBar.setRating(detail_item.getRating());
        }
        else if (holder instanceof RatingViewHolder) {
            RvItem_Rating rating_item = (RvItem_Rating) itemList.get(position);

            ((RatingViewHolder) holder).name.setText(rating_item.getTitle());
            ((RatingViewHolder) holder).comment.setText(rating_item.getDescription());
            ((RatingViewHolder) holder).raingPoint.setText(String.valueOf(rating_item.getRating()));
            ((RatingViewHolder) holder).avatar.setImageResource(rating_item.getImageResId());
            ((RatingViewHolder) holder).ratingBar.setRating(rating_item.getRating());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()); // Thay đổi định dạng nếu cần
            String formattedDate = sdf.format(rating_item.getTime());
            ((RatingViewHolder) holder).time.setText(formattedDate);

        }
        else if (holder instanceof CommentViewHolder) {
            RvItem_Comment comment_item = (RvItem_Comment) itemList.get(position);
            ((CommentViewHolder) holder).name.setText(comment_item.getName());
            ((CommentViewHolder) holder).comment.setText(comment_item.getComment());
            ((CommentViewHolder) holder).chapter.setText(String.valueOf(comment_item.getChapter()));
            ((CommentViewHolder) holder).avatar.setImageResource(comment_item.getImageResId());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()); // Thay đổi định dạng nếu cần
            String formattedDate = sdf.format(comment_item.getTime());
            ((CommentViewHolder) holder).date.setText(formattedDate);

        }
        else if (holder instanceof ChapterViewHolder) {
            RvItem_Chapter chapter_item = (RvItem_Chapter) itemList.get(position);
            ((ChapterViewHolder) holder).title.setText(chapter_item.getTitle());
            ((ChapterViewHolder) holder).stt.setText(String.valueOf(chapter_item.getOrder_number()));
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()); // Thay đổi định dạng nếu cần
            String formattedDate = sdf.format(chapter_item.getTime());
            ((ChapterViewHolder) holder).date.setText(formattedDate);

        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    public static class RatingViewHolder extends ViewHolder {
        TextView name;
        TextView comment;
        TextView raingPoint;
        ImageView avatar;
        TextView time;      // Thêm TextView cho thể loại
        RatingBar ratingBar;    // Thêm RatingBar cho đánh giá
        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_danhgia_title);
            comment = itemView.findViewById(R.id.tv_danhgia_comment);
            time = itemView.findViewById(R.id.tv_danhgia_time);
            raingPoint = itemView.findViewById(R.id.tv_danhgia_ratingpoint);
            avatar = itemView.findViewById(R.id.iv_danhgia_avatar);
            ratingBar = itemView.findViewById(R.id.rb_danhgia_ratingbar);
        }
    }
    public static class CungTacGiaViewHolder extends ViewHolder {
        TextView title;
        TextView subtitle;
        TextView raingPoint;
        ImageView thumb;
        TextView category;      // Thêm TextView cho thể loại
        RatingBar ratingBar;    // Thêm RatingBar cho đánh giá

        public CungTacGiaViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_cungtacgia_title);
            subtitle = itemView.findViewById(R.id.tv_cungtacgia_desc);
            raingPoint = itemView.findViewById(R.id.tv_cungtacgia_rating);
            thumb = itemView.findViewById(R.id.iv_cungtacgia_thumb);
            category = itemView.findViewById(R.id.tv_cungtacgia_category);
            ratingBar = itemView.findViewById(R.id.rb_cungtacgia_ratingbar);
        }
    }
    public static class CungTheLosiViewHolder extends ViewHolder {
        TextView title;
        TextView category;
        ImageView thumb;

        public CungTheLosiViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_cungtheloai_title);
            category = itemView.findViewById(R.id.tv_cungtheloai_category);
            thumb = itemView.findViewById(R.id.iv_cungtheloai_thumb);
        }
    }
    public static class CommentViewHolder extends ViewHolder {
        TextView name, date, comment, chapter;
        ImageView avatar;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_binhluan_name);
            comment = itemView.findViewById(R.id.tv_binhluan_comment);
            date = itemView.findViewById(R.id.tv_binhluan_date);
            chapter = itemView.findViewById(R.id.tv_binhluan_chapter);
            avatar = itemView.findViewById(R.id.iv_binhluan_avatar);
        }
    }
    public static class ChapterViewHolder extends ViewHolder {
        TextView title, date, stt;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_chuong_tile);
            date = itemView.findViewById(R.id.tv_chuong_date);
            stt = itemView.findViewById(R.id.tv_chuong_stt);
        }
    }
}
