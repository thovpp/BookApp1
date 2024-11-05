package com.example.bookapp1.User.KhamPha;

import com.example.bookapp1.DTOs.BaseItem;

public class RvItem_Moidang extends BaseItem {
    private String thumbnail;


    public RvItem_Moidang(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String getTitles() {
        return "";
    }
}
