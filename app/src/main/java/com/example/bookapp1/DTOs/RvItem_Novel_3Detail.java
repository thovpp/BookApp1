package com.example.bookapp1.DTOs;

public class RvItem_Novel_3Detail extends BaseItem {
private String title;
private String description;
private int imageResId;

public RvItem_Novel_3Detail(String title, String subtitle, int imageResId) {
    this.title = title;
    this.description = subtitle;
    this.imageResId = imageResId;
}

public String getTitle() {
    return title;
}

public String getDescription() {
    return description;
}

public int getImageResId() {
    return imageResId;
}

@Override
public String getTitles() {
    return "";
}
}

