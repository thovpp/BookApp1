package com.example.bookapp1.Dtos;

public class RvItem_Novel_5Detail extends BaseItem {
    private String title;
    private String description;
    private int imageResId;
    private String category;  // Thêm thể loại
    private float rating;     // Thêm đánh giá

    public RvItem_Novel_5Detail(String title, String description, float rating, String category, int imageResId) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.category = category;
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

    public String getCategory() {
        return category;  // Getter cho thể loại
    }

    public float getRating() {
        return rating;    // Getter cho đánh giá
    }

    @Override
    public String getTitles() {
        return "";
    }
}
