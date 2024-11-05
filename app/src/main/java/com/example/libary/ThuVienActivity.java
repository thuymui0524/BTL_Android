package com.example.libary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThuVienActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuvien_activity);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_account) {
                    Intent intent = new Intent(ThuVienActivity.this, AccountActivity.class);
                    startActivity(intent);
                    return true;
                }
                else if (id == R.id.nav_home) {
                    Intent intent = new Intent(ThuVienActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                }else{
                    Intent intent = new Intent(ThuVienActivity.this, ThuVienActivity.class);
                    startActivity(intent);

                }
                return false;
            }
        });
    }
}
