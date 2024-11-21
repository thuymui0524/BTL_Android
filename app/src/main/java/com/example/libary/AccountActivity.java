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

public class AccountActivity extends AppCompatActivity {
    private Button btnlogOut,btnHistory ;
    DatabaseHelper databaseHelper;
    private ImageView imgBack;
    private String name;
    public int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        TextView usernameTextView = findViewById(R.id.tvUsername);
        btnlogOut=findViewById(R.id.buttonLogout);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "Người dùng");

        databaseHelper=new DatabaseHelper(this);
        // kiem tra ton tai user va lay ten
        id=databaseHelper.idUser(username);
        if(id==0) {
              Toast.makeText(this, "loi", Toast.LENGTH_SHORT).show();
        }else {
        name=databaseHelper.getNameUser(username);

        }
        usernameTextView.setText("Xin chào, " + name);
        btnlogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        getview();
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AccountActivity.this,BookHistory.class);
                intent.putExtra("idUSER",id);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AccountActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        // code dung de them bottomNavigation vao trong LinearLayout
        BottomNavigationFragment bottomNavigationFragment = new BottomNavigationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.bottom_navigation_container, bottomNavigationFragment)
                .commit();
    }

    private void getview(){
        imgBack=findViewById(R.id.back_home);
        btnHistory=findViewById(R.id.history);
    }
}


