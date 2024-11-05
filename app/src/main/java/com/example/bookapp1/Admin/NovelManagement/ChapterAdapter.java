package com.example.bookapp1.Admin.NovelManagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.RvItem_Chapter;
import com.example.bookapp1.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ChapterViewHolder> {
    private List<RvItem_Chapter> chapterList;
    private OnChapterClickListener chapterClickListener;
    private OnDeleteClickListener deleteClickListener;

    public interface OnChapterClickListener {
        void onChapterClick(RvItem_Chapter chapter);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(RvItem_Chapter chapter);
    }

    public ChapterAdapter(List<RvItem_Chapter> chapterList, OnChapterClickListener chapterClickListener, OnDeleteClickListener deleteClickListener) {
        this.chapterList = chapterList;
        this.chapterClickListener = chapterClickListener;
        this.deleteClickListener = deleteClickListener;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        RvItem_Chapter chapter = chapterList.get(position);
        holder.bind(chapter);
    }

    @Override
    public int getItemCount() {
        return chapterList.size();
    }

    class ChapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvOrderNumber, tvDate;
        ImageButton btnDeleteChapter;

        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOrderNumber = itemView.findViewById(R.id.tv_order_number);
            tvDate = itemView.findViewById(R.id.tv_date);
            btnDeleteChapter = itemView.findViewById(R.id.btn_delete_chapter);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && chapterClickListener != null) {
                    chapterClickListener.onChapterClick(chapterList.get(position));
                }
            });

            btnDeleteChapter.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && deleteClickListener != null) {
                    deleteClickListener.onDeleteClick(chapterList.get(position));
                }
            });
        }

        public void bind(RvItem_Chapter chapter) {
            tvTitle.setText(chapter.getTitle());
            tvOrderNumber.setText("Chương " + chapter.getOrder_number());
            tvDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(chapter.getTime()));
        }
    }
}
