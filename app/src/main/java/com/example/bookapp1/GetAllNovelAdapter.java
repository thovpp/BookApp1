package com.example.bookapp1;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.Models.Novel;

import java.util.List;

public class GetAllNovelAdapter extends RecyclerView.Adapter<GetAllNovelAdapter.NovelViewHolder> {
    private List<Novel> novelList;
    private Context context;

    public GetAllNovelAdapter(List<Novel> novelList, Context context) {
        this.novelList = novelList;
        this.context = context;
    }

    @NonNull
    @Override
    public NovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_novel, parent, false);
        return new NovelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NovelViewHolder holder, int position) {
        Novel novel = novelList.get(position);
        holder.titleTextView.setText(novel.getTitle());
        holder.categoryTextView.setText(novel.getCategory());
        holder.currentChapterTextView.setText(String.valueOf(novel.getTotalChapters()));
        holder.totalChapterTextView.setText(String.valueOf(novel.getTotalChapters()));

        // Set up delete action
        holder.deleteButton.setOnClickListener(v -> {
            deleteNovel(holder.getAdapterPosition());
        });

        // Load the thumbnail URI with Glide
        String thumbnailUri = novel.getThumbnailUri();
        Glide.with(context)
                .load(Uri.parse(thumbnailUri))
                .diskCacheStrategy(DiskCacheStrategy.NONE) // Disable caching
                .skipMemoryCache(true) // Skip memory cache
                .placeholder(R.drawable.background_novel_2)
                .into(holder.thumbnailImageView);
        Log.d("GetAllNovelAdapter", "Thumbnail URI: " + novel.getThumbnailUri());
        holder.thumbnailImageView.setOnClickListener(v -> {
            // Add your intent code here to open details if needed
        });
    }

    @Override
    public int getItemCount() {
        return novelList.size();
    }

    static class NovelViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, currentChapterTextView, totalChapterTextView, categoryTextView;
        ImageView thumbnailImageView;
        ImageButton settingsButton, deleteButton;

        public NovelViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tv_lichsu_title);
            currentChapterTextView = itemView.findViewById(R.id.tv_lichsu_currentchapter);
            totalChapterTextView = itemView.findViewById(R.id.tv_lichsu_totalchapter);
            categoryTextView = itemView.findViewById(R.id.tv_lichsu_category);
            thumbnailImageView = itemView.findViewById(R.id.iv_lichsu_thumb);
            settingsButton = itemView.findViewById(R.id.btn_setting);
            deleteButton = itemView.findViewById(R.id.btn_delete);
        }
    }

    private void deleteNovel(int position) {
        Novel novel = novelList.get(position);
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);

        if (dbHelper.deleteNovel(novel.getId())) {
            novelList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Novel deleted successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed to delete novel", Toast.LENGTH_SHORT).show();
        }
    }
}