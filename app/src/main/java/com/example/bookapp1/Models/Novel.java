package com.example.bookapp1.Models;

public class Novel {
    private int id; // Database ID
    private String title; // Novel title
    private String author; // Author name
    private String category; // Category of the novel
    private int totalChapters; // Total number of chapters
    private String thumbnailUri; // URI of the thumbnail image
    private String summary; // Summary of the novel
    private String content; // Content of the novel
    private int authorId; // Foreign key to the author's ID

    public Novel(int id, String title, String author, String category, int totalChapters, String thumbnailUri, String summary, String content, int authorId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalChapters = totalChapters;
        this.thumbnailUri = thumbnailUri;
        this.summary = summary;
        this.content = content; // Initialize content
        this.authorId = authorId;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(int totalChapters) {
        this.totalChapters = totalChapters;
    }

    public String getThumbnailUri() {
        return thumbnailUri;
    }

    public void setThumbnailUri(String thumbnailUri) {
        this.thumbnailUri = thumbnailUri;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() { // New getter for content
        return content;
    }

    public void setContent(String content) { // New setter for content
        this.content = content;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
