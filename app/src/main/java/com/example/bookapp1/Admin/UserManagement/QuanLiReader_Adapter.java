package com.example.bookapp1.Admin.UserManagement;

import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.DTOs.RvItem_QuanliSach;
import com.example.bookapp1.Models.Reader;
import com.example.bookapp1.R;

import java.util.List;

public class QuanLiReader_Adapter extends RecyclerView.Adapter<QuanLiReader_Adapter.ViewHolder> {
    private List<Reader> itemList;
    private OnItemClickListener itemClickListener;

    // Variable to store the current PopupWindow
    private PopupWindow currentPopupWindow;

    public QuanLiReader_Adapter(List<Reader> itemList, OnItemClickListener itemClickListener) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }

    public QuanLiReader_Adapter(List<Reader> itemList) {
        this.itemList = itemList;
    }

    public interface OnItemClickListener {
        void onItemClick(Reader item);
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position) instanceof Reader) {
            return 0;
        }
        return -1; // Default
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case 0:{
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_quanlireader, parent, false);
                return new ReaderViewHolder(view);
            }
            default:{
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reader item = itemList.get(position);
        // Set item click listener
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(item);
            }
            showReaderDetailsPopup(v, item);
        });
        if (holder instanceof ReaderViewHolder) {
            Reader detail_item = (Reader) itemList.get(position);
            ((ReaderViewHolder) holder).stt.setText(String.valueOf(position + 1));
            ((ReaderViewHolder) holder).name.setText(detail_item.getUsername());
            ((ReaderViewHolder) holder).email.setText(detail_item.getEmail());

            ((ReaderViewHolder) holder).btnBan.setOnClickListener(v -> showBanConfirmationPopup(v, detail_item));

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

    public static class ReaderViewHolder extends ViewHolder {
        TextView stt;
        TextView name;
        TextView email;
        ImageView btnBan;  // Add this reference for the ban button

        public ReaderViewHolder(@NonNull View itemView) {
            super(itemView);
            stt = itemView.findViewById(R.id.tv_quanlireader_stt);
            name = itemView.findViewById(R.id.tv_quanlireader_name);
            email = itemView.findViewById(R.id.tv_quanlireader_email);
            btnBan = itemView.findViewById(R.id.btn_ban);  // Initialize the button
        }
    }


    // Method to show the reader details popup
    private void showReaderDetailsPopup(View anchor, Reader reader) {
        // Kiểm tra xem có popup nào đang mở không và đóng nó nếu cần
        if (currentPopupWindow != null && currentPopupWindow.isShowing()) {
            currentPopupWindow.dismiss();
        }

        // Inflate layout cho popup
        View popupView = LayoutInflater.from(anchor.getContext()).inflate(R.layout.dialog_reader_detail, null);

        // Set dữ liệu vào các TextViews trong layout
        TextView tvUsername = popupView.findViewById(R.id.tv_popup_username);
        TextView tvEmail = popupView.findViewById(R.id.tv_popup_email);
        TextView tvPhone = popupView.findViewById(R.id.tv_popup_phone);
        TextView tvRole = popupView.findViewById(R.id.tv_popup_role);
        TextView tvStatus = popupView.findViewById(R.id.tv_popup_status);

        tvUsername.setText(reader.getUsername());
        tvEmail.setText(reader.getEmail());
        tvPhone.setText(reader.getPhone());
        tvRole.setText(reader.getRole());
        tvStatus.setText(reader.getStatus());

        // Set up PopupWindow
        currentPopupWindow = new PopupWindow(popupView,
                900,   // Width: Full màn hình
                ViewGroup.LayoutParams.WRAP_CONTENT);  // Height: wrap content

        // Hiển thị PopupWindow tại trung tâm màn hình
        currentPopupWindow.showAtLocation(anchor, Gravity.CENTER, 0, 0);

        // Đóng PopupWindow khi nhấn nút "Close"
        Button btnClosePopup = popupView.findViewById(R.id.btn_close_popup);
        btnClosePopup.setOnClickListener(v -> currentPopupWindow.dismiss());

        // Tùy chọn: Đóng PopupWindow khi chạm bên ngoài
        currentPopupWindow.setOutsideTouchable(true);
        currentPopupWindow.setFocusable(true);

    }
    private void showBanConfirmationPopup(View anchor, Reader reader) {
        // Inflate the confirmation dialog layout
        View popupView = LayoutInflater.from(anchor.getContext()).inflate(R.layout.dialog_ban_reader, null);

        // Set up the PopupWindow for the confirmation dialog
        PopupWindow confirmationPopup = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        // Show the confirmation popup at the center of the screen
        confirmationPopup.showAtLocation(anchor, Gravity.CENTER, 0, 0);

        // Get the Ban and Cancel buttons from the layout
        Button btnBan = popupView.findViewById(R.id.banButton);
        Button btnCancel = popupView.findViewById(R.id.cancelButton);

        // Handle the Ban action
        btnBan.setOnClickListener(v -> {
            // Perform the ban action (for example, update the reader status in the database)
            banReader(reader);
            confirmationPopup.dismiss();  // Close the popup after the action
        });

        // Handle the Cancel action
        btnCancel.setOnClickListener(v -> {
            confirmationPopup.dismiss();  // Close the popup without banning
        });

        // Optional: Close the popup when clicking outside
        confirmationPopup.setOutsideTouchable(true);
        confirmationPopup.setFocusable(true);
    }

    private void banReader(Reader reader) {
        // Logic to ban the reader (e.g., update the status in the database)
        reader.setStatus("Banned");
        notifyDataSetChanged();  // Update the RecyclerView to reflect the changes
    }


}
