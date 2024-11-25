package com.example.libary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThuVienActivity extends AppCompatActivity {
    BottomNavigationFragment bottomNavigationFragment = new BottomNavigationFragment();
    Button btn_Giaotrinh, btn_Thamkhao, btn_Tapchi, btn_Luavan;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thuvien_activity);


        // code dung de them bottomNavigation vao trong LinearLayout
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.bottom_navigation_container, bottomNavigationFragment)
                .commit();
        // các sự kiện lấy loại sách
        btn_Giaotrinh = findViewById(R.id.btn_Giaotrinh);
        btn_Giaotrinh.setOnClickListener(v -> {
            Intent intent = new Intent(ThuVienActivity.this, book_type.class);
            intent.putExtra("type", "Sách giáo trình");
            intent.putExtra("id","1");
            startActivity(intent);
        });
        btn_Thamkhao = findViewById(R.id.btn_Thamkhao);
        btn_Thamkhao.setOnClickListener(v -> {
            Intent intent = new Intent(ThuVienActivity.this, book_type.class);
            intent.putExtra("type", "Sách tham khảo");
            intent.putExtra("id","2");
            startActivity(intent);
        });
        btn_Tapchi = findViewById(R.id.btn_Tapchi);
        btn_Tapchi.setOnClickListener(v -> {
            Intent intent = new Intent(ThuVienActivity.this, book_type.class);
            intent.putExtra("type", "Tạp chí");
            intent.putExtra("id","3");
            startActivity(intent);
        });
        btn_Luavan = findViewById(R.id.btn_Luanvan);
        btn_Luavan.setOnClickListener(v -> {
            Intent intent = new Intent(ThuVienActivity.this, book_type.class);
            intent.putExtra("type", "Luận văn");
            intent.putExtra("id","4");
            startActivity(intent);
        });

    }


}
