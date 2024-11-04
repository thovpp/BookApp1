package com.example.bookapp1.Dtos;

import java.util.Date;

public class RvItem_Chapter extends BaseItem{
    private int ID;
    private String title;
    private Date time;
    private int order_number;

    public RvItem_Chapter(int ID, String title, Date time, int order_number) {
        this.ID = ID;
        this.title = title;
        this.time = time;
        this.order_number = order_number;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    @Override
    public String getTitles() {
        return "";
    }
}
