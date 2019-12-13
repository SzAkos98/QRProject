package com.example.asdlo.qrreadertest2.model;

public class AboutItem extends RecyclerViewItem {

    private String title;
    private String description;


    public AboutItem (String title, String description) {
        this.description = description;
        this.title = title;
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
}
