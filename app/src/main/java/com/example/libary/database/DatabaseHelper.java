package com.example.libary.database;
import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.libary.Book;
import com.example.libary.textUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "LibraryDB";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và cột cho bảng User
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    // Tên bảng và cột cho bảng Book
    private static final String TABLE_BOOK = "book";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE_ID = "image_id";
    private static final String COLUMN_TITLE = "title";
    // Tên bảng và cột cho bảng Borrow History
    private static final String TABLE_BORROW_HISTORY = "BorrowHistory";
    private static final String COLUMN_HISTORY_ID = "history_id";
    private static final String COLUMN_BOOK_ID = "book_id";
    private static final String COLUMN_TITLEBOOK = "title";
    private static final String COLUMN_IMAGE_RESOURCE_ID = "imageResourceId";
    private static final String COLUMN_BORROW_DATE = "borrowDate";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

        // Tạo bảng Book
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_IMAGE_ID + " INTEGER, "
                + COLUMN_TITLE + " TEXT)";
        db.execSQL(CREATE_BOOK_TABLE);



        db.execSQL("INSERT INTO " + TABLE_USER + " (username, password) VALUES ('admin', 'admin123')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (username, password) VALUES ('user1', 'password1')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (username, password) VALUES ('21A100100247', '21A100100247')");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BORROW_HISTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }
    //check thong tin username and password
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE "
                + COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    // Thêm sách mới
    public void addBook(int imageId, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_ID, imageId);
        values.put(COLUMN_TITLE, title);
        db.insert(TABLE_BOOK, null, values);
        db.close();
    }

    // Lấy danh sách truyện từ database
    public ArrayList<Book> getAllBook() {
        ArrayList<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOK, null);

        if (cursor.moveToFirst()) {
            do {

                int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));

                Book book = new Book(imageId, title);
                bookList.add(book);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return bookList;
    }
    //tim kiem sach
    public List<String> searchTruyen(String keyword) {

        List<String> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        String query = "SELECT * FROM " + TABLE_BOOK + " WHERE " + COLUMN_TITLE + " LIKE ?";
        Cursor cursor = db.rawQuery(query, new String[]{"%" + keyword + "%"});

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                bookList.add(title);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return bookList;
    }

    // update sach
    public void updateBook( String oldName,String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IMAGE_ID, newName);

        db.update(TABLE_BOOK, values, COLUMN_IMAGE_ID + "=?", new String[]{String.valueOf(oldName)});
        db.close();
    }
    //delete sach
    public void deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BOOK, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
    //luu lich su doc sach


    //check nguoi dung va mat khau
    public boolean checkUserCredentials(String username, String oldPassword) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " +
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{username, oldPassword});

        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }

    // update mat khau moi
    public boolean updatePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PASSWORD, newPassword);

        int rowsUpdated = db.update(TABLE_USER, values, COLUMN_USERNAME + "=?", new String[]{username});
        return rowsUpdated > 0;
    }
    public int idimg;
    public String luu;
    public String selectidbook(String idimg){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_BOOK + " WHERE " + COLUMN_TITLE + "=?", new String[]{idimg});
        if (cursor.moveToFirst()) {
             luu = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
        } else {
            luu = "Not Found";
        }
        cursor.close();
        return luu;

    }


    public int sellectbook(String idBook){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOK + " WHERE " + COLUMN_TITLE + " =?", new String[]{String.valueOf(idBook)});

        if (cursor.moveToFirst()) {
            idimg=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_ID));

        }
        else {
            idimg=0;
        }
        cursor.close();

        return idimg;
    }

}
