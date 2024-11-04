package com.example.bookapp1.Dtos;

import java.util.Date;

public class RvItem_Rating  extends BaseItem {
    private String title;
    private String description;
    private int imageResId;
    private Date time;  // Thêm thể loại
    private float rating;

    public RvItem_Rating(String title, String description, int imageResId, Date time, float rating) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
        this.time = time;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String getTitles() {
        return "";
    }
}
