package org.dan.callintent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        editText = (EditText) findViewById(R.id.editText);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String receiver = editText.getText().toString();

                //방법 1
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + receiver));
                startActivity(intent);

                //방법 2
                //객체를 지정하는것이 아닌, 문자열로 액티비티 지정할 수 있음.(남이 만들어놓은 액티비티를 가져다 쓴다던가)
                Intent intent2 = new Intent();
                ComponentName name = new ComponentName("org.dan.callintent", "ord.dan.callintent.MenuActivity");
                intent2.setComponent(name);
                startActivity(intent2);
            }
        });


        //Switch에서 넘어올때 받는 녀석
        ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK){
                        Intent intent = result.getData();
                        Log.d("MainActivity", intent.getStringExtra("result"));
                        Snackbar.make(this.editText, "returned result = " + intent.getStringExtra("result"), Snackbar.LENGTH_SHORT).show();
                    }});


        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, switchActivity.class);
                mStartForResult.launch(intent);
            }
        });
    }
}