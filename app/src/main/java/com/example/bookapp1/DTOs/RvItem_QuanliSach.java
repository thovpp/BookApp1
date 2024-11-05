package com.example.bookapp1.DTOs;

public class RvItem_QuanliSach  extends BaseItem {

    private int id;
    private String title;
    private String tacGia;
    private String thumb;
    private int tongSoChuong;  // Thêm thể loại

    public RvItem_QuanliSach(int id, String title, String tacGia, String thumb, int tongSoChuong) {
        this.id = id;
        this.title = title;
        this.tacGia = tacGia;
        this.thumb = thumb;
        this.tongSoChuong = tongSoChuong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
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