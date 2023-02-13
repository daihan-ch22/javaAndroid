package org.dan.myintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.zip.Inflater;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = (Button) findViewById(R.id.button2);
        EditText editText = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name", "value"); //다른 Activity로 전달할 수 있는 수단 - key value type
                setResult(Activity.RESULT_OK, intent);

                finish(); // 첫번째 화면은 밑에 깔려있다고 생각하면 된다 - Activity Stack
            }
        });

        /*editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("editTextInput", editText.getText().toString() );
                setResult(Activity.RESULT_OK, intent);
            }
        });*/
    }
}