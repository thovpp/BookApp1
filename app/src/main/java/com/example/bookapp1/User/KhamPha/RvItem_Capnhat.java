package com.example.bookapp1.User.KhamPha;

import com.example.bookapp1.DTOs.BaseItem;

public class RvItem_Capnhat extends BaseItem {

    private String tieude;
    private String theloai;
    private String luotxem;
    private String thumbnail;

    public RvItem_Capnhat(String tieude, String theloai, String luotxem, String thumbnail) {
        this.tieude = tieude;
        this.theloai = theloai;
        this.luotxem = luotxem;
        this.thumbnail = thumbnail;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(String luotxem) {
        this.luotxem = luotxem;
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
