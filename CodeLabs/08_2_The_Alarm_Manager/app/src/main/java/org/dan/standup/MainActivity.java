package org.dan.standup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.CompoundButton;

import com.google.android.material.snackbar.Snackbar;

import org.dan.standup.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotificationSettings notificationSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        buttonListener();
        notificationSetup(getApplicationContext());
    }

    //NOTIFICATION PRE-SETUP
    private void notificationSetup(Context context){
        notificationSettings = new NotificationSettings(context);
        notificationSettings.createNotificationChannel();
    }

    private void buttonListener(){
        binding.alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                String msg;
                if(b)  {
                    msg = "Stand Up Alarm On!";
                    notificationSettings.deliverNotification(getApplicationContext());
                }
                else {
                    msg = "Stand Up Alarm Off!";
                    notificationSettings.cancelNotification();
                }
                Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

}