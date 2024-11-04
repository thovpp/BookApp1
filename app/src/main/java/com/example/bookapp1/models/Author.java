package com.example.bookapp1.models;

public class Author {
    private int authorId;
    private String username;
    private String password;
    private String name;
    private String role;
    private String status;
    private String email;
    private String phone;
    private String confirmCode;
    private int year;
    private String gender;
    private String avatar;
    private String title;
    private int coin;


    public Author(int authorId, String username, String password, String name, String role, String status, String email, String phone, String confirmCode, int year, String gender, String avatar, String title, int coin) {
        this.authorId = authorId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = status;
        this.email = email;
        this.phone = phone;
        this.confirmCode = confirmCode;
        this.year = year;
        this.gender = gender;
        this.avatar = avatar;
        this.title = title;
        this.coin = coin;
    }

    // Constructor with authorId (for updating or reading)
    public Author(int authorId, String username, String email) {
        this.authorId = authorId;
        this.username = username;
        this.email = email;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}

