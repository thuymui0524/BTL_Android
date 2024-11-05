package com.example.libary;

public class BookHistory {
    private String bookTitle;
    private String borrowDate;
    private String returnDate;

    public BookHistory(String bookTitle, String borrowDate, String returnDate) {
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getters và Setters
    public String getBookTitle() { return bookTitle; }
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }
}
