package com.example.bookapp1.KhamPha_Fragment;

import com.example.bookapp1.Dtos.BaseItem;

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
