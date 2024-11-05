package com.example.libary;

public class Book {
    private int imageId;
    private String title;

    public Book(int imageId, String title ) {
        this.imageId = imageId;
        this.title = title;

    }
    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }


}
