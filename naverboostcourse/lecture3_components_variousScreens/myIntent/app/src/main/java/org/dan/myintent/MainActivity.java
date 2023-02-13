package org.dan.myintent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/* <액티비티 새로 생성하고 구성하는 방식>
* 1. 새로운 액티비티 생성
* 2. 새로운 액티비티의 XML 레이아웃 정의 + Inflation
* 3. 메인 액티비티에서 새로운 액티비티 띄우기
* 4. 새로운 액티비티에서 응답 보내기
* 5. 메인 액티비티에서 응답 처리하기
* */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    //콜백함수
    // 응답을 받아준다 = intent를 전달 받을 수 있다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101) {
            String name = data.getStringExtra("name"); //MenuActivity [ name - value ]
            Toast.makeText(getApplicationContext(), "call backed from: " + name, Toast.LENGTH_SHORT).show();
        }
    }
}