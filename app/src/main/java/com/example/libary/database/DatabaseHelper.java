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
import com.example.libary.ClassAdapterHistory;
import com.example.libary.R;
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
    //
    public  ArrayList<ClassAdapterHistory> bookList = new ArrayList<>();
    //
    private static final String DATABASE_NAME = "LibraryDB";
    private static final int DATABASE_VERSION = 1;

    // Tên bảng và cột cho bảng User
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUM_ID_USER="idUser";// MSV
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Tên bảng và cột cho bảng Book
    private static final String TABLE_BOOK = "book";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_IMAGE_ID = "image_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_IDTYPE_BOOK="idType";


    // Bảng lịch sử đọc sách
    private static final String TABLE_HISTORYBOOK="btlHisStory";
    private static final String COLUMN_IDHisStory="idHTR";
    private static final String COLUMN_IDBOOK="idBook";
    private static final String COLUMN_ID_USES="idUser";
    private static final String COLUMN_TIME="dTime";

    //Bảng lưu trữ dử liệu Sách
    private static final String TABLE_DATABOOK="btlDataBook";
    private static final String COLUMN_IDDATA="idData";
    private static final String COLUMN_IDBOOKS="idBook";
    private static final String COLUMN_DATABOOK="sDatabook";
    //bảng loại sách
    private static final String TABLE_TYPEBOOK="btlTypeBook";
    private static final String COLUMN_IDTYPE="idType";
    private static final String COLUMN_NAMETYPE="nameType";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUM_ID_USER + " TEXT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

        // Tạo bảng Book
        String CREATE_BOOK_TABLE = "CREATE TABLE " + TABLE_BOOK + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_IDTYPE_BOOK + " INTEGER, "
                + COLUMN_IMAGE_ID + " INTEGER, "
                + COLUMN_TITLE + " TEXT "
                + ")";
        db.execSQL(CREATE_BOOK_TABLE);

        // Tạo bảng lịch sử đọc sách với khóa ngoại
        String CREATE_HISTORY_TABLE = "CREATE TABLE " + TABLE_HISTORYBOOK + "("
                + COLUMN_IDHisStory + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_IDBOOK + " INTEGER, "
                + COLUMN_ID_USES + " INTEGER, "
                + COLUMN_TIME + " DATE, "
                + "FOREIGN KEY(" + COLUMN_IDBOOK + ") REFERENCES " + TABLE_BOOK + "(" + COLUMN_ID + "), "
                + "FOREIGN KEY(" + COLUMN_ID_USES + ") REFERENCES " + TABLE_USER + "(" + COLUMN_USER_ID + ")"
                + ")";
        db.execSQL(CREATE_HISTORY_TABLE);

        // Tạo bảng lưu trữ dữ liệu sách với khóa ngoại
        String CREATE_DATABOOK_TABLE = "CREATE TABLE " + TABLE_DATABOOK + "("
                + COLUMN_IDDATA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_IDBOOKS + " INTEGER, "
                + COLUMN_DATABOOK + " TEXT, "
                + "FOREIGN KEY(" + COLUMN_IDBOOKS + ") REFERENCES " + TABLE_BOOK + "(" + COLUMN_ID + ")"
                + ")";
        db.execSQL(CREATE_DATABOOK_TABLE);
        // loai sach
        String CREATE_TYPEBOOK_TABLE = "CREATE TABLE " + TABLE_TYPEBOOK + "("
                + COLUMN_IDTYPE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAMETYPE + " TEXT"
                + ")";
        db.execSQL(CREATE_TYPEBOOK_TABLE);
        // Sample data insertions

        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('admin', 'admin123','ADMIN')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('user1', 'password1','USER1')");
        // Sample data insertions
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.img+", 'Giáo trình triết học Mac-Lenin', 1)");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.toanroirac+", 'Giáo trình Toán rời rạc', 1)");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.tutuonghcm+", 'Tài Liệu Tư Tưởng HCM', 2)");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.giaotrinhtinhocdaicuong_001thumbimage+", 'Giáo trình tin học đại cương', 1)");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.kientrucmt+", 'Đề ôn Kiến trúc máy tính', 3)");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.on30caucnxhkh+", 'Đề ôn 30 câu CNXHKH', 3)");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.on30caucnxhkh+", 'Vấn đề ôn tập CNXH', 3)");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.giaotrinhandroid+", 'Giao Trình android cơ bản', 1)");
        db.execSQL("INSERT INTO " + TABLE_BOOK + " (image_id, title, idType) VALUES ( "+ R.drawable.giaotrinhandroid+", 'Kĩ thuật lập trình C cơ bản', 1)");

        db.execSQL("INSERT INTO " + TABLE_HISTORYBOOK + " (idBook, idUser, dTime) VALUES (1, 1, '2012-05-04')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('21A100100026', '21A100100026','Phạm Đức Anh')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('22A1001D0377', '22A1001D0377','Cao Đình Vương')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('21A100100087', '21A100100087','Vũ Tiến Đạt')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('21A100100159', '21A100100157','Vũ Công Hùng')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('21A100100038', '21A100100038','Vương Đăng Kim Bảo')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('21A100100038', '21A100100038','Vũ Hoàng Bắc')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('21A100100122', '21A100100122','Nguyễn Thu Hiền')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('21A100100145', '21A100100145','Nguyễn Khánh Hoàng')");
        db.execSQL("INSERT INTO " + TABLE_USER + " (idUser, password,username) VALUES ('21A100100059', '21A100100059','Nguyễn Tiến Dũng')");

        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (1, 'TriethocMacLenin.pdf')");
        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (2, 'TRR_Ontap.pdf')");
        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (3, 'TaiLieuTuTuongHCM.pdf')");
        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (4, 'Tin_doc_dai_cuong_.pdf')");
        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (5, 'On-Ktrucmaytinh.pdf')");
        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (6, 'CNXHKH 30 chủ đề.pdf')");
        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (7, 'Vấn đề ôn tập CNXHKH-Gửi sinh viên.pdf')");
        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (8, 'tai-lieu-lap-trinh-android-co-ban.pdf')");
        db.execSQL("INSERT INTO "+TABLE_DATABOOK+ " (idBook, sDatabook) VALUES (9, 'kithuatlaptrinhC.pdf')");
        // dư liệu loại sách
        db.execSQL("INSERT INTO "+TABLE_TYPEBOOK+ " (nameType) VALUES ('Giáo trình')");
        db.execSQL("INSERT INTO "+TABLE_TYPEBOOK+ " (nameType) VALUES ('Tài liệu')");
        db.execSQL("INSERT INTO "+TABLE_TYPEBOOK+ " (nameType) VALUES ('Đề ôn')");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORYBOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }
    //check users dùng trong AccountActivity
    public int idUser(String nameusser){
        int idusers = -1;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USER_ID
                + " FROM " + TABLE_USER + " WHERE "
                + COLUM_ID_USER + "=?", new String[]{nameusser});
        if (cursor.moveToFirst()) {
            idusers = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_USER_ID));
        } else {
            idusers = 0;
        }
        // Đóng cơ sở dữ liệu sau khi sử dụng return idusers;
        cursor.close();
        db.close();
        return idusers;
    }
    //lay thong tin ten nguoi dung AccountActivity
    public String getNameUser(String id){
        String name="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_USERNAME
                + " FROM " + TABLE_USER + " WHERE "
                + COLUM_ID_USER + "=?", new String[]{String.valueOf(id)});

        if (cursor.moveToFirst()) {
            name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME));
        }
        cursor.close();
        db.close();
        return name;

    }
    //check thong tin username and password
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER + " WHERE "
                + COLUM_ID_USER + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{username, password});
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
                // them lay id cua sach
                String idBook = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));

                Book book = new Book(imageId, title,idBook);
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
                COLUMN_ID_USES + "=? AND " + COLUMN_PASSWORD + "=?";
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

        int rowsUpdated = db.update(TABLE_USER, values,  COLUM_ID_USER+ "=?", new String[]{username});
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

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOK + " WHERE " + COLUMN_ID + " =?", new String[]{String.valueOf(idBook)});

        if (cursor.moveToFirst()) {
            idimg=cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_ID));

        }
        else {
            idimg=0;
        }
        cursor.close();

        return idimg;
    }
    // tim du lieu pdf của sach
    @SuppressLint("Range")
    public String getBookPdfPath(String bookId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String pdfPath = "";
        String query = "SELECT " + TABLE_BOOK + "." + COLUMN_ID + ", " + TABLE_DATABOOK + "." + COLUMN_IDBOOKS + ", " + COLUMN_DATABOOK
        + " FROM " + TABLE_DATABOOK + " INNER JOIN " + TABLE_BOOK
        + " ON " + TABLE_BOOK + "." + COLUMN_ID + " = " + TABLE_DATABOOK + "." + COLUMN_IDBOOKS
        + " WHERE " + COLUMN_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{bookId});
        if (cursor.moveToFirst()) {
            pdfPath = cursor.getString(cursor.getColumnIndex(COLUMN_DATABOOK));
        } cursor.close();
        db.close();
        return pdfPath;
    }
    // lấy sách cùng loại sách
    public ArrayList<Book> getBooksByType( String idType) {
        ArrayList<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + ", " + COLUMN_TITLE + ", " + COLUMN_IMAGE_ID
                + " FROM " + TABLE_BOOK
                + " WHERE " + COLUMN_IDTYPE_BOOK + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{idType});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_ID));

                Book book = new Book(imageId, title, String.valueOf(id));
                books.add(book);
            }
            cursor.close();
        }
        return books;
    }
    // lay lich su sách
    // Lấy danh sách lịch sử đọc sách
    public ArrayList<ClassAdapterHistory> getAllhtrBook(int idUser) {

        ArrayList<ClassAdapterHistory> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_TITLE + ", " + COLUMN_IDBOOK + ", "
                + COLUMN_IMAGE_ID + ", " + COLUMN_TIME + " FROM "
                + TABLE_BOOK + " INNER JOIN " + TABLE_HISTORYBOOK
                + " ON " + TABLE_BOOK + "." + COLUMN_ID + " = "
                + TABLE_HISTORYBOOK + "." + COLUMN_IDBOOK + " WHERE "
                + COLUMN_ID_USES + " = ?" + " ORDER BY " + COLUMN_TIME
                + " DESC";
        // Thêm ORDER BY tại đây
        Cursor cursor = null;
        try { cursor = db.rawQuery(query, new String[]{String.valueOf(idUser)});
            if (cursor.moveToFirst()) {
                do {
                    String idBook = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IDBOOK));
                    String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
                    int idImg = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_ID));
                    String time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME));
                    ClassAdapterHistory classAdapterHistory = new ClassAdapterHistory(idImg, title, time,idBook);
                    bookList.add(classAdapterHistory); }
                while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }
        return bookList;
    }
    // luu sach
    public void saveHistory(int idUser, int idBook){
        Date datesave=new Date();
        // Định dạng thời gian
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedNow = formatter.format(datesave);
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_HISTORYBOOK + " (idBook, idUser,dTime) VALUES ( "+ idBook+","+idUser+",'"+formattedNow+"'"+" )");


    }
    // tim kiem sach cho SearchActivity.java
    public ArrayList<Book> searchBooksByTitle(String keyword) {
        ArrayList<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Câu lệnh SQL tìm kiếm
        String query = "SELECT * FROM " + TABLE_BOOK +
                " WHERE " + COLUMN_TITLE + " LIKE ?";
        Cursor cursor = db.rawQuery(query, new String[]{"%" + keyword + "%"});

        // Duyệt kết quả và thêm vào danh sách
        if (cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID));
//                int idTypeBook = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IDTYPE_BOOK));
                int imageId = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));

                // Tạo đối tượng Book và thêm vào danh sách
                Book book = new Book(  imageId, title,id);
                bookList.add(book);
            } while (cursor.moveToNext());
        }

        // Đóng cursor và database
        cursor.close();
        db.close();

        return bookList;
    }
}