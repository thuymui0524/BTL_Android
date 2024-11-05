package com.example.libary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libary.Adapter.BookAdapter;
import com.example.libary.database.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@SuppressLint("Range")
public class AccountActivity extends AppCompatActivity {
    private TextView  tvname,tvdate;
    private Button tvLogout ,tvChangePassWord;
    private DatabaseHelper databaseHelper;
    private ListView listViewBooks;
        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_account);
            TextView usernameTextView = findViewById(R.id.tvUsername);
            tvLogout = findViewById(R.id.tvLogout);
            tvname = findViewById(R.id.tvname);
            tvdate = findViewById(R.id.tvdate);
            tvChangePassWord = findViewById(R.id.tvChangePassword);
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("USERNAME", "Người dùng");
            usernameTextView.setText("Xin chào, " + username);
            tvname.setText("Tên Độc Giả:" + username);
            String borrowDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());
            tvdate.setText("Ngày Thành Viên:" + borrowDate);

            databaseHelper = new DatabaseHelper(this);


//            List<Book> savedBooks = databaseHelper.getAllBook();
//
//            BookAdapter adapter = new BookAdapter(bookList, this); // Sử dụng adapter của bạn
//            listViewBooks.setAdapter(adapter);
//            listViewBooks.setLayoutManager(new LinearLayoutManager(this));

//            displaySavedBooks();



            tvLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            tvChangePassWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(AccountActivity.this, ChangepasswordActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
//            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    int id = item.getItemId();
//                    if (id == R.id.nav_home) {
//                        Intent intent = new Intent(AccountActivity.this, HomeActivity.class);
//                        startActivity(intent);
//                        return true;
//                    }
//                    else if (id == R.id.nav_favorite) {
//                        Intent intent = new Intent(AccountActivity.this, ThuVienActivity.class);
//                        startActivity(intent);
//                        return true;
//                    }
//                    return false;
//                }
//            });
//
//        }
//
//    }
        }

}


