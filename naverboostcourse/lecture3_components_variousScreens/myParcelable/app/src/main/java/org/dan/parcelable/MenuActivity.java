package org.dan.parcelable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent passedIntentFromMain = getIntent();
        setResult(RESULT_OK, passedIntentFromMain);
        processIntent(passedIntentFromMain);
    }

    private void processIntent(Intent intent){
        if(intent != null) {
            //ArrayList는 자바 기본 API이고 Serialize 인터페이스를 구현했기 때문에 사용 가능
            ArrayList<String> names = (ArrayList<String>) intent.getSerializableExtra("names");

            if(names != null) {
                Toast.makeText(getApplicationContext(), "전달받은 이름 리스트 갯수: " + names.size(), Toast.LENGTH_SHORT).show();
            }

            SimpleData data = (SimpleData) intent.getParcelableExtra("data");
            if(data != null){

                Toast.makeText(getApplicationContext(), "전달받은 SimpleData: " + data.number + " " + data.message , Toast.LENGTH_SHORT).show();
            }
        }
    }
}