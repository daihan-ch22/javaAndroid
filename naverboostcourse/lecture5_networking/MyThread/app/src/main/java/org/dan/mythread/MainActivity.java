package org.dan.mythread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    Button button;
    Button button2;
    //BackgroundThread thread;
    ValueHandler handler;
    ValueHandler handler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        handler = new ValueHandler(textView);
        handler2 = new ValueHandler(textView);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //thread = new BackgroundThread(handler);
                //thread.start();

                //한번 실행될 객체 생성하는 방법
                new Thread(new Runnable() {
                    int value = 0;
                    boolean running = false;
                    @Override
                    public void run() {
                        running = true;
                        while (running) {
                            value += 1;

                            handler2.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(String.valueOf(value));
                                    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                                    progressBar.setProgress(value, true);
                                    progressBar.setMax(100);
                                }
                            });

                            try {
                                Thread.sleep(1000);
                            } catch (Exception e){
                                Log.d("EXCEPTION", e.getMessage());
                            }
                        }
                    }}).start();
            }
        });


        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
