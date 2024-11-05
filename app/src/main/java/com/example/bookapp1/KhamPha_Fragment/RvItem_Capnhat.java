package com.example.bookapp1.KhamPha_Fragment;

import com.example.bookapp1.Dtos.BaseItem;

public class RvItem_Capnhat extends BaseItem {

    private String tieude;
    private String theloai;
    private String luotxem;
    private int thumbnail;

    public RvItem_Capnhat(String tieude, String theloai, String luotxem, int thumbnail) {
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
