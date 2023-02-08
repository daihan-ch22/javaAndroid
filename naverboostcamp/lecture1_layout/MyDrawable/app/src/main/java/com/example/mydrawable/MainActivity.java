package com.example.mydrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//상태 드로어블 (state drawable) - 클릭시 이미지 변경등
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}