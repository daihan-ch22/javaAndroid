package org.dan.myprogress_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar) findViewById(R.id.progressBar);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(new Runnable() { //일정 시간 이후에 Thread 실행
                    @Override
                    public void run() {
                        ProgressThread thread = new ProgressThread();
                        thread.start();
                    }
                }, 5000);
            }
        });
    }

    class ProgressThread extends Thread{
        int val = 0;

        @Override
        public void run() {
            while(true){
                if(val>100) break;
                val++;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        bar.setProgress(val);
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (Exception e) {}

            }}
    }
}