// MyAdapter.java
package com.example.bookapp1.User.KhamPha;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp1.DTOs.BaseItem;
import com.example.bookapp1.R;

import java.util.List;

public class bmAdapter extends RecyclerView.Adapter<bmAdapter.ViewHolder> {
    private List<BaseItem> itemList;

    public bmAdapter(List<BaseItem> itemList) {
        this.itemList = itemList;
    }


    @Override
    public int getItemViewType(int position) {
        if (itemList != null && position < itemList.size()) {
            if (itemList.get(position) instanceof RvItem_Banner) {return 0;}
            else if (itemList.get(position) instanceof RvItem_Moidang) {return 1;}
            else if (itemList.get(position) instanceof RvItem_Capnhat) {return 2;}
        }

        return -1; // Mặc định
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_banner, parent, false);
                return new BannerViewHolder(view);
            }
            case 1: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_moidang, parent, false);
                return new MoidangViewHolder(view);
            }
            case 2: {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_capnhat, parent, false);
                return new CapnhatViewHolder(view);
            }
            default: {
                return null;
            }
        }
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder instanceof CapnhatViewHolder) {
            RvItem_Capnhat item = (RvItem_Capnhat) itemList.get(position);
            ((CapnhatViewHolder) holder).tieude.setText(item.getTieude());
            ((CapnhatViewHolder) holder).theloai.setText(item.getTheloai());
            ((CapnhatViewHolder) holder).thumbnail.setImageResource(item.getThumbnail());
            ((CapnhatViewHolder) holder).luotxem.setText(item.getLuotxem());
        } else if (holder instanceof MoidangViewHolder) {
            RvItem_Moidang item = (RvItem_Moidang) itemList.get(position);
            ((MoidangViewHolder) holder).thumb.setImageResource(item.getThumbnail());
        }
        else if (holder instanceof BannerViewHolder) {
            RvItem_Banner item = (RvItem_Banner) itemList.get(position);
            ((BannerViewHolder) holder).banner.setImageResource(item.getBanner());
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

    public static class CapnhatViewHolder extends ViewHolder {
        TextView tieude;
        TextView theloai;
        TextView luotxem;
        ImageView thumbnail;
        public CapnhatViewHolder(@NonNull View itemView) {
            super(itemView);
            tieude = itemView.findViewById(R.id.tv_capnhat_tieude);
            theloai = itemView.findViewById(R.id.tv_capnhat_theloai);
            luotxem = itemView.findViewById(R.id.tv_capnhat_luotxem);
            thumbnail = itemView.findViewById(R.id.iv_capnhat_thumbnail);
        }
    }

    public static class MoidangViewHolder extends ViewHolder {
        ImageView thumb;
        public MoidangViewHolder(@NonNull View itemView) {
            super(itemView);
            thumb = itemView.findViewById(R.id.iv_moidang_thumbnail);
        }
    }

    public static class BannerViewHolder extends ViewHolder {
        ImageView banner;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.iv_banner_img);
        }
    }
}