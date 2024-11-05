package com.example.libary;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libary.database.DatabaseHelper;

public class ChangepasswordActivity extends AppCompatActivity {

    private EditText etOldPassword, etNewPassword;
    private Button btnChangePassword,btnCancel;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btnCancel=findViewById(R.id.btnCancel);
        etOldPassword = findViewById(R.id.etOldPassword);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnChangePassword = findViewById(R.id.btnChangePassword);
        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "Người dùng");
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = etOldPassword.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                if (databaseHelper.checkUserCredentials(username, oldPassword)) {
                    if (databaseHelper.updatePassword(username, newPassword)) {
                        Toast.makeText(ChangepasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(ChangepasswordActivity.this,LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ChangepasswordActivity.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChangepasswordActivity.this, "Mật khẩu hiện tại không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChangepasswordActivity.this,AccountActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

