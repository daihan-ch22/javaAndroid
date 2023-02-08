package com.example.myframelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/*
* FrameLayout을 사용하면 레이아웃을 중첩해서 설정 가능
* 그래서 invisible등을 사용해서 보이는 화면을 조정 가능함.
* */
public class MainActivity extends AppCompatActivity {

     ImageView imageView;
     ImageView imageView2;

     int idx = 0;  //현재 이미지 인덱스

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //R = Resource 폴더 내부
        //findByView는 그냥 View객체 반환하기 때문에 알맞게 캐스팅하면 된다.
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView4);
    }

    public void onButton1Click(View view){
        idx += 1;

        if(idx > 1) idx = 0;

        if(idx == 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
        } else if (idx == 1){
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
        }
    }
}