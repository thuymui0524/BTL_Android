package com.example.libary;

public class Book {
    private int imageId;
    private String title;
    private String id_book;

    public Book(int imageId, String title, String id_book ) {
        this.imageId = imageId;
        this.title = title;
        this.id_book=id_book;

    }
    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }
    public String getId_book() { return id_book; }

}
