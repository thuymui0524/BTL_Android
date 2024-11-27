package com.example.libary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libary.database.DatabaseHelper;

public class ChangepasswordActivity extends AppCompatActivity {
    private Button btnChangePassword,btnCancel;
    private EditText etOldPassword,etNewPassword,etAccout,etNewPasswordl2;
    Context context;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);
        getview();
        databaseHelper = new DatabaseHelper(this);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String account = sharedPreferences.getString("USERNAME", "Người dùng");
        // dổi mật khẩu
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = etOldPassword.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                String newPassword2 = etNewPasswordl2.getText().toString().trim();
                if (newPassword.length() <= 5) {
                    Toast.makeText(ChangepasswordActivity.this, "Mật khẩu mới phải có ít nhất 6 ký tự!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(ChangepasswordActivity.this, oldPassword+"d", Toast.LENGTH_SHORT).show();
                if (databaseHelper.checkUserCredentials(account, oldPassword)) {
                    if (newPassword.equals(newPassword2)) {
                        if (databaseHelper.updatePassword(account, newPassword)) {
                            Toast.makeText(ChangepasswordActivity.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ChangepasswordActivity.this, LoginActivity.class); startActivity(intent);
                        } else {
                            Toast.makeText(ChangepasswordActivity.this, "Đổi mật khẩu không thành công! Lỗi hệ thống!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChangepasswordActivity.this, "Mật khẩu mới phải nhập giống nhau!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChangepasswordActivity.this, "Mật khẩu hoặc tài khoản hiện tại không đúng", Toast.LENGTH_SHORT).show();
                } }
        });
        btnCancel.setOnClickListener(v->finish());

    }

    private void getview(){
        btnChangePassword=findViewById(R.id.btnAccept);
        etAccout=findViewById(R.id.etUsername);
        etNewPasswordl2=findViewById(R.id.etNewPasswordl2);
        etNewPassword=findViewById(R.id.etNewPassword);
        etOldPassword=findViewById(R.id.etOldPassword);
        btnCancel=findViewById(R.id.btncancel);

    }
}