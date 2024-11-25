package com.example.libary;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libary.Adapter.BookAdapter;
import com.example.libary.database.DatabaseHelper;

import java.util.ArrayList;

public class book_type extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private  DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private ArrayList<Book> bookList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_type);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        String title = getIntent().getStringExtra("type");
        textView=findViewById(R.id.Type);
        textView.setText(title);
        imageView=findViewById(R.id.back_home);
        imageView.setOnClickListener(v -> {
            finish();
        });
        // Cài đặt RecyclerView
        String id=getIntent().getStringExtra("id");
        recyclerView = findViewById(R.id.bookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper = new DatabaseHelper(this);
        bookList = databaseHelper.getBooksByType(id);
        //bookList = databaseHelper.getAllBook();

        bookAdapter = new BookAdapter(this, bookList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(bookAdapter);
    }
}