package com.example.libary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private long backPressedTime = 0L;
    // tạo sự kiện khi thoat app bằng back
    private OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            if (backPressedTime + 2000 > System.currentTimeMillis()) {
                setEnabled(false);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Nhấn lại để thoát", Toast.LENGTH_SHORT).show();
                backPressedTime = System.currentTimeMillis();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Cài đặt sự kiện chuyển đổi Fragment
        setupBottomNavigation();

        // Mặc định hiển thị HomeActivity khi mở ứng dụng
        if (savedInstanceState == null) {
            replaceFragment(new HomeActivity());
        }
        getOnBackPressedDispatcher().addCallback(this, backPressedCallback);
    }

    // Xử lý sự kiện khi chọn mục trong BottomNavigationView
    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_home) {


                    selectedFragment = new HomeActivity();
                } else if (item.getItemId() == R.id.nav_favorite) {
                    selectedFragment = new ThuVienActivity();
                } else if (item.getItemId() == R.id.nav_account) {
                    selectedFragment = new AccountActivity();
                }

                if (selectedFragment != null) {
                    replaceFragment(selectedFragment);
                    return true;
                }
                return false;
            }
        });
    }

    // Thay thế Fragment hiện tại bằng Fragment được chọn
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.view_Fragment, fragment);
        fragmentTransaction.commit();
    }
    @Override
    protected void onDestroy() {
        backPressedCallback.remove();
        super.onDestroy();
    }
}
