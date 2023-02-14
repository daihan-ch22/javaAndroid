package org.dan.parcelable;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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
                ArrayList<String> names = new ArrayList<>();
                names.add("김진수");
                names.add("황수연");

                intent.putExtra("names", names);

                SimpleData data = new SimpleData(100, "HELLO WORLD MESSAGE!");
                intent.putExtra("data", data); //Menu로 던지기


                //startActivity(intent);
                mStartForResult.launch(intent); //인텐트 전송
            }
        });
    }

    //인텐트 응답 관련
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->
    {
        if(result.getResultCode() == RESULT_OK){
            Intent intent = result.getData();

            //MenuActivity에서 넘어온 SimpleData 정보
            // SimpleData 클래스는 Parcel객체에 담겨 전송된다.
            SimpleData data = intent.getParcelableExtra("data");
            String returnedMessage = data.message;


            Log.i("--",returnedMessage);
        }
    });
}