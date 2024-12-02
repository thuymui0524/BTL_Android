package com.example.libary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.libary.Adapter.BannerAdapter;
import com.example.libary.Adapter.BookAdapter;
import com.example.libary.Adapter.ItemAdapter;
import com.example.libary.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Fragment {
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        // Khởi tạo ViewPager2 và RecyclerView
        bannerViewPager = view.findViewById(R.id.bannerViewPager);
        recyclerView = view.findViewById(R.id.bookRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
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

        // Chức năng tìm kiếm
        btnsearch = view.findViewById(R.id.btnsearch);
//        searchEditText = view.findViewById(R.id.searchEditText);
        databaseHelper = new DatabaseHelper(getActivity());
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                getActivity();
            }
        });

//        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
//                        (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN)) {
//                    searchBook();
//                    return true;
//                }
//                return false;
//            }
//        });

        handler.postDelayed(runnable, 3000);

        // Cài đặt RecyclerView cho danh sách sách
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        databaseHelper = new DatabaseHelper(getActivity());
        bookList = databaseHelper.getAllBook();
        bookAdapter = new BookAdapter(getActivity(), bookList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(bookAdapter);

        return view;
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

    // Phương thức tìm kiếm sách
//    private void searchBook() {
//        String keyword = searchEditText.getText().toString().toLowerCase().trim();
//        if (keyword.equalsIgnoreCase(keyword)) {
//            List<String> searchResults = databaseHelper.searchTruyen(keyword);
//            Intent intent = new Intent(getActivity(), SearchResultActivity.class);
//            intent.putStringArrayListExtra("searchResults", (ArrayList<String>) searchResults);
//            startActivity(intent);
//        } else {
//            Toast.makeText(getActivity(), "Vui lòng nhập từ khóa!", Toast.LENGTH_SHORT).show();
//        }
//    }
}
