package com.example.bookapp1.Models;


public class Reader {
    private int readerId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String status;
    private String role;

    // Constructor
    public Reader(int readerId, String username, String password, String email, String phone, String status, String role) {
        this.readerId = readerId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    public Reader(int readerId, String username, String email) {
        this.readerId = readerId;
        this.username = username;
        this.email = email;
    }

    // Getters and setters
    public int getReaderId() { return readerId; }
    public void setReaderId(int readerId) { this.readerId = readerId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
