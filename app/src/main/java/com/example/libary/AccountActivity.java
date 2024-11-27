package com.example.libary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libary.database.DatabaseHelper;

public class AccountActivity extends Fragment {

    private Button btnlogOut, btnHistory, btnChangePassword;
    private ImageView imgBack;
    private DatabaseHelper databaseHelper;
    private String name;
    public int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout for Fragment
        View view = inflater.inflate(R.layout.activity_account, container, false);

        // Khởi tạo view
        TextView usernameTextView = view.findViewById(R.id.tvUsername);
        btnlogOut = view.findViewById(R.id.buttonLogout);
        imgBack = view.findViewById(R.id.back_home);
        btnHistory = view.findViewById(R.id.history);
        btnChangePassword = view.findViewById(R.id.buttonChangePassword);

        // Lấy thông tin người dùng từ SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "Người dùng");

        databaseHelper = new DatabaseHelper(requireContext());
        id = databaseHelper.idUser(username);

        if (id == 0) {
            Toast.makeText(requireContext(), "Lỗi", Toast.LENGTH_SHORT).show();
        } else {
            name = databaseHelper.getNameUser(username);
            usernameTextView.setText("Xin chào, " + name);
        }

        // Đăng xuất
        btnlogOut.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });

        // Lịch sử đọc
        btnHistory.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), BookHistory.class);
            intent.putExtra("idUSER", id);
            startActivity(intent);
        });

        // Quay lại trang chính
        imgBack.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), HomeActivity.class);
            startActivity(intent);
        });

        // Đổi mật khẩu
        btnChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), ChangepasswordActivity.class);
            startActivity(intent);
        });

        return view;
    }
}
