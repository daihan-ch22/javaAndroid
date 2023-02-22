package org.dan.fragmentsummary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements FragmentCallBack {

    Fragment1 fragment1;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragment1 != null){
                    fragment1.onCommandFromActivity("show", "Changed due to request from Activity");
                }
            }
        });

        fragment1 = new Fragment1();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();//fragment 뷰 생성


    }

    @Override
    public void onCommand(String command, String data) {
        button = (Button) findViewById(R.id.button);
        button.setText("CHANGED due to callback from Fragment1");

    }
}