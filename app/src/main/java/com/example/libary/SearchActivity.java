package com.example.libary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libary.database.DatabaseHelper;

@SuppressLint("MissingInflatedId")
public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Lấy AutoCompleteTextView
        AutoCompleteTextView searchAutoComplete = findViewById(R.id.search_autocomplete);

        // Dữ liệu gợi ý từ khóa
        String[] suggestions = {"giao trinh tin hoc dai cuong", "giao trinh triet hoc Mac-Lenin", "nghe thuat chinh phuc khach hang"};

        // Adapter cho AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, suggestions);

        // Gán adapter cho AutoCompleteTextView
        searchAutoComplete.setAdapter(adapter);

        // Thiết lập hiển thị gợi ý ngay khi nhập
        searchAutoComplete.setThreshold(1); // Số ký tự tối thiểu để bắt đầu hiển thị gợi ý
        searchAutoComplete.setOnItemClickListener((parent, view, position, id) -> {
            String selectedSuggestion = adapter.getItem(position);

            DatabaseHelper db = new DatabaseHelper(this);
            String idBook = db.selectidbook(selectedSuggestion);
            int imageResourceId = db.sellectbook(idBook);


            if (idBook != null) {

                Intent intent = new Intent(SearchActivity.this, BookDetailActivity.class);
                intent.putExtra("title", selectedSuggestion);
                intent.putExtra("imageResourceId", imageResourceId);
                startActivity(intent);// Lấy ID sách theo tiêu đề


            } else {
                Toast.makeText(SearchActivity.this, "Không tìm thấy sách!", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(SearchActivity.this, "Searching for: " + selectedSuggestion, Toast.LENGTH_SHORT).show();
        });
         ImageView imgback=findViewById(R.id.imgback);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}

