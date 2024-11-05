package com.example.bookapp1.User.KhamPha;

import com.example.bookapp1.DTOs.BaseItem;

public class RvItem_Moidang extends BaseItem {
    private int thumbnail;

    public RvItem_Moidang(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String getTitles() {
        return "";
    }
}
