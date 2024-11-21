package com.example.libary;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.libary.Adapter.BannerAdapter;
import com.example.libary.Adapter.BookAdapter;
import com.example.libary.Adapter.ItemAdapter;
import com.example.libary.database.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;
public class HomeActivity extends AppCompatActivity{
    private BookAdapter bookAdapter;
    private ImageView btnsearch;
    private EditText searchEditText;
    private ViewPager2 bannerViewPager;
    private List<Integer> bannerImages;
    private Handler handler;
    private Runnable runnable;
    private int currentPage = 0;
    private RecyclerView recyclerView;
    private ItemAdapter itemAdapter;
    private ArrayList<Book> bookList;
    private DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Khởi tạo ViewPager2 và RecyclerView
        bannerViewPager = findViewById(R.id.bannerViewPager);
        recyclerView = findViewById(R.id.bookRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        itemAdapter = new ItemAdapter(getItems());
        recyclerView.setAdapter(itemAdapter);
        // Thêm các hình ảnh banner vào List
        bannerImages = new ArrayList<>();
        bannerImages.add(R.drawable.banner);
        bannerImages.add(R.drawable.img_2);
        bannerImages.add(R.drawable.tuyensinhdhmo);
        bannerImages.add(R.drawable.elearning);

        // Cài đặt Adapter cho ViewPager
        BannerAdapter bannerAdapter = new BannerAdapter(bannerImages);
        bannerViewPager.setAdapter(bannerAdapter);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == bannerImages.size()) {
                    currentPage = 0;
                }
                bannerViewPager.setCurrentItem(currentPage++, true);
                handler.postDelayed(this, 3000);
            }
        };
//        chuc nang tim kiem
        btnsearch=findViewById(R.id.btnsearch);
        searchEditText=findViewById(R.id.searchEditText);
        databaseHelper = new DatabaseHelper(this);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                searchBook();
                Intent intent=new Intent(HomeActivity.this,SearchActivity.class);
                startActivity(intent);
                finish();
            }
        });

        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    searchBook();
                    return true;
                }
                    return false;
            }
        });
// code dung de them bottomNavigation vao trong LinearLayout
        BottomNavigationFragment bottomNavigationFragment = new BottomNavigationFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.bottom_navigation_container, bottomNavigationFragment)
                .commit();
// xu lu su kien  BottomNavigationView
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                if (id == R.id.nav_account) {
//                    Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
//                    startActivity(intent);
//                    return true;
//               }
//                else if (id == R.id.nav_favorite) {
//                    Intent intent = new Intent(HomeActivity.this, ThuVienActivity.class);
//                    startActivity(intent);
//                    return true;
//                }
//                return false;
//            }
//        });

        handler.postDelayed(runnable, 3000);
// Cài đặt RecyclerView cho danh sách sách
        ImageView bannerImageView = findViewById(R.id.bannerImageView);
        searchEditText = findViewById(R.id.searchEditText);

        recyclerView = findViewById(R.id.bookRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseHelper = new DatabaseHelper(this);
        bookList = databaseHelper.getAllBook();
        bookAdapter = new BookAdapter(this, bookList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(bookAdapter);

        // Thêm sách
//        databaseHelper.addBook(R.drawable.img, "Giao trinh Triet hoc Mac-Lenin");
//        databaseHelper.addBook(R.drawable.giaotrinhtinhocdaicuong_001thumbimage, "Giao trinh Tin hoc dai cuong");


//        String newName="giao trinh tin hoc dai cuong";
//        String oldName = "Giáo trình Tin học đại cương";
//        databaseHelper.updateBook( oldName,newName);

        databaseHelper.addBook(R.drawable.img, "giao trinh triet hoc Mac-Lenin");
        databaseHelper.addBook(R.drawable.giaotrinhtinhocdaicuong_001thumbimage, "giao trinh tin hoc dai cuong");



    }


    private List<String> getItems() {
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        items.add("Item 2");
        items.add("Item 3");
        items.add("Item 4");
        items.add("Item 5");
        return items;
    }
    //phuong thuc tim kiem sach
    private void searchBook() {
        String keyword = searchEditText.getText().toString().toLowerCase().trim();
//        String normalizedInput = textUtils.removeDiacritics((keyword));
//        if (normalizedInput.contains(normalizedInput)) {
//
//            List<String> searchResults = databaseHelper.searchTruyen(normalizedInput);
//            Intent intent = new Intent(HomeActivity.this, SearchResultActivity.class);
//            intent.putStringArrayListExtra("searchResults", (ArrayList<String>) searchResults);
//            startActivity(intent);
////            Toast.makeText(this, "Found!", Toast.LENGTH_SHORT).show();
//        } else {
//
//            Toast.makeText(this, "Not Found!", Toast.LENGTH_SHORT).show();
//        }
        if (keyword.equalsIgnoreCase(keyword)) {
            List<String> searchResults = databaseHelper.searchTruyen(keyword);
            Intent intent = new Intent(HomeActivity.this, SearchResultActivity.class);
            intent.putStringArrayListExtra("searchResults", (ArrayList<String>) searchResults);
            startActivity(intent);
        } else {
            Toast.makeText(HomeActivity.this, "Vui lòng nhập từ khóa!", Toast.LENGTH_SHORT).show();
        }

    }
}
