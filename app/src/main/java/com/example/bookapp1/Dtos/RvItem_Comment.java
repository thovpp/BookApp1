package com.example.bookapp1.Dtos;

import java.util.Date;

public class RvItem_Comment extends BaseItem {
    private String name;
    private String comment;
    private String chapter;
    private int imageResId;
    private Date time;  // Thêm thể loại

    @Override
    public String getTitles() {
        return "";
    }

    public RvItem_Comment(String name, String comment, String chapter, int imageResId, Date time) {
        this.name = name;
        this.comment = comment;
        this.chapter = chapter;
        this.imageResId = imageResId;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
