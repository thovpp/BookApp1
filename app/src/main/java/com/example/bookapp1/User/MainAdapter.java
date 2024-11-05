package com.example.bookapp1.User;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_History;
import com.example.bookapp1.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private final List<BaseItem> itemList;
    private MainAdapter.OnItemClickListener itemClickListener;
    private final OnItemDeleteClickListener deleteClickListener; // Declare delete listener
    private final OnItemEditClickListener editClickListener; // Declare edit listener

    public interface OnItemEditClickListener {
        void onItemEdit(int id);
    }
    // Interface for delete click listener
    public interface OnItemDeleteClickListener {
        void onItemDelete(int id);
    }
    public MainAdapter(List<BaseItem> itemList, MainAdapter.OnItemClickListener itemClickListener,
                       OnItemEditClickListener editClickListener, OnItemDeleteClickListener deleteClickListener) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
        this.editClickListener = editClickListener; // Initialize the edit listener
        this.deleteClickListener = deleteClickListener; // Initialize the delete listener
    }

    public interface OnItemClickListener {
        void onItemClick(BaseItem item);
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
//    ==========================================================================================
    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof RvItem_History) {
            return 0;
        }
        return -1; // Mặc định
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_lichsu, parent, false);
                return new MainAdapter.LichSuViewHolder(view);
            }
            default:{
                return null;
            }
        }
    }
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        BaseItem item = itemList.get(position);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(item);
            }
        });

        if (holder instanceof LichSuViewHolder) {
            RvItem_History detail_item = (RvItem_History) itemList.get(position);
            ((LichSuViewHolder) holder).title.setText(detail_item.getTitle());
            ((LichSuViewHolder) holder).category.setText(detail_item.getCategory());
            ((LichSuViewHolder) holder).currentChapter.setText(String.valueOf(detail_item.getCurrentChapter()));
            ((LichSuViewHolder) holder).totalChapter.setText(String.valueOf(detail_item.getTotalChapters()));

            // Load the image using Glide
            Glide.with(holder.itemView.getContext())
                    .load(detail_item.getThumbnailUri())
                    .into(((LichSuViewHolder) holder).thumb);

            // Set delete button click listener

        }
    }



    public static class LichSuViewHolder extends MainAdapter.ViewHolder {
        TextView title;
        TextView currentChapter;
        TextView totalChapter;
        TextView category;
        ImageView thumb;
        ImageButton btn_edit; // Declare edit button
        public LichSuViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_lichsu_title);
            category = itemView.findViewById(R.id.tv_lichsu_category);
            currentChapter = itemView.findViewById(R.id.tv_lichsu_currentchapter);
            totalChapter = itemView.findViewById(R.id.tv_lichsu_totalchapter);
            thumb = itemView.findViewById(R.id.iv_lichsu_thumb);

            btn_edit = itemView.findViewById(R.id.btn_setting);
        }
    }
}
