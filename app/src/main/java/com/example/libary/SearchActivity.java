package com.example.libary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libary.Adapter.BookAdapter;
import com.example.libary.Adapter.ItemAdapter;
import com.example.libary.database.DatabaseHelper;

import java.util.ArrayList;

@SuppressLint("MissingInflatedId")
public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private ArrayList<Book> bookList;
    private ArrayList<Book> bookList_Search;
    private DatabaseHelper databaseHelper=new DatabaseHelper(this);
    private ImageButton searchButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bookList = databaseHelper.getAllBook();
        searchButton=findViewById(R.id.search);
        // Lấy AutoCompleteTextView
        AutoCompleteTextView searchAutoComplete = findViewById(R.id.search_autocomplete);

        // Dữ liệu gợi ý từ khóa
        String[] suggestions = bookList.stream().map(Book::getTitle).toArray(String[]::new);

        // Adapter cho AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, suggestions);

        // Gán adapter cho AutoCompleteTextView
        searchAutoComplete.setAdapter(adapter);

        // Thiết lập hiển thị gợi ý ngay khi nhập

        searchAutoComplete.setThreshold(1); // Số ký tự tối thiểu để bắt đầu hiển thị gợi ý
        // sự kiện khi nhấn nut tim kiếm
        searchButton.setOnClickListener(v -> {
            // Lấy dữ liệu từ AutoCompleteTextView
            String search = searchAutoComplete.getText().toString();
            recyclerView = findViewById(R.id.bookRecyclerView);
            TextView noDataTextView = findViewById(R.id.noDataTextView);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            databaseHelper = new DatabaseHelper(this);

            // Lấy danh sách sách theo tiêu đề
            bookList_Search = databaseHelper.searchBooksByTitle(search);

            // Kiểm tra danh sách
            if (bookList_Search == null || bookList_Search.isEmpty()) {
                noDataTextView.setVisibility(View.VISIBLE); // Hiển thị thông báo
                recyclerView.setVisibility(View.GONE);      // Ẩn RecyclerView
            } else {
                noDataTextView.setVisibility(View.GONE);   // Ẩn thông báo
                recyclerView.setVisibility(View.VISIBLE); // Hiển thị RecyclerView

                bookAdapter = new BookAdapter(this, bookList_Search);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(bookAdapter);
            }
        });
        // sự kiện sau khi chọn
//        searchAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
//           String  selectedSuggestion = adapter.getItem(position);
//            recyclerView = findViewById(R.id.bookRecyclerView);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            databaseHelper = new DatabaseHelper(this);
//            bookList_Search=databaseHelper.searchBooksByTitle(selectedSuggestion);
//            bookAdapter = new BookAdapter(this, bookList_Search);
//            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//
//            recyclerView.setLayoutManager(gridLayoutManager);
//            recyclerView.setAdapter(bookAdapter);
//            DatabaseHelper db = new DatabaseHelper(this);
//            String idBook = db.selectidbook(selectedSuggestion);
//            int imageResourceId = db.sellectbook(idBook);


//            if (idBook != null) {
//
//                Intent intent = new Intent(SearchActivity.this, BookDetailActivity.class);
//                intent.putExtra("title", selectedSuggestion);
//                intent.putExtra("imageResourceId", imageResourceId);
//                startActivity(intent);// Lấy ID sách theo tiêu đề
//
//
//            } else {
//                Toast.makeText(SearchActivity.this, "Không tìm thấy sách!", Toast.LENGTH_SHORT).show();
//            }
//            Toast.makeText(SearchActivity.this, "Searching for: " + selectedSuggestion, Toast.LENGTH_SHORT).show();
//        });
        // su kien tim kiem



         ImageView imgback=findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}

