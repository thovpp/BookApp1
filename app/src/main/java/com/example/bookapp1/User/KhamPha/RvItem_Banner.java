package com.example.bookapp1.User.KhamPha;

import com.example.bookapp1.DTOs.BaseItem;

public class RvItem_Banner extends BaseItem {
    private int banner;

    public RvItem_Banner(int banner) {
        this.banner = banner;
    }

    public int getBanner() {
        return banner;
    }

    public void setBanner(int thumbnail) {
        this.banner = thumbnail;
    }

    @Override
    public String getTitles() {
        return "";
    }
}
