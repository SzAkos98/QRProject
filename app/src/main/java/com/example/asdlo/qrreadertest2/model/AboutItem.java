package com.example.asdlo.qrreadertest2.model;

public class AboutItem extends RecyclerViewItem {

    private String title;
    private int id;
    private int userId;


    public AboutItem (int id , int userId, String title) {
        this.title = title;
        this.id=id;
        this.userId=userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
