package com.example.libary;

import android.annotation.SuppressLint;
import android.content.Intent;
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

        title = getIntent().getStringExtra("title");
        imageResourceId = getIntent().getIntExtra("imageResourceId", 0);
        String b;
        String a= getIntent().getStringExtra("idbook01");
        if (title==null || title.equals("")){
            title =a;
            imageResourceId=databaseHelper.sellectbook(a);
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
//                    // Quay lại trang HomeActivity hoặc Tài Khoản
//                    Intent intent_back = new Intent(BookDetailActivity.this, HomeActivity.class);
//                    startActivity(intent_back);
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

//        Intent intent = new Intent(this, PdfViewActivity.class);
//        startActivity(intent);
    }
    private void getview(){
        imageView_back=findViewById(R.id.back_home_read);
    }

}
