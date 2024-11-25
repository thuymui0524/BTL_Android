package com.example.libary;

public class ClassAdapterHistory {
    public int img;
    public String nameBook;
    public String Time;

    public ClassAdapterHistory(int img, String nameBook, String time) {
        this.img = img;
        this.nameBook = nameBook;
        Time = time;
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
}
