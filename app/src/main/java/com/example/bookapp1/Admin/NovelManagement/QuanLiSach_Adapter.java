// MyAdapter.java
package com.example.bookapp1.Admin.NovelManagement;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_QuanliSach;
import com.example.bookapp1.R;

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
        // Set item click listener
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(item);
            }
        });
        if (holder instanceof NovelViewHolder) {
            RvItem_QuanliSach detail_item = (RvItem_QuanliSach) itemList.get(position);
            ((NovelViewHolder) holder).title.setText(detail_item.getTitle());
            ((NovelViewHolder) holder).author.setText(detail_item.getTacGia());
            ((NovelViewHolder) holder).totalChapter.setText(detail_item.getTongSoChuong() + "");
            ((NovelViewHolder) holder).thumb.setImageResource(detail_item.getThumb());

            // Khi bấm vào nút "Danh sách chương"
            ((NovelViewHolder) holder).btn_dsChuong.setOnClickListener(v -> {
                // Tạo Intent để mở DanhSachChuongActivity
                Intent intent = new Intent(v.getContext(), DanhSachChuongActivity.class);

                // Truyền dữ liệu qua Intent nếu cần (Ví dụ: truyền id sách)
                //intent.putExtra("book_id", detail_item.getTitle());  // Giả sử bạn muốn truyền ID sách

                // Mở Activity DanhSachChuongActivity
                v.getContext().startActivity(intent);
            });
            ((NovelViewHolder) holder).btn_chinhSuaTruyen.setOnClickListener(v -> {
                // Tạo Intent để mở DanhSachChuongActivity
                Intent intent = new Intent(v.getContext(), Novel_Edit_Activity.class);

                // Truyền dữ liệu qua Intent nếu cần (Ví dụ: truyền id sách)
                //intent.putExtra("book_id", detail_item.getTitle());  // Giả sử bạn muốn truyền ID sách

                // Mở Activity DanhSachChuongActivity
                v.getContext().startActivity(intent);
            });
            ((NovelViewHolder) holder).btn_themChuong.setOnClickListener(v -> {
                // Tạo Intent để mở DanhSachChuongActivity
                Intent intent = new Intent(v.getContext(), Add_Chapter_Activity.class);

                // Truyền dữ liệu qua Intent nếu cần (Ví dụ: truyền id sách)
                //intent.putExtra("book_id", detail_item.getTitle());  // Giả sử bạn muốn truyền ID sách

                // Mở Activity DanhSachChuongActivity
                v.getContext().startActivity(intent);
            });
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
    public static class NovelViewHolder extends ViewHolder {
        TextView title;
        TextView author;
        TextView totalChapter;
        ImageView thumb;
        LinearLayout btn_dsChuong;
        LinearLayout btn_chinhSuaTruyen;
        LinearLayout btn_themChuong;

        public NovelViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            author = itemView.findViewById(R.id.tv_author);
            totalChapter = itemView.findViewById(R.id.tv_tongsochuong);
            thumb = itemView.findViewById(R.id.iv_quanlisach_thumb);
            btn_dsChuong = itemView.findViewById(R.id.btn_dschuong);
            btn_chinhSuaTruyen = itemView.findViewById(R.id.btn_editTruyen);
            btn_themChuong = itemView.findViewById(R.id.btn_themchuong);
        }
    }

}
