package org.dan.standup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.snackbar.Snackbar;

import org.dan.standup.alarm.AlarmSettings;
import org.dan.standup.databinding.ActivityMainBinding;
import org.dan.standup.notification.NotificationSettings;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotificationSettings notificationSettings;
    private AlarmSettings alarmSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        notificationSetup(getApplicationContext());
        alarmSetup(getApplicationContext());
        buttonListener();
    }

    //ALARM PRE-SETUP
    private void alarmSetup(Context context){
        alarmSettings = new AlarmSettings(context);
        alarmSettings.setAlarmManager(context);
    }


    //NOTIFICATION PRE-SETUP
    private void notificationSetup(Context context){
        notificationSettings = new NotificationSettings(context);
        notificationSettings.createNotificationChannel();
    }

    private void buttonListener(){

        boolean isAlarmOn = alarmSettings.alarmCheck();
        binding.alarmToggle.setChecked(isAlarmOn);
        binding.alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String msg;
                if(b)  {
                    msg = "Stand Up Alarm On!";
                    //notificationSettings.deliverNotification(getApplicationContext());
                    alarmSettings.alarmStart();
                }
                else {
                    msg = "Stand Up Alarm Off!";
                    notificationSettings.cancelNotification();
                    alarmSettings.cancelAlarm();
                }
                Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.nextTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nextClock = alarmSettings.getNextClock();
                Snackbar.make(view, nextClock, Snackbar.LENGTH_SHORT).show();

            }
        });
    }

}