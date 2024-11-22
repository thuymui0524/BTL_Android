package com.example.libary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThuVienActivity extends AppCompatActivity {
    BottomNavigationFragment bottomNavigationFragment = new BottomNavigationFragment();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuvien_activity);


        // code dung de them bottomNavigation vao trong LinearLayout
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.bottom_navigation_container, bottomNavigationFragment)
                .commit();
        // các sự kiện lấy loại sách


    }


}
