package com.example.libary;

public class ClassAdapterHistory {
    public int img;
    private String id_book;

    public String nameBook;
    public String Time;

    public ClassAdapterHistory(int img, String nameBook, String time,String id_book) {
        this.img = img;
        this.nameBook = nameBook;
        this.id_book=id_book;
        this.Time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getId_book() { return id_book; }

    public void setId_book(String id_book) { this.id_book = id_book; }
}
