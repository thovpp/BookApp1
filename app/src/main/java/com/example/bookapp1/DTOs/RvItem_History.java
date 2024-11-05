package com.example.bookapp1.DTOs;

public class RvItem_History extends BaseItem {
    private int ID; // Novel ID
    private String title; // Title of the novel
    private String author; // Author of the novel
    private int currentChapter; // Current chapter user is on
    private int totalChapters; // Total number of chapters
    private String category; // Genre/category of the novel
    private String thumbnailUri; // URI for the thumbnail image

    // Constructor
    public RvItem_History(int ID, String title, String author, int currentChapter, int totalChapters, String category, String thumbnailUri) {
        this.ID = ID;
        this.title = title;
        this.author = author;
        this.currentChapter = currentChapter;
        this.totalChapters = totalChapters;
        this.category = category;
        this.thumbnailUri = thumbnailUri;
    }

    // Getters
    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCurrentChapter() {
        return currentChapter;
    }

    public int getTotalChapters() {
        return totalChapters;
    }

    public String getCategory() {
        return category;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    @Override
    public String getTitles() {
        return title; // Assuming you want to return the title for this method
    }
}
