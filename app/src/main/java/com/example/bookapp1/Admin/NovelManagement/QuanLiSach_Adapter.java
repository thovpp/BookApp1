// MyAdapter.java
package com.example.bookapp1.Admin.NovelManagement;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_QuanliSach;
import com.example.bookapp1.Database.DatabaseHelper;
import com.example.bookapp1.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class QuanLiSach_Adapter extends RecyclerView.Adapter<QuanLiSach_Adapter.ViewHolder> {
    private List<BaseItem> itemList;
    private OnItemClickListener itemClickListener;

    public QuanLiSach_Adapter(List<BaseItem> itemList, OnItemClickListener itemClickListener) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }
    public QuanLiSach_Adapter(List<BaseItem> itemList) {
        this.itemList = itemList;
    }
    public interface OnItemClickListener {
        void onItemClick(BaseItem item);
    }
    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof RvItem_QuanliSach) {
            return 0;
        }
        return -1; // Mặc định
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_sachquanli, parent, false);
                return new NovelViewHolder(view);
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaseItem item = itemList.get(position);
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(item);
            }
        });

        if (holder instanceof NovelViewHolder) {
            RvItem_QuanliSach detail_item = (RvItem_QuanliSach) itemList.get(position);
            ((NovelViewHolder) holder).title.setText(detail_item.getTitle());
            ((NovelViewHolder) holder).author.setText(detail_item.getTacGia());
            ((NovelViewHolder) holder).totalChapter.setText(String.valueOf(detail_item.getTongSoChuong()));

            Log.d("Image URI", "URI for Glide: " + detail_item.getThumb());

            // Load the specific image using Glide from local file path
            File imageFile = new File(detail_item.getThumb()); // Assuming getThumb() returns the file path

            if (imageFile.exists()) {
                Glide.with(holder.itemView.getContext())
                        .load(imageFile) // Use the local file path directly
                        .placeholder(R.drawable.background_novel_1) // Placeholder while loading
                        .error(R.drawable._logo) // Error image if loading fails
                        .into(((NovelViewHolder) holder).thumb);
            } else {
                Log.d("Image Load", "Image file does not exist: " + detail_item.getThumb());
                // Optionally, set a fallback if file doesn't exist
                ((NovelViewHolder) holder).thumb.setImageResource(R.drawable._logo);
            }

            // Set up click listener for btn_themChuong to open Add_Chapter_Activity with novel details
            ((NovelViewHolder) holder).btn_themChuong.setOnClickListener(v -> {
                // In QuanLiSach_Adapter or relevant adapter
                Log.d("QuanLiSach_Adapter", "Passing novelId: " + detail_item.getId());
                Intent intent = new Intent(holder.itemView.getContext(), Add_Chapter_Activity.class);
                intent.putExtra("novelId", detail_item.getId());  // Ensure detail_item.getId() provides the correct novel ID
                intent.putExtra("novelTitle", detail_item.getTitle());
                intent.putExtra("novelAuthor", detail_item.getTacGia());
                intent.putExtra("chapterCount", detail_item.getTongSoChuong());
                intent.putExtra("image", detail_item.getThumb());
                holder.itemView.getContext().startActivity(intent);
            });

            // Set up click listener for btn_dsChuong to open DanhSachChuongActivity with novel ID or title
            ((NovelViewHolder) holder).btn_dsChuong.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), DanhSachChuongActivity.class);
                intent.putExtra("novelId", detail_item.getId()); // Assuming `getId()` returns the novel ID
                intent.putExtra("novelTitle", detail_item.getTitle());
                holder.itemView.getContext().startActivity(intent);
            });

            ((NovelViewHolder) holder).btn_chinhSuaTruyen.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), Novel_Edit_Activity.class);
                intent.putExtra("isEditMode", true);  // Indicate that we are editing
                intent.putExtra("novelId", detail_item.getId());  // Pass the novel ID
                holder.itemView.getContext().startActivity(intent);
            });

            // Set up delete button click listener
            ((NovelViewHolder) holder).btn_delete.setOnClickListener(v ->
                    showDeleteConfirmationDialog(holder.itemView.getContext(), detail_item, position));
        }
    }
    // Method to delete a novel from the database and adapter
    private void deleteNovel(Context context, RvItem_QuanliSach novelItem, int position) {
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(context);

        boolean isDeleted = dbHelper.deleteNovel(novelItem.getId());
        if (isDeleted) {
            // Remove item from list and notify adapter
            itemList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Truyện đã được xóa.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Xóa truyện thất bại.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to show a confirmation dialog before deleting
    private void showDeleteConfirmationDialog(Context context, RvItem_QuanliSach novelItem, int position) {
        new AlertDialog.Builder(context)
                .setTitle("Xóa Truyện")
                .setMessage("Bạn có chắc chắn muốn xóa truyện này không?")
                .setPositiveButton("Xóa", (dialog, which) -> deleteNovel(context, novelItem, position))
                .setNegativeButton("Hủy", null)
                .show();
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
    public static class NovelViewHolder extends ViewHolder {
        TextView title;
        TextView author;
        TextView totalChapter;
        ImageView thumb;
        LinearLayout btn_dsChuong;
        LinearLayout btn_chinhSuaTruyen;
        LinearLayout btn_themChuong;
        private LinearLayout btn_delete;

        public NovelViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            author = itemView.findViewById(R.id.tv_author);
            totalChapter = itemView.findViewById(R.id.tv_tongsochuong);
            thumb = itemView.findViewById(R.id.iv_quanlisach_thumb);
            btn_dsChuong = itemView.findViewById(R.id.btn_dschuong);
            btn_chinhSuaTruyen = itemView.findViewById(R.id.btn_editTruyen);
            btn_themChuong = itemView.findViewById(R.id.btn_themchuong);
            btn_delete = itemView.findViewById(R.id.btn_delete);

        }


    }

}
