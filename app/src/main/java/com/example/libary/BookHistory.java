package com.example.libary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libary.Adapter.HistorybookAdapter;
import com.example.libary.database.DatabaseHelper;

import java.util.ArrayList;

public class BookHistory extends AppCompatActivity {
    ArrayList<ClassAdapterHistory> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    DatabaseHelper databaseHelper;
    ImageView backClick;
    private int USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //databaseHelper.deleteHistory();

        //lấy id USER
        USER= getIntent().getIntExtra("idUSER",-1);
        getview();

        // cần lấy id người dung
        databaseHelper=new DatabaseHelper(BookHistory.this);
        arrayList=databaseHelper.getAllhtrBook(USER);
        if (arrayList!=null){
            HistorybookAdapter adapterLisview=new HistorybookAdapter(BookHistory.this,arrayList);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(adapterLisview);
        }
        else Toast.makeText(this, "Lịch sử rỗng", Toast.LENGTH_SHORT).show();
        backClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookHistory.this,HomeActivity.class);
                //
                startActivity(intent);
            }
        });





    }
    private void getview(){
        recyclerView=findViewById(R.id.lishtr);
        backClick=findViewById(R.id.back_home);
    }
}
