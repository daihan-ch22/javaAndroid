package org.dan.mylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplication(), "onCreated() 호출됨", Toast.LENGTH_SHORT).show();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplication(), "onStart() 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplication(), "onStop() 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplication(), "onDestroy() 호출됨", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplication(), "onPause() 호출됨", Toast.LENGTH_SHORT).show();

        //SharedPreferences
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", "김두한");
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplication(), "onResume() 호출됨", Toast.LENGTH_SHORT).show();

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(pref != null){
            String name = pref.getString("name", "");
            Log.i("Val from sharedPreferences: ", name);
        }
    }
}