package com.example.libary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
@SuppressLint("MissingInflatedId")
public class SearchResultActivity extends AppCompatActivity {
    private ListView resultListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ImageView imgback=findViewById(R.id.imgback);

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SearchResultActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        resultListView = findViewById(R.id.resultListView);
        Intent intent = getIntent();
        ArrayList<String> searchResults = intent.getStringArrayListExtra("searchResults");

        if (searchResults != null && !searchResults.isEmpty()) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, searchResults);
            resultListView.setAdapter(adapter);

            resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selectedTitle = searchResults.get(i);

                        String idBook= getIntent().getStringExtra("idbooks");

                        Intent intent =new Intent(SearchResultActivity.this,BookDetailActivity.class);
                        intent.putExtra("idbook01",idBook);

                         intent.putExtra("title", selectedTitle);
                         startActivity(intent);
                        finish();

                }
            });

        } else {
            Toast.makeText(this, "Không tìm thấy kết quả nào!", Toast.LENGTH_SHORT).show();
        }



    }
}
