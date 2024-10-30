package com.example.libary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libary.database.DatabaseHelper;

public class AccountActivity extends AppCompatActivity {
        private Button btnlogOut ;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_account);
            TextView usernameTextView = findViewById(R.id.tvUsername);
            btnlogOut=findViewById(R.id.buttonLogout);
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("USERNAME", "Người dùng");
            usernameTextView.setText("Xin chào, " + username);
            btnlogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(AccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
}


