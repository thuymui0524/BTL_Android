package com.example.libary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class BookDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ImageView storyImage = findViewById(R.id.detailImage);
        TextView storyTitle = findViewById(R.id.detailTitle);
        Button borrowButton = findViewById(R.id.borrowButton);
        Button readButton = findViewById(R.id.readButton);

        String title = getIntent().getStringExtra("title");
        int imageResourceId = getIntent().getIntExtra("imageResourceId", 0);

        storyTitle.setText(title);
        storyImage.setImageResource(imageResourceId);

        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrowBook();
            }
        });


        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readBook();
            }
        });
    }
    private void borrowBook() {

        Intent intent = new Intent(BookDetailActivity.this, BorrowBookActivity.class);
        startActivity(intent);
    }
    private void readBook() {

//        Intent intent = new Intent(this, PdfViewActivity.class);
//        startActivity(intent);
    }

}
