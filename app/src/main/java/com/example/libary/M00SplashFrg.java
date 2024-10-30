package com.example.libary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class M00SplashFrg extends AppCompatActivity {
    private static final int SPLASH_DELAY = 2000;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m00_frg_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(M00SplashFrg.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_DELAY);
    }
}
