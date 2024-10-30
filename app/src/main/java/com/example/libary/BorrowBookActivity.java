package com.example.libary;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libary.database.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BorrowBookActivity extends AppCompatActivity {

    private TextView textViewReaderName, textViewBookName, textViewBorrowDate, textViewReturnDate;
    private Button buttonConfirmBorrow, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book);

        textViewReaderName = findViewById(R.id.textViewReaderName);
        textViewBookName = findViewById(R.id.textViewBookName);
        textViewBorrowDate = findViewById(R.id.textViewBorrowDate);
        textViewReturnDate = findViewById(R.id.textViewReturnDate);
        buttonConfirmBorrow = findViewById(R.id.buttonConfirmBorrow);
        btnCancel=findViewById(R.id.btnCancel);

        String readerBook=getIntent().getStringExtra("title");
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String readerName = sharedPreferences.getString("USERNAME", "Người dùng");
        textViewReaderName.setText("Tên Độc Giả: " + readerName);
        textViewBookName.setText("Tên Sách: " +readerBook );
        String borrowDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());
        textViewBorrowDate.setText("Ngày Mượn: " + borrowDate);
        Calendar returnCal = Calendar.getInstance();
        returnCal.add(Calendar.DATE, 7);
        String returnDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(returnCal.getTime());
        textViewReturnDate.setText("Ngày Trả: " + returnDate);

        buttonConfirmBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (BorrowBookActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(BorrowBookActivity.this,BookDetailActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

