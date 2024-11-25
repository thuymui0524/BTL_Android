package com.example.libary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libary.database.DatabaseHelper;

import org.jetbrains.annotations.Nullable;
@SuppressLint("MissingInflatedId")
public class BookDetailActivity extends AppCompatActivity {
    public String id;
    public String idBookcheck;
    public String idIMG;
    ImageView imageView_back;
    String title;
    // them id book
    String id_book;
    private int imageResourceId;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        ImageView storyImage = findViewById(R.id.detailImage);
        TextView storyTitle = findViewById(R.id.detailTitle);
        Button SaveButton = findViewById(R.id.btnSave);
        Button readButton = findViewById(R.id.btnread);
          // lay du lieu tu intent
        title = getIntent().getStringExtra("title");
        imageResourceId = getIntent().getIntExtra("imageResourceId", 0);
        String b;
        String a=getIntent().getStringExtra("book01");
         id_book= getIntent().getStringExtra("id_book");
        if (title==null || title.equals("")){
            title =a;
            imageResourceId=databaseHelper.sellectbook(id_book);
        }
        id=title;
        storyTitle.setText("Tên sách:" +title);
        storyImage.setImageResource(imageResourceId);



        // Đặt tiêu đề và hình ảnh
        storyTitle.setText("Tên sách: " + title);
        if (imageResourceId != 0) {
            storyImage.setImageResource(imageResourceId);
        } else {
            storyImage.setImageResource(R.drawable.img); // Đặt hình ảnh mặc định nếu không có
        }
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (title != null && !title.isEmpty()) {
//                    DatabaseHelper databaseHelper = new DatabaseHelper(BookDetailActivity.this);
//
//                    // Lưu sách vào cơ sở dữ liệu
//
//
//                    Toast.makeText(BookDetailActivity.this, "Sách đã được lưu!", Toast.LENGTH_SHORT).show();
//
////                    // Quay lại trang HomeActivity hoặc Tài Khoản
////                    Intent intent_back = new Intent(BookDetailActivity.this, HomeActivity.class);
////                    startActivity(intent_back);
//                } else {
//                    Toast.makeText(BookDetailActivity.this, "Không thể lưu sách!", Toast.LENGTH_SHORT).show();
//                }


            }
        });


        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readBook();
            }
        });
        getview();
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_back=new Intent(BookDetailActivity.this, HomeActivity.class);

                startActivity(intent_back);
            }
        });
    }
    private void borrowBook() {

        databaseHelper.selectidbook(title);
        String idBook=databaseHelper.luu;


        Intent intent = new Intent(BookDetailActivity.this,BorrowBookActivity.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
    private void readBook() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String readerName = sharedPreferences.getString("USERNAME", "Người dùng");
        databaseHelper.selectidbook(title);
        int idBook=Integer.parseInt(databaseHelper.luu) ;
        Toast.makeText(BookDetailActivity.this, idBook+"de", Toast.LENGTH_SHORT).show();
        int iduser=databaseHelper.idUser(readerName);
        databaseHelper.saveHistory(iduser,idBook);
        Intent intent = new Intent(this, read_book.class);
        intent.putExtra("id", id);// chuyen tieu de cua sach
        intent.putExtra("idBook", id_book);// chuyen id cua sach
        startActivity(intent);
    }
    private void getview(){
        imageView_back=findViewById(R.id.back_home_read);
    }

}
