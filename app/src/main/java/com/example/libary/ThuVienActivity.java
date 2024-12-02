package com.example.libary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ThuVienActivity extends Fragment {

    Button btn_Giaotrinh, btn_Thamkhao, btn_Tapchi, btn_Luavan;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Nạp layout cho Fragment
        View view = inflater.inflate(R.layout.thuvien_activity, container, false);

        // Khởi tạo các Button
        btn_Giaotrinh = view.findViewById(R.id.btn_Giaotrinh);
        btn_Giaotrinh.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), book_type.class);
            intent.putExtra("type", "Sách giáo trình");
            intent.putExtra("id", "1");
            startActivity(intent);
        });

        btn_Thamkhao = view.findViewById(R.id.btn_Thamkhao);
        btn_Thamkhao.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), book_type.class);
            intent.putExtra("type", "Sách tham khảo");
            intent.putExtra("id", "2");
            startActivity(intent);
        });

        btn_Tapchi = view.findViewById(R.id.btn_Tapchi);
        btn_Tapchi.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), book_type.class);
            intent.putExtra("type", "Ôn Tập");
            intent.putExtra("id", "3");
            startActivity(intent);
        });

        btn_Luavan = view.findViewById(R.id.btn_Luanvan);
        btn_Luavan.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), book_type.class);
            intent.putExtra("type", "Luận văn");
            intent.putExtra("id", "4");
            startActivity(intent);
        });

        return view;
    }
}
