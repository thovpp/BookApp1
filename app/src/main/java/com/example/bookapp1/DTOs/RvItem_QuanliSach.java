package com.example.bookapp1.DTOs;

public class RvItem_QuanliSach  extends BaseItem {
    private String title;
    private String tacGia;
    private int thumb;
    private int tongSoChuong;  // Thêm thể loại

    public RvItem_QuanliSach(String title, String tacGia, int thumb, int tongSoChuong) {
        this.title = title;
        this.tacGia = tacGia;
        this.thumb = thumb;
        this.tongSoChuong = tongSoChuong;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public int getTongSoChuong() {
        return tongSoChuong;
    }

    public void setTongSoChuong(int tongSoChuong) {
        this.tongSoChuong = tongSoChuong;
    }

    @Override
    public String getTitles() {
        return "";
    }
}