package org.dan.powerreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + " .ACTION_CUSTOM_BROADCAST";
    private CustomReceiver mReceiver = new CustomReceiver();
    private Button sendBroadCastBtn;
    private TextView numberView;
    private Button countButton;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SYSTEM BROADCAST
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        //Register Broadcast Receiver using the activity
        getApplicationContext().registerReceiver(mReceiver, intentFilter );

        buttonsInit();
        buttonsLinstner();


        //LOCAL BROADCAST - CUSTOMED [LocalBroadcastManager - getInstance - registerReceiver(receiver , intentfilter)
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
                new IntentFilter(ACTION_CUSTOM_BROADCAST));
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver,
                new IntentFilter("COUNTS"));
    }

    //Need to unregister the receiver when activity is terminated
    //otherwise it may cause memory leak
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getApplicationContext().unregisterReceiver(mReceiver);

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    public void sendCustomBroadcast(View view) {

        if(view.getId() == R.id.textView){
            TextView view1 = (TextView) view;
            Intent customBroadcastIntent = new Intent("COUNTS").putExtra("wow",view1.getText().toString() );
            LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
        } else {
            Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
            LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
        }
    }

    private void buttonsInit(){
        numberView = findViewById(R.id.textView);
        countButton = findViewById(R.id.countButton);
        sendBroadCastBtn = findViewById(R.id.sendBroadcast);
    }

    private void buttonsLinstner(){

        sendBroadCastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCustomBroadcast(view);
            }
        });

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    int count = Integer.parseInt(numberView.getText().toString());
                    @Override
                    public void run() {
                        count++;
                        count *= 2;
                        numberView.setText(String.valueOf(count));
                        if(count < 100000) {
                            handler.postDelayed(this, 100);
                        } else {
                            sendCustomBroadcast(numberView);
                        }
                    }
                });

            }
        });}
}