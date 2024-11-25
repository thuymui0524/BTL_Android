package com.example.libary;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.libary.database.DatabaseHelper;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

import java.io.IOException;
import java.io.InputStream;

public class read_book extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    String title;
    String id;
    String id_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_read_book);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // quay ve lay out cu
        ImageView back_homett = findViewById(R.id.back_homett);
        back_homett.setOnClickListener(v -> finish());

        TextView readbook = findViewById(R.id.readbook);
        //lay du lieu tu intent
         id_book= getIntent().getStringExtra("idBook");
        String a = getIntent().getStringExtra("id"); //lay ten sach
//        String a ="qua tang cuoc song";
        readbook.setText(a);
        // Load the PDF
        PDFView pdfView = findViewById(R.id.pdfView);
       // da thay doi tu tim kiem data tu String a sang id_book 22/11/2024
        if (a != null) {
            String pdf_path = databaseHelper.getBookPdfPath(id_book);
            try {
                InputStream inputStream = getAssets().open("TRR_Ontap.pdf");
                pdfView.fromStream(inputStream)
                        .defaultPage(0) // trang mặc định
                        .enableSwipe(true) // cho phép vuốt
                        .swipeHorizontal(false) // vuốt ngang
                        .enableDoubletap(true) // phóng to
                        .scrollHandle(new DefaultScrollHandle(this)) // thêm thanh cuộn
                        .load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Xử lỗi nếu a là null
            Toast.makeText(this, "ID sách không được cung cấp", Toast.LENGTH_SHORT).show();
        }


    }
}